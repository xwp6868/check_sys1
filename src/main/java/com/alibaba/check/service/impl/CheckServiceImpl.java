package com.alibaba.check.service.impl;
import java.util.List;

import com.alibaba.check.mapper.AliCheckMapper;
import com.alibaba.check.pojo.AliCheckExample;
import com.alibaba.check.pojo.AliStaff;
import com.alibaba.check.pojo.vo.PageResult;
import com.alibaba.check.pojo.vo.StatisticsVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.check.pojo.AliCheck;
import com.alibaba.check.service.CheckService;
import org.springframework.stereotype.Service;


/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class CheckServiceImpl implements CheckService {

	@Autowired
	private AliCheckMapper aliCheckMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<AliCheck> findAll() {
		return aliCheckMapper.selectByExample(null);
	}

	/**
	 * 分页查询+搜索
	 * @param aliCheck
	 * @param pageNum 当前页 码
	 * @param pageSize 每页记录数
	 * @return
	 */
	@Override
	public PageResult findPage(AliCheck aliCheck, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		AliCheckExample example=new AliCheckExample();
		AliCheckExample.Criteria criteria = example.createCriteria();
		
		if(aliCheck!=null){
			if(aliCheck.getWbNumber()!=null && aliCheck.getWbNumber().length()>0){
				criteria.andWbNumberLike("%"+aliCheck.getWbNumber()+"%");
			}
		}
		List<AliCheck>  lits = aliCheckMapper.selectByExample(example);
		PageInfo<AliCheck> pageInfo= new PageInfo(lits);

		return new PageResult(pageInfo.getTotal(), pageInfo.getList());
	}

	/**
	 * 员工出勤信息统计
	 */
	@Override
	public Object countInfos() {
		aliCheckMapper.dropStatistics();
		aliCheckMapper.createStatistics();
		aliCheckMapper.insertStatistics();
		return aliCheckMapper.countInfos();
	}

	@Override
	public PageResult countInfos(int pageNum, int pageSize) {
		aliCheckMapper.dropStatistics();
		aliCheckMapper.createStatistics();
		aliCheckMapper.insertStatistics();

		PageHelper.startPage(pageNum, pageSize);
		List  lits = aliCheckMapper.countInfos();
		PageInfo pageInfo= new PageInfo(lits);
		return new PageResult(pageInfo.getTotal(), pageInfo.getList());
	}

	@Override
	public StatisticsVO countInfo(StatisticsVO statisticsVO) {
		aliCheckMapper.countInfo(statisticsVO);
		return statisticsVO;
	}


}
