package work.liyue.service;


import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.liyue.dao.UserDao;
import work.liyue.model.User;
import work.liyue.util.PracticeUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * Created by hzliyue1 on 2016/7/15,0015.
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public void addUser(User user) {
        userDao.addUser(user);
    }

    public User getUser(int id) {
        return userDao.selectById(id);
    }

    public Map<String, Object> register(String username, String password) {
        Map<String, Object> map = new HashMap<>();
        if (StringUtils.isBlank(username)) {
            map.put("msgname", "name must not be blank");
            return map;
        }
        if (StringUtils.isBlank(password)) {
            map.put("pwdname", "pwd must not be blank");
            return map;
        }
        User user = userDao.selectByName(username);
        if (user != null) {
            map.put("msgname", "already registered");
            return map;
        }
        user = new User();
        user.setName(username);
        //设置密码钱要加盐
        user.setSalt(UUID.randomUUID().toString().substring(0, 5));
        user.setHeadUrl(String.format("http://images.nowcoder.com/head/%dt.png", new Random().nextInt(1000)));
        user.setPassword(PracticeUtil.MD5(password + user.getSalt()));
        userDao.addUser(user);

        //login func

        return map;
    }

}
