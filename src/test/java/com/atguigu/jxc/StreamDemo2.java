package com.atguigu.jxc;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * stream操作
 * @author : SongMc
 * @ClassName : StreamDemo2
 * @date : 2022/9/19 22:46
 **/
public class StreamDemo2 {

	public static void main(String[] args){

		/*//List集合
		List<String> stringList = new ArrayList<>();
		//Set集合
		Set<String> stringSet = new HashSet<>();
		//Map集合
		Map<String,Object> stringObjectMap = new HashMap<>();
		//数组
		String[] stringArray = {"张三三","李四","王五","王五","赵八",};

		// Stream<String> streamList = stringList.stream();
		// Stream<String> stream = stringSet.stream();
		// Stream<String> streamMap = stringObjectMap.keySet().stream();
		// Stream<String> streamArray = Arrays.stream(stringArray);

		// 1. Individual values
		Stream array = Stream.of("a", "b", "c");
		array.forEach(System.out::println);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

		// 2. Arrays
		String[] str = new String[]{"aa","bb","cc"};
		array = Stream.of(str);
		array = Arrays.stream(str);
		array.forEach(System.out::println);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

		// 3. Collections
		List<String> listArr = Arrays.asList(str);
		array = listArr.stream();
		array.forEach(System.out::println);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");*/


		List<String> list = new ArrayList<>();
		list.add("张三三");
		list.add("李四");
		list.add("王五");
		list.add("孙七");
		list.add("赵八");
		list.add("张六六");

		// 获取姓张的人
		// list.stream().filter(name -> name.contains("张")).forEach(System.out::println);

		// 	过滤所有姓张的人 过滤所有姓名是3个字的人 遍历打印
		// list.stream().filter(name -> name.startsWith("张"))
		// 		.filter(name -> name.length() == 3)
		// 		.forEach(System.out::println);

		// 获取前3个
		// list.stream().limit(3).forEach(System.out::println);

		// 跳过前3个
		// list.stream().skip(3).forEach(System.out::println);

		//skip+limit实现分页
		list.stream().skip(2).limit(3).forEach(System.out::println);




}



}
