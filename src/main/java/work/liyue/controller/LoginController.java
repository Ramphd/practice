package work.liyue.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import work.liyue.aspect.LogAspect;
import work.liyue.service.UserService;
import work.liyue.util.PracticeUtil;

import java.util.Map;

/**
 * Created by hzliyue1 on 2016/7/16,0016.
 */
@Controller
public class LoginController {
    private static final Logger logger =
            LoggerFactory.getLogger(LogAspect.class);

    @Autowired
    UserService userService;

    @RequestMapping(path = {"/reg/"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String reg(Model model, @RequestParam("username") String username,
                      @RequestParam("password") String password,
                      @RequestParam(value = "remember", defaultValue = "0") int remember) {
        try {
            Map<String, Object> map =
                    userService.register(username, password);
            if (map.isEmpty()) {
                return PracticeUtil.getJSONString(0, "reg OK!");
            } else {
                return PracticeUtil.getJSONString(1, map);
            }

        } catch (Exception e) {
            logger.error("reg error" + e.getMessage());
            return PracticeUtil.getJSONString(1, "reg error");
        }
    }
}
