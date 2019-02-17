package method;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.text.html.HTMLDocument.Iterator;

import entity.Customer;
import entity.Room;
import entity.RoomLog;

public class RegisterDemo {

	static ArrayList<Customer> customers = new ArrayList<Customer>();
	static List<Room> rooms = new ArrayList<Room>();
	static List<RoomLog> roomlogs = new ArrayList<RoomLog>();

	// 管理员登录
	public static void UsersRegister() {
		String AccountNumber = "WLDJD";
		String password = "wldjd";
		Scanner sc = new Scanner(System.in);
		A: while (true) {
			System.out.println("请输入账号");
			String input = sc.next();
			System.out.println("请输入密码");
			String inputpassword = sc.next();
			if (input.equals(AccountNumber) && inputpassword.equals(password)) {
				System.out.println("登录成功");
				break;
			} else {
				System.out.println("账号或密码错误，登录失败");
				continue A;
			}
		}
	}

	// 1、客户的登记

	public static void Register() {
		Scanner in = new Scanner(System.in);
		System.out.println("客户开始登记");

		// 输入姓名、性别、年龄、手机号、身份证号、所住天数登记

		A: while (true) {
			System.out.println("请输入姓名：");
			String name = in.next();
			if (name == "" || name == null) {
				System.out.println("输入的姓名有误");
				continue A;
			}
			System.out.println("请输入性别：");
			String sex = in.next();
			if (sex == "" || sex == null) {
				System.out.println("输入的性别有误");
				continue A;
			} else if (!(sex.equals("男")) && !(sex.equals("女"))) {
				System.out.println("输入的性别有误");
				continue A;
			}
			System.out.println("请输入年龄：");
			int age = in.nextInt();
			if (age < 18) {
				System.out.println("该用户未成年无法登记");
				continue A;
			} else if (age > 200) {
				System.out.println("该用户年龄输入错误");
				continue A;
			}
			System.out.println("请输入手机号：");
			String TelephoneNumber = in.next();
			int count = TelephoneNumber.length();
			if (TelephoneNumber == "" || TelephoneNumber == null || count != 11) {
				System.out.println("电话号码输入错误");
				continue A;
			}
			System.out.println("请输入身份证号：");
			String CustomerId = in.next();
			int count1 = CustomerId.length();
			if (CustomerId == "" || CustomerId == null || count1 != 18) {
				System.out.println("身份证号输入错误");
				continue A;
			}
			System.out.println("客户信息开始保存");
			try {
				BufferedWriter w = new BufferedWriter(new FileWriter("data-customer.txt", true));// true表示以追加形式写文件
				w.write(name + ":" + sex + ":" + age + ":" + TelephoneNumber + ":" + CustomerId);
				w.flush();
				w.newLine();
				w.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("客户信息保存完毕");
			System.out.println("客户信息开始读取");
			try {
				BufferedReader r = new BufferedReader(new FileReader("data-customer.txt"));
				String line;
				for (line = r.readLine(); line != null; line = r.readLine()) {
					String[] t = line.split(":");
					Customer customer = new Customer(t[0], t[1], Integer.parseInt(t[2]), t[3], t[4]);
					customers.add(customer);
				}
				r.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("客户信息读取完毕");

			// 登记房间

			try {
				BufferedReader r = new BufferedReader(new FileReader("user.txt"));
				String line;
				for (line = r.readLine(); line != null; line = r.readLine()) {
					String[] t = line.split(":");
					Room room = new Room(t[0], t[1], t[2]);
					rooms.add(room);
				}
				r.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			B: while (true) {
				System.out.println("请输入房间ID");
				String RoomId = in.next();
				if (RoomId == "" || RoomId == null) {
					System.out.println("输入错误");
					continue B;
				}
				boolean c = true;
				for (Room room : rooms) {
					if (room.getRoomId().equals(RoomId) && room.getCustomerId().equals("null")) {
						room.setCustomerId(CustomerId);
						try {
							BufferedWriter w = new BufferedWriter(new FileWriter("user.txt"));
							for (Room room1 : rooms) {
								w.write(room1.toString());
								w.flush();
								w.newLine();
							}
							w.close();

						} catch (IOException e) {
							e.printStackTrace();
						}
						c = false;
						break;
					}

				}
				if(c) {
					System.out.println("房间已满");
					continue B;
				}
				
				try {
					BufferedReader r = new BufferedReader(new FileReader("user.txt"));
					String line;
					for (line = r.readLine(); line != null; line = r.readLine()) {
						String[] t = line.split(":");
						Room room = new Room(t[0], t[1], t[2]);
						rooms.add(room);
					}
					r.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				rooms.clear();

				// 登记入住记录
				System.out.println("请输入入住日期：");
				String date = in.next();
				System.out.println("请输入入住天数：");
				int days = in.nextInt();
				System.out.println("请输入房间名：");
				String RoomName = in.next();
				System.out.println("开始保存入住信息");
				double money = 0.0;
				if (RoomName.equals("普通单人间")) {
					money = days * 100;
				} else if (RoomName.equals("普通双人间")) {
					money = days * 170;
				} else if (RoomName.equals("豪华单人间")) {
					money = days * 200;
				} else if (RoomName.equals("豪华双人间")) {
					money = days * 370;
				} else if (RoomName.equals("贵宾单人间")) {
					money = days * 300;
				} else if (RoomName.equals("贵宾双人间")) {
					money = days * 570;
				} else if (RoomName.equals("总统套房")) {
					money = days * 700;
				}
				try {
					BufferedWriter w = new BufferedWriter(new FileWriter("data-RoomLog.txt", true));// true表示以追加形式写文件
					w.write(name + ":" + sex + ":" + age + ":" + TelephoneNumber + ":" + CustomerId + ":" + RoomName
							+ ":" + RoomId + ":" + date + ":" + days + ":" + money);
					w.flush();
					w.newLine();
					w.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println("开始读取入住信息");
				try {
					BufferedReader r = new BufferedReader(new FileReader("data-RoomLog.txt"));
					String line;
					for (line = r.readLine(); line != null; line = r.readLine()) {
						String[] t = line.split(":");
						RoomLog roomlog = new RoomLog(t[0], t[1], Integer.parseInt(t[2]), t[3], t[4], t[5], t[6], t[7],
								Integer.parseInt(t[8]), Double.parseDouble(t[9]));
						roomlogs.add(roomlog);
					}
					r.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("入住信息读取完毕");
				break;
			}
			break;
		}

	}

}