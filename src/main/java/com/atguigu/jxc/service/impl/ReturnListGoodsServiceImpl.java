package com.atguigu.jxc.service.impl;

import com.atguigu.jxc.dao.GoodsDao;
import com.atguigu.jxc.dao.ReturnListGoodsMapper;
import com.atguigu.jxc.dao.UserDao;
import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.domain.SuccessCode;
import com.atguigu.jxc.entity.*;
import com.atguigu.jxc.service.LogService;
import com.atguigu.jxc.service.ReturnListGoodsService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : SongMc
 * @ClassName : ReturnListGoodsServiceImpl
 * @date : 2022/8/24 17:31
 **/
@Service
public class ReturnListGoodsServiceImpl implements ReturnListGoodsService {

	@Autowired
	private ReturnListGoodsMapper returnListGoodsMapper;

	@Autowired
	private UserDao userDao;

	@Autowired
	private GoodsDao goodsDao;

	@Autowired
	private LogService logService;

	@Override
	public ServiceVO save(ReturnList returnList, String returnListGoodsStr) {

		// 设置当前操作用户
		User currentUser = userDao.findUserByName((String) SecurityUtils.getSubject().getPrincipal());
		returnList.setUserId(currentUser.getUserId());

		// 将json字符串转为集合
		Gson gson = new Gson();
		List<ReturnListGoods> returnListGoodsList = gson.fromJson(returnListGoodsStr, new TypeToken<List<ReturnListGoods>>() {
		}.getType());

		// 1.表示已退 2表示未退 todo

		// 保存退货单
		returnListGoodsMapper.saveList(returnList);

		for (ReturnListGoods r : returnListGoodsList){

			// 设置退货单id
			r.setReturnListId(returnList.getReturnListId());
			// 查询库里商品
			Goods goods = goodsDao.findByGoodsId(r.getGoodsId());
			// 设置商品类别id
			r.setGoodsTypeId(goods.getGoodsTypeId());

			returnListGoodsMapper.saveGood(r);

			// 修改库里商品属性
			goods.setInventoryQuantity(goods.getInventoryQuantity() - r.getGoodsNum());
			goods.setState(2);

			goodsDao.updateGoods(goods);
		}

		// 保存日志
		logService.save(new Log(Log.INSERT_ACTION,"新增退货单：" + returnList.getReturnNumber()));

		// 返回表示
		return new ServiceVO(SuccessCode.SUCCESS_CODE,SuccessCode.SUCCESS_MESS);
	}
}
