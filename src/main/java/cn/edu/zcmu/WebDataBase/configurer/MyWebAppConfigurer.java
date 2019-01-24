package cn.edu.zcmu.WebDataBase.configurer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Component
public class MyWebAppConfigurer extends WebMvcConfigurationSupport {

    @Autowired
    private URLInterceptor urlInterceptor;

    /**
     * 敏感操作全部拦截
     * 例如：增加、删除
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(urlInterceptor).
                addPathPatterns("/**/add", "/**/delete");
        super.addInterceptors(registry);
    }

    /**
     * 静态界面一律不拦截
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }

    /**
     * 默认首页
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index.html");
    }
}
