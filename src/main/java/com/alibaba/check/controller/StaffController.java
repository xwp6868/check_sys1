package com.alibaba.check.controller;
import java.util.List;

import com.alibaba.check.pojo.AliStaff;
import com.alibaba.check.pojo.vo.PageResult;
import com.alibaba.check.pojo.vo.Result;
import com.alibaba.check.pojo.vo.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.alibaba.check.service.StaffService;



/**员工信息
 * controller
 * @author Administrator
 */
@CrossOrigin(origins = "*",
		maxAge = 3600,
		methods = {RequestMethod.GET, RequestMethod.POST})
@RestController
@RequestMapping("/staff")
public class StaffController {


	@Autowired
	private StaffService staffService;
	
	/**
	 * 返回全部列表
	 * @return
	 */

	@RequestMapping("/findAll")
	public List<AliStaff> findAll(){
		return staffService.findAll();
	}


	/**
	 * 返回全部列表+分页
	 * @param page 当前页码
	 * @param rows 每页记录数
	 * @return
	 */
	@RequestMapping(value = "/findPage/{page}/{rows}",method=RequestMethod.POST)
	public PageResult findPage(@PathVariable int page, @PathVariable int rows ){
		return staffService.findPage(page, rows);
	}

	/**
	 * 根据员工 ID 查询
	 * @param
	 * @return
	 */
	@RequestMapping( value = "/{workNumber}/{page}/{rows}", method = RequestMethod.POST )
	@ResponseBody
	public Result findById(@PathVariable String workNumber,@PathVariable int page, @PathVariable int rows ) {
		Object as = staffService.selectByPrimaryKey(workNumber,page,rows);
		return new Result(true, StatusCode.OK, "查询成功", as);

	}
	@RequestMapping( value = "/{workNumber}", method = RequestMethod.POST )
	@ResponseBody
	public Result findId(@PathVariable String workNumber ) {
		Object as = staffService.selectByPrimaryKey(workNumber);
		return new Result(true, StatusCode.OK, "查询成功", as);

	}
	/**
	 * 查询+分页+模糊
	 * @param
	 * @param page 当前页码
	 * @param rows 每页记录数
	 * @return
	 */
	@RequestMapping(value = "/search/{page}/{rows}",method=RequestMethod.POST)
	public PageResult search(@RequestBody AliStaff aliStaff,@PathVariable int page, @PathVariable int rows ){
		return staffService.findPage(aliStaff, page, rows);
	}
	
}
