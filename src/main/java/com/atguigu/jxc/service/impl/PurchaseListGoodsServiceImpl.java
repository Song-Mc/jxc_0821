package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.GoodsDao;
import com.atguigu.jxc.dao.PurchaseListGoodsMapper;
import com.atguigu.jxc.dao.UserDao;
import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.*;
import com.atguigu.jxc.service.LogService;
import com.atguigu.jxc.service.PurchaseListGoodsService;
import com.atguigu.jxc.util.BigDecimalUtil;
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
 * @ClassName : PurchaseListGoodsServiceImpl
 * @date : 2022/8/24 16:03
 **/
@Service
public class PurchaseListGoodsServiceImpl implements PurchaseListGoodsService {

	@Autowired
	private PurchaseListGoodsMapper purchaseListGoodsMapper;

	@Autowired
	private UserDao userDao;

	@Autowired
	private GoodsDao goodsDao;

	@Autowired
	private LogService logService;

	@Override
	public ServiceVO save(PurchaseList purchaseList, String purchaseListGoodsStr) {


		// 使用谷歌转json字符串 todo
		Gson gson = new Gson();
		List<PurchaseListGoods> purchaseListGoodsList = gson.fromJson(purchaseListGoodsStr, new TypeToken<List<PurchaseListGoods>>(){}.getType());

		// 设置当前操作用户
		User currentUser = userDao.findUserByName((String) SecurityUtils.getSubject().getPrincipal());

		purchaseList.setUserId(currentUser.getUserId());

		// 保存进货单
		purchaseListGoodsMapper.save(purchaseList);

		// 保存进货单商品列表
		for (PurchaseListGoods p : purchaseListGoodsList){

			// 商品表中设置进货单id  商品类别id
			p.setPurchaseListId(purchaseList.getPurchaseListId());
			p.setGoodsTypeId(goodsDao.findByGoodsId(p.getGoodsId()).getGoodsTypeId());

			purchaseListGoodsMapper.saveGood(p);

			//修改库里商品
			// 修改商品上一次进货价，进货均价，库存，状态
			Goods goods = goodsDao.findByGoodsId(p.getGoodsId());

			goods.setLastPurchasingPrice(p.getPrice());

			goods.setInventoryQuantity(goods.getInventoryQuantity() + p.getGoodsNum());

			goods.setPurchasingPrice(BigDecimalUtil.keepTwoDecimalPlaces((goods.getPurchasingPrice()+p.getPrice())/2));

			goods.setState(2);

			goodsDao.updateGoods(goods);

		}

		// 保存日志
		logService.save(new Log(Log.INSERT_ACTION,"新增进货单："+purchaseList.getPurchaseNumber()));

		return new ServiceVO<>(SuccessCode.SUCCESS_CODE,SuccessCode.SUCCESS_MESS);

	}

	@Override
	public Map<String, Object> getListBy(String purchaseNumber, Integer supplierId, Integer state, String sTime, String eTime) {

		Map<String, Object> map = new HashMap<>();
		List<PurchaseList> purchaseList = purchaseListGoodsMapper.getListBy(purchaseNumber, supplierId, state, sTime, eTime);

		logService.save(new Log(Log.SELECT_ACTION, "进货单据查询"));

		map.put("rows", purchaseList);
		return map;
	}

	@Override
	public Map<String, Object> getGoodsList(Integer purchaseListId) {
		Map<String,Object> map = new HashMap<>();

		if (purchaseListId != 0){

			List<PurchaseListGoods> purchaseListGoods = purchaseListGoodsMapper.getGoodsList(purchaseListId);

			map.put("rows", purchaseListGoods);
			return map;
		}

		return null;

	}
}
