package cn.edu.zcmu.WebDataBase.controller;

import cn.edu.zcmu.WebDataBase.entity.College;
import cn.edu.zcmu.WebDataBase.service.BaseService;
import cn.edu.zcmu.WebDataBase.service.CollegeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 院校 控制层
 */
@Controller
@RequestMapping("/college")
public class CollegeController extends BaseController {
    @Resource
    private CollegeService collegeService;
    private ObjectMapper mapper;
    private ObjectNode json;

    @Autowired
    public CollegeController(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * 查询全部
     */
    @ResponseBody
    @RequestMapping(value = "/list")
    public ObjectNode list(Integer page, Integer size) {
        Page<College> colleges = collegeService.findAll(BaseService.buildPageable(page, size, BaseService.buildSort("id", "ASC")));
        json = mapper.createObjectNode();
        ArrayNode collegeJsons = mapper.createArrayNode();
        for (College college : colleges.getContent()) {
            ObjectNode collegeJson = mapper.createObjectNode();
            collegeJson.put("id", college.getId());
            collegeJson.put("name", college.getName());
            collegeJson.put("cCode", college.getcCode());
            collegeJson.put("cNature", college.getcNature().getName());
            collegeJson.put("cType", college.getcType());
            collegeJson.put("ranking", college.getRanking());
            if (college.getFoundingYear() != null) {
                collegeJson.put("foundingYear", college.getFoundingYear().toString());
            }
            collegeJson.put("location", college.getLocation().getName());
            collegeJsons.add(collegeJson);
        }
        json.set("colleges", collegeJsons);
        json.put("total_pages", colleges.getTotalPages());
        json.put("total_elements", colleges.getTotalElements());
        json.put(STATUS_NAME, STATUS_CODE_SUCCESS);
        return json;
    }

    /**
     * 根据地区获取院校列表
     */
    @ResponseBody
    @RequestMapping(value = "/byLocation")
    public ObjectNode findByLocationId(Integer page, Integer size, HttpServletRequest request) {
        Page<College> colleges = null;
        json = mapper.createObjectNode();
        // 从request中获取json串
        String jsonStr = BaseService.getJson(request);
        try {
            // 转换成json对象
            JSONObject jsonId = new JSONObject(jsonStr);
            jsonStr = jsonId.getString("id");
            // 分离数组左右的 []
            jsonStr = jsonStr.substring(1, jsonStr.length() - 1);
            // 分离id
            String[] ids = jsonStr.split(",");
            // 组装id数组
            int[] id = new int[ids.length];
            for (int i = 0; i < id.length; i++) {
                if (ids[i].trim().equals(""))
                    continue;
                id[i] = Integer.parseInt(ids[i].trim());
            }
            colleges = collegeService.findByLocationId(id, BaseService.buildPageable(page, size, BaseService.buildSort("id", "ASC")));
        } catch (JSONException e) {
            json.put(STATUS_NAME, STATUS_CODE_EXCEPTION);
            json.put("e_msg", e.getMessage());
        }
        ArrayNode collegeJsons = mapper.createArrayNode();
        for (College college : colleges.getContent()) {
            ObjectNode collegeJson = mapper.createObjectNode();
            collegeJson.put("id", college.getId());
            collegeJson.put("name", college.getName());
            collegeJson.put("cCode", college.getcCode());
            collegeJson.put("cNature", college.getcNature().getName());
            collegeJson.put("cType", college.getcType());
            collegeJson.put("ranking", college.getRanking());
            if (college.getFoundingYear() != null) {
                collegeJson.put("foundingYear", college.getFoundingYear().toString());
            }
            collegeJson.put("location", college.getLocation().getName());
            collegeJsons.add(collegeJson);
        }
        json.put("total_pages", colleges.getTotalPages());
        json.put("total_elements", colleges.getTotalElements());
        json.set("colleges", collegeJsons);
        json.put(STATUS_NAME, STATUS_CODE_SUCCESS);
        return json;
    }
}
