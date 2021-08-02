package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.PurchaseListDao;
import com.atguigu.jxc.dao.PurchaseListGoodsDao;
import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.entity.PurchaseList;
import com.atguigu.jxc.entity.PurchaseListGoods;
import com.atguigu.jxc.service.PurchaseListGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author : SongMc
 * @date : 2021/3/9 18:03
 * className : PurchaseListGoodsServiceImpl
 * package: com.atguigu.jxc.service.impl
 * version : 1.0
 * Description
 */
@Service
public class PurchaseListGoodsServiceImpl implements PurchaseListGoodsService {

    @Autowired
    private PurchaseListGoodsDao purchaseListGoodsDao;

    @Autowired
    private PurchaseListDao purchaseListDao;

    @Override
    public List<PurchaseList> list(String purchaseNumber, Integer supplierId, Integer state, String sTime, String eTime) {

        return purchaseListGoodsDao.list(purchaseNumber,supplierId,state,sTime,eTime);


    }

    @Override
    public List<PurchaseListGoods> goodsList(Integer purchaseListId) {

        return this.purchaseListGoodsDao.goodsList(purchaseListId);

    }

    @Override
    public Boolean delete(Integer purchaseListId) {

        if (purchaseListId == null){
            return false;
        }

        this.purchaseListGoodsDao.delete(purchaseListId);
        this.purchaseListDao.delete(purchaseListId);
        return true;

    }

    @Override
    public ServiceVO save(PurchaseList purchaseList, String purchaseListGoodsStr) {

        return this.purchaseListDao.save(purchaseList);

    }
}
