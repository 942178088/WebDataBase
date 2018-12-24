package cn.edu.zcmu.WebDataBase.controller;

import cn.edu.zcmu.WebDataBase.entity.Professional;
import cn.edu.zcmu.WebDataBase.service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 院校 控制层
 */
@Controller
@RequestMapping("/professional")
public class ProfessionalController extends BaseController<Professional,Integer> {
    @Resource
    private ProfessionalService professionalService;

    @Override
    public BaseService<Professional, Integer> getService() {
        return professionalService;
    }

    @Autowired
    public ProfessionalController(ObjectMapper mapper) {
        this.mapper = mapper;
    }

}
