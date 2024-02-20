package com.uzinfo.datagenerate.dataapp.generate;

import com.uzinfo.datagenerate.dataapp.exceptions.InvalidFakerResources;
import com.uzinfo.datagenerate.web.dto.FilterParamsDto;
import com.uzinfo.datagenerate.web.dto.FieldDto;
import com.uzinfo.datagenerate.web.dto.FieldsDto;
import com.uzinfo.datagenerate.web.exception.ResourceNotFoundException;
import lombok.*;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;


@Getter
@Setter
@RequiredArgsConstructor
@Service
public class FakeGenerator {
    private static Faker faker = new Faker();

    public static List<Map<String, Object>> generate(FieldsDto fieldsDto) {
        List<Map<String, Object>> result = new ArrayList<>();
        for (int i = 0; i < fieldsDto.getCount(); i++) {
            Map<String, Object> data = new LinkedHashMap<>();
            for (int j = 0; j < fieldsDto.getFields().size(); j++) {
                String type = fieldsDto.getFields().get(j).getGenerateBaseType();
                String field = fieldsDto.getFields().get(j).getGenerateValue();
                String fieldName = fieldsDto.getFields().get(j).getFieldName();
                data.put(fieldName, generator(type, field, fieldsDto.getFields().get(j)));
            }
            result.add(data);
        }
        return result;
    }

    /**
     * Generates a result word based on the given function type, function name, and modelDto.
     *
     * @param fType    the type of the function to be generated
     * @param fName    the name of the function to be generated
     * @param fieldDto the modelDto containing filter parameters
     * @return the generated result word
     */
    private static Object generator(String fType, String fName, FieldDto fieldDto) {
//        System.out.println(faker.phoneNumber().phoneNumber());
        Optional<Method> baseMethods;
        try {
            try {
                baseMethods = Optional.of(faker.getClass().getMethod(fType));
            } catch (Exception e) {
                throw new InvalidFakerResources("Class '" + fType + "' not found");
            }
            baseMethods.get().setAccessible(true);
            Object o = Faker.class.getConstructor().newInstance();
            Object returnValue = baseMethods.orElseThrow(() ->
                    new InvalidFakerResources("Method " + fName + " not found")).invoke(o);
            Optional<Method[]> methods = Optional.of(Arrays.stream(returnValue.getClass().getMethods())
                    .filter(method -> method.getName().equals(fName)).toArray(Method[]::new));
            if (methods.get().length > 1) {
                String[] expectedTypes = fieldDto.getFilterParamsDtoList()
                        .stream().filter(FilterParamsDto::isActive).map(FilterParamsDto::getValueType).toArray(String[]::new);
                Class<?>[] expectedParams = parameterTypes(expectedTypes);
                Optional<Method> matchMethods = Arrays.stream(Arrays.stream(methods.orElseThrow(
                                () -> new InvalidFakerResources("Method " + fName + " not found"))).toArray(Method[]::new))
                        .filter(method -> isParameterMatch(method.getParameterTypes(),
                                expectedParams)).findFirst();
                matchMethods.orElseThrow(() ->
                        new InvalidFakerResources("Method " + fName + " not found")).setAccessible(true);
                Object[] parameterized = parameters(matchMethods.orElseThrow(), fieldDto);
                return matchMethods.orElseThrow(() ->
                        new InvalidFakerResources("Method " + fName + " not found")).invoke(returnValue, parameterized);
            } else if (methods.get().length == 0) {
                throw new InvalidFakerResources("Class '" + fType + "' has no method " + fName);
            }
            return methods.get()[0].invoke(returnValue);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException |
                 InvalidFakerResources e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }


    private static boolean isParameterMatch(Class<?>[] parameterTypes, Class<?>[] expectedTypes) {
        if (parameterTypes.length != expectedTypes.length) {
            return false;
        }
        for (int i = 0; i < parameterTypes.length; i++) {
            if (!parameterTypes[i].equals(expectedTypes[i])) {
                return false;
            }
        }
        return true;
    }

    private static Object[] parameters(Method method, FieldDto fieldDto) {
        Parameter[] parameters = method.getParameters();
        Object[] objects = new Object[parameters.length];
        try {
            for (int i = 0; i < parameters.length; i++) {
                int finalI = i;
                objects[i] = typeCast(parameters[i].getType().getSimpleName(),
                        fieldDto.getFilterParamsDtoList().get(i).getValue())
                        .orElseThrow(
                                () -> new InvalidFakerResources("Parameter " + parameters[finalI].getName() + " not found")
                        );
            }
        } catch (InvalidFakerResources e) {
            throw new RuntimeException(e.getMessage());
        }
        return objects;
    }

    private static Class<?>[] parameterTypes(String[] parameters) {
        Class<?>[] classes = new Class[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i].toLowerCase().startsWith("str"))
                classes[i] = String.class;
            else if (parameters[i].toLowerCase().startsWith("int"))
                classes[i] = int.class;
            else if (parameters[i].toLowerCase().startsWith("dou"))
                classes[i] = double.class;
            else if (parameters[i].toLowerCase().startsWith("bool"))
                classes[i] = boolean.class;
            else if (parameters[i].toLowerCase().startsWith("long"))
                classes[i] = long.class;
        }
        return classes;
    }

    private static Optional<Object> typeCast(String clazz, String str) throws InvalidFakerResources {
        Optional<Object> obj = Optional.empty();
        try {
            if (clazz.toLowerCase().startsWith("str"))
                obj = Optional.of(str);
            else if (clazz.toLowerCase().startsWith("int"))
                obj = Optional.of(Integer.parseInt(str));
            else if (clazz.toLowerCase().startsWith("dou"))
                obj = Optional.of(Double.parseDouble(str));
            else if (clazz.toLowerCase().startsWith("bool"))
                obj = Optional.of(Boolean.parseBoolean(str));
            else if (clazz.toLowerCase().startsWith("long"))
                obj = Optional.of(Long.parseLong(str));
        } catch (Exception e) {
            throw new InvalidFakerResources("type Cast Exception\n" + e.getMessage());
        }
        return obj;
    }

}
