package com.guru.util;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


@Component
public class ExtractExcelUtil {
	/**
	 * This method for checking excel sheet format xls or xlsx and return row itrator 
	 * @author :- Guruprasanna N.
	 * date    :- june 30, 2015
	 * @param  : Multipartfile 
	 * @return : Row
	 */
	public Iterator<Row> checkExcelformat(MultipartFile file){
		Iterator<Row> rowIterator = null;
		try{
			ByteArrayInputStream bis = new ByteArrayInputStream(file.getBytes());
			Workbook workbook;
			if (file.getOriginalFilename().endsWith("xls")) {
				workbook = new HSSFWorkbook(bis);
			} else if (file.getOriginalFilename().endsWith("xlsx")) {
				workbook = new XSSFWorkbook(bis);
			} else {
				throw new IllegalArgumentException("Received file does not have a standard excel extension.");
			}

			Sheet sheet = workbook.getSheetAt(0);
			rowIterator=sheet.rowIterator();
		}catch(Exception e){
			e.printStackTrace();
		}
		return rowIterator;
	}
	
	/**
	 * This method for checking excel sheet row is empty or not
	 * @author :- Guruprasanna N.
	 * date    :- june 30, 2015
	 * @param  : Row 
	 * @return : boolean value
	 *
	 */
	public boolean isEmptyRow(Row row){
		boolean isEmptyRow = true;
		for(int cellNum = row.getFirstCellNum(); cellNum < row.getLastCellNum(); cellNum++){
			Cell cell = row.getCell(cellNum);
			if(cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK && StringUtils.isNotBlank(cell.toString())){
				isEmptyRow = false;
			}    
		}
		return isEmptyRow;
	}
	
	/**
	 * This method for adding  error columns of excel of particular row to hashmap when importing excel data and dump into database
	 * @author :- Guruprasanna N.
	 * date    :- jan 20, 2016
	 * @param  : HashMap 
	 * @param  : Set 
	 * @param  : int 
	 * @param  : int 
	 * @param  : String 
	 * @return :HashMap
	 */
	public HashMap<Integer,Set<Map<Integer,String>>> addCellComment(HashMap<Integer, Set<Map<Integer,String>>> errorRow,int rowNum,int cellNum,String cellcmt){
		
		if(errorRow.containsKey(rowNum)){
			HashMap<Integer,String>	errorcolumn=new HashMap<Integer, String>();
			errorcolumn.put(cellNum,cellcmt);
			errorRow.get(rowNum).add(errorcolumn);
		}else{
			HashSet<Map<Integer,String>>	setOfColumns=new HashSet<Map<Integer,String>>();
			HashMap<Integer,String>	errorcolumn=new HashMap<Integer, String>();
			errorcolumn.put(cellNum,cellcmt);
			setOfColumns.add(errorcolumn);
			errorRow.put(rowNum, setOfColumns);
		}
		
		return errorRow;
	}
}
