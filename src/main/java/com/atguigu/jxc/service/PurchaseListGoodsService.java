package com.atguigu.jxc.service;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.entity.PurchaseList;
import com.atguigu.jxc.entity.PurchaseListGoods;

import java.util.List;

/**
 * @author SongMc
 * @date 2021/3/9 18:03
 * @InterfaceName PurchaseListGoodsService
 * version : 1.0
 * Description
 **/
public interface PurchaseListGoodsService {
    List<PurchaseList> list(String purchaseNumber, Integer supplierId, Integer state, String sTime, String eTime);

    List<PurchaseListGoods> goodsList(Integer purchaseListId);

    Boolean delete(Integer purchaseListId);

    ServiceVO save(PurchaseList purchaseList, String purchaseListGoodsStr);
}
