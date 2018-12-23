package cn.edu.zcmu.WebDataBase.controller;

import cn.edu.zcmu.WebDataBase.entity.Subject;
import cn.edu.zcmu.WebDataBase.service.SubjectService;
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
 * 学科 控制层
 */
@Controller
@RequestMapping("/subject")
public class SubjectController extends BaseController {
    @Resource
    private SubjectService subjectService;
    private ObjectMapper mapper;
    private ObjectNode json;

    @Autowired
    public SubjectController(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * 门类列表
     */
    @ResponseBody
    @GetMapping("/list")
    public ObjectNode list() {
        json = mapper.createObjectNode();
        List<Subject> subjects = (List<Subject>) subjectService.findAll();
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
     * 增加门类
     */
    @ResponseBody
    @PostMapping("/add")
    public ObjectNode add(Subject subject) {
        json = mapper.createObjectNode();
        subject.setsTime(new Date());
        try {
            subjectService.save(subject);
            json.put(STATUS_NAME, STATUS_CODE_SUCCESS);
        } catch (Exception e) {
            json.put(STATUS_NAME, STATUS_CODE_EXCEPTION);
            json.put("msg", e.getMessage());
        }
        return json;
    }
}
