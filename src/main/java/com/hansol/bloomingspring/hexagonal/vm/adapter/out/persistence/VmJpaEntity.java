package com.hansol.bloomingspring.hexagonal.vm.adapter.out.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vm")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VmJpaEntity {

    @Id
    @GeneratedValue
    private Long id;
}
