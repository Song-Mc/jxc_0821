package com.atguigu.jxc.dao;

import com.atguigu.jxc.entity.ReturnList;
import com.atguigu.jxc.entity.ReturnListGoods;

/**
 * @author : SongMc
 * @ClassName : ReturnListGoodsMapper
 * @date : 2022/8/24 17:30
 **/
public interface ReturnListGoodsMapper {


	void saveList(ReturnList returnList);

	void saveGood(ReturnListGoods r);
}
