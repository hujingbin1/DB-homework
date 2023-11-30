package books.serverlet.service;

import books.serverlet.bean.Users;

/**
 * ClassName:UsersService
 * Package:tianjiao.serverlet.service
 * Description:
 *
 * @Author 赵天礁
 * @Creat 2023-10-2723:06
 * Version 1.0
 */
public interface UsersService {
    public Users login(String username);
    public boolean regist(Users users);
    public boolean checkUname(String uname);
}
