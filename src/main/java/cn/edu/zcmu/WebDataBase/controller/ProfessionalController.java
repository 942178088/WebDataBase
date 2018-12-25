package cn.edu.zcmu.WebDataBase.controller;

import cn.edu.zcmu.WebDataBase.entity.Professional;
import cn.edu.zcmu.WebDataBase.service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 院校 控制层
 */
@Controller
@RequestMapping("/professional")
public class ProfessionalController extends BaseController<Professional, Integer> {
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

    @Override
    public ObjectNode add(Professional professional) {
        if (professionalService.findRepeat(professional.getInstitute().getId(), professional.getKind().getId(), professional.getSubject().getId())) {
            json = mapper.createObjectNode();
            json.put(STATUS_NAME, STATUS_CODE_REPEAT);
            return json;
        } else {
            return super.add(professional);
        }
    }
}
