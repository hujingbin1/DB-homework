package books.serverlet.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * ClassName:JDBCUtil
 * Package:books.serverlet.dao
 * Description:
 *
 * @Author 赵天礁
 * @Creat 2023-11-2020:52
 * Version 1.0
 */
public class JDBCUtil {
    private  static String url;
    private  static String passWord;
    private  static String userName;
    private static ThreadLocal<Connection> tl=new ThreadLocal<>();

    //1.加载驱动(只需要做一次)
    static{
        try {
            //在静态代码块中读取属性文件
                   //读取属性文件，方法的参数只写属性文件的名称，且该属性文件要放在src下
            ResourceBundle db = ResourceBundle.getBundle("druid");
            Class.forName(db.getString("driverClassName"));
            url=db.getString("url");
            userName=db.getString("username");
            passWord=db.getString("password");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    //2.获取连接
    public static Connection getConnection(){
        Connection connection=null;
        try {
            connection=tl.get();
            if(connection==null)
            {
                connection = DriverManager.getConnection(url, userName, passWord);
                tl.set(connection);
            }
            return  connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void free(){
        try {
            Connection connection=tl.get();
            if(connection!=null)
            {
                tl.remove();
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
