package cn.edu.zcmu.WebDataBase.controller;

import cn.edu.zcmu.WebDataBase.entity.College;
import cn.edu.zcmu.WebDataBase.entity.Location;
import cn.edu.zcmu.WebDataBase.service.CollegeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import javax.annotation.Resource;

@Controller
@RequestMapping("/college")
public class CollegeController extends BaseController{
    @Resource
    private CollegeService collegeService;

    @ResponseBody
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public List<College> findById(int id[]){
        List<College> colleges = collegeService.findById(id);
        return colleges;
    }
}
