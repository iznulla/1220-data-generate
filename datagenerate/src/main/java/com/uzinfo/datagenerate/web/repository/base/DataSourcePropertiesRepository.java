package com.uzinfo.datagenerate.web.repository.base;

import com.uzinfo.datagenerate.web.entity.DataBaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DataSourcePropertiesRepository extends JpaRepository<DataBaseEntity, Long> {
    Optional<DataBaseEntity> findByName(String name);

    @Query(value = "SELECT COUNT(1) > 0 FROM pg_database WHERE datname = :name", nativeQuery = true)
    boolean checkHasDataBase(@Param("name") String name);


}
