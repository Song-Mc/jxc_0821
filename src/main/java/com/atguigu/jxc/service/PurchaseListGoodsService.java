package com.atguigu.jxc.service;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.entity.PurchaseList;

import java.util.List;
import java.util.Map;

/**
 * @author SongMc
 * @InterfaceName PurchaseListGoodsService
 * @date 2022/8/24 16:03
 **/
public interface PurchaseListGoodsService {
	ServiceVO save(PurchaseList purchaseList, String purchaseListGoodsStr);

	Map<String,Object> getListBy(String purchaseNumber, Integer supplierId, Integer state, String sTime, String eTime);

	Map<String, Object> getGoodsList(Integer purchaseListId);

	ServiceVO delete(Integer purchaseListId);

	ServiceVO updateState(Integer purchaseListId);

	String count(String sTime, String eTime, Integer goodsTypeId, String codeOrName);

}
