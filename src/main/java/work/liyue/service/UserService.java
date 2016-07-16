package work.liyue.service;


import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.liyue.dao.UserDao;
import work.liyue.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by hzliyue1 on 2016/7/15,0015.
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public void addUser(User user){
        userDao.addUser(user);
    }
    public User getUser(int id){
        return userDao.selectById(id);
    }

    public Map<String, Object> register(String username, String password) {
        Map<String, Object> map = new HashMap<>();
        if (StringUtils.isBlank(username)) {
            map.put("msgname", "name must bot be blank");
        }
        if(StringUtils.isBlank(password)){
            map.put("pwdname", "pwd must bot be blank");
        }
        User user = userDao.selectByName(username);
        if (user != null) {
            map.put("msgname", "already registered");
        }
        user = new User();
        user.setName(username);
        user.setSalt(UUID.randomUUID().toString().substring(0, 5));

    return null;
    }

}
