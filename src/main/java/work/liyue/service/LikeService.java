package work.liyue.service;

import org.springframework.beans.factory.annotation.Autowired;
import work.liyue.util.JedisAdapter;
import work.liyue.util.RedisKeyUtil;

/**
 * Created by hzliyue1 on 2016/7/24 ,0:41.
 */
public class LikeService {

    @Autowired
    JedisAdapter jedisAdapter;

    /**
     * 某个用户对某个元素是否喜欢
     *喜欢返回1，不喜欢返回-1，否则返回0
     * 用户有没有在对应集合里面
     * @param userId
     * @return
     */
    public int getLikeStatus(int userId, int entityType, int entityId) {
        String likeKey = RedisKeyUtil.getLikeKey(entityId, entityType);
        if (jedisAdapter.sismember(likeKey, String.valueOf(userId))) {
            return 1;
        }
        String disLikeKey = RedisKeyUtil.getDisLikeKey(entityId, entityType);
        return jedisAdapter.sismember(disLikeKey, String.valueOf(userId)) ? -1 : 0;
    }

    /**
     * 如果喜欢就增加一个喜欢
     * like搞出来，用户插进去
     * @param userId
     * @param entityType
     * @param entityId
     * @return 返回对少人喜欢
     */
    public long like(int userId, int entityType, int entityId) {
        // 在喜欢集合里增加
        String likeKey = RedisKeyUtil.getLikeKey(entityId, entityType);
        jedisAdapter.sadd(likeKey, String.valueOf(userId));
        // 从反对里删除
        String disLikeKey = RedisKeyUtil.getDisLikeKey(entityId, entityType);
        jedisAdapter.srem(disLikeKey, String.valueOf(userId));
        return jedisAdapter.scard(likeKey);
    }

    /**
     * dislike搞出来，用户插进去
     * @param userId
     * @param entityType
     * @param entityId
     * @return
     */
    public long disLike(int userId, int entityType, int entityId) {
        // 在反对集合里增加
        String disLikeKey = RedisKeyUtil.getDisLikeKey(entityId, entityType);
        jedisAdapter.sadd(disLikeKey, String.valueOf(userId));
        // 从喜欢里删除
        String likeKey = RedisKeyUtil.getLikeKey(entityId, entityType);
        jedisAdapter.srem(likeKey, String.valueOf(userId));
        return jedisAdapter.scard(likeKey);
    }
}
