package cn.edu.zcmu.WebDataBase.controller;

import cn.edu.zcmu.WebDataBase.entity.Type;
import cn.edu.zcmu.WebDataBase.service.TypeService;
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
 * 院校类型 控制层
 * 综合类，理工类.....
 */
@Controller
@RequestMapping("/type")
public class TypeController extends BaseController {
    @Resource
    private TypeService typeService;

    private ObjectMapper mapper;
    private ObjectNode json;

    @Autowired
    public TypeController(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * 获取院校类型列表
     */
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ObjectNode list() {
        json = mapper.createObjectNode();
        List<Type> types = (List<Type>) typeService.findAll();
        ArrayNode specialityJsons = mapper.createArrayNode();
        for (Type type : types) {
            ObjectNode specialityJson = mapper.createObjectNode();
            specialityJson.put("id", type.getId());
            specialityJson.put("name", type.getName());
            specialityJsons.add(specialityJson);
        }
        json.set("types", specialityJsons);
        return json;
    }
}