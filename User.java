public class User {
    private int userId;
    private String userName;
    private long phoneNumber;
    private String address;

    public User(int userId, String userName, long phoneNumber, String address) {
        this.userId = userId;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return String.format("User %d: %s | %d | %s", userId, userName, phoneNumber, address);
    }
}
