package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.SaleListGoodsDao;
import com.atguigu.jxc.entity.SaleListGoods;
import com.atguigu.jxc.service.SaleListGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : SongMc
 * @date : 2021/7/13 21:39
 * className : SaleListGoodsServiceImpl
 * package: com.atguigu.jxc.service.impl
 * version : 1.0
 * Description
 */
@Service
public class SaleListGoodsServiceImpl implements SaleListGoodsService {

    @Autowired
    private SaleListGoodsDao saleListGoodsDao;

    @Override
    public Integer getSaleTotalByGoodsId(Integer goodsId) {

        // goodsId查到的不唯一。。。。。TODO
        Integer goodsNum = saleListGoodsDao.getSaleTotalByGoodsId(goodsId);
        return goodsNum;
    }
}
