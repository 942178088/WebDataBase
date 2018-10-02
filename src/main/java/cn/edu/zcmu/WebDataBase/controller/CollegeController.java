package cn.edu.zcmu.WebDataBase.controller;

import cn.edu.zcmu.WebDataBase.entity.College;
import cn.edu.zcmu.WebDataBase.service.BaseService;
import cn.edu.zcmu.WebDataBase.service.CollegeService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/college")
public class CollegeController extends BaseController {
    @Resource
    private CollegeService collegeService;

    /**
     * 根据地区获取院校列表
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/byLocation")
    public List<College> findByLocationId(HttpServletRequest request) {
        String json = BaseService.getJson(request);
        System.out.println(json);
        try {
            JSONObject jsonId = new JSONObject(json);
            json = jsonId.getString("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(json);
        json = json.substring(1, json.length() - 1);
        System.out.println(json);
        String[] ids = json.split(",");
        int[] id = new int[ids.length];
        for (int i = 0; i < id.length; i++) {
            id[i] = Integer.parseInt(ids[i].trim());
        }
        List<College> colleges = collegeService.findByLocationId(id);
        return colleges;
    }

    /**
     * 添加测试数据
     *
     * @return json
     */
    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ObjectNode test() {
        try {
            collegeService.testAddData();
            objectNode.put("status", 0);
        } catch (Exception e) {
            objectNode.put("status", 1);
            objectNode.put("message", e.getMessage());
        }
        return objectNode;
    }
}
