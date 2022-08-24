package com.atguigu.jxc.controller;

import com.atguigu.jxc.domain.ServiceVO;
import com.atguigu.jxc.entity.ReturnList;
import com.atguigu.jxc.service.ReturnListGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : SongMc
 * @ClassName : ReturnListGoodsController
 * @date : 2022/8/24 17:30
 **/
@RestController
@RequestMapping("/returnListGoods")
public class ReturnListGoodsController {

	@Autowired
	private ReturnListGoodsService returnListGoodsService;

	@RequestMapping(value = "save" ,method = RequestMethod.POST)
	public ServiceVO save(ReturnList returnList, String returnListGoodsStr){

		return returnListGoodsService.save(returnList,returnListGoodsStr);

	}



}
