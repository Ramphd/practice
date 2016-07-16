package work.liyue.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hzliyue1 on 2016/7/15,0015.
 */
public class ViewObject {//专门用来视图展示的对象，viewObject
    private Map<String, Object> objs = new HashMap<>();

    public void set(String key, Object value) {
        objs.put(key, value);
    }

    public Object get(String key) {
        return objs.get(key);
    }
}
