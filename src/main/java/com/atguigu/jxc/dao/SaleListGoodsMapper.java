package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.SaleList;
import com.atguigu.jxc.entity.SaleListGoods;

import java.util.List;
import java.util.Map;

/**
 * @author SongMc
 * @InterfaceName saleListGoodsMapper
 * @date 2022/8/25 15:00
 **/
public interface SaleListGoodsMapper {

	void save(SaleList saleList);

	void saveListGoods(SaleListGoods saleListGood);

	List<SaleList> list(String saleNumber, Integer customerId, Integer state, String sTime, String eTime);

	List<SaleListGoods> goodsList(Integer saleListId);

	void delete(Integer saleListId);

	void deleteGoods(Integer saleListId);

	void updateState(Integer saleListId);
}
