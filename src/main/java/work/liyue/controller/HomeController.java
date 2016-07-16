package work.liyue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import work.liyue.model.News;
import work.liyue.model.ViewObject;
import work.liyue.service.NewsService;
import work.liyue.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzliyue1 on 2016/7/16.
 */
@Controller //controller --> service--> dao--->model
public class HomeController {

    //method = {RequestMethod.GET, RequestMethod.POST}
    @Autowired
    private NewsService newsService;
    @Autowired
    private UserService userService;

    private List<ViewObject> getNews(int userId, int offset, int limit) {
        List<News> newsList = newsService.getLastestNews(userId, offset, limit);
        List<ViewObject> vos = new ArrayList<>();

        for (News news : newsList) {
            ViewObject vo = new ViewObject();
            vo.set("news", news);
            vo.set("user", userService.getUser(news.getUserId()));
            vos.add(vo);
        }
        return vos;
    }

    @RequestMapping(path = {"/", "/index"}, method = {RequestMethod.GET, RequestMethod.POST})
    /**
     *  whole news list in index page
     */
    public String index(Model model) {

        model.addAttribute("vos", getNews(0, 0, 10));
        return "home";
    }

    @RequestMapping(path = {"/user/{userId}"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String userIndex(Model model, @PathVariable("userId") int userId) {
        /**
         *  specific user's news selected by userId
         */
        model.addAttribute("vos", getNews(userId, 0, 10));
        return "home";
    }
}
