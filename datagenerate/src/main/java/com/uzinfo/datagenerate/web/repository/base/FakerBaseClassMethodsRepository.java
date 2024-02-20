package com.uzinfo.datagenerate.web.repository.base;

import com.uzinfo.datagenerate.web.entity.MethodValues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FakerBaseClassMethodsRepository extends JpaRepository<MethodValues, Long> {

    Optional<List<MethodValues>> findAllBymethodValue(String name);
}
