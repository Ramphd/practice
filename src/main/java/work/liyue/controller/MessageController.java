package work.liyue.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import work.liyue.dao.MessageDao;
import work.liyue.model.Message;
import work.liyue.service.MessageService;
import work.liyue.util.PracticeUtil;

import java.util.Date;

/**
 * Created by hzliyue1 on 2016/7/21,0021, 16:59:39.
 */
@Controller
public class MessageController {
    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);
    @Autowired
    MessageDao messageDao;
    @Autowired
    MessageService messageService;

    @RequestMapping(path = {"/msg/addMessage"}, method = {RequestMethod.POST})
    @ResponseBody
    public String addMessage(@RequestParam("fromId") int fromId,
                             @RequestParam("toId") int toId,
                             @RequestParam("content") String content) {
        try {
            Message msg = new Message();
            msg.setContent(content);
            msg.setFromId(fromId);
            msg.setToId(toId);
            msg.setCreatedDate(new Date());
            //msg.setConversationId(fromId < toId ? String.format("%d_%d", fromId, toId) : String.format("%d_%d", toId, fromId));
            messageService.addMessage(msg);
            return PracticeUtil.getJSONString(msg.getId());
        } catch (Exception e) {
            logger.error("增加评论失败" + e.getMessage());
            return PracticeUtil.getJSONString(1, "插入评论失败");
        }
    }

}
