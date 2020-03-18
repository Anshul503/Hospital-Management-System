
package sanjeevani.pojo;
public class UserPojo {
    private String userid;
    private String password;
    private String userType;
    private String userName;
    private String empId;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    @Override
    public String toString() {
        return "UserPojo{" + "userid=" + userid + ", password=" + password + ", userType=" + userType + ", userName=" + userName + ", empId=" + empId + '}';
    }
    
    
}
