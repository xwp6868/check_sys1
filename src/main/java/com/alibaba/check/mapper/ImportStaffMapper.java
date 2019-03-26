package com.alibaba.check.mapper;

import com.alibaba.check.pojo.AliStaff;

import java.util.List;

public interface ImportStaffMapper {
    List<AliStaff> selectImportStaff();


    int selectByName(String name);

    void addUser(AliStaff userResord);

    void updateUserByName(AliStaff userResord);
}
