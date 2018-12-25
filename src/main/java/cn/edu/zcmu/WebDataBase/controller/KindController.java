package cn.edu.zcmu.WebDataBase.controller;

import cn.edu.zcmu.WebDataBase.entity.Kind;
import cn.edu.zcmu.WebDataBase.service.BaseService;
import cn.edu.zcmu.WebDataBase.service.KindService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 种类 控制层
 */
@Controller
@RequestMapping("/kind")
public class KindController extends BaseController<Kind, Integer> {
    @Resource
    private KindService kindService;

    @Override
    public BaseService<Kind, Integer> getService() {
        return kindService;
    }

    @Autowired
    public KindController(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public ObjectNode add(Kind kind) {
        if (BaseService.checkNullStr(kind.getName())) {
            json.put(STATUS_NAME, STATUS_CODE_FILED);
            return json;
        } else {
            return super.add(kind);
        }
    }

    @ResponseBody
    @GetMapping("/list")
    public ObjectNode list() {
        json = mapper.createObjectNode();
        List<Kind> kinds = (List<Kind>) kindService.findAll();
        ArrayNode arrayNode = mapper.createArrayNode();
        for (Kind kind : kinds) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("id", kind.getId());
            objectNode.put("name", kind.getName());
            arrayNode.add(objectNode);
        }
        json.set("kinds", arrayNode);
        json.put(STATUS_NAME, STATUS_CODE_SUCCESS);
        return json;
    }

}
