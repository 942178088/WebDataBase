package cn.edu.zcmu.WebDataBase.controller;

import cn.edu.zcmu.WebDataBase.entity.Subject;
import cn.edu.zcmu.WebDataBase.service.BaseService;
import cn.edu.zcmu.WebDataBase.service.SubjectService;
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
 * 学科 控制层
 */
@Controller
@RequestMapping("/subject")
public class SubjectController extends BaseController<Subject, Integer> {
    @Resource
    private SubjectService subjectService;
    private ObjectMapper mapper;
    private ObjectNode json;

    @Override
    public BaseService<Subject, Integer> getService() {
        return subjectService;
    }

    @Autowired
    public SubjectController(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * 二级学科列表
     */
    @ResponseBody
    @GetMapping("/child_list")
    public ObjectNode child_list(Integer id) {
        json = mapper.createObjectNode();
        List<Subject> subjects = subjectService.findAllChild(id);
        ArrayNode arrayNode = mapper.createArrayNode();
        for (Subject subject : subjects) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("id", subject.getId());
            objectNode.put("name", subject.getName());
            objectNode.put("code", subject.getsCode());
            arrayNode.add(objectNode);
        }
        json.set("subjects", arrayNode);
        json.put(STATUS_NAME, STATUS_CODE_SUCCESS);
        return json;
    }

    /**
     * 一级学科列表
     */
    @ResponseBody
    @GetMapping("/father_list")
    public ObjectNode list(Integer id) {
        json = mapper.createObjectNode();
        List<Subject> subjects = subjectService.findAllFather(id);
        ArrayNode arrayNode = mapper.createArrayNode();
        for (Subject subject : subjects) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("id", subject.getId());
            objectNode.put("name", subject.getName());
            objectNode.put("code", subject.getsCode());
            arrayNode.add(objectNode);
        }
        json.set("subjects", arrayNode);
        json.put(STATUS_NAME, STATUS_CODE_SUCCESS);
        return json;
    }

}
