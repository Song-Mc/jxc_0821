package com.atguigu.jxc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author : SongMc
 * @ClassName : PersonDemo
 * @date : 2022/9/1 16:07
 **/
public class PersonDemo {

	public static void main(String[] args) {
		List<Person> personList = new ArrayList<>();
		personList.add(new Person("欧阳雪",18,"中国",'F'));
		personList.add(new Person("Tom",24,"美国",'M'));
		personList.add(new Person("Harley",22,"英国",'F'));
		personList.add(new Person("向天笑",20,"中国",'M'));
		personList.add(new Person("李康",22,"中国",'M'));
		personList.add(new Person("小梅",20,"中国",'F'));
		personList.add(new Person("何雪",21,"中国",'F'));
		personList.add(new Person("李康",22,"中国",'M'));

		// 找出年龄大于20的人并打印输出
		personList.stream().filter((person -> person.getAge() >= 20)).forEach(System.out::println);
		System.out.println("??????////////////");

		// .map(Function<? super T,?>  mapper )       将元素转换成其他形式或提取信息。
		// 打印出集合中所有人的年龄各式多少
		personList.stream().map((person -> person.getAge())).forEach(System.out::println);
		System.out.println("??????////////////");

		// .sorted()  /    .sort(Comparator<? super Integet> comparator)  对元素进行排序
		// 打印出所有的中国人并按照年龄升序排序
		personList.stream().filter((person -> person.getCountry().equals("中国")))
				.sorted((p1,p2) -> p1.getAge() - p2.getAge())
				.forEach(System.out::println);
		System.out.println("??????////////////");

		// .count()      计算元素的个数
		// 求中国人的个数并打印输出
		System.out.println(personList.stream().filter((person -> person.getCountry().equals("中国"))).count());
		System.out.println("??????////////////");

		// .forEach()             遍历  打印输出所有元素：
		personList.stream().forEach(System.out::println);
		System.out.println("??????////////////");

		// .reduce ()   规约        元素反复结合，得到一个值
		// 求元素中的年龄总和
		Optional<Integer> reduce = personList.stream().map((person -> person.getAge())).reduce((a1, a2) -> a1 + a2);
		System.out.println(reduce.get());
		System.out.println("??????////////////");

		// .collect()    收集    将流转换为其他形式
		// 将元素中年龄大于20的人收集并转为list
		List<Person> persons = personList.stream().filter((person -> person.getAge() > 20)).collect(Collectors.toList());
		System.out.println(persons);
		System.out.println("??????////////////");

		// .max(Comparator<? super T> comparator)   最大值   传入一个比较器参数
		//          .min(Comparator<? super T> comparator)    最小值   传入一个比较器参数
		//  求集合中年龄最大的人
		Optional<Person> max = personList.stream().max((p1, p2) -> p1.getAge() - p2.getAge());
		System.out.println(max.get().getAge());
		System.out.println("??????////////////");

		Optional<Person> min = personList.stream().min((p1, p2) -> p1.getAge() - p2.getAge());
		System.out.println(min.get().getAge());
		System.out.println("??????////////////");

		// .allMatch(Predicate<? super T> predicate)         检查是否匹配所有元素
		//
		//           .anyMatch(Predicate<? super T> predicate)       检查是否至少匹配一个元素
		//
		//           .noneMatch(Predicate<? super T> predicate)     检查是否没有匹配所有元素

		boolean b = personList.stream().allMatch((person -> person.getAge() > 17));
		System.out.println(b);

		boolean b1 = personList.stream().anyMatch((person -> person.getAge() > 20));
		System.out.println(b1);

		boolean b2 = personList.stream().noneMatch((person -> person.getAge() > 26));
		System.out.println(b2);
		System.out.println("??????////////////");

		// .findFirst()                      返回第一个元素
		// .findAny()                        返回当前流中的任意元素

		//随机返回一个中国人
		Optional<Person> any = personList.stream().filter((person -> person.getCountry().equals("中国"))).findAny();
		System.out.println(any.get());
		System.out.println("??????////////////");

		//返回第一个年龄大于20的人
		Optional<Person> first = personList.stream().filter((person -> person.getAge() > 20)).findFirst();
		System.out.println(first.get());
		System.out.println("??????////////////");


	}

}
