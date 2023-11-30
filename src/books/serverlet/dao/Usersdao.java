package books.serverlet.dao;

import books.serverlet.bean.Users;

/**
 * ClassName:Usersdao
 * Package:tianjiao.serverlet.dao
 * Description:
 *
 * @Author 赵天礁
 * @Creat 2023-10-2722:49
 * Version 1.0
 */
public interface Usersdao {
    public Users login(String username);
    public boolean regist(Users users);
    public boolean checkUname(String uname);
}
