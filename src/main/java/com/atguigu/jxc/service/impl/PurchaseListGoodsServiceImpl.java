package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.GoodsDao;
import com.atguigu.jxc.dao.GoodsTypeDao;
import com.atguigu.jxc.dao.PurchaseListGoodsMapper;
import com.atguigu.jxc.dao.UserDao;
import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.*;
import com.atguigu.jxc.service.LogService;
import com.atguigu.jxc.service.PurchaseListGoodsService;
import com.atguigu.jxc.util.BigDecimalUtil;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
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

	@Autowired
	private GoodsTypeDao goodsTypeDao;

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

	@Override
	public ServiceVO delete(Integer purchaseListId) {

		purchaseListGoodsMapper.deleteListGoods(purchaseListId);

		purchaseListGoodsMapper.delete(purchaseListId);

		// 删除日志
		logService.save(new Log(Log.DELETE_ACTION,"删除进货单"+purchaseListId));


		return new ServiceVO(SuccessCode.SUCCESS_CODE, SuccessCode.SUCCESS_MESS);
	}

	@Override
	public ServiceVO updateState(Integer purchaseListId) {

		purchaseListGoodsMapper.updateState(purchaseListId);

		// 更新日志
		logService.save(new Log(Log.UPDATE_ACTION,"更改支付状态："+purchaseListId));

		return new ServiceVO(SuccessCode.SUCCESS_CODE,SuccessCode.SUCCESS_MESS);
	}

	@Override
	public String count(String sTime, String eTime, Integer goodsTypeId, String codeOrName) {

		JsonArray jsonArray = new JsonArray();

		List<PurchaseList> purchaseLists = purchaseListGoodsMapper.getListBy(null, null, null, sTime, eTime);

		for (PurchaseList purchaseList : purchaseLists) {

			List<PurchaseListGoods> purchaseListGoods =
					purchaseListGoodsMapper.getGoodsBy(purchaseList.getPurchaseListId(),goodsTypeId,codeOrName);

			for (PurchaseListGoods good : purchaseListGoods) {
				JsonObject object = new JsonObject();
				object.addProperty( "number",purchaseList.getPurchaseNumber());
				object.addProperty("date", purchaseList.getPurchaseDate());

				object.addProperty("supplierName", purchaseList.getSupplierName());

				object.addProperty("code", good.getGoodsCode());

				object.addProperty("name", good.getGoodsName());

				object.addProperty("model", good.getGoodsModel());

				object.addProperty("goodsType", goodsTypeDao.getGoodsTypeById(good.getGoodsTypeId()).getGoodsTypeName());

				object.addProperty("unit", good.getGoodsUnit());

				object.addProperty("price", good.getPrice());

				object.addProperty("num", good.getGoodsNum());

				object.addProperty("total", good.getTotal());

				jsonArray.add(object);
			}
		}
		logService.save(new Log(Log.SELECT_ACTION, "进货商品统计查询"));

		return jsonArray.toString();
	}
}
