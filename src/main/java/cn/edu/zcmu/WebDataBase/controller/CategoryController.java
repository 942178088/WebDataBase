package cn.edu.zcmu.WebDataBase.controller;

import cn.edu.zcmu.WebDataBase.entity.Category;
import cn.edu.zcmu.WebDataBase.service.CategoryService;
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
 * 门类 控制层
 */
@Controller
@RequestMapping("/category")
public class CategoryController extends BaseController {
    @Resource
    private CategoryService categoryService;
    private ObjectMapper mapper;
    private ObjectNode json;

    @Autowired
    public CategoryController(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * 门类列表
     */
    @ResponseBody
    @GetMapping("/list")
    public ObjectNode list() {
        json = mapper.createObjectNode();
        List<Category> categories = (List<Category>) categoryService.findAll();
        ArrayNode arrayNode = mapper.createArrayNode();
        for (Category category : categories) {
            ObjectNode objectNode = mapper.createObjectNode();
            objectNode.put("id", category.getId());
            objectNode.put("name", category.getName());
            objectNode.put("code", category.getcCode());
            arrayNode.add(objectNode);
        }
        json.set("categories", arrayNode);
        json.put(STATUS_NAME, STATUS_CODE_SUCCESS);
        return json;
    }

    /**
     * 增加门类
     */
    @ResponseBody
    @PostMapping("/add")
    public ObjectNode add(Category category) {
        json = mapper.createObjectNode();
        category.setcTime(new Date());
        try {
            categoryService.save(category);
            json.put(STATUS_NAME, STATUS_CODE_SUCCESS);
        } catch (Exception e) {
            json.put(STATUS_NAME, STATUS_CODE_EXCEPTION);
            json.put("msg", e.getMessage());
        }
        return json;
    }
}
