package work.liyue.dao;


import org.apache.ibatis.annotations.*;
import work.liyue.model.LoginTicket;
import work.liyue.model.User;


/**
 * Created by hzliyue1 on 2016/7/15,0015.
 */
@Mapper//Mapper interface UserDao,做了Mapper映射之后，
// 通过@Insert注解进行解析sql语句，一个sql语句对应一个函数，
// 在应用逻辑中调用函数，起到调用sql语句的作用。框架后台将封
// 装了信息的对象进行解包，将对应字段数据填入sql语句中对数据
// 库进行crud,表名和字段要用驼峰形式进行对应。
public interface LoginTicketDao {
    String TABLE_NAME = "login_ticket";
    String INSERT_FIELDS = " user_id, expired, status, ticket ";
    String SELECT_FIELDS = " id," +  INSERT_FIELDS;

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
            ") values (#{userId},#{expired},#{status},#{ticket})"})
    int addTicket(LoginTicket loginTicket);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where ticket=#{ticket}"})
    LoginTicket selectByTicket(String ticket);

    @Update({"update ", TABLE_NAME, " set status=#{status} where ticket=#{ticket}"})
    void updateStatus(@Param("ticket") String ticket, @Param("status") int status);

//    @Delete({"delete from ", TABLE_NAME, " where id=#{id}"})
//    void deleteById(int id);
}

