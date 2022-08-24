package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.entity.PurchaseList;
import com.atguigu.jxc.entity.PurchaseListGoods;
import com.atguigu.jxc.service.PurchaseListGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author : SongMc
 * @ClassName : 进货单
 * @date : 2022/8/24 15:48
 **/

@RestController
@RequestMapping("/purchaseListGoods")
public class PurchaseListGoodsController {

	@Autowired
	private PurchaseListGoodsService purchaseListGoodsService;

	/**
	 *
	 * 查询进货单商品信息
	 * @author SongMc
	 * @date 22:18 2022/8/24
	 * @param purchaseListId
	 * @return [java.lang.Integer]
	 **/
	@PostMapping("/goodsList")
	public Map<String,Object> goodsList(Integer purchaseListId){

		return purchaseListGoodsService.getGoodsList(purchaseListId);
	}


	/**
	 *
	 * 进货单列表展示
	 * @author SongMc
	 * @date 22:16 2022/8/24
	 * @param purchaseNumber, supplierId, state, sTime, eTime
	 * @return [java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.String, java.lang.String]
	 **/
	@PostMapping("/list")
	public Map<String,Object> getList(String purchaseNumber, Integer supplierId, Integer state, String sTime,String eTime){

		return purchaseListGoodsService.getListBy(purchaseNumber,supplierId,state,sTime,eTime);

	}


	/**
	 *
	 * 进货单保存
	 * @author SongMc
	 * @date 21:13 2022/8/24
	 * @param purchaseList,purchaseListGoodsStr
	 * @return [com.atguigu.jxc.entity.PurchaseList, java.lang.String]
	 **/
	@PostMapping("/save")
	public ServiceVO save(PurchaseList purchaseList, String purchaseListGoodsStr){

		return purchaseListGoodsService.save(purchaseList,purchaseListGoodsStr);
	}


}
