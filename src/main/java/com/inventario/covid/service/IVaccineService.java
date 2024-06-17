package com.inventario.covid.service;

import com.inventario.covid.exceptions.NoRecordsFoundException;
import com.inventario.covid.model.VaccineEntity;

import java.util.List;

public interface IVaccineService {
    VaccineEntity findById(Long id);
    List<VaccineEntity> findAll() throws NoRecordsFoundException;
}
