package com.alibaba.check.service;
import java.util.List;

import com.alibaba.check.pojo.AliCheck;
import com.alibaba.check.pojo.vo.PageResult;
import com.alibaba.check.pojo.vo.StatisticsVO;

/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface CheckService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<AliCheck> findAll();

	/**
	 * 分页
	 * @param pageNum 当前页 码
	 * @param pageSize 每页记录数
	 * @return
	 */
	public PageResult findPage(AliCheck aliCheck, int pageNum, int pageSize);

	/**
	 * 员工出勤信息统计
	 * @param
	 * @return
	 */
	public PageResult countInfos(int pageNum, int pageSize);

	public StatisticsVO countInfo(StatisticsVO statisticsVO);

	Object countInfos();
}
