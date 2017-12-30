package com.guru.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.guru.services.ExtractExcelService;

@Controller
public class ExcelSheetReadController {


	@Autowired
	ExtractExcelService extractExcelService;

	@RequestMapping(value="/",method=RequestMethod.GET)
	public String enter(Model model,HttpSession session) {
		return "redirect:/excelExtract";
	}
	@RequestMapping(value="/excelExtract")
	public String getFile(Model model,HttpSession session) {
		return "fileupload";
	}

	@RequestMapping(value="/fileupload",method=RequestMethod.POST)
	public String getFile(Model model,@RequestParam("file") MultipartFile file,HttpSession session) {
		extractExcelService.readExcel(file);
		return "fileupload";
	}

}
