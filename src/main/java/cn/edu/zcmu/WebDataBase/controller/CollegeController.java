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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 院校控制层
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
     * 根据地区获取院校列表
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/byLocation")
    public ObjectNode findByLocationId(HttpServletRequest request) {
        List<College> colleges;
        json = mapper.createObjectNode();
        // 从request中获取json串
        String jsonStr = BaseService.getJson(request);
        int page = 1, size = 10;
        try {
            // 转换成json对象
            JSONObject jsonId = new JSONObject(jsonStr);
            jsonStr = jsonId.getString("id");
            page = Integer.parseInt(jsonId.getString("page"));
            size = Integer.parseInt(jsonId.getString("size"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // 分离数组左右的 []
        jsonStr = jsonStr.substring(1, jsonStr.length() - 1);
        if (jsonStr.equals(""))
            colleges = collegeService.findByLocationId(null, page, size);
        // 分离id
        String[] ids = jsonStr.split(",");
        // 组装id数组
        int[] id = new int[ids.length];
        for (int i = 0; i < id.length; i++) {
            if (ids[i].trim().equals(""))
                continue;
            id[i] = Integer.parseInt(ids[i].trim());
        }
        // 查询并返回数据
        colleges = collegeService.findByLocationId(id, page, size);
        ArrayNode collegeJsons = mapper.createArrayNode();
        for (College college : colleges) {
            ObjectNode collegeJson = mapper.createObjectNode();
            collegeJson.put("id", college.getId());
            collegeJson.put("name", college.getName());
            collegeJson.put("cCode", college.getcCode());
            collegeJson.put("cNature", college.getcNature());
            collegeJson.put("cType", college.getcType());
            collegeJson.put("ranking", college.getRanking());
            collegeJson.put("foundingYear", college.getFoundingYear().toString());
            collegeJson.put("location", college.getLocation().getName());
            collegeJsons.add(collegeJson);
        }
        json.set("colleges", collegeJsons);
        return json;
    }
}
