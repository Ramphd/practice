package work.liyue.model;

/**
 * Created by hzliyue1 on 2016/7/13,0013.
 */
public class User {
    private int id;
    private String password;
    private String salt;
    private String headUrl;
    private String name;

    public User(String name) {

        this.name = name;
        this.password = "";
        this.salt = "";
        this.headUrl = "";
    }

    public User() {

    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }
}
