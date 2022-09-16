package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.entity.SaleList;
import com.atguigu.jxc.service.SaleListGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author : SongMc
 * @ClassName : saleListGoodsController
 * @date : 2022/8/25 14:59
 **/
@RestController
@RequestMapping("/saleListGoods")
public class SaleListGoodsController {

	@Autowired
	private SaleListGoodsService saleListGoodsService;



	@RequestMapping(value = "/getSaleDataByMonth",method = RequestMethod.POST)
	public String getSaleDataByMonth(String sTime,String eTime){

		return saleListGoodsService.getSaleDataByMonth(sTime,eTime);

	}

	/**
	 *
	 * 按日统计接口
	 * @author SongMc
	 * @date 16:50 2022/9/16
	 * @param sTime,eTime
	 * @return [java.lang.String, java.lang.String]
	 **/
	@RequestMapping(value = "/getSaleDataByDay",method = RequestMethod.POST)
	public String getSaleDataByDay(String sTime,String eTime){

		return saleListGoodsService.getSaleDataByDay(sTime,eTime);

	}


	// 客户退货单保存 todo

	/**
	 *
	 * 修改销售单付款状态
	 * @author SongMc
	 * @date 16:47 2022/8/25
	 * @param saleListId
	 * @return [java.lang.Integer]
	 **/
	@PostMapping("/updateState")
	public ServiceVO updateState(Integer saleListId){

		return saleListGoodsService.updateState(saleListId);
	}

	/**
	 *
	 * 删除销售单
	 * @author SongMc
	 * @date 16:23 2022/8/25
	 * @param saleListId
	 * @return [java.lang.Integer]
	 **/
	@PostMapping("/delete")
	public ServiceVO delete(Integer saleListId){

		return saleListGoodsService.delete(saleListId);
	}

	/**
	 *
	 * 销售单商品信息查询
	 * @author SongMc
	 * @date 16:08 2022/8/25
	 * @param saleListId
	 * @return [java.lang.Integer]
	 **/
	@PostMapping("/goodsList")
	public Map<String,Object> goodsList(Integer saleListId){

		return saleListGoodsService.goodsList(saleListId);
	}

	/**
	 *
	 * 销售单查询
	 * @author SongMc
	 * @date 15:42 2022/8/25
	 * @param saleNumber, customerId, state, sTime, eTime
	 * @return [java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String]
	 **/
	@PostMapping("/list")
	public Map<String,Object> list(String saleNumber, Integer customerId, Integer state, String sTime,String eTime){

		return saleListGoodsService.list(saleNumber,customerId,state,sTime,eTime);

	}


	/**
	 *
	 * 销售单保存
	 * @author SongMc
	 * @date 15:24 2022/8/25
	 * @param saleList, saleListGoodsStr
	 * @return [com.atguigu.jxc.entity.SaleList, java.lang.String]
	 **/
	@PostMapping("/save")
	public ServiceVO save(SaleList saleList, String saleListGoodsStr){

		return saleListGoodsService.save(saleList,saleListGoodsStr);

	}

}
