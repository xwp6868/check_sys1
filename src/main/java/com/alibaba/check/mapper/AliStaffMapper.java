package com.alibaba.check.mapper;

import com.alibaba.check.pojo.AliStaff;
import com.alibaba.check.pojo.AliStaffExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AliStaffMapper {

    List<AliStaff> selectByExample(AliStaffExample example);

}