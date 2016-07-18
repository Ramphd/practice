package work.liyue.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import work.liyue.aspect.LogAspect;
import work.liyue.service.UserService;
import work.liyue.util.PracticeUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
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
                      @RequestParam(value = "remember", defaultValue = "0") int remember ,
                      HttpServletResponse response) {
        try {
            Map<String, Object> map =
                    userService.register(username, password);
            if (map.containsKey("ticket")) {
                Cookie cookie = new Cookie("ticket", map.get("ticket").toString());
                cookie.setPath("/");
                if(remember > 0){
                    cookie.setMaxAge(3600 * 24 * 5);
                }
                response.addCookie(cookie);
                return PracticeUtil.getJSONString(0, "reg OK!");
            } else {
                return PracticeUtil.getJSONString(1, map);
            }

        } catch (Exception e) {
            logger.error("reg error" + e.getMessage());
            return PracticeUtil.getJSONString(1, "reg error");
        }
    }

    @RequestMapping(path = {"/login/"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String login(Model model, @RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam(value = "remember", defaultValue = "0") int remember,
                        HttpServletResponse response) {
        try {
            Map<String, Object> map = userService.login(username, password);
            if (map.containsKey("ticket")) {
                Cookie cookie = new Cookie("ticket", map.get("ticket").toString());
                cookie.setPath("/");
                if (remember > 0) {
                    cookie.setMaxAge(3600*24*5);
                }
                response.addCookie(cookie);
                return PracticeUtil.getJSONString(0, "注册成功");
            } else {
                return PracticeUtil.getJSONString(1, map);
            }

        } catch (Exception e) {
            logger.error("login error" + e.getMessage());
            return PracticeUtil.getJSONString(1, "login error");
        }
    }


    @RequestMapping(path = {"/logout","/logout/"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String logout(@CookieValue("ticket") String ticket) {
        userService.logout(ticket);
        return "redirect:/";
    }

}
