package com.atguigu.jxc;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author : SongMc
 * @ClassName : StremDemo
 * @date : 2022/9/1 16:03
 **/
public class StreamDemo {

	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<>();
		Collections.addAll(list, "张无忌", "周芷若", "赵敏", "张强", "张三丰", "张无忌");

		// .limit(n)   从list中取出n个元素
		list.stream().limit(2).forEach(System.out::println);
		System.out.println("??????////////////");

		// .skip(n) 从list第n个元素开始，取出所有元素
		list.stream().skip(2).forEach(System.out::println);
		System.out.println("??????////////////");

		// .distinct() 去除重复
		list.stream().distinct().forEach(System.out::println);
		System.out.println("??????////////////");

		// .filter(Predicate<? super T> predicate)    筛选条件
		list.stream().filter((str -> str.length() == 3)).forEach(System.out::println);
		System.out.println("??????////////////");

	}

}
