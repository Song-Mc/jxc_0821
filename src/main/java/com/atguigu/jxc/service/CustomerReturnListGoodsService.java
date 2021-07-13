package com.atguigu.jxc.service;

/**
 * @author : SongMc
 * @date : 2021/7/13 21:37
 * className : CustomerReturnListGoodsService
 * package: com.atguigu.jxc.service
 * version : 1.0
 * Description
 */
public interface CustomerReturnListGoodsService {

    /**
     * 查询退货单据的退货数据
     * @Date 21:38 2021/7/13
     * @Param goodsId
     * @return java.lang.Integer
     */
    public Integer getCustomerReturnTotalByGoodsId(Integer goodsId);
}
