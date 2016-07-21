package work.liyue.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.liyue.dao.MessageDao;
import work.liyue.model.Message;

import java.util.List;

/**
 * Created by hzliyue1 on 2016/7/21,0021, 16:59:01.
 */
@Service
public class MessageService {
    @Autowired
    MessageDao messageDAO;
    public int addMessage(Message message) {
        return messageDAO.addMessage(message);
    }

    public List<Message> getConversationDetail(String conversationId, int offset, int limit) {
        return messageDAO.getConversationDetail(conversationId, offset, limit);
    }

    public List<Message> getConversationList(int userId, int offset, int limit) {
        return messageDAO.getConversationList(userId, offset, limit);
    }

    public int getConvesationUnreadCount(int userId, String conversationId) {
        return messageDAO.getConvesationUnreadCount(userId, conversationId);
    }


}
