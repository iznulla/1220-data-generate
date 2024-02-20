package com.uzinfo.datagenerate.web.repository.base;

import com.uzinfo.datagenerate.web.entity.DataBaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DataSourcePropertiesRepository extends JpaRepository<DataBaseEntity, Long> {
    Optional<DataBaseEntity> findByName(String name);
}
