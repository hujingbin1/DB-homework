package books.serverlet.dao;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import javax.sql.DataSource;
import java.sql.*;
import java.util.Properties;
import java.util.ResourceBundle;

/*
这个工具类的作用就是用来给所有的SQL操作提供“连接”，和释放连接。
这里使用ThreadLocal的目的是为了让同一个线程，在多个地方getConnection得到的是同一个连接。
这里使用DataSource的目的是为了（1）限制服务器的连接的上限（2）连接的重用性等
 */
public class BaseDao{
   protected   Connection connection =null;
   protected   PreparedStatement pps=null;
    private  ResultSet resultSet=null;
    private  static String url;
    private  static String passWord;
    private  static String userName;

    //1.加载驱动(只需要做一次)
    static{
        try {
            //在静态代码块中读取属性文件
           /* InputStream inputStream = BaseDao.class.getClassLoader().getResourceAsStream("db.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            String driverClassName = properties.getProperty("driverClassName");*/
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
    public Connection getConnection(){
        try {
            connection = DriverManager.getConnection(url, userName, passWord);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  connection;
    }
    //3.增删改   params表示的是给占位符所赋的值
    public int update(String sql,Object... params){
        int i = 0;
        try {
            getConnection();
            pps = connection.prepareStatement(sql);
            if(params.length>0){
                for (i=0;i<params.length;i++) {
                    pps.setObject(1+i,params[i]);
                }
            }
            i = pps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return i;
    }
    //4.查询的方法
    public ResultSet query(String sql,Object... params){
        int i=0;
        try {
            getConnection();
            pps = connection.prepareStatement(sql);
            if(params.length>0){
                for (i=0;i<params.length;i++) {
                    pps.setObject(1+i,params[i]);
                }
            }
            resultSet = pps.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }
    //5.关闭资源
    public void free(){
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (pps != null) {
                pps.close();
            }
            if (connection != null) {
                connection.close();
            }} catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
