package work.liyue.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import work.liyue.model.HostHolder;
import work.liyue.model.News;
import work.liyue.service.NewsService;
import work.liyue.service.QiniuService;
import work.liyue.util.PracticeUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

/**
 * Created by hzliyue1 on 2016/7/18,0018, 12:53:26.
 */
@Controller
public class NewsController {
    @Autowired
    NewsService newsService;
    @Autowired
    QiniuService qiniuService;
    @Autowired
    HostHolder hostHolder;
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(NewsController.class);

    @RequestMapping(path = {"/uploadImage/"}, method = {RequestMethod.POST})
    @ResponseBody
    //核心@RequestParam("file") MultipartFile file
    public String uploadImage(@RequestParam("file") MultipartFile file) {

        try {
            String fileUrl = newsService.saveImage(file);
            //String fileUrl = qiniuService.saveImage(file);

            if (fileUrl == null) {
                return PracticeUtil.getJSONString(1, "上传图片失败");
            }
            return PracticeUtil.getJSONString(0, fileUrl);

        } catch (Exception e) {
            logger.error("上传图片失败" + e.getMessage());
            return PracticeUtil.getJSONString(1, "上传图片失败");
            //匿名内部类初始化实例。
            //return new JSONObject() {{put("a", "sss");}}.toString();
        }
    }
    @RequestMapping(path = {"/image"}, method = {RequestMethod.GET})
    @ResponseBody
    public void getImage(@RequestParam("name") String imageName,
                         HttpServletResponse response) {
        try {
            response.setContentType("image/jpeg");
            StreamUtils.copy(new FileInputStream(new
                    File(PracticeUtil.IMAGE_DIR + imageName)), response.getOutputStream());
        } catch (Exception e) {
            logger.error("读取图片错误" + imageName + e.getMessage());
        }
    }


    @RequestMapping(path = {"/user/addNews/"}, method = {RequestMethod.POST})
    @ResponseBody
    public String addNews(@RequestParam("image") String image, @RequestParam("title") String title,
                          @RequestParam("link") String link) {
        try {
            News news = new News();
            news.setCreatedDate(new Date());
            news.setTitle(title);
            news.setImage(image);
            news.setLink(link);
            if (hostHolder.getUser() != null) {
                news.setUserId(hostHolder.getUser().getId());
            } else {
                //设置匿名用户
                news.setUserId(3);
            }
            newsService.addNews(news);
            return PracticeUtil.getJSONString(0);
        } catch (Exception e) {
            logger.error("添加资讯失败" + e.getMessage());
            return PracticeUtil.getJSONString(1, "发布失败");
        }
    }

}
