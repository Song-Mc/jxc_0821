package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.GoodsDao;
import com.atguigu.jxc.dao.SaleListGoodsMapper;
import com.atguigu.jxc.dao.UserDao;
import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.*;
import com.atguigu.jxc.service.LogService;
import com.atguigu.jxc.service.SaleListGoodsService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : SongMc
 * @ClassName : saleListGoodsImpl
 * @date : 2022/8/25 15:00
 **/
@Service
public class SaleListGoodsImpl implements SaleListGoodsService {

	@Autowired
	private SaleListGoodsMapper saleListGoodsMapper;

	@Autowired
	private UserDao userDao;

	@Autowired
	private GoodsDao goodsDao;

	@Autowired
	private LogService logService;

	@Override
	public ServiceVO save(SaleList saleList, String saleListGoodsStr) {

		// 设置当前操作用户
		User currentUser = userDao.findUserByName((String) SecurityUtils.getSubject().getPrincipal());
		saleList.setUserId(currentUser.getUserId());

		//使用gson将json字符串转为集合
		Gson gson = new Gson();
		List<SaleListGoods> saleListGoods =  gson.fromJson(saleListGoodsStr, new TypeToken<List<SaleListGoods>>(){}.getType());

		// 保存销售单
		saleListGoodsMapper.save(saleList);

		// 保存商品清单
		for (SaleListGoods saleListGood : saleListGoods) {

			saleListGood.setSaleListId(saleList.getSaleListId());
			saleListGood.setGoodsTypeId(goodsDao.findByGoodsId(saleListGood.getGoodsId()).getGoodsTypeId());

			saleListGoodsMapper.saveListGoods(saleListGood);

			// 更改库存属性
			Goods goods = goodsDao.findByGoodsId(saleListGood.getGoodsId());
			goods.setInventoryQuantity(goods.getInventoryQuantity() - saleListGood.getGoodsNum());

			goods.setState(2);

			goodsDao.updateGoods(goods);
		}

		// 保存日志
		logService.save(new Log(Log.INSERT_ACTION,"保存销售单：" + saleList.getSaleListId()));

		return new ServiceVO<>(SuccessCode.SUCCESS_CODE,SuccessCode.SUCCESS_MESS);
	}

	@Override
	public Map<String, Object> list(String saleNumber, Integer customerId, Integer state, String sTime, String eTime) {

		Map<String, Object>  map = new HashMap<>();

		List<SaleList> saleLists = saleListGoodsMapper.list(saleNumber,customerId,state,sTime,eTime);
		map.put("rows", saleLists);
		logService.save(new Log(Log.SELECT_ACTION,"查询销售单"));

		return map;
	}

	@Override
	public Map<String, Object> goodsList(Integer saleListId) {
		Map<String, Object>  map = new HashMap<>();

		List<SaleListGoods> saleListGoods = saleListGoodsMapper.goodsList(saleListId);
		map.put("rows", saleListGoods);
		logService.save(new Log(Log.SELECT_ACTION,"查询销售商品单"));
		return map;
	}

	@Override
	public ServiceVO delete(Integer saleListId) {

		saleListGoodsMapper.deleteGoods(saleListId);

		saleListGoodsMapper.delete(saleListId);

		logService.save(new Log(Log.DELETE_ACTION,"删除操作：" + saleListId));

		return new ServiceVO<>(SuccessCode.SUCCESS_CODE,SuccessCode.SUCCESS_MESS);
	}

	@Override
	public ServiceVO updateState(Integer saleListId) {

		saleListGoodsMapper.updateState(saleListId);

		logService.save(new Log(Log.UPDATE_ACTION,"修改销售单支付状态：" + saleListId));

		return new ServiceVO(SuccessCode.SUCCESS_CODE,SuccessCode.SUCCESS_MESS);
	}
}
