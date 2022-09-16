package com.atguigu.jxc.service;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.entity.SaleList;

import java.util.Map;

/**
 * @author SongMc
 * @InterfaceName saleListGoodsService
 * @date 2022/8/25 15:00
 **/
public interface SaleListGoodsService {
	ServiceVO save(SaleList saleList, String saleListGoodsStr);

	Map<String, Object> list(String saleNumber, Integer customerId, Integer state, String sTime, String eTime);

	Map<String, Object> goodsList(Integer saleListId);

	ServiceVO delete(Integer saleListId);

	ServiceVO updateState(Integer saleListId);

	String getSaleDataByDay(String sTime, String eTime);

	String getSaleDataByMonth(String sTime, String eTime);
}
