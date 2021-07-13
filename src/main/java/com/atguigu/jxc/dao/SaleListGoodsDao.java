package com.atguigu.jxc.dao;

/**
 * @author SongMc
 * @date 2021/7/13 21:52
 * @InterfaceName SaleListGoodsDao
 * version : 1.0
 * Description  销售单据
 **/
public interface SaleListGoodsDao {

    /**
     * 根据商品id去查询销售单据的销售数据商品数量
     * @Date 22:12 2021/7/13
     * @Param goodsId
     * @return java.lang.Integer
     */
    Integer getSaleTotalByGoodsId(Integer goodsId);
}
