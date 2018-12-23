package cn.edu.zcmu.WebDataBase.controller;

import cn.edu.zcmu.WebDataBase.entity.Professional;
import cn.edu.zcmu.WebDataBase.service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 院校 控制层
 */
@Controller
@RequestMapping("/professional")
public class ProfessionalController extends BaseController {
    @Resource
    private ProfessionalService professionalService;
    private ObjectMapper mapper;
    private ObjectNode json;

    @Autowired
    public ProfessionalController(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * 增加专业
     */
    @ResponseBody
    @PostMapping("/add")
    public ObjectNode add(Professional professional) {
        json = mapper.createObjectNode();
        professional.setpTime(new Date());
        try {
            if (professionalService.save(professional)) {
                json.put(STATUS_NAME, STATUS_CODE_SUCCESS);
            } else {
                json.put(STATUS_NAME, STATUS_CODE_FILED);
            }
        } catch (Exception e) {
            json.put(STATUS_NAME, STATUS_CODE_EXCEPTION);
            json.put("msg", e.getMessage());
        }
        return json;
    }
}
