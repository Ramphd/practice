package work.liyue.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import work.liyue.interceptor.LoginRequiredInterceptor;
import work.liyue.interceptor.PassportInterceptor;

/**
 * Created by hzliyue1 on 2016/7/17.
 */
@Component
public class PracticeConfiguration extends WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter {
    @Autowired
    PassportInterceptor passportInterceptor;

    @Autowired
    LoginRequiredInterceptor loginRequiredInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册有先后顺序，对拦截有影响。和AOP的思路一致，在某个节点进行拦截。
        registry.addInterceptor(passportInterceptor);
        //对专门一个路径请求做拦截,要有拦截效果首先必须有对应的controller才可以，不然就是error
        registry.addInterceptor(loginRequiredInterceptor).addPathPatterns("/setting/**");
        super.addInterceptors(registry);
    }
}
