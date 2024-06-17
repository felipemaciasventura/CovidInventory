package com.inventario.covid.web.controller;

import com.inventario.covid.config.ErrorMessages;
import com.inventario.covid.exceptions.NoRecordsFoundException;
import com.inventario.covid.model.VaccineEntity;
import com.inventario.covid.service.IVaccineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v2/vaccine")
public class VaccineController {

    private static final Logger logger = LoggerFactory.getLogger(VaccineController.class);

    private final IVaccineService vaccineService;

    public VaccineController(IVaccineService vaccineService) {
        this.vaccineService = vaccineService;
    }

    @GetMapping("/index")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> index(){
        try{
            logger.debug(ErrorMessages.LOGGER_GET_RECORDS_FOUND.getErrorMessage());
            List<VaccineEntity> vaccineEntityList = vaccineService.findAll();
            return ResponseEntity.ok(vaccineEntityList);
        }catch (NoRecordsFoundException e){
            logger.info(ErrorMessages.LOGGER_GET_RECORDS_NOT_FOUND.getErrorMessage());
            return ResponseEntity.ok(Collections.singletonMap("message", ErrorMessages.LOGGER_GET_RECORDS_NOT_FOUND.getErrorMessage()));
        }catch (Exception e){
            logger.error("Error to get server response", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
