package work.liyue.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.liyue.dao.UserDao;
import work.liyue.model.User;

/**
 * Created by hzliyue1 on 2016/7/15,0015.
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public User getUser(int id){
        return userDao.selectById(id);
    }
}
