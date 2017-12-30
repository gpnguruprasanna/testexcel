package com.guru.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.guru.entities.Excelsheetinfo;

@Repository
public interface ExcelsheetinfoRepository extends CrudRepository<Excelsheetinfo, Integer>{

}
