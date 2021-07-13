package com.atguigu.jxc.service;

/**
 * @author SongMc
 * @date 2021/7/13 21:36
 * @InterfaceName SaleListGoodsService
 * version : 1.0
 * Description
 **/
public interface SaleListGoodsService {

    /**
     *
     * 查询销售单据的销售数据
     * @Date 21:37 2021/7/13
     * @Param goodsId
     * @return java.lang.Integer
     */
    Integer getSaleTotalByGoodsId(Integer goodsId);
}
