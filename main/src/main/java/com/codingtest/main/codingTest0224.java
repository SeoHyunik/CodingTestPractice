package com.codingtest.main;

import java.util.ArrayList;
import java.util.Collections;

class Solution2 {

/*  둘레가 가장 큰 삼각형을 구하려고 합니다.
    N개의 정수로 만들 수 있는 
    둘레가 가장 큰 삼각형의 둘레를 구하는 함수를 완성해주세요.*/
    
    public static void main(String[] args){
        int[] lengths = {3,4,5,2};
        System.out.println(triangle(lengths));
    }

    public static int triangle(int[] lengths) {
        int sumLength = 0;
        ArrayList<Integer> sumLengths = new ArrayList<>();
        for (int i = 0; i < lengths.length-2; i++) {
            if (lengths[i]+lengths[i+1] > lengths[i+2] && lengths[i]+lengths[i+2] > lengths[i+1] && lengths[i] < lengths[i+1]+lengths[i+2]) {
                sumLengths.add(lengths[i]+lengths[i+1]+lengths[i+2]);
            }
        }//end for
        System.out.println(sumLengths);
        sumLength  = Collections.max(sumLengths);

        return sumLength;
    }

}