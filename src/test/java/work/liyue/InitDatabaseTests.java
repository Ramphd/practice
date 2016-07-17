package work.liyue;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import work.liyue.dao.LoginTicketDao;
import work.liyue.dao.NewsDao;
import work.liyue.dao.UserDao;
import work.liyue.model.LoginTicket;
import work.liyue.model.News;
import work.liyue.model.User;

import java.util.Date;
import java.util.Random;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PracticeApplication.class)
@Sql("/init-schema.sql")
public class InitDatabaseTests {
    @Autowired
    UserDao userDao;
    //因为Mybatis注入属于动态注入，所以静态检查并不能确定，
    // 会显示成error，将Mapper改成Service就可以静态检查到
    @Autowired
    NewsDao newsDao;
    @Autowired
    LoginTicketDao loginTicketDao;

    //例如Intellij会对Spring的bean做检查，如果在xml或者注解里引用了不存在的bean，
    //它会出错误提示，但是实际上我们的bean是runtime生成的(例如iBatis的dao)，这个时候它还会提示error
    @Test
    public void initData() {
        Random random = new Random();
        for (int i = 0; i < 11; ++i) {
            User user = new User();
            user.setHeadUrl(String.format("http://images.nowcoder.com/head/%dt.png", random.nextInt(1000)));
            user.setName(String.format("USER%d", i));
            user.setPassword("");
            user.setSalt("");
            userDao.addUser(user);

            News news = new News();
            news.setCommentCount(i);
            Date date = new Date();
            date.setTime(date.getTime() + 1000 * 3600 * 5 * i);
            news.setCreatedDate(date);
            news.setImage(String.format("http://images.nowcoder.com/head/%dm.png", random.nextInt(1000)));
            news.setLikeCount(i + 1);
            news.setUserId(i + 1);
            news.setTitle(String.format("TITLE{%d}", i));
            news.setLink(String.format("http://www.nowcoder.com/%d.html", i));
            newsDao.addNews(news);

            user.setPassword("newpassword");
            userDao.updatePassword(user);

            LoginTicket ticket = new LoginTicket();
            ticket.setStatus(0);
            ticket.setUserId(i + 1);
            ticket.setExpired(date);
            ticket.setTicket(String.format("TICKET%d", i + 1));
            loginTicketDao.addTicket(ticket);

            loginTicketDao.updateStatus(ticket.getTicket(), 2);
        }

        Assert.assertEquals("newpassword", userDao.selectById(1).getPassword());
        userDao.deleteById(1);
        Assert.assertNull(userDao.selectById(1));
        Assert.assertEquals(1, loginTicketDao.selectByTicket("TICKET1").getUserId());
        Assert.assertEquals(2, loginTicketDao.selectByTicket("TICKET1").getStatus());
    }

}
