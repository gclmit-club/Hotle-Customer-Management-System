package Test;

import entity.Administrators;
import entity.Customer;
import method.LookUp;
import method.OtherMethod;

import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Administrators a=new Administrators();
        a.login();
        boolean bo=true;
        while (bo) {
            // 主菜单与点单
            System.out.println();
            System.out.println("=======================");
            System.out.println("0.管理员注册;");
            System.out.println("1.登记客户并分配房间;");
            System.out.println("2.显示所有房间;");
            System.out.println("3.显示未入住房间;");
            System.out.println("4.显示已入住房间;");
            System.out.println("5.显示所有客户;");
            System.out.println("6.客户退房;");
            System.out.println("7.根据手机号查询入住记录;");
            System.out.println("8.根据房间ID查询入住记录;");
            System.out.println("9.中途换房;");
            System.out.println("10.续住;");
            System.out.println("11.中途退房;");
            System.out.println("12.退出程序;");
            System.out.println("========================");
            System.out.println();
            System.out.print("请选择菜单：");
            LookUp lu = new LookUp();
            OtherMethod om = new OtherMethod();
            Scanner sc = new Scanner(System.in);
            String choice = sc.next();
                switch (choice) {
                    case "0":
                        a.register();
                        break;
                    case "1":
                        Customer cu=new Customer();
                        cu.register();
                        break;
                    case "2":
                        lu.firstLookUp();
                        break;
                    case "3":
                        lu.secondLookUp();
                        break;
                    case "4":
                        lu.thirdLookUp();
                        break;
                    case "5":
                        lu.forthLookUp();
                        break;
                    case "6":
                        om.remove();
                        break;
                    case "7":
                        lu.fifthLookUp();
                        break;
                    case "8":
                        lu.sixthLookUp();
                        break;
                    case "9":
                        om.replace();
                        break;
                    case "10":
                        om.relet();
                        break;
                    case "11":
                        om.middleRemove();
                        break;
                    case "12":
                        bo=false;
                        break;
                    default:
                        System.out.println("输入的符号不正确");
                        break;
            }
        }
    }
}
