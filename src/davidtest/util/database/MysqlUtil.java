/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package davidtest.util.database;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author lenovo
 */
public class MysqlUtil {
    
    public static void initSchema(final String schema) throws ClassNotFoundException, SQLException {
        createSchema(schema);
     
    }
     public static void createSchema(final String schema) throws ClassNotFoundException, SQLException {
         Connection connection = MysqlConnection.getInstance().getConnection();
        final Statement requete = connection.createStatement();
        try {
            requete.executeUpdate("CREATE SCHEMA " + schema);
        }
        catch (SQLException ex2) {
            throw new SQLException();
        }
    }
   
     /**
      *  importer un fichier sql dans une base de donnée
      * @param path
      * @throws IOException
      * @throws InterruptedException 
      */
      public static void loadSchema(final String path) throws IOException, InterruptedException {
          
//          Runtime rt = Runtime.getRuntime();
//          Process pr = rt.exec("mysql -p -h ServerName DbName < dump.sql");
           String absolutePath = Settings.getPROJECT_URL()+path;
            final String command = "\"" + Settings.getDISTINATION_URL_MYSQL() + "\\bin\\mysql\" --user=" 
                    + Settings.getUSERNAME() + " --password=" + Settings.getPASSWORD() + " -B " 
                    + Settings.getDEFAULT_SCHEMA() + " < \"" + absolutePath + "\"";
            final String[] arg = {"cmd.exe", "/c", "\"" + command + "\""};
            final Runtime runtime = Runtime.getRuntime();
            final Process process2 = runtime.exec(arg);
            InputStream inputStream;
            if (process2.waitFor() == 0) {
                inputStream = process2.getInputStream();
            } else {
                inputStream = process2.getErrorStream();
            }
            final byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            final String str = new String(buffer);
            System.out.println(str); 
        }
      
     
    
}
