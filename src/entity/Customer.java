package entity;


import method.Reader;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import static method.Reader.rooms;

public class Customer extends Person implements Register{

    public Customer(){}

    public Customer(String name, String sex, int age, String telephoneNumber, String idNumber) {
        this.setName(name);             //顾客姓名
        this.setSex(sex);               //顾客性别
        this.setAge(age);               //顾客年龄
        this.setTelephoneNumber(telephoneNumber);       //顾客电话号码
        this.setIdNumber(idNumber);       //顾客身份证号
    }
    public String toString(){
        return this.getName()+"-"+this.getSex()+"-"+this.getAge()+"-"+this.getTelephoneNumber()+"-"+this.getIdNumber();
    }
    public void register(){
    RoomLog rl=new RoomLog();
        System.out.println("客户信息开始登记。");
    A:while(true) {
        System.out.println("请输入顾客姓名：");
        Scanner sc = new Scanner(System.in);
        rl.setName(sc.next());
        if (rl.getName().equals("") || rl.getName() == null) {
            System.out.println("顾客姓名输入错误。");
            continue A;
        } else if (rl.getName().contains("-")) {
            System.out.println("顾客姓名输入错误。");
            continue A;
        }
        System.out.println("请输入顾客性别：");
        rl.setSex(sc.next());
        if (rl.getSex().equals("") || rl.getSex() == null) {
            System.out.println("顾客性别输入错误。");
            continue A;
        } else if ((!(rl.getSex().equals("男"))) && (!(rl.getSex().equals("女")))) {          //限定性别必须为男女
            System.out.println("输入的性别有误");
            continue A;
        } else if (rl.getSex().contains("-")) {
            System.out.println("顾客性别输入错误。");
            continue A;
        }
        System.out.println("请输入顾客年龄：");
        rl.setAge(sc.nextInt());
        if (rl.getAge() <= 0 || rl.getAge() >= 200) {
            System.out.println("顾客年龄输入错误。");
            continue A;
        }
        System.out.println("请输入顾客手机号码：");
        rl.setTelephoneNumber(sc.next());
        int count = rl.getTelephoneNumber().length();
        if (rl.getTelephoneNumber().equals("") || rl.getTelephoneNumber() == null) {
            System.out.println("手机号码输入错误。");
            continue A;
        } else if (count != 11) {                   //限定手机号码必须为11位
            System.out.println("手机号码输入错误。");
            continue A;
        } else if (rl.getTelephoneNumber().contains("-")) {
            System.out.println("手机号码输入错误。");
            continue A;
        }
        System.out.println("请输入顾客的身份证号：");
        rl.setIdNumber(sc.next());
        int count1 = rl.getIdNumber().length();
        if (rl.getIdNumber().equals("") || rl.getIdNumber() == null) {
            System.out.println("顾客的身份证号输入错误。");
            continue A;
        } else if (count1 != 18) {                      //限定身份证号长度为18位
            System.out.println("顾客的身份证号输入错误。");
            continue A;
        } else if (rl.getIdNumber().contains("-")) {
            System.out.println("顾客的身份证号输入错误。");
            continue A;
        }
        System.out.println("顾客信息输入完毕");
        System.out.println("顾客信息开始保存。");
        try {
            BufferedWriter w = new BufferedWriter(new FileWriter("customer.txt", true));// true表示以追加形式写文件
            w.write(rl.getName() + "-" + rl.getSex() + "-" + rl.getAge() + "-" + rl.getTelephoneNumber() + "-" + rl.getIdNumber());
            w.flush();
            w.newLine();
            w.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("顾客信息保存完毕");
        System.out.println("客户信息开始读取");
        Reader re=new Reader();
        re.customerReader();
        System.out.println("客户信息读取完毕");

        //开始输入房间信息
        re.roomReader();
        System.out.println("开始输入房间信息");
        System.out.println("请输入入住天数：");
        rl.setDays(sc.nextInt());
        System.out.println("请顾客输入1-7挑选房间：");
        System.out.println("1.普通单人间(601--605)   2.普通双人间(606--610)  3.豪华单人间(701--705)  4.豪华双人间(706--710)  5.贵宾单人间(801--805)   6.贵宾双人间(806--810)  7.总统套房(901--905) ");
        String choice=sc.next();
        switch(choice){
            case "1":
                rl.setPrice(200.00*rl.getDays());
                rl.setRoomName("普通单人间");
                break ;
            case "2":
                rl.setPrice(380.00*rl.getDays());
                rl.setRoomName("普通双人间");
                break ;
            case "3":
                rl.setPrice(400.00*rl.getDays());
                rl.setRoomName("豪华单人间");
                break ;
            case "4":
                rl.setPrice(580.00*rl.getDays());
                rl.setRoomName("豪华双人间");
                break ;
            case "5":
                rl.setPrice(600.00*rl.getDays());
                rl.setRoomName("贵宾单人间");
                break ;
            case "6":
                rl.setPrice(780.00*rl.getDays());
                rl.setRoomName("贵宾双人间");
                break ;
            case "7":
                rl.setPrice(800.00*rl.getDays());
                rl.setRoomName("总统套间房");
                break ;
            default:
                System.out.println("输入符号错误。");
                break ;
        }
        B:while (true) {
            System.out.println("请输入房间号：");
            rl.setRoomId(sc.next());
            if (rl.getRoomId().equals("") || rl.getRoomId() == null) {
                System.out.println("房间号输入错误。");
            }
            boolean boo = true;
            if (boo) {
                for (Room room : rooms) {
                    if (room.getRoomId().equals(rl.getRoomId()) && room.getCustomerIdNumber().equals("null")) {         //如果有输入的房间号并且对应的身份证号为null
                        room.setCustomerIdNumber(rl.getIdNumber());
                        try {
                            BufferedWriter w = new BufferedWriter(new FileWriter("room.txt"));
                            for (Room room1 : rooms) {
                                w.write(room1.toString());          //循环覆盖写入
                                w.flush();
                                w.newLine();
                            }
                            w.close();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        boo = false;
                        break;
                    }
                }
            }
            if (boo) {
                System.out.println("该房间已满或有人居住");
                continue B;
            }
            break ;
        }
        rooms.clear();
        re.roomReader();
        rooms.clear();

        //入住记录开始输入

        //写入当前日期
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
        Date now = new Date();
        rl.setDate(dateFormat.format(now));

        try {
            BufferedWriter w = new BufferedWriter(new FileWriter("RoomLog.txt", true));// true表示以追加形式写文件
            w.write(rl.getName() + "-" + rl.getSex() + "-" + rl.getAge() + "-" + rl.getTelephoneNumber()+ "-" + rl.getIdNumber() + "-" + rl.getRoomId()
                    + "-" + rl.getRoomName() + "-" + rl.getDate() + "-" + rl.getDays() + "-" + rl.getPrice());
            w.flush();
            w.newLine();
            w.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("开始读取入住信息");
        re.roomlogReader();
        System.out.println("入住信息读取完毕");
        break ;
    }
}

}


