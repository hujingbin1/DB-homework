package books.serverlet.service.impl;

import books.serverlet.service.UsersService;
import books.serverlet.bean.Users;
import books.serverlet.dao.Usersdao;
import books.serverlet.dao.impl.Usersdaoimpl;

/**
 * ClassName:UsersServiceimpl
 * Package:tianjiao.serverlet.service.impl
 * Description:
 *
 * @Author 赵天礁
 * @Creat 2023-10-2723:06
 * Version 1.0
 */
public class UsersServiceimpl implements UsersService {
    Usersdao usersdao=new Usersdaoimpl();
    @Override
    public Users login(String username) {

        return usersdao.login(username);
    }

    @Override
    public boolean regist(Users users) {
        return usersdao.regist(users);
    }

    @Override
    public boolean checkUname(String uname) {
        return usersdao.checkUname(uname);
    }
}
