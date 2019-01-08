package cn.edu.zcmu.WebDataBase;


import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class URLInterceptor implements HandlerInterceptor{
    public final static String SESSION_KEY="username";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws  Exception{
        HttpSession session = request.getSession();

//            判断是否已有该用户登录的session
        if(session.getAttribute(SESSION_KEY) != null){
            return true;
        }

//            跳转到登录页
        response.sendRedirect("/user/login");
        return false;
    }
}

