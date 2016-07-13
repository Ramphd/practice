package work.liyue.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hzliyue1 on 2016/7/13,0013.
 */
@Controller
public class SettingController {
    @RequestMapping(value = {"/setting"})
    @ResponseBody
    public String Setting(){
        return "setting is ok";
    }
}
