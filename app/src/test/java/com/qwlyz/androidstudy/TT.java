package com.qwlyz.androidstudy;

import java.util.List;

public class TT {

    public static void main(String[] args) {
        System.out.println("111");
    }


    private int reuslt;

    public void addAll(List<Integer> list) {
        int sum = 0;
        for (int s : list) {
            sum += s;
        }
        reuslt = sum;
    }

    public void addAll2(List<Integer> list) {
        for (int s : list) {
            reuslt += s;
        }
    }
}
