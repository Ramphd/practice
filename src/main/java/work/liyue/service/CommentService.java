package work.liyue.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work.liyue.dao.CommentDao;
import work.liyue.model.Comment;

import java.util.List;

/**
 * Created by hzliyue1 on 2016/7/20,0020, 12:35:57.
 */
@Service
public class CommentService {
    private static final Logger logger = LoggerFactory.getLogger(QiniuService.class);
    @Autowired
    CommentDao commentDao;

    public List<Comment> getCommentsByEntity(int entityId, int entityType) {
        return commentDao.selectByEntity(entityId, entityType);
    }
    public int addComment(Comment comment) {
        return commentDao.addComment(comment);
    }
    public int getCommentCount(int entityId, int entityType) {
        return commentDao.getCommentCount(entityId, entityType);
    }
    public void deleteComment(int entityId, int entityType) {
        commentDao.updateStatus(entityId, entityType, 1);
    }

}
