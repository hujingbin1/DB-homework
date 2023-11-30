package books.serverlet.bean;

/**
 * ClassName:Users
 * Package:tianjiao.serverlet.bean
 * Description:
 *
 * @Author 赵天礁
 * @Creat 2023-10-2722:45
 * Version 1.0
 */
public class Users {
    private Integer id;
    private String userName;
    private String passWord;
    private String email;

    public Users() {
    }

    public Users(Integer id, String userName, String passWord, String email) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
