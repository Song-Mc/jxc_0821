package com.atguigu.jxc.service;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.entity.ReturnList;

/**
 * @author SongMc
 * @InterfaceName ReturnListGoodsService
 * @date 2022/8/24 17:31
 **/
public interface ReturnListGoodsService {
	ServiceVO save(ReturnList returnList, String returnListGoodsStr);
}
