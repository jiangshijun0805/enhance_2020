package com.xiaojiang.jdbc.utils;

import java.util.Scanner;

/**
 * 测试main函数加载参数
 */
public class InputService {
    public static void main(String[] args) {
        //通过scanner对象来读取键盘输入的数据
        Scanner scanner = new Scanner(System.in);
        System.out.println("********************************** 提示信息 *********************************************");
        System.out.println("********************** 本程序主要用来实现简单的计算器操作  *********************************");
        System.out.println("**********每行输入包含三部分：numberOne numberTwo operation,之间以空格隔开******************");
        System.out.println("***********************当结束操作，请输入字符 exit ****************************************");
        while(true){
           try{
               String line = scanner.nextLine();
               if("exit".equalsIgnoreCase(line)){
                   scanner.close();
                   break;
               }
               String[] entities = line.split(" ");
               System.out.println("结果："+entities[0].trim() +" "+entities[1].trim()+" "+entities[2].trim());
           }catch (Exception e){
               System.out.println("输入的有错误，请重新输入：");
           }
        }

        System.out.println("程序结束");
    }
}
