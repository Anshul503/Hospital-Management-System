
package sanjeevani.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import sanjeevani.dbutil.DBConnection;
import sanjeevani.pojo.EmpPojo;
import sanjeevani.pojo.UserPojo;

public class ReceptionistDao {
     private static PreparedStatement ps,ps1,ps2;
     private static Statement st,st1;
     public static boolean addReceptionist(UserPojo user)throws SQLException
     {
         ps=DBConnection.getConnection().prepareStatement("insert into users values(?,?,?,?,?,'Y')");
         ps.setString(1,  user.getUserid());
         ps.setString(2,  user.getUserName());
         ps.setString(3,  user.getEmpId());
         ps.setString(4,  user.getPassword());
         ps.setString(5,  user.getUserType());
         return ps.executeUpdate()!=0;
     }
      public static ArrayList<EmpPojo> getAllReceptionist()throws SQLException
     {
        st=DBConnection.getConnection().createStatement();
        ArrayList<EmpPojo> receptionist=new ArrayList<>();
        ResultSet rs=st.executeQuery("select * from employees where active='Y' and role='RECEPTIONIST' ORDER BY empid ASC");
        while(rs.next())
        {
            EmpPojo emp=new EmpPojo();
            emp.setEmpid(rs.getString(1));
            emp.setEmpname(rs.getString(2));
            emp.setJob(rs.getString(3));
            emp.setSal(rs.getDouble(4));
            receptionist.add(emp);
        }
        return receptionist;
      }
      public static ArrayList<String> getAllUserId()throws SQLException
      {
        st1=DBConnection.getConnection().createStatement();
        ArrayList<String> userid=new ArrayList<>();
        ResultSet rs=st1.executeQuery("select userid from users where active='Y' and usertype='RECEPTIONIST'");
        while(rs.next())
        {
            userid.add(rs.getString("userid"));
        }
        return userid;
      }
      public static boolean removeReceptionist(String userid)throws SQLException
      {
         ps1=DBConnection.getConnection().prepareStatement("update users set active='N' where userid=?");
         ps1.setString(1, userid);
         return (ps1.executeUpdate()!=0);     
      }
}
