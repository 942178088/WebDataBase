package cn.edu.zcmu.WebDataBase.controller;


import cn.edu.zcmu.WebDataBase.URLInterceptor;
import cn.edu.zcmu.WebDataBase.entity.User;
import cn.edu.zcmu.WebDataBase.service.BaseService;
import cn.edu.zcmu.WebDataBase.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/user")
public class UserController extends BaseController<User,Integer>{
    @Resource
    private UserService userService;

    @Override
    public BaseService<User, Integer> getService() {
        return userService;
    }

    @Autowired
    public UserController(ObjectMapper mapper) {
        this.mapper = mapper;
    }


    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }


    /*@RequestMapping(value = "/",method = RequestMethod.GET)
    public  String index(){
        return "redirect:index.html";
    }*/

    @ResponseBody
    @RequestMapping(value = "/loginVerify",method = RequestMethod.POST)
    public ObjectNode login(User user, HttpServletRequest request, HttpServletResponse response,HttpSession session){
        json = mapper.createObjectNode();
        user.setPassword(BaseService.getHash(user.getPassword(),"MD5"));
        if(BaseService.checkNullStr(user.getUsername())|| BaseService.checkNullStr(user.getPassword())){
            json.put(STATUS_NAME,STATUS_CODE_NOT_FOUND);
            return json;
        }
        user = userService.findByUsernameAndPassword(user.getUsername(),user.getPassword());
        if(user != null){
            response.addCookie(BaseService.createCookie(BaseService.COOKIE_NAMES.get(0),user.getId()+"",30));
            response.addCookie(BaseService.createCookie(BaseService.COOKIE_NAMES.get(1),BaseService.getKey(user.getId()),30));
            response.addCookie(BaseService.createCookie(BaseService.COOKIE_NAMES.get(2),user.getUsername(),30));
            json.put(STATUS_NAME,STATUS_CODE_SUCCESS);
            session.setAttribute(URLInterceptor.SESSION_KEY,user.getUsername());
            try {
                response.sendRedirect("/index.html");
            }catch (Exception e){
                System.out.println(e.toString());
            }
        }else{
            BaseService.removeCookies(response);
            json.put(STATUS_NAME,STATUS_CODE_FILED);
            try {
                response.sendRedirect("/user/login");
            }catch (Exception e){
                System.out.println(e.toString());
            }
        }
        return json;
    }

    @ResponseBody
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ObjectNode add(User user, HttpServletRequest request, HttpServletResponse response) {
        json = mapper.createObjectNode();
        if (BaseService.checkNullStr(user.getUsername())) {
            json.put(STATUS_NAME, STATUS_CODE_NOT_FOUND);
            return json;
        } else if (userService.findByUsername(user.getUsername()) != null) {
            json.put(STATUS_NAME, STATUS_CODE_NOT_FOUND);
            return json;
        } else {
            user.setPassword(BaseService.getHash(user.getPassword(), "MD5"));
            return super.add(user);
        }
    }


    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public void logout(HttpSession session,HttpServletResponse response) throws Exception{
        session.removeAttribute(URLInterceptor.SESSION_KEY);
        response.sendRedirect("/user/login");
    }


}
