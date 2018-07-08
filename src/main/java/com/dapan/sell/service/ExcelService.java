package com.dapan.sell.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ExcelService {

    boolean batchImport(String fileName, MultipartFile file) throws IOException;
}
