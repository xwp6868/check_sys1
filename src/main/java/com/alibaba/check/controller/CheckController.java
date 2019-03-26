package com.alibaba.check.controller;

import com.alibaba.check.pojo.AliCheck;
import com.alibaba.check.pojo.vo.PageResult;
import com.alibaba.check.pojo.vo.Result;
import com.alibaba.check.pojo.vo.StatisticsVO;
import com.alibaba.check.pojo.vo.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.alibaba.check.service.CheckService;

/**
 * 员工出勤信息
 * controller
 * @author Administrator
 */
@CrossOrigin(origins = "*",
		maxAge = 3600,
		methods = {RequestMethod.GET, RequestMethod.POST})

@RestController
@RequestMapping("/check")
public class CheckController {

	@Autowired
	private CheckService checkService;

	/**
	 * 查询所有
	 *
	 * @return
	 */
	@RequestMapping( method = RequestMethod.GET )
	public Result findAll() {
		return new Result(true, StatusCode.OK, "查询成功", checkService.findAll());
	}
	/**
	 * 查询+分页+模糊
	 * @param aliCheck
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value = "/search/{page}/{rows}",method=RequestMethod.POST)
	//@ResponseBody
	public PageResult search(@RequestBody AliCheck aliCheck, @PathVariable int page, @PathVariable int rows  ){
		return checkService.findPage(aliCheck, page, rows);
	}

	/**
	 * 员工出勤信息统计
	 */
	@RequestMapping(value = "/checks",method = RequestMethod.POST )
	public Result countInfos() {
		return new Result(true, StatusCode.OK, "统计成功", checkService.countInfos());
	}
	@RequestMapping(value = "/checks/{page}/{rows}",method = RequestMethod.POST )
	public Result countInfos(@PathVariable int page, @PathVariable int rows) {
		return new Result(true, StatusCode.OK, "统计成功", checkService.countInfos(page, rows));
	}
	/**
	 * 员工出勤信息统计搜索
	 */
	@RequestMapping(value = "/check",method=RequestMethod.POST)
	public Result countInfo(@RequestBody StatisticsVO statisticsVO) {
		System.out.println("返回结果:"+checkService.countInfo(statisticsVO));
		return new Result(true, StatusCode.OK, "统计成功", checkService.countInfo(statisticsVO));
	}


}
