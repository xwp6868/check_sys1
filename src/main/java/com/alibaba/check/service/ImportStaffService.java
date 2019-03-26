package com.alibaba.check.service;

import com.alibaba.check.pojo.AliStaff;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImportStaffService {

    List<AliStaff> selectImportStaff();

    boolean batchImport(String fileName, MultipartFile file) throws IOException;
}
