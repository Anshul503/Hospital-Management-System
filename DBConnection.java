
package sanjeevani.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DBConnection {
    
 private static Connection conn;
  static
  {
      try
        {
          Class.forName("oracle.jdbc.OracleDriver");
          //conn=DriverManager.getConnection("jdbc:oracle:thin:@//abc:1521/XE","myhms","student");
          //System.out.println("Connection opened!");
         conn= DriverManager.getConnection("jdbc:oracle:thin:@//DESKTOP-M424OBH:1521/xe","system","abc");
        }
      catch(Exception ex)
      {
          JOptionPane.showMessageDialog(null,"DB Error in opening onnection in DBConnection!","Error",JOptionPane.ERROR_MESSAGE);  
           ex.printStackTrace();
      }
  }
  public static Connection getConnection()
  {
      return conn;
  }
  public static void closeConnection()
  {
      try
      {
          conn.close();
          System.out.println("Connection Closed!");
      }
      catch(SQLException ex)
      {
         
          JOptionPane.showMessageDialog(null,"DB Error in closing connection in DBConnection!","Error",JOptionPane.ERROR_MESSAGE);  
          ex.printStackTrace();
      }
  }
}
