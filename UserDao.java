
package sanjeevani.dao;
import java.sql.*;
import java.util.HashMap;
import sanjeevani.dbutil.DBConnection;
import sanjeevani.pojo.*;

public class UserDao {
     private static PreparedStatement ps,ps1;
     public static String validateUser(User user)throws SQLException
    {
        ps=DBConnection.getConnection().prepareStatement("Select username from users where userid=? and password=? and usertype=? and active='Y'");
        ps.setString(1, user.getUserid());
        ps.setString(2,user.getPassword());
        ps.setString(3, user.getUsertype());
        ResultSet rs=ps.executeQuery();
        if(rs.next())
            return rs.getString("username");
        return null;
    }
     public static boolean changePassoword(String userid,String pwd)throws SQLException
     {
        ps1=DBConnection.getConnection().prepareStatement("Update users set password=? where userid=?");
        ps1.setString(1, pwd);
        ps1.setString(2, userid);
        return ps1.executeUpdate()!=0;
     }
     public static HashMap<String,String> getReceptionistList()throws SQLException
     {
         ResultSet rs=DBConnection.getConnection().createStatement().executeQuery("select userid,username from users where active='Y' and usertype='RECEPTIONIST'");
         HashMap<String,String> receptionList=new HashMap<>();
         while(rs.next())
         {
             receptionList.put(rs.getString(1), rs.getString(2));
         }
         return receptionList;
     }
}
