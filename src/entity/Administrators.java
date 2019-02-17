package entity;

import method.Reader;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static method.Reader.administrators;

public class Administrators extends Person implements Register{
    public Administrators(){}

    public Administrators(String name,String sex,int age,String telephoneNumber,String idNumber){
        this.setName(name);         //管理员姓名
        this.setSex(sex);           //管理员性别
        this.setAge(age);           //管理员年龄
        this.setTelephoneNumber(telephoneNumber);       //管理员手机号码
        this.setIdNumber(idNumber);         //管理员身份证号
    }

    //管理员信息登记

    public void register(){
        Administrators ad=new Administrators();
        System.out.println("管理员信息开始输入：");
        A:while(true) {
            System.out.println("请输入管理员姓名：");
            Scanner sc = new Scanner(System.in);
            ad.setName(sc.next());              //写入输入的管理员姓名
            if (ad.getName().equals("")||ad.getName() == null) {            //如果输入为空，则输出提示并返回
                System.out.println("管理员姓名输入错误。");
                continue A;
            } else if (ad.getName().contains("-")) {            //如果输入的字符串含-
                System.out.println("管理员姓名输入错误。");
            }
            System.out.println("请输入管理员性别：");
            ad.setSex(sc.next());               //写入输入的管理员性别
            if (ad.getSex().equals("")||ad.getSex() == null) {              //如果输入为空，则输出提示并返回
                System.out.println("管理员性别输入错误。");
                continue A;
            }else if ((!(ad.getSex().equals("男")))&&(!(ad.getSex().equals("女")))) {         //如果输入的不是男或女
                System.out.println("输入的性别有误");
                continue A;
            }else if (ad.getSex().contains("-")) {              //如果输入的字符串含-
                System.out.println("管理员性别输入错误。");
                continue A;
            }
            System.out.println("请输入管理员年龄：");
            ad.setAge(sc.nextInt());
            if (ad.getAge()<=0 || ad.getAge() >= 200) {
                System.out.println("管理员年龄输入错误。");
                continue A;
            }
            System.out.println("请输入管理员手机号码：");
            ad.setTelephoneNumber(sc.next());
            int count = ad.getTelephoneNumber().length();
            if (ad.getTelephoneNumber().equals("") || ad.getTelephoneNumber() == null) {        //如果输入为空，则输出提示并返回
                System.out.println("管理员手机号码输入错误。");
                continue A;
            } else if (count != 11) {                   //限定手机号码长度为11位
                System.out.println("管理员手机号码输入错误。");
                continue A;
            } else if (ad.getTelephoneNumber().contains("-")) {
                System.out.println("管理员手机号码输入错误。");
                continue A;
            }
            System.out.println("请输入管理员的身份证号：");
            ad.setIdNumber(sc.next());
            int count1 = ad.getIdNumber().length();
            if (ad.getIdNumber().equals("") || ad.getIdNumber() == null) {
                System.out.println("管理员的身份证号输入错误。");
                continue A;
            } else if (count1 != 18) {                 //限定身份证号长度为18位
                System.out.println("管理员的身份证号输入错误。");
                continue A;
            } else if (ad.getIdNumber().contains("-")) {
                System.out.println("管理员的身份证号输入错误。");
                continue A;
            }
            System.out.println("管理员信息输入完毕");
            System.out.println("管理员信息开始保存。");
            try {
                BufferedWriter w = new BufferedWriter(new FileWriter("administrators.txt", true));// true表示以追加形式写文件
                w.write(ad.getName() + "-" + ad.getSex() + "-" + ad.getAge() + "-" + ad.getTelephoneNumber() + "-" + ad.getIdNumber());
                w.flush();
                w.newLine();
                w.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("管理员信息保存完毕");
            System.out.println("管理员信息开始读取");
            Reader re=new Reader();
            re.administratorsReader();
            System.out.println("管理员信息读取完毕");
            break;
        }
    }

    public void login(){
        Reader re=new Reader();
        re.administratorsReader();
        Scanner sc=new Scanner(System.in);
        A:while(true) {
            System.out.println("请输入你的名字:");
            String name = sc.next();
            for (Administrators administrator : administrators) {
                if (administrator.getName().equals(name)) {
                    System.out.println("请输入登录密码:");
                    String password = sc.next();
                    if (password.equals("123456")) {
                        System.out.println("登陆成功");
                        break ;
                    } else {
                        System.out.println("密码输入错误");
                        continue A;
                    }
                } else {
                    System.out.println("您不为该酒店管理员");
                    continue A;
                }
            }
            break;
        }
    }
}
