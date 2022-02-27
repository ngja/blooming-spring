package com.hansol.bloomingspring.hexagonal.vm.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VmRepository extends JpaRepository<VmJpaEntity, Long> {
}
