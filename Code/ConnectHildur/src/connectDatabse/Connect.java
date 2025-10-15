package connectDatabse;
import java.sql.*;


public class Connect {

    public static void main(String[] args) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            Connection con = DriverManager.getConnection(
                    "jdbc:sqlserver://hildur.ucn.dk:1433;databaseName=DMA-CSD-V251_10647364;user=DMA-CSD-V251_10647364;password=Password1!;encrypt=false");


                System.out.println("Connected to database:"+ !con.isClosed()); 
                con.close(); 
                System.out.println("Connected to database:"+ !con.isClosed());
            } catch (ClassNotFoundException | SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } 


        System.out.println("Works");
    }
}
