package work.liyue.dao;


import org.apache.ibatis.annotations.*;
import work.liyue.model.User;


/**
 * Created by hzliyue1 on 2016/7/15,0015.
 */
@Mapper//Mapper interface UserDao,做了Mapper映射之后，
// 通过@Insert注解进行解析sql语句，一个sql语句对应一个函数，
// 在应用逻辑中调用函数，起到调用sql语句的作用。框架后台将封
// 装了信息的对象进行解包，将对应字段数据填入sql语句中对数据
// 库进行crud
public interface UserDao {
    String TABLE_NAME = "user";
    String INSERT_FIELDS = " name, password, salt, head_url ";
    String SELECT_FIELDS = " id, name, password, salt, head_url";

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
            ") values (#{name},#{password},#{salt},#{headUrl})"})
    int addUser(User user);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where id=#{id}"})
    User selectById(int id);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where name=#{name}"})
    User selectByName(String name);

    @Update({"update ", TABLE_NAME, " set password=#{password} where id=#{id}"})
    void updatePassword(User user);

    @Delete({"delete from ", TABLE_NAME, " where id=#{id}"})
    void deleteById(int id);
}

