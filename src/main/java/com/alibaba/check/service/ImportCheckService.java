package com.alibaba.check.service;


import com.alibaba.check.pojo.AliCheck;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImportCheckService {

    List<AliCheck> selectImportCheck();

    boolean batchImport(String fileName, MultipartFile file) throws IOException;
}
