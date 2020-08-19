
package sanjeevani.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import sanjeevani.dbutil.DBConnection;
import sanjeevani.pojo.EmpPojo;

public class EmpDao {
    private static PreparedStatement ps,ps1,ps2,ps3,ps4;
    private static Statement st,st1,st2,st3,st4;
    public static String getNewId()throws SQLException
    {
        st=DBConnection.getConnection().createStatement();
        ResultSet rs=st.executeQuery("select max(empid) from employees");
        int id=1;
        if(rs.next())
        {
           String empid=rs.getString(1);
           int eno=Integer.parseInt(empid.substring(1));
           id=id+eno;
        }
       return "E"+id;
    }
     public static boolean addEmp(EmpPojo emp)throws SQLException
     {
         ps=DBConnection.getConnection().prepareStatement("insert into employees values(?,?,?,?,'Y')");
         ps.setString(1, emp.getEmpid());
         ps.setString(2, emp.getEmpname());
         ps.setString(3, emp.getJob());
         ps.setDouble(4, emp.getSal());
         return ps.executeUpdate()!=0;
     }
     public static ArrayList<EmpPojo> getAllEmp()throws SQLException
     {
        st1=DBConnection.getConnection().createStatement();
        ArrayList<EmpPojo> empList=new ArrayList<>();
        ResultSet rs=st1.executeQuery("select * from employees where active='Y' where ORDER BY empid ASC");
        while(rs.next())
        {
            EmpPojo e=new EmpPojo();
            e.setEmpid(rs.getString("empid"));
            e.setEmpname(rs.getString("ename"));
            e.setJob(rs.getString("role"));
            e.setSal(rs.getDouble("sal"));
            empList.add(e);
        }
        return empList;
     }
     public static HashMap<String,String> getNotRegisterReceptionist()throws SQLException
     {
        st2=DBConnection.getConnection().createStatement();
        HashMap<String,String> receptionist=new HashMap<>();
        ResultSet rs=st2.executeQuery("select empid,empname from employees where active='Y' and role='RECEPTIONIST' and empid not in (select empid from users where usertype='RECEPTIONIST')");  
        while(rs.next())
        {
            String id=rs.getString(1);
            String name=rs.getString(2);
            receptionist.put(id, name);
        }
        return receptionist;
     }
     public static HashMap<String,EmpPojo> getEmployeeDetailsById()throws SQLException
     {
        st3=DBConnection.getConnection().createStatement();
        HashMap<String,EmpPojo> empDetails;
        empDetails = new HashMap<>();
        ResultSet rs=st3.executeQuery("select * from employees where active='Y' ORDER BY empid ASC");  
        while(rs.next())
        {
            EmpPojo emp=new EmpPojo();
            String id=rs.getString(1);
            emp.setEmpid(id);
            emp.setEmpname(rs.getString(2));
            emp.setJob(rs.getString(3));
            emp.setSal(rs.getDouble(4));
            empDetails.put(id, emp);
        }
        return empDetails; 
     }
     public static boolean updateEmployee(EmpPojo emp)throws SQLException
     {
         ps1=DBConnection.getConnection().prepareStatement("update employees set empname=?,role=?,sal=? where empid=?");
         ps1.setString(1, emp.getEmpname());
         ps1.setString(2, emp.getJob());
         ps1.setDouble(3, emp.getSal());
         ps1.setString(4, emp.getEmpid());
         return ps1.executeUpdate()!=0;
     }
     public static boolean removeEmployee(String empid,String role)throws SQLException
     {
          if(role.equals("DOCTOR"))
          {
             ps3=DBConnection.getConnection().prepareStatement("update doctors set active='N' where userid=(select userid from users where empid=?)");
             ps3.setString(1, empid);
             ps3.executeUpdate();
          }
           ps4=DBConnection.getConnection().prepareStatement("update users set active='N' where empid=?");
           ps4.setString(1, empid);
           ps4.executeUpdate();
           ps2=DBConnection.getConnection().prepareStatement("update employees set active='N' where empid=?");
           ps2.setString(1, empid);
           return ps2.executeUpdate()!=0;
     }
     public static HashMap<String,String> getNotRegisterDoctors()throws SQLException
     {
        st4=DBConnection.getConnection().createStatement();
        HashMap<String,String> doctor=new HashMap<>();
        ResultSet rs=st4.executeQuery("select empid,empname from employees where active='Y' and role='DOCTOR' and empid not in (select empid from users where usertype='DOCTOR')");  
        while(rs.next())
        {
            String id=rs.getString(1);
            String name=rs.getString(2);
            doctor.put(name,id);
        }
        return doctor;
     }
}
