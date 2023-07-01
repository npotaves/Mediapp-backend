package com.mitocode.service;

import com.mitocode.model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPatientService extends ICRUD<Patient, Integer> {

    /*Patient save(Patient patient);
    Patient update(Patient patient, Integer id);
    List<Patient> findAll();
    Patient findById(Integer id);
    void delete(Integer id);*/

    Page<Patient> listPage(Pageable pageable);
}
