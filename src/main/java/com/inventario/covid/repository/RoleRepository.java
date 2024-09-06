package com.inventario.covid.repository;

import com.inventario.covid.model.RoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByRoleName(String roleName);
}
