package work.liyue.service;


import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.liyue.dao.LoginTicketDao;
import work.liyue.dao.UserDao;
import work.liyue.model.LoginTicket;
import work.liyue.model.User;
import work.liyue.util.PracticeUtil;

import java.util.*;

/**
 * Created by hzliyue1 on 2016/7/15,0015.
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private LoginTicketDao loginTicketDao;

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
            map.put("msgpwd", "pwd must not be blank");
            return map;
        }
        User user = userDao.selectByName(username);
        if (user != null) {
            map.put("msgname", "already registered");
            return map;
        }
        user = new User();
        user.setName(username);
        //设置密码前要加盐
        user.setSalt(UUID.randomUUID().toString().substring(0, 5));
        user.setHeadUrl(String.format("http://images.nowcoder.com/head/%dt.png", new Random().nextInt(1000)));
        user.setPassword(PracticeUtil.MD5(password + user.getSalt()));
        userDao.addUser(user);

        //注册直接登录下发ticket
        String ticket = addLoginTicket(user.getId());
        map.put("ticket", ticket);

        return map;
    }


    public Map<String, Object> login(String username, String password) {
        Map<String, Object> map = new HashMap<>();
        if (StringUtils.isBlank(username)) {
            map.put("msgname", "name must not be blank");
            return map;
        }
        if (StringUtils.isBlank(password)) {
            map.put("msgpwd", "pwd must not be blank");
            return map;
        }
        User user = userDao.selectByName(username);
        if (user == null) {
            map.put("msgname", "user not exist");
            return map;
        }
        if (!PracticeUtil.MD5(password + user.getSalt()).equals(user.getPassword())) {
            map.put("msgpwd", "account or password is not correct");
            return map;
        }
        //注册下发TICKET
        String ticket = addLoginTicket(user.getId());
        map.put("ticket", ticket);

        return map;
    }

    private String addLoginTicket(int userId){
        LoginTicket ticket = new LoginTicket();
        ticket.setUserId(userId);
        Date date = new Date();
        date.setTime(date.getTime() + 1000 * 3600 * 24);
        ticket.setExpired(date);
        ticket.setStatus(0);
        ticket.setTicket(UUID.randomUUID().toString().replace("-", ""));
        loginTicketDao.addTicket(ticket);
        return ticket.getTicket();
    }

    public void logout(String ticket) {
        loginTicketDao.updateStatus(ticket, 1);

    }
}
