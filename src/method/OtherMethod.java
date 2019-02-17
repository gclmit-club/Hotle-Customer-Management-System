package method;

import entity.Customer;
import entity.Room;
import entity.RoomLog;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import static method.Reader.customers;
import static method.Reader.roomlogs;
import static method.Reader.rooms;

public class OtherMethod {

    //客户退房

    public void remove() {
        Reader re = new Reader();
        Scanner sc = new Scanner(System.in);
        re.roomReader();                                //读room文件
        A:while (true) {
            System.out.println("请输入房间ID：");
            String RoomId = sc.next();
            if (RoomId == "" || RoomId == null) {
                System.out.println("输入的房间ID错误");
                continue A;
            }
            boolean boo = true;
            if (boo) {
                for (Room room1 : rooms) {
                    if (room1.getRoomId().equals(RoomId) && !(room1.getCustomerIdNumber().equals("null"))) {
                        try {
                            room1.setCustomerIdNumber(null);    //房间文件信息中的客户身份证号变为null
                            BufferedWriter w = new BufferedWriter(new FileWriter("room.txt"));
                            for (Room room : rooms) {
                                w.write(room.toString());    //房间信息循环覆盖写入
                                w.flush();
                                w.newLine();
                            }
                            w.close();
                            System.out.println("退房成功");
                            boo = false;
                            break;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            if (boo) {
                System.out.println("该房间不存在或者无人居住");
                continue A;
            }
            break;
        }
        rooms.clear();
    }


    //中途换房

    public void replace() {
        Reader re = new Reader();
        re.customerReader();
        Scanner sc = new Scanner(System.in);
        boolean boo = true;
        A:
        while (true) {
            if (boo) {
                System.out.println("请输入所要换房的顾客名字：");
                String name = sc.next();
                for (Customer customer : customers) {
                    if (customer.getName().equals(name)) {
                        System.out.println(customer.toString());
                        boo = false;
                        break;
                    }
                }
            }
            if (boo) {
                System.out.println("查无此人");
                continue A;
            }
            break;
        }
        re.roomlogReader();                             //读RoomLog文件
        re.roomReader();                                //读room文件
        System.out.println("请输入客户手机号码：");
        String TelephoneNumber = sc.next();
        for (RoomLog roomlog : roomlogs) {
            if (roomlog.getTelephoneNumber().equals(TelephoneNumber)) {
                System.out.println("请输已入入住天数：");
                roomlog.setDays(sc.nextInt());
                System.out.println("请管理员输入1-7选择顾客所住房间类型：");
                System.out.println("1.普通单人间(601--605)   2.普通双人间(606--610)  3.豪华单人间(701--705)  4.豪华双人间(706--710)  5.贵宾单人间(801--805)   6.贵宾双人间(806--810)  7.总统套间房(901--905) ");
                String choice = sc.next();
                switch (choice) {
                    case "1":
                        roomlog.setPrice(200 * roomlog.getDays());
                        break;
                    case "2":
                        roomlog.setPrice(380 * roomlog.getDays());
                        break;
                    case "3":
                        roomlog.setPrice(400 * roomlog.getDays());
                        break;
                    case "4":
                        roomlog.setPrice(580 * roomlog.getDays());
                        break;
                    case "5":
                        roomlog.setPrice(600 * roomlog.getDays());
                        break;
                    case "6":
                        roomlog.setPrice(780 * roomlog.getDays());
                        break;
                    case "7":
                        roomlog.setPrice(800 * roomlog.getDays());
                        break;
                    default:
                        System.out.println("输入符号错误。");
                        break;
                }
                try {
                    BufferedWriter w = new BufferedWriter(new FileWriter("RoomLog.txt"));
                    for (RoomLog roomLog : roomlogs) {
                        w.write(roomLog.toString());
                        w.flush();
                        w.newLine();
                    }
                    w.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //写入换房后的信息
                        System.out.println("请输入要继续居住的天数：");
                        int days1 = sc.nextInt();
                        roomlog.setDays(days1);
                        System.out.println("请顾客输入1-7挑选房间类型：");
                        System.out.println("1.普通单人间(601--605)   2.普通双人间(606--610)  3.豪华单人间(701--705)  4.豪华双人间(706--710)  5.贵宾单人间(801--805)   6.贵宾双人间(806--810)  7.总统套间房(901--905) ");
                        String choice1 = sc.next();
                        double price1 = 0.0;
                        String roomname = "";
                        switch (choice1) {
                            case "1":
                                price1 = 200 * days1;
                                roomname = "普通单人间";
                                break;
                            case "2":
                                price1 = 380 * days1;
                                roomname = "普通双人间";
                                break;
                            case "3":
                                price1 = 400 * days1;
                                roomname = "豪华单人间";
                                break;
                            case "4":
                                price1 = 580 * days1;
                                roomname = "豪华双人间";
                                break;
                            case "5":
                                price1 = 600 * days1;
                                roomname = "贵宾单人间";
                                break;
                            case "6":
                                price1 = 780 * days1;
                                roomname = "贵宾双人间";
                                break;
                            case "7":
                                price1 = 800 * days1;
                                roomname = "总统套间房";
                                break;
                            default:
                                System.out.println("输入符号错误。");
                                break;
                        }
                        roomlog.setPrice(price1);
                        roomlog.setRoomName(roomname);
                        A:while (true) {
                            System.out.println("请输入所要换的房间ID：");
                            String roomId = sc.next();
                            boolean bo = true;
                            if (bo) {
                                for (Room room : rooms) {
                                    if (room.getRoomId().equals(roomId) && room.getCustomerIdNumber().equals("null")) {
                                        bo = false;
                                        //写入当前日期
                                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
                                        Date now = new Date();
                                        String date = dateFormat.format(now);
                                        roomlog.setDate(date);
                                        try {
                                            BufferedWriter w = new BufferedWriter(new FileWriter("RoomLog.txt", true));

                                            w.write(roomlog.getName() + "-" + roomlog.getSex() + "-" + roomlog.getAge() + "-"
                                                    + roomlog.getTelephoneNumber() + "-" + roomlog.getIdNumber() + "-" + roomId + "-"
                                                    + roomlog.getRoomName() + "-" + roomlog.getDate() + "-" + roomlog.getDays() + "-" + roomlog.getPrice());
                                            w.flush();
                                            w.newLine();

                                            w.close();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }
                            if (bo) {
                                System.out.println("该房间不存在或者已有人居住");
                                continue A;
                            }
                            break;
                        }
            }
        }
            rooms.clear();
            System.out.println("开始退房");
            remove();            //调用退房方法
            //将顾客的身份证号覆盖到新入住的房间
            re.roomReader();
            System.out.println("请输入顾客现居住房间号：");
            String roomId = sc.next();
            for (Room room : rooms) {
                if (room.getRoomId().equals(roomId) && room.getCustomerIdNumber().equals("null")) {
                    System.out.println("---------------");
                    System.out.println("请输入客户的身份证号：");
                    room.setCustomerIdNumber(sc.next());
                    try {
                        BufferedWriter w = new BufferedWriter(new FileWriter("room.txt"));
                        for (Room room1 : rooms) {
                            w.write(room1.toString());
                            w.flush();
                            w.newLine();
                        }
                        w.close();
                        break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            rooms.clear();
            System.out.println("换房成功");
            re.roomReader();            //房间信息发生改变
            rooms.clear();
            customers.clear();
            roomlogs.clear();   //清空集合，避免下次重复读取
    }


    //续住

    public void relet() {
        Reader re = new Reader();
        re.customerReader();                    //读customer文件
        Scanner sc = new Scanner(System.in);
        A:while(true) {
            boolean b=true;
            if(b) {
                System.out.println("请输入所要续住的顾客姓名：");
                String name = sc.next();
                for (Customer customer : customers) {
                    if (customer.getName().equals(name)) {
                        System.out.println(customer.toString());
                        b=false;
                        break ;
                    }
                }
            }if(b){
                System.out.println("查无此人");
                continue A;
            }
            break;
        }
        re.roomlogReader();
        System.out.println("请输入客户电话号码：");
        String TelephoneNumber = sc.next();
        for (RoomLog roomlog : roomlogs) {
            if (roomlog.getTelephoneNumber().equals(TelephoneNumber)) {
                System.out.println("请输入继续居住的天数：");
                roomlog.setDays(sc.nextInt());
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
                Date now = new Date();
                roomlog.setDate(dateFormat.format(now));
                if (roomlog.getRoomName().equals("普通单人间")) {
                    roomlog.setPrice(200 * roomlog.getDays());
                } else if (roomlog.getRoomName().equals("普通双人间")) {
                    roomlog.setPrice(380 * roomlog.getDays());
                } else if (roomlog.getRoomName().equals("豪华单人间")) {
                    roomlog.setPrice(400 * roomlog.getDays());
                } else if (roomlog.getRoomName().equals("豪华双人间")) {
                    roomlog.setPrice(580 * roomlog.getDays());
                } else if (roomlog.getRoomName().equals("贵宾单人间")) {
                    roomlog.setPrice(600 * roomlog.getDays());
                } else if (roomlog.getRoomName().equals("贵宾双人间")) {
                    roomlog.setPrice(780 * roomlog.getDays());
                } else if (roomlog.getRoomName().equals("总统套间房")) {
                    roomlog.setPrice(800 * roomlog.getDays());
                }
                try {
                    BufferedWriter w = new BufferedWriter(new FileWriter("RoomLog.txt", true));// true表示以追加形式写文件
                    w.write(roomlog.getName() + "-" + roomlog.getSex() + "-" + roomlog.getAge() + "-" + TelephoneNumber
                            + "-" + roomlog.getIdNumber() + "-" + roomlog.getRoomId() + "-" + roomlog.getRoomName() + "-" + roomlog.getDate() + "-" + roomlog.getDays()
                            + "-" + roomlog.getPrice());            //续住的信息写入
                    w.flush();
                    w.newLine();
                    w.close();
                    System.out.println("续住成功");
                    break;

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        roomlogs.clear();
        customers.clear();
    }

    //中途退房

    public void middleRemove() {
        Scanner sc = new Scanner(System.in);
        Reader re=new Reader();
        re.customerReader();
        A:while (true) {
            boolean b=true;
            if(b) {
                System.out.println("请输入所要退房人姓名：");
                String name = sc.next();
                for (Customer customer : customers) {
                    if (customer.getName().equals(name)) {
                        System.out.println(customer.toString());
                        b=false;
                    }
                }
                break ;
            }
            if(b){
                System.out.println("查无此人");
                continue A;
            }
            break;
        }
        re.roomlogReader();
        System.out.println("请输入电话号码：");
        String TelephoneNumber = sc.next();
        for (RoomLog roomlog : roomlogs) {
            if (roomlog.getTelephoneNumber().equals(TelephoneNumber)) {
                double price = roomlog.getPrice();   //原本天数应花的钱
                double money = 0.0;                        //实际天数要花的钱
                System.out.println("请输入已住天数：");
                roomlog.setDays(sc.nextInt());
                String RoomName = roomlog.getRoomName();
                if (RoomName.equals("普通单人间")) {
                    money = roomlog.getDays() * 200;
                } else if (RoomName.equals("普通双人间")) {
                    money = roomlog.getDays()  * 380;
                } else if (RoomName.equals("豪华单人间")) {
                    money = roomlog.getDays()  * 400;
                } else if (RoomName.equals("豪华双人间")) {
                    money = roomlog.getDays() * 580;
                } else if (RoomName.equals("贵宾单人间")) {
                    money = roomlog.getDays()  * 600;
                } else if (RoomName.equals("贵宾双人间")) {
                    money = roomlog.getDays()  * 780;
                } else if (RoomName.equals("总统套间房")) {
                    money = roomlog.getDays()  * 800;
                }
                roomlog.setPrice(money);    //实际居住的价钱
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
                Date now = new Date();
                roomlog.setDate(dateFormat.format(now));
                System.out.println("开始退房：");
                remove();
                try {
                    BufferedWriter w = new BufferedWriter(new FileWriter("RoomLog.txt"));
                        for(RoomLog roomLog:roomlogs){
                            w.write(roomLog.toString());
                            w.flush();
                            w.newLine();
                        }
                        w.close();
                }catch (IOException e) {
                    e.printStackTrace();
                }
                double money2 = price - money;        //退款金额
                System.out.println("退款" + money2 + "元");
                break;
            }
        }
        roomlogs.clear();
    }
}