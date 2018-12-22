package cn.edu.zcmu.WebDataBase.controller;

import cn.edu.zcmu.WebDataBase.entity.Speciality;
import cn.edu.zcmu.WebDataBase.service.SpecialityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 院校特性 控制层
 * 985，211
 */
@Controller
@RequestMapping("/speciality")
public class SpecialityController extends BaseController {
    @Resource
    private SpecialityService specialityService;

    private ObjectMapper mapper;
    private ObjectNode json;

    @Autowired
    public SpecialityController(ObjectMapper mapper) {
        this.mapper = mapper;
    }


    /**
     * 获取院校特性列表
     */
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ObjectNode list() {
        json = mapper.createObjectNode();
        List<Speciality> specialities = (List<Speciality>) specialityService.findAll();
        ArrayNode specialityJsons = mapper.createArrayNode();
        for (Speciality speciality : specialities) {
            ObjectNode specialityJson = mapper.createObjectNode();
            specialityJson.put("id", speciality.getId());
            specialityJson.put("name", speciality.getName());
            specialityJsons.add(specialityJson);
        }
        json.set("specialities", specialityJsons);
        return json;
    }
}
