package com.alibaba.check.service;
import java.util.List;

import com.alibaba.check.pojo.AliCheck;
import com.alibaba.check.pojo.AliStaff;
import com.alibaba.check.pojo.vo.PageResult;
/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface StaffService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<AliStaff> findAll();

	/**
	 * 返回分页列表 + 分页
	 * @return
	 */
	public PageResult findPage(int pageNum, int pageSize);


	/**
	 *查询+分页+模糊
	 * @param pageNum 当前页 码
	 * @param pageSize 每页记录数
	 * @return
	 */
	public PageResult findPage(AliStaff aliStaff, int pageNum, int pageSize);

	Object selectByPrimaryKey(String workNumber, int pageNum, int pageSize);

    Object selectByPrimaryKey(String workNumber);
}
