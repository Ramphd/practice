package work.liyue.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import work.liyue.model.News;

import java.util.List;

/**
 * Created by hzliyue1 on 2016/7/15,0015.
 */
@Mapper
public interface NewsDao {
    String TABLE_NAME = "news";
    String INSERT_FIELDS = " title, link, image, like_count, comment_count, created_date, user_id ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
            ") values (#{title},#{link},#{image},#{likeCount}," +
                    "#{commentCount},#{createdDate},#{userId})"})
    int addNews(News news);

    List<News> selectByUserIdAndOffset(@Param("userId") int userId,
                                       @Param("offset") int offset,
                                       @Param("limit") int limit);
}