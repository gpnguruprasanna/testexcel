package com.guru.serviceImpl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.guru.entities.Excelsheetinfo;
import com.guru.repositories.ExcelsheetinfoRepository;
import com.guru.services.ExtractExcelService;
import com.guru.util.ExtractExcelUtil;

@Component
public class ExtractExcelServiceImpl implements ExtractExcelService{

	@Autowired
	ExcelsheetinfoRepository excelsheetinfoRepository;
	@Autowired
	ExtractExcelUtil extractExcelUtil;

	@Override
	public void readExcel(MultipartFile file) {
		try {
			Iterator<Row> rowIterator=extractExcelUtil.checkExcelformat(file);
			int rowcount = 0;			

			Set<Row> errorRowSet=new LinkedHashSet<Row>();
			HashMap<Integer,Set<Map<Integer,String>>>  errorRow=new HashMap<Integer, Set<Map<Integer,String>>>();
			while(rowIterator.hasNext()) {
				Row row = rowIterator.next();
				System.out.println("klkklkl"+"  "+row);
				if(rowcount==0){
					errorRowSet.add(row);
				}
				boolean ischeck=true;

				if(rowcount>0 && !extractExcelUtil.isEmptyRow(row)){

					Excelsheetinfo excelsheetinfo =new Excelsheetinfo();

					try{
						excelsheetinfo.setUsername(row.getCell(0)!=null?row.getCell(0).getStringCellValue():"");
						if(excelsheetinfo.getUsername().equals("")){
							ischeck=false;
							errorRow=extractExcelUtil.addCellComment(errorRow, row.getRowNum(), 0, "Enter user Name,It should contain max 50 characters.");
						}
					}catch (Exception e) {
						ischeck=false;
						errorRow=extractExcelUtil.addCellComment(errorRow, row.getRowNum(), 0, "user Name Error");
					}

					try{
						excelsheetinfo.setCompany(row.getCell(1)!=null?row.getCell(1).getStringCellValue():"");
						if(excelsheetinfo.getCompany().equals("")){
							ischeck=false;
							errorRow=extractExcelUtil.addCellComment(errorRow, row.getRowNum(), 1, "Enter company Name,It should contain max 50 characters.");
						}
					}catch (Exception e) {
						ischeck=false;
						errorRow=extractExcelUtil.addCellComment(errorRow, row.getRowNum(), 1, "company Name Error");
					}



					try{
						if(row.getCell(2)!=null && row.getCell(2).getCellType()== Cell.CELL_TYPE_NUMERIC){
							excelsheetinfo.setAge(row.getCell(2)!=null?((Double)row.getCell(2).getNumericCellValue()).intValue():-1);
						}else{
							excelsheetinfo.setAge(row.getCell(2)!=null?((Double)row.getCell(2).getNumericCellValue()).intValue():-1);
						}
						if(excelsheetinfo.getAge()==-1){
							ischeck=false;
							errorRow=extractExcelUtil.addCellComment(errorRow, row.getRowNum(), 2, "age value error");
						}
					}catch (Exception e) {
						ischeck=false;
						errorRow=extractExcelUtil.addCellComment(errorRow, row.getRowNum(), 2, "age value Error");
					}


					try{
						if(row.getCell(3)!=null && row.getCell(3).getCellType()== Cell.CELL_TYPE_NUMERIC){
							excelsheetinfo.setSalary(row.getCell(3)!=null?((Double)row.getCell(3).getNumericCellValue()).doubleValue():-1);
						}else{
							excelsheetinfo.setSalary(row.getCell(3)!=null?((Double)row.getCell(3).getNumericCellValue()).doubleValue():-1);
						}
						if(excelsheetinfo.getSalary()==-1){
							ischeck=false;
							errorRow=extractExcelUtil.addCellComment(errorRow, row.getRowNum(), 2, "salaray value error");
						}
					}catch (Exception e) {
						ischeck=false;
						errorRow=extractExcelUtil.addCellComment(errorRow, row.getRowNum(), 2, "salary value error");
					}

					if(ischeck) {
						excelsheetinfoRepository.save(excelsheetinfo);
					}
					rowcount++;
				}

			}

		}catch (Exception e) {
		}

	}
}