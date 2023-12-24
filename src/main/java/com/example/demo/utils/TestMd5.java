package com.example.demo.utils;

import cn.hutool.crypto.SecureUtil;

import java.io.File;

public class TestMd5 {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\Zqunnnnnn\\Desktop\\vue\\files\\640ccbaeddf14d4e80945579e2adf739.jpg");
        File file1 = new File("C:\\Users\\Zqunnnnnn\\Desktop\\boxx\\wallpaper\\60e3baaee0bf51625537198.jpg");
        String md5 = SecureUtil.md5(file);
        System.out.println("MD5值：" + md5);
        String md51 = SecureUtil.md5(file1);
        System.out.println("MD5值：" + md51);
        System.out.println(md5.equals(md51));
    }
}
