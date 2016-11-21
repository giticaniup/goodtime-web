package com.goodtime.user.test;

/**
 * Created by zhongcy on 2016/10/24.
 */
public class MyTest {
    public static void main(String[] args) {
        String chinese = "中12w问";
        char[] array = chinese.toCharArray();
        for(char c:array){
            System.out.println(c);
            System.out.println(c>128);
        }
        for(int i=0;i<129;i++){
            char s = (char) i;
            System.out.println(s);
        }
    }
}
