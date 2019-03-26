package com.alibaba.check.service.impl;

import com.alibaba.check.mapper.AliCheckMapper;
import com.alibaba.check.mapper.AliStaffMapper;
import com.alibaba.check.pojo.AliCheck;
import com.alibaba.check.pojo.AliStaff;
import com.alibaba.check.pojo.AliStaffExample;
import com.alibaba.check.pojo.vo.PageResult;
import com.alibaba.check.service.StaffService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class StaffServiceImpl implements StaffService {

	@Autowired
	private AliStaffMapper aliStaffMapper;
	@Autowired
	private AliCheckMapper aliCheckMapper;

	/**
	 * 查询全部
	 */
	@Override
	public List<AliStaff> findAll() {
		return aliStaffMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		Page<AliStaff> page=   (Page<AliStaff>) aliStaffMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}
	/**
	 *查询+分页+模糊
	 * @param aliStaff
	 * @param pageNum 当前页 码
	 * @param pageSize 每页记录数
	 * @return
	 */
	@Override
	public PageResult findPage(AliStaff aliStaff, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		AliStaffExample example=new AliStaffExample();
		AliStaffExample.Criteria criteria = example.createCriteria();
		
		if(aliStaff!=null){

			if(aliStaff.getWorkNumber()!=null && aliStaff.getWorkNumber().length()>0){
				criteria.andWorkNumberLike("%"+aliStaff.getWorkNumber()+"%");
			}
			if(aliStaff.getName()!=null && aliStaff.getName().length()>0){
				criteria.andNameLike("%"+aliStaff.getName()+"%");
			}
	
		}
		
		Page<AliStaff> page= (Page<AliStaff>)aliStaffMapper.selectByExample(example);
		return new PageResult(page.getTotal(), page.getResult());
	}



	/**
	 * 根据工号查询
	 * @param workNumber
	 * @return
	 */

	@Override
	public Object selectByPrimaryKey(String workNumber, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		Page<AliCheck> page= (Page<AliCheck>) aliCheckMapper.selectByPrimary(workNumber);
		return new PageResult(page.getTotal(), page.getResult());
	}

	@Override
	public Object selectByPrimaryKey(String workNumber) {
		return aliCheckMapper.selectByPrimary(workNumber);
	}

}
