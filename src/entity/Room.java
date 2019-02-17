package entity;

public class Room{

    private String roomId;                      //房间号
    private String roomName;                    //房间名
    private String customerIdNumber;            //顾客身份证号

    public String getRoomId() {
        return roomId;
    }

    public void setCustomerIdNumber(String customerIdNumber) {
        this.customerIdNumber = customerIdNumber;
    }

    public String getCustomerIdNumber() {
        return customerIdNumber;
    }

    public String toString() {
        return roomId+"-"+roomName+"-"+customerIdNumber;
    }

    public Room(String roomId,String roomName,String customerIdNumber){
        this.roomId=roomId;
        this.roomName=roomName;
        this.customerIdNumber=customerIdNumber;
    }
}
