package com.atguigu.jxc.dao;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.entity.PurchaseList;

/**
 * @author SongMc
 * @date 2021/3/9 21:18
 * @InterfaceName PurchaseListDao
 * version : 1.0
 * Description
 **/
public interface PurchaseListDao {
    void delete(Integer purchaseListId);

    ServiceVO save(PurchaseList purchaseList);
}
