package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.entity.PurchaseList;
import com.atguigu.jxc.entity.PurchaseListGoods;
import com.atguigu.jxc.service.PurchaseListGoodsService;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : SongMc
 * @date : 2021/3/9 18:00
 * className : PurchaseListGoodsController
 * package: com.atguigu.jxc.controller
 * version : 1.0
 * Description
 */
@RestController
@RequestMapping("/purchaseListGoods")
public class PurchaseListGoodsController {

    @Autowired
    private PurchaseListGoodsService purchaseListGoodsService;

    @PostMapping("/list")
    public Map<String,Object> list(String purchaseNumber,
                                   Integer supplierId,
                                   Integer state,
                                   String eTime,
                                   String sTime){
        List<PurchaseList> list =  purchaseListGoodsService.list(purchaseNumber,supplierId,state,sTime,eTime);
        HashMap<String, Object> map = new HashMap<>();
        map.put("rows", list);
        System.out.println(map);
        return map;
    }

    @PostMapping("goodsList")
    public Map<String,Object> goodsList(Integer purchaseListId){
        List<PurchaseListGoods> goodsList = this.purchaseListGoodsService.goodsList(purchaseListId);
        HashMap<String, Object> map = new HashMap<>();
        map.put("rows", goodsList);
        return map;
    }

    @PostMapping("delete")
    public ServiceVO delete(Integer purchaseListId){
        Boolean flag = this.purchaseListGoodsService.delete(purchaseListId);

        if (flag){
            return new ServiceVO(100,"请求成功",null);
        }else{
            return new ServiceVO(999,"请求失败",null);
        }

    }

}
