package com.autos24.test.repository;

import com.autos24.test.domain.ListingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Database Access Object for loan table.
 */
public interface ListingRepository extends JpaRepository<ListingEntity, Long>,
        JpaSpecificationExecutor<ListingEntity> {

    @Modifying
    @Query(value = "delete from listing where id in(?1)", nativeQuery = true)
    void deleteAllById(List<Long> ids);
}
