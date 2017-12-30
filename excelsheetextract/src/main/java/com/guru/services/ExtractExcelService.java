package com.guru.services;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface ExtractExcelService {
	public void readExcel(MultipartFile file);
}
