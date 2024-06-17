package com.inventario.covid.service.impl;

import com.inventario.covid.config.ErrorMessages;
import com.inventario.covid.exceptions.NoRecordsFoundException;
import com.inventario.covid.model.VaccineEntity;
import com.inventario.covid.repository.VaccineRepository;
import com.inventario.covid.service.IVaccineService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional(readOnly = true)
public class VaccineServiceImpl implements IVaccineService {

    private final VaccineRepository vaccineRepository;

    public VaccineServiceImpl(VaccineRepository vaccineRepository) {
        this.vaccineRepository = vaccineRepository;
    }

    public VaccineEntity findById(Long id){
        return vaccineRepository.findById(id).orElse(null);
    }

    public List<VaccineEntity> findAll() throws NoRecordsFoundException {
        List<VaccineEntity> vaccineEntityList = (List<VaccineEntity>) vaccineRepository.findAll();
        if (vaccineEntityList.isEmpty())
            throw new NoRecordsFoundException(ErrorMessages.EMPTY_RECORDS.getErrorMessage());
        return vaccineEntityList;
    }

}
