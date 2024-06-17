package com.inventario.covid.repository;

import com.inventario.covid.model.VaccineEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccineRepository extends CrudRepository<VaccineEntity, Long> {
}
