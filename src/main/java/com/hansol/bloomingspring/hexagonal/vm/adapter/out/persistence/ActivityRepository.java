package com.hansol.bloomingspring.hexagonal.vm.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ActivityRepository extends JpaRepository<ActivityJpaEntity, Long> {

    @Query("select a from ActivityJpaEntity a " +
            "where a.vmId = :vmId " +
            "and a.timestamp >= :since")
    List<ActivityJpaEntity> findByVmSince(
            @Param("vmId") Long vmId,
            @Param("since")LocalDateTime since);
}
