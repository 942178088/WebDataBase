package cn.edu.zcmu.WebDataBase.controller;

import cn.edu.zcmu.WebDataBase.entity.Institute;
import cn.edu.zcmu.WebDataBase.entity.Professional;
import cn.edu.zcmu.WebDataBase.service.BaseService;
import cn.edu.zcmu.WebDataBase.service.CollegeService;
import cn.edu.zcmu.WebDataBase.service.InstituteService;
import cn.edu.zcmu.WebDataBase.service.ProfessionalService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 院校 控制层
 */
@Controller
@RequestMapping("/institute")
public class InstituteController extends BaseController<Institute, Integer> {
    @Resource
    private InstituteService instituteService;

    @Resource
    private ProfessionalService professionalService;

    @Resource
    private CollegeService collegeService;

    @Override
    public BaseService<Institute, Integer> getService() {
        return instituteService;
    }

    @Autowired
    public InstituteController(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * 增加学院
     */
    @ResponseBody
    @PostMapping("/add")
    public ObjectNode add(Institute institute) {
        institute.setCollege(collegeService.findById(institute.getCollege().getId()));
        json = mapper.createObjectNode();
        if (BaseService.checkNullStr(institute.getName())) {
            json.put(STATUS_NAME, STATUS_CODE_FILED);
            return json;
        }
        if (institute.getiTime() == null) {
            institute.setiTime(new Date());
        }
        try {
            instituteService.save(institute);
            json.put(STATUS_NAME, STATUS_CODE_SUCCESS);
        } catch (Exception e) {
            json.put(STATUS_NAME, STATUS_CODE_EXCEPTION);
            json.put("msg", e.getMessage());
        }
        return json;
    }

    /**
     * 查询指定院校下属的学院
     */
    @ResponseBody
    @GetMapping("/list")
    public ObjectNode list(Integer id) {
        json = mapper.createObjectNode();
        List<Institute> institutes = instituteService.findAllByCollege(id);
        ArrayNode instituteJsons = mapper.createArrayNode();
        for (Institute institute : institutes) {
            ObjectNode institureJson = mapper.createObjectNode();
            institureJson.put("id", institute.getId());
            institureJson.put("name", institute.getName());
            List<Professional> professionals = professionalService.findByInstitute(institute.getId());
            ArrayNode professionalJsons = mapper.createArrayNode();
            for (Professional professional : professionals) {
                ObjectNode professionalJson = mapper.createObjectNode();
                professionalJson.put("id", professional.getId());
                professionalJson.put("name", professional.getSubject().getName());
                professionalJson.put("code", professional.getSubject().getsCode());
                professionalJson.put("category", professional.getSubject().getCategory().getName());
                professionalJson.put("kind", professional.getKind().getName());
                professionalJsons.add(professionalJson);
            }
            institureJson.set("professional", professionalJsons);
            instituteJsons.add(institureJson);
        }
        json.set("institute", instituteJsons);
        json.put(STATUS_NAME, STATUS_CODE_SUCCESS);
        return json;
    }
}
