package com.uzinfo.datagenerate.web.repository.base;

import com.uzinfo.datagenerate.web.entity.BaseMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FakerBaseClassRepository extends JpaRepository<BaseMethod, Long> {

    Optional<BaseMethod> findByName(String name);
}
