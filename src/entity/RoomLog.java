package entity;

public class RoomLog {

    private String name;                        //顾客姓名
    private String sex;                         //顾客性别
    private int age;                            //顾客年龄
    private String telephoneNumber;             //顾客电话号码
    private String idNumber;                    //顾客身份证号
    private String roomId;                      //房间号
    private String roomName;                    //房间名
    private String date;                        //日期
    private int days;                           //顾客所住天数
    private double price;                       //顾客应付金额

    public RoomLog(String name, String sex, int age, String telephoneNumber, String idNumber, String roomId,
                   String roomName, String date, int days, double price) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.telephoneNumber = telephoneNumber;
        this.idNumber = idNumber;
        this.roomId = roomId;
        this.roomName = roomName;
        this.date = date;
        this.days = days;
        this.price = price;
    }

    public RoomLog(){}

    public String toString() {
        return name+"-"+sex+"-"+age+"-"+telephoneNumber+"-"+idNumber+"-"+roomId+"-"+roomName+"-"+date+"-"+days+"-"+price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSex(){
        return sex;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
