package com.atguigu.jxc;

import java.util.EnumSet;

/**
 * @author : SongMc
 * @ClassName : EnumTest
 * @date : 2022/9/25 20:52
 **/
public class EnumTest {

	public enum Numbers {
		ONE, TWO, THREE, FOUR, FIVE
	};


	public static void main(String[] args) {

		// create a set
		EnumSet<Numbers> set;

		// add elements
		// 此方法相当于是按顺序(枚举里的顺序)返回这个枚举里对应的变量
		set = EnumSet.of( Numbers.FOUR, Numbers.FIVE, Numbers.TWO,Numbers.ONE);

		// print the set
		System.out.println("Set:" + set);
	}

}
