package books.serverlet.dao.impl;

import books.serverlet.bean.Users;
import books.serverlet.dao.BaseDao;
import books.serverlet.dao.Usersdao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * ClassName:Usersdaoimpl
 * Package:tianjiao.serverlet.dao.impl
 * Description:
 *
 * @Author 赵天礁
 * @Creat 2023-10-2722:50
 * Version 1.0
 */
public class Usersdaoimpl extends BaseDao implements Usersdao{
    @Override
    public Users login(String username)  {
        Users users=null;
        ResultSet resultSet=null;
        try {
            String sql="select * from users where username=?;";
            resultSet=query(sql,username);
            while (resultSet.next()) {
                users = new Users();
                users.setId(resultSet.getInt("id"));
                users.setUserName(resultSet.getString("username"));
                users.setPassWord(resultSet.getString("password"));
                users.setEmail(resultSet.getString("email"));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally{
            free();
        }
        return users;
    }

    @Override
    public boolean regist(Users users) {
        int len= 0;
            String sql="insert into users (userName,passWord,email) values(?,?,?);";
            len = update(sql,users.getUserName(),users.getPassWord(),users.getEmail());
            free();
        return len>0?true:false;
    }

    @Override
    public boolean checkUname(String uname) {
        try {
            String sql="select * from users where username=?;";
            ResultSet resultSet=query(sql,uname);
            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            free();
        }
    }
}
