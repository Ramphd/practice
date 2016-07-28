package work.liyue;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzliyue1 on 2016/7/21,0021, 19:24:22.
 */

public class Teee {
    @Test
    public void abc(){


        List<String> list = new ArrayList<>();
        list.add("adasdasd");
        list.add("1544534354");
        String jsonString = JSON.toJSONString(list);
        System.out.println(jsonString);
        List<String> ll =  JSON.parseObject(jsonString, List.class);



    }



}
