package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.PurchaseList;
import com.atguigu.jxc.entity.PurchaseListGoods;

import java.util.List;

/**
 * @author SongMc
 * @date 2021/3/9 18:09
 * @InterfaceName PurchaseListGoodsDao
 * version : 1.0
 * Description
 **/
public interface PurchaseListGoodsDao {
    List<PurchaseList> list(String purchaseNumber, Integer supplierId, Integer state, String sTime, String eTime);

    List<PurchaseListGoods> goodsList(Integer purchaseListId);

    Boolean delete(Integer purchaseListId);
}
