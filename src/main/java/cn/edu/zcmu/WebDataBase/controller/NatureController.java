package cn.edu.zcmu.WebDataBase.controller;

import cn.edu.zcmu.WebDataBase.entity.Nature;
import cn.edu.zcmu.WebDataBase.service.BaseService;
import cn.edu.zcmu.WebDataBase.service.NatureService;
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
 * 高等院校，科研院所
 */
@Controller
@RequestMapping("/nature")
public class NatureController extends BaseController<Nature, Integer> {
    @Resource
    private NatureService natureService;

    @Override
    public BaseService<Nature, Integer> getService() {
        return natureService;
    }

    @Autowired
    public NatureController(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ObjectNode list() {
        json = mapper.createObjectNode();
        List<Nature> natures = (List<Nature>) natureService.findAll();
        ArrayNode specialityJsons = mapper.createArrayNode();
        for (Nature nature : natures) {
            ObjectNode specialityJson = mapper.createObjectNode();
            specialityJson.put("id", nature.getId());
            specialityJson.put("name", nature.getName());
            specialityJsons.add(specialityJson);
        }
        json.set("natures", specialityJsons);
        return json;
    }

}
