package work.liyue.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import work.liyue.aspect.LogAspect;
import work.liyue.model.User;
import work.liyue.service.PracticeService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;


/**
 * Created by hzliyue1 on 2016/7/12,0012.
 */
@Controller
public class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
    @Autowired
    private PracticeService practiceService;

    @RequestMapping(path = {"/", "/index"})
    @ResponseBody
    public String index(HttpSession session) {
        logger.info("visit indexLog");
        return ("hello! " + session. getAttribute("msg") +
                practiceService.say());

    }

    @RequestMapping(value = {"/profile/{groupId}/{userId}"})
    @ResponseBody
    public String profile(@PathVariable("groupId") String groupId,
                          @PathVariable("userId") int userId,
                          @RequestParam(value = "type", defaultValue = "1") int type) {
        return String.format("{%s},{%d},{%d}", groupId, userId, type);
    }

    @RequestMapping(value = {"/vm"})
    public String news(Model model) {
        model.addAttribute("value1", "vv1");
        List<String> color = Arrays.asList(new String[]{"red", "green"});
        Map<String, String> map = new HashMap<String, String>();
        map.put("1", "11");
        map.put("2", "22");

        model.addAttribute("colors", color);
        model.addAttribute("map", map);
        model.addAttribute("user", new User("Jim"));
        return "news";

    }

    @RequestMapping(value = {"/request"})
    @ResponseBody
    public String request(HttpServletRequest request,
                          HttpServletResponse response,
                          HttpSession session) {
        StringBuilder sb = new StringBuilder();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            sb.append(name + ":" + request.getHeader(name) + "<br>");
        }
        for (Cookie cookie : request.getCookies()) {
            sb.append("Cookies:");
            sb.append(cookie.getName());
            sb.append(":");
            sb.append(cookie.getValue());
            sb.append("<br>");
        }
        return sb.toString();
    }

    @RequestMapping(value = {"/response"})
    @ResponseBody
    public String response(@CookieValue(value = "nowcoderId", defaultValue = "aaa") String nowcoderId,
                           @RequestParam(value = "key", defaultValue = "key") String key,
                           @RequestParam(value = "value", defaultValue = "value") String value,
                           HttpServletResponse response) {
        response.addCookie(new Cookie(key, value));
        response.addHeader(key, value);
        return "nowcoderId form Cookie" + nowcoderId;
    }

    @RequestMapping("/redirect/{code}")
    public String redirect(@PathVariable("code") int code,
                           HttpSession session) {
        RedirectView red = new RedirectView("/", true);
        if (code == 301) {
            red.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        }
        session.setAttribute("msg", "jump from redirect");
        return "redirect:/";
    }

    @RequestMapping(value = {"/admin"})
    @ResponseBody
    public String admin(@RequestParam(value = "key", required = false) String key) {
        if ("admin".equals(key))
            return "hel; admin";
        throw new IllegalArgumentException("key wrong");
    }

    @ExceptionHandler
    @ResponseBody
    public String error(Exception e) {
        return "error:" + e.getMessage();
    }
}
