package com.uzinfo.datagenerate.web.repository.app;

import com.uzinfo.datagenerate.web.exception.ResourceNotFoundException;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Map;

@Getter
@Setter
@Repository
@AllArgsConstructor
@NoArgsConstructor
public class SaveGeneratedDataRepositoryImpl implements SaveGeneratedDataRepository {

    @Autowired
    private DataSource dataSource;

    @Override
    public int save(String tableName, Map<String, Object> generatedValue) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName(tableName)
                .usingGeneratedKeyColumns("id");
        try {
            String[] columns = generatedValue.keySet().toArray(new String[0]);
            simpleJdbcInsert.usingColumns(columns);
            return simpleJdbcInsert.execute(generatedValue);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Failed to save data\n" + e.getMessage());
        }
    }


//        StringBuilder sql = new StringBuilder("insert into ");
//        StringBuilder values = new StringBuilder();
//        sql.append(modelsDto.getTableName()).append(" (");
//        int count = 0;
//        for (ModelDto modelDto : modelsDto.getModels()) {
//            sql.append(modelDto.getFieldName());
//            if (count != (modelsDto.getModels().size() - 1))
//                sql.append(", ");
//            else sql.append(")");
//            count++;
//        }
//        sql.append(" values (");
//        for (int i = 0; i < count; i++) {
//            sql.append("?");
//            if (i != (count - 1))
//                sql.append(", ");
//            else sql.append(");");
//        }

//            for (Map.Entry<String, String> entry : generatedValue.entrySet()) {
////                System.out.println( "field name: " + entry.getKey() + ": " + entry.getValue());
//                values.append("""
//                        "
//                        """);
//                values.append(entry.getValue());
//                values.append("""
//                        ",
//                        """);
//            }
//
//        System.out.println(values);
//

}
