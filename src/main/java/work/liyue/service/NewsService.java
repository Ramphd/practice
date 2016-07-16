package work.liyue.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.liyue.dao.NewsDao;
import work.liyue.model.News;

import java.util.List;

/**
 * Created by hzliyue1 on 2016/7/16.
 */
@Service
public class NewsService {
    @Autowired
    private NewsDao newsDao;

    public List<News> getLastestNews(int userId, int offset, int limit){
        return newsDao.selectByUserIdAndOffset(userId, offset, limit);

    }


}
