
package sanjeevani.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import sanjeevani.dbutil.DBConnection;
import sanjeevani.pojo.PatientPojo;

public class PatientDao {
    private static PreparedStatement ps,ps1,ps2,ps3;
    private static Statement st,st1;
    public static boolean addPatient(PatientPojo p)throws SQLException
    {
       ps=DBConnection.getConnection().prepareStatement("insert into patient values(?,?,?,?,?,?,?,?,?,?,?,?,'Y')");
       ps.setString(1, p.getP_id());
       ps.setString(2, p.getF_name());
       ps.setString(3, p.getS_name());
       ps.setInt(4, p.getAge());
       ps.setString(5, p.getOpd());
       ps.setString(6, p.getGender());
       ps.setString(7, p.getM_status());
       ps.setDate(8, p.getDate());
       ps.setString(9, p.getAddress());
       ps.setString(10, p.getCity());
       ps.setString(11, p.getMno());
       ps.setString(12, p.getDoctor_id());
       return (ps.executeUpdate()!=0);
    }
    public static String getNewPatientId()throws SQLException
    {
      ResultSet rs=DBConnection.getConnection().prepareStatement("Select count(*) from patient").executeQuery();
      int id=101;
      if(rs.next())
      {
          id=id+rs.getInt(1);
      } 
       return("P"+id);
    }
    public static ArrayList<PatientPojo> getAllPatient()throws SQLException
    {
        ArrayList<PatientPojo> patient=new ArrayList<>();
        ResultSet rs=DBConnection.getConnection().createStatement().executeQuery("select * from patient where active='Y'");
        
        while(rs.next())
        {
            PatientPojo p=new PatientPojo();
            p.setP_id(rs.getString("p_id"));
            p.setF_name(rs.getString("f_name"));
            p.setS_name(rs.getString("s_name"));
            p.setAge(rs.getInt("age"));
            p.setOpd(rs.getString("opd"));
            p.setGender(rs.getString("gender"));
            p.setM_status(rs.getString("m_status"));
            p.setDate(rs.getDate("p_date"));
            p.setAddress(rs.getString("address"));
            p.setCity(rs.getString("city"));
            p.setMno(rs.getString("phone_no"));
            p.setDoctor_id(rs.getString("doctor_id"));
            patient.add(p);
        }
        return patient;
    }
    public static PatientPojo getSinglePatientDetails(String pid)throws SQLException
    {
       ps1=DBConnection.getConnection().prepareStatement("select * from patient where p_id=? and active='Y'");
       ps1.setString(1, pid); 
       ResultSet rs=ps1.executeQuery();
       PatientPojo p=new PatientPojo();
       while(rs.next())
       {
            p.setP_id(rs.getString("p_id"));
            p.setF_name(rs.getString("f_name"));
            p.setS_name(rs.getString("s_name"));
            p.setAge(rs.getInt("age"));
            p.setOpd(rs.getString("opd"));
            p.setGender(rs.getString("gender"));
            p.setM_status(rs.getString("m_status"));
            p.setDate(rs.getDate("p_date"));
            p.setAddress(rs.getString("address"));
            p.setCity(rs.getString("city"));
            p.setMno(rs.getString("phone_no"));
            p.setDoctor_id(rs.getString("doctor_id"));
       }
       return p;
    }
    public static ArrayList<String> getAllPatientId()throws SQLException
    {
        ResultSet rs=DBConnection.getConnection().createStatement().executeQuery("select p_id from patient where active='Y'");
        ArrayList<String> pid=new ArrayList<>();
        while(rs.next())
        {
            pid.add(rs.getString("p_id"));
        }
        return pid;
    }
    public static boolean deletePatient(String pid)throws SQLException
    {  
        ps2=DBConnection.getConnection().prepareStatement("update patient set active='N' where p_id=?");
        ps2.setString(1, pid);
        return (ps2.executeUpdate()!=0);
    }
    public static boolean updatePatient(PatientPojo p)throws SQLException    
    {
       ps3=DBConnection.getConnection().prepareStatement("update patient set f_name=?,s_name=?,age=?,opd=?,gender=?,m_status=?,p_date=?,address=?,city=?,phone_no=? where p_id=?");
       ps3.setString(1, p.getF_name());
       ps3.setString(2, p.getS_name());
       ps3.setInt(3, p.getAge());
       ps3.setString(4, p.getOpd());
       ps3.setString(5, p.getGender());
       ps3.setString(6, p.getM_status());
       ps3.setDate(7, p.getDate());
       ps3.setString(8, p.getAddress());
       ps3.setString(9, p.getCity());
       ps3.setString(10, p.getMno());
       ps3.setString(11, p.getP_id());
       return (ps3.executeUpdate()!=0);
    }
}




