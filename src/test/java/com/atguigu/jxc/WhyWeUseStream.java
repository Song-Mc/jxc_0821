package com.atguigu.jxc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WhyWeUseStream {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "张无忌", "周芷若", "赵敏", "张强", "张三丰");
        //需求:1.拿到所有姓张的 2.拿到名字长度为3个字的 3.打印这些数据

        //传统做法  for循环，new新的ArrayList存放需求的数据
        //1.拿到所有姓张的
        List<String> nameList = new ArrayList<>();
        for (String s : list) {
            if (s.startsWith("张")) {
                nameList.add(s);
            }
        }

        //3.打印这些数据
        for (String s : nameList) {
            System.out.println(s);
        }
        System.out.println("------------");

        list.stream().filter((str) ->str.startsWith("张")).forEach(System.out::println);
        System.out.println("///////////////////////");

        //2.拿到名字长度为3个字的
        List<String> lengthList = new ArrayList<>();
        for (String s : list) {
            if (s.length() == 3) {
                lengthList.add(s);
            }
        }
        for (String s : lengthList) {
            System.out.println(s);
        }
        System.out.println("+++++++++++++++++++++++++++++++");

        list.stream().filter((str -> str.length() == 3)).forEach(System.out::println);

        String[] str = new String[6];
        str = new String[]{"1","2","3","4","5","6"};

        String[] str2 = {"1","2","3"};

    }

}
