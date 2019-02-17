package method;


import entity.Room;
import entity.RoomLog;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import static method.Reader.customers;
import static method.Reader.roomlogs;
import static method.Reader.rooms;

public class LookUp{

    //查询所有房间

    public void firstLookUp() {
        System.out.println("房间号-房间名");
        System.out.println("------------------");
        try {
            BufferedReader r = new BufferedReader(new FileReader("room.txt"));      //读取room.txt文件
            String str;
            for (str = r.readLine(); str != null; str = r.readLine()) {
                char[] ch = str.toCharArray();
                for (int i = 9; i < str.length(); i++) {            //将身份证号部分隐藏
                    ch[i] = ' ';
                }
                for (int i = 0; i < str.length(); i++) {
                    System.out.print(ch[i]);
                }
                System.out.println();
            }

        }catch(IOException e){
            e.printStackTrace();
        }
        rooms.clear();
    }


    //查询未入住房间

    public void secondLookUp() {
        Reader re=new Reader();
        re.roomReader();
        System.out.println("房间号-房间名");
        System.out.println("-------------------");
        for(Room room:rooms) {
            if(room.getCustomerIdNumber().equals("null")) {
                String str=room.toString();
                char[] ch=str.toCharArray();
                for(int i=9;i<str.length();i++){                //将身份证号部分隐藏
                    ch[i]=' ';
                }
                for(int i=0;i<str.length();i++){
                    System.out.print(ch[i]);
                }
                System.out.println();
            }
        }
        rooms.clear();
    }


    //查询已入住房间

    public void thirdLookUp() {
        Reader re=new Reader();
        re.roomReader();        //读room文件
        System.out.println("房间号-房间名");
        System.out.println("-------------------");
        for(Room room:rooms) {
            if(!(room.getCustomerIdNumber().equals("null"))) {
                String str=room.toString();
                char[] ch=str.toCharArray();
                for(int i=9;i<str.length();i++){                //将身份证号部分隐藏
                    ch[i]=' ';
                }
                for(int i=0;i<str.length();i++){
                    System.out.print(ch[i]);
                }
                System.out.println();
            }
        }
        rooms.clear();	//读完之后清空，避免调用其它方法时重复读取
    }


    //查询所有顾客

    public  void forthLookUp() {
        Reader re=new Reader();
        re.customerReader();        //读customer文件
        System.out.println("姓名-性别-年龄-手机号码--------身份证号");
        try {
            BufferedReader r = new BufferedReader(new FileReader("customer.txt"));
            String str;
            for (str = r.readLine(); str != null; str = r.readLine()) {
                char[] ch = str.toCharArray();
                for (int i = 26; i>=26&&i<=33; i++) {               //将身份证号中间八位变为*
                    ch[i] = '*';
                }
                for (int i = 0; i < str.length(); i++) {
                    System.out.print(ch[i]);
                }
                System.out.println();
            }
        }
            catch(IOException e){
                e.printStackTrace();
        }
        customers.clear();	//读完之后清空，避免调用其它方法时重复读取
    }


    //根据手机号码查询入住记录

    public void fifthLookUp() {
        Reader re=new Reader();
        re.roomlogReader();     	//读RoomLog文件
        System.out.println("请输入手机号：");
        Scanner sc=new Scanner(System.in);
        String TelephoneNumber=sc.next();
        if(TelephoneNumber==""||TelephoneNumber==null) {
            System.out.println("手机号输入错误");
        }
        int count=TelephoneNumber.length();
        if(count!=11) {
            System.out.println("手机号输入错误");
        }
        System.out.println("姓名-性别-年龄-手机号码-------身份证号-----房间号--房间名---------日期------------天数-金额");
        for(RoomLog roomlog:roomlogs) {
            if(roomlog.getTelephoneNumber().equals(TelephoneNumber)) {
                String str=roomlog.toString();
                char[] ch=str.toCharArray();
                for(int i=26;i>=26&&i<=33;i++){                     //将身份证号中间八位变为*
                    ch[i]='*';
                }
                for(int i=0;i<str.length();i++){
                    System.out.print(ch[i]);
                }
                System.out.println();
            }
        }
        roomlogs.clear();	//读完之后清空，避免调用其它方法时重复读取
    }


    //根据房间号查询入住记录

    public void sixthLookUp() {
        Reader re=new Reader();
        re.roomlogReader();			//读RoomLog文件
        System.out.println("请输入房间号");
        Scanner sc=new Scanner(System.in);
        String RoomId=sc.next();
        System.out.println("姓名-性别-年龄-手机号码-------身份证号-----房间号--房间名---------日期------------天数-金额");
        for(RoomLog roomlog:roomlogs) {
            if(roomlog.getRoomId().equals(RoomId)) {
                String str=roomlog.toString();
                char[] ch=str.toCharArray();
                for(int i=26;i>=26&&i<=33;i++){                     //将身份证号中间八位变为*
                    ch[i]='*';
                }
                for(int i=0;i<str.length();i++){
                    System.out.print(ch[i]);
                }
                System.out.println();
            }
        }
        roomlogs.clear();	//读完之后清空，避免调用其它方法时重复读取
    }
}
