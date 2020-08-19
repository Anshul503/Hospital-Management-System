
package sanjeevani.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import sanjeevani.dbutil.DBConnection;
import sanjeevani.pojo.DoctorPojo;
import sanjeevani.pojo.UserPojo;

public class DoctorDao {
    private static PreparedStatement ps,ps1,ps2,ps3;
    private static Statement st,st1,st2;
    public static String getNewId()throws SQLException
    {
        st=DBConnection.getConnection().createStatement();
        ResultSet rs=st.executeQuery("select max(doctorid) from doctors");
        int id=1;
        if(rs.next())
        {
           String docid=rs.getString(1);
           int eno=Integer.parseInt(docid.substring(3));
           id=id+eno;
        }
       return "DOC"+id;
    }
    public static boolean addDoctor(UserPojo user,DoctorPojo doctor)throws SQLException
     {           
         ps=DBConnection.getConnection().prepareStatement("insert into users values(?,?,?,?,?,'Y')");
         ps.setString(1,  user.getUserid());
         ps.setString(2,  user.getUserName());
         ps.setString(3,  user.getEmpId());
         ps.setString(4,  user.getPassword());
         ps.setString(5,  user.getUserType().toUpperCase());
         if(ps.executeUpdate()!=0)
         {
           ps1=DBConnection.getConnection().prepareStatement("insert into doctors values(?,?,?,?,'Y')");
           ps1.setString(1, doctor.getUserid());
           ps1.setString(2, doctor.getDoctorid());
           ps1.setString(3, doctor.getQualification());
           ps1.setString(4, doctor.getSpecialist());
           return ps1.executeUpdate()!=0;
         }
         else
             return false;
     }
    public static ArrayList<DoctorPojo> getAllDoctors()throws SQLException
     {
        st1=DBConnection.getConnection().createStatement();
        ArrayList<DoctorPojo> doctorList=new ArrayList<>();
        ResultSet rs=st1.executeQuery("select * from doctors ORDER BY doctorid ASC");
        while(rs.next())
        {
            DoctorPojo doc=new DoctorPojo();
            doc.setUserid(rs.getString("userid"));
            doc.setDoctorid(rs.getString("doctorid"));
            doc.setQualification(rs.getString("qualification"));
            doc.setSpecialist(rs.getString("specialist"));
            doc.setIsActive(rs.getString("active"));
            doctorList.add(doc);
        }
        return doctorList;
     }

    public static ArrayList<String> getAllDoctorId()throws SQLException 
    {
       ArrayList<String> docId=new ArrayList<>();
       ResultSet rs=DBConnection.getConnection().createStatement().executeQuery("select doctorid from doctors where active='Y' ORDER BY doctorid ASC");
       while(rs.next())
       {
           docId.add(rs.getString(1));
       }
       return docId;
    }
    public static boolean removeDoctor(String docid)throws SQLException 
    {
      
       ps2=DBConnection.getConnection().prepareStatement("update doctors set active='N' where doctorid=?");
       ps2.setString(1, docid);
       if(ps2.executeUpdate()!=0)
       {
         ps3=DBConnection.getConnection().prepareStatement("update users set active='N' where userid=(select userid from doctors where doctorid=?)");
         ps3.setString(1, docid); 
         return (ps3.executeUpdate()!=0);
       }
       return false;
    }
   
}
     
