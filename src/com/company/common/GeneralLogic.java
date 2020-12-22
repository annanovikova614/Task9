package com.company.common;

import java.util.List;

public class GeneralLogic {
    public static List<Integer> process(List<Integer> list){
        int size = list.size();
        for (int i = 0; i < size / 2 ; i++) {
            Integer temp = list.get(i);
            list.set(i,list.get(size-i-1));
            list.set(size-i-1, temp);
        }
        System.out.println(list);
        return list;
    }
}