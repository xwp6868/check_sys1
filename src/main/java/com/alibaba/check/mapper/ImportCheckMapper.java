package com.alibaba.check.mapper;


import com.alibaba.check.pojo.AliCheck;

import java.util.List;

public interface ImportCheckMapper {

    void addUser(AliCheck userResord);

    List<AliCheck> selectImportCheck();
}
