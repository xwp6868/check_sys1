package com.alibaba.check.mapper;

import com.alibaba.check.pojo.AliCheck;
import com.alibaba.check.pojo.AliCheckExample;
import java.util.List;
import java.util.Map;

import com.alibaba.check.pojo.vo.PageResult;
import com.alibaba.check.pojo.vo.StatisticsVO;
import org.apache.ibatis.annotations.Param;

public interface AliCheckMapper {
    int countByExample(AliCheckExample example);

    int deleteByExample(AliCheckExample example);

    int deleteByPrimaryKey(Integer checkId);

    int insert(AliCheck record);

    int insertSelective(AliCheck record);

    List<AliCheck> selectByExample(AliCheckExample example);

    int updateByExampleSelective(@Param( "record" ) AliCheck record, @Param( "example" ) AliCheckExample example);

    int updateByExample(@Param( "record" ) AliCheck record, @Param( "example" ) AliCheckExample example);

    int updateByPrimaryKeySelective(AliCheck record);

    int updateByPrimaryKey(AliCheck record);

    public List<AliCheck> selectByPrimary(String workNumber);

    Map countInfo(StatisticsVO statisticsVO);

    List insertWorkState(List checkList);


    List countInfos();

    void dropStatistics();

    void createStatistics();

    void insertStatistics();
}