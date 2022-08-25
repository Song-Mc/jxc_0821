package com.atguigu.jxc.dao;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.entity.PurchaseList;
import com.atguigu.jxc.entity.PurchaseListGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author SongMc
 * @InterfaceName PurchaseListGoodsMapper
 * @date 2022/8/24 16:06
 **/
public interface PurchaseListGoodsMapper {


	void save(PurchaseList purchaseList);

	void saveGood(PurchaseListGoods p);

	List<PurchaseList> getListBy(String purchaseNumber, Integer supplierId, Integer state, String sTime, String eTime);

	List<PurchaseListGoods> getGoodsList(Integer purchaseListId);

	Integer delete(Integer purchaseListId);

	Integer deleteListGoods(Integer purchaseListId);

	void updateState(Integer purchaseListId);

	List<PurchaseListGoods> getGoodsBy(Integer purchaseListId, Integer goodsTypeId, @Param("codeOrName") String codeOrName);
}
