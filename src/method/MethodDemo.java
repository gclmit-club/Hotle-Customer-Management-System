package method;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import entity.Room;
import entity.RoomLog;

public class MethodDemo {
	static List<Room> rooms = new ArrayList<Room>();
	static List<RoomLog> roomlogs = new ArrayList<RoomLog>();
	
	//读user文件:房间的信息
	
	public static void extra() {
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
	}
	
	//读data-RoomLog文件:客户入住信息
	
	public static void extra1() {
		try {
			BufferedReader r = new BufferedReader(new FileReader("data-RoomLog.txt"));
			String line;
			for (line = r.readLine(); line != null; line = r.readLine()) {
				String[] t = line.split(":");
				RoomLog roomlog = new RoomLog(t[0], t[1], Integer.parseInt(t[2]), t[3], t[4], t[5], t[6],
						t[7],Integer.parseInt(t[8]), Double.parseDouble(t[9]));
				roomlogs.add(roomlog);
			}
			r.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 客户退房

	public static void thirdshow() {
		extra();			//读room文件
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入房间ID：");
		String RoomId = sc.next();
		if (RoomId == "" || RoomId == null) {
			System.out.println("输入的房间ID错误");
		}
		for(Room room1:rooms) {
			
			if(room1.getRoomId().equals(RoomId)) {
				try {
					room1.setCustomerId(null);	//房间文件信息中的客户身份证号变为null
					BufferedWriter w = new BufferedWriter(new FileWriter("user.txt"));
					for (Room room : rooms) {
						
						w.write(room.toString());	//房间信息循环覆盖写入
						w.flush();
						w.newLine();
						
					}
					w.close();
					System.out.println("退房成功");
					break;
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			
			}
		rooms.clear();
		}
	

	// 中途换房

	public static void Replace() {
		extra1();		//读RoomLog文件
		extra();			//读user文件
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入客户手机号码：");
		String TelephoneNumber = sc.next();
		System.out.println("请输入所要换的房间ID：");
		String RoomId = sc.next();
		for (RoomLog roomlog : roomlogs) {
			
			//如果文件中有输入电话号码
			
			if (roomlog.getTelephoneNumber().equals(TelephoneNumber)) {
				
				System.out.println("请输入已经居住的天数：");
				int days = sc.nextInt();
				double money = 0.0;
				if (roomlog.getRoomName().equals("普通单人间")) {
					money = days * 100;
				} else if (roomlog.getRoomName().equals("普通双人间")) {
					money = days * 170;
				} else if (roomlog.getRoomName().equals("豪华单人间")) {
					money = days * 200;
				} else if (roomlog.getRoomName().equals("豪华双人间")) {
					money = days * 370;
				} else if (roomlog.getRoomName().equals("贵宾单人间")) {
					money = days * 300;
				} else if (roomlog.getRoomName().equals("贵宾双人间")) {
					money = days * 570;
				} else if (roomlog.getRoomName().equals("总统套房")) {
					money = days * 700;
				}
				roomlog.setDays(days);		//已经住的天数
				roomlog.setMoney(money);	//换房之前应该付的价钱
				try {
					BufferedWriter w = new BufferedWriter(new FileWriter("data-RoomLog.txt"));
					for (RoomLog roomlog2 : roomlogs) {
						w.write(roomlog2.toString());	//重新遍历循环覆盖
						w.flush();
						w.newLine();
					}
					w.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				//写入换房后的信息
				boolean boo=true;
				if(boo) {
				for(Room room:rooms) {
					
				if (room.getRoomId().equals(RoomId)&&room.getCustomerId().equals("null")) {
					System.out.println("请输入要换的房间名：");
					String RoomName = sc.next();
					System.out.println("请输入换房日期：");
					String date=sc.next();
					System.out.println("请输入要住的天数：");
					int days1 = sc.nextInt();
					double money2 = 0.0;
					if (RoomName.equals("普通单人间")) {
						money2 = days1 * 100;
					} else if (RoomName.equals("普通双人间")) {
						money2 = days1 * 170;
					} else if (RoomName.equals("豪华单人间")) {
						money2 = days1 * 200;
					} else if (RoomName.equals("豪华双人间")) {
						money2 = days1 * 370;
					} else if (RoomName.equals("贵宾单人间")) {
						money2 = days1 * 300;
					} else if (RoomName.equals("贵宾双人间")) {
						money2 = days1 * 570;
					} else if (RoomName.equals("总统套房")) {
						money2 = days1 * 700;
					}
					try {
						BufferedWriter w = new BufferedWriter(new FileWriter("data-RoomLog.txt", true));

						w.write(roomlog.getName() + ":" + roomlog.getSex() + ":" + roomlog.getAge() + ":"
								+ TelephoneNumber + ":" + roomlog.getCustomerId() + ":" + RoomName + ":"
								+ RoomId + ":" +date+":"+ days1 + ":" + money2);
						w.flush();
						w.newLine();

						w.close();
						
						
					} catch (IOException e) {
						e.printStackTrace();
					}
					boo=false;
					break;
					} 
				}if(boo){
					System.out.println("该房间已满");
					break;
				}
				break;
				}
				break;
			}

		}
		thirdshow();			//调用退房方法
		extra();				//房间信息发生改变，再读一遍user文件
		
		//将顾客的身份证号覆盖到新入住的房间
		
			System.out.println("请输入客户的身份证号：");
			String CustomerId=sc.next();
			for(Room room:rooms) {
			if(room.getRoomId().equals(RoomId)&&room.getCustomerId().equals("null")) {
				room.setCustomerId(CustomerId);
				try {
					BufferedWriter w = new BufferedWriter(new FileWriter("user.txt"));
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
		extra();			//房间信息发生改变
		System.out.println("换房成功");
		rooms.clear();
		roomlogs.clear();   //清空集合，避免下次重复读取
	}

	// 续住

	public static void relet() {
		extra1();			//方法开始前先读一遍文件
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入客户电话号码：");
		String TelephoneNumber = sc.next();
		for(RoomLog roomlog1:roomlogs) {
		if (roomlog1.getTelephoneNumber().equals(TelephoneNumber)) {
		for (RoomLog roomlog : roomlogs) {
				System.out.println("请输入继续居住的天数：");
				int days = sc.nextInt();
				System.out.println("请输入续住时日期：");
				String date=sc.next();
				String RoomName = roomlog.getRoomName();
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
					w.write(roomlog1.getName() + ":" + roomlog1.getSex() + ":" + roomlog1.getAge() + ":" + TelephoneNumber
							+ ":" + roomlog1.getCustomerId() + ":" + RoomName + ":" + roomlog1.getRoomId() + ":" + date+":"+days
							+ ":" + money);     //续住的信息写入
					w.flush();
					w.newLine();
					w.close();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println("续住成功");
				break;
			}
		break;
		}
	
	}
		roomlogs.clear();
	}

	// 中途退房

	public static void remove() {
		extra1();		//读RoomLog文件
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入电话号码：");
		String TelephoneNumber = sc.next();
		for(RoomLog roomlog:roomlogs) {
			if(roomlog.getTelephoneNumber().equals(TelephoneNumber)) {
				double money1 = roomlog.getMoney();	//原本天数应花的钱
				double money = 0.0;						//实际天数要花的钱
				System.out.println("请输入已住天数：");
				int days1 = sc.nextInt();
				thirdshow();		
				String RoomName = roomlog.getRoomName();
				if (RoomName.equals("普通单人间")) {
					money = days1 * 100;
				} else if (RoomName.equals("普通双人间")) {
					money = days1 * 170;
				} else if (RoomName.equals("豪华单人间")) {
					money = days1 * 200;
				} else if (RoomName.equals("豪华双人间")) {
					money = days1 * 370;
				} else if (RoomName.equals("贵宾单人间")) {
					money = days1 * 300;
				} else if (RoomName.equals("贵宾双人间")) {
					money = days1 * 570;
				} else if (RoomName.equals("总统套房")) {
					money = days1 * 700;
				}
				roomlog.setDays(days1);		//实际居住的天数
				roomlog.setMoney(money);	//实际居住的价钱
				try {
					BufferedWriter w = new BufferedWriter(new FileWriter("data-RoomLog.txt"));
					for (RoomLog roomlog1 : roomlogs) {
						w.write(roomlog1.toString());
						w.flush();
						w.newLine();
					}
						w.close();
						
					} catch (IOException e) {
						e.printStackTrace();
					}
				double money2 = money1 - money;		//退款金额
				System.out.println("退款" + money2 + "元");
				break;
					}
			}
		roomlogs.clear();
	}
	
}