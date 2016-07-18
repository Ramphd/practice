package work.liyue.model;

import org.springframework.stereotype.Component;

/**
 * Created by hzliyue1 on 2016/7/17.
 */
//这个类表示当前用户
@Component
public class HostHolder {
    //threadLocal 每个线程存自己的东西
    private static ThreadLocal<User> users  =new ThreadLocal<>();

    public  User getUser(){
        return users.get();
    }

    public void setUser(User user) {
        users.set(user);
    }
    public void clear() {
        users.remove();
    }
}
