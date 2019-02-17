package method;

import entity.Administrators;
import entity.Customer;
import entity.Room;
import entity.RoomLog;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Reader {

    public static ArrayList<Customer> customers = new ArrayList<>();
    public static ArrayList<Administrators> administrators = new ArrayList<>();
    public static ArrayList<Room> rooms = new ArrayList<>();
    public static ArrayList<RoomLog> roomlogs=new ArrayList<>();

    //读customer文件

    public void customerReader(){
        try {
            BufferedReader r = new BufferedReader(new FileReader("customer.txt"));
            String line;
            for (line = r.readLine(); line != null; line = r.readLine()) {
                String[] t = line.split("-");
                Customer customer = new Customer(t[0], t[1], Integer.parseInt(t[2]), t[3], t[4]);
                customers.add(customer);
            }
            r.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //读administrators文件

    public void administratorsReader(){
        try {
            BufferedReader r = new BufferedReader(new FileReader("administrators.txt"));
            String line;
            for (line = r.readLine(); line != null; line = r.readLine()) {
                String[] t = line.split("-");
                Administrators administratorss = new Administrators(t[0], t[1], Integer.parseInt(t[2]), t[3], t[4]);
                administrators.add(administratorss);
            }
            r.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //读room文件

    public void roomReader(){
        try {
            BufferedReader r = new BufferedReader(new FileReader("room.txt"));
            String line;
            for (line = r.readLine(); line != null; line = r.readLine()) {
                String[] t = line.split("-");
                Room room = new Room(t[0], t[1], t[2]);
                rooms.add(room);
            }
            r.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //读RoomLog文件

    public void roomlogReader(){
        try {
            BufferedReader r = new BufferedReader(new FileReader("RoomLog.txt"));
            String line;
            for (line = r.readLine(); line != null; line = r.readLine()) {
                String[] t = line.split("-");
                RoomLog roomlog = new RoomLog(t[0], t[1], Integer.parseInt(t[2]), t[3], t[4], t[5], t[6], t[7],
                        Integer.parseInt(t[8]), Double.parseDouble(t[9]));
                roomlogs.add(roomlog);
            }
            r.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
