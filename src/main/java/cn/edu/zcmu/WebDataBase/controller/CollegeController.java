package cn.edu.zcmu.WebDataBase.controller;

import cn.edu.zcmu.WebDataBase.entity.College;
import cn.edu.zcmu.WebDataBase.service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 院校 控制层
 */
@Controller
@RequestMapping("/college")
public class CollegeController extends BaseController<College, Integer> {
    @Resource
    private CollegeService collegeService;
    @Resource
    private LocationService locationService;
    @Resource
    private NatureService natureService;
    @Resource
    private SpecialityService specialityService;
    @Resource
    private TypeService typeService;

    @Override
    public BaseService<College, Integer> getService() {
        return collegeService;
    }

    @Autowired
    public CollegeController(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * 重写 增 / 删
     */
    @Override
    public ObjectNode add(College college) {
        if (college.getcNature().getId() != null) {
            college.setcNature(natureService.findById(college.getcNature().getId()));
        }
        if (college.getcType().getId() != null) {
            college.setcType(typeService.findById(college.getcType().getId()));
        }
        if (college.getLocation().getId() != null) {
            college.setLocation(locationService.findById(college.getLocation().getId()));
        }
        if (BaseService.checkNullStr(college.getName()) || BaseService.checkNullStr(college.getcCode())) {
            json = mapper.createObjectNode();
            json.put(STATUS_NAME, STATUS_CODE_FILED);
            return json;
        } else {
            return super.add(college);
        }
    }

    /**
     * 根据ID或者代码查询院校
     */
    @GetMapping(value = "/{idOrCode}")
    public String one(@PathVariable("idOrCode") String idOrCode, Map<String, Object> map) {
        College college;
        try {
            Integer id = Integer.parseInt(idOrCode.trim());
            college = collegeService.findById(id);
            map.put("college", college);
        } catch (Exception e) {
            college = collegeService.findByCode(idOrCode);
            map.put("college", college);
        }
        return "college";
    }

    /**
     * 查询全部
     */
    @ResponseBody
    @RequestMapping(value = "/list")
    public ObjectNode list(String keyword, Integer page, Integer size) {
        Page<College> colleges;
        if (!BaseService.checkNullStr(keyword)) {
            colleges = collegeService.findAllByNameAndCode(keyword, BaseService.buildPageable(page, size, BaseService.buildSort("id", "ASC")));
        } else {
            colleges = collegeService.findAll(BaseService.buildPageable(page, size, BaseService.buildSort("id", "ASC")));
        }
        json = mapper.createObjectNode();
        ArrayNode collegeJsons = mapper.createArrayNode();
        for (College college : colleges.getContent()) {
            ObjectNode collegeJson = mapper.createObjectNode();
            collegeJson.put("id", college.getId());
            collegeJson.put("name", college.getName());
            collegeJson.put("cCode", college.getcCode());
            collegeJson.put("cNature", college.getcNature().getName());
            collegeJson.put("cType", college.getcType().getName());
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
    @RequestMapping(value = "/index")
    public ObjectNode index(String keyword, Integer page, Integer size, HttpServletRequest request) {
        Page<College> colleges = null;
        json = mapper.createObjectNode();
        Integer[] locationId;
        Integer[] natureId;
        Integer[] specialityId;
        Integer[] typeId;
        // 从request中获取json串
        String jsonStr = BaseService.getJson(request);
        try {
            // 转换成json对象
            JSONObject jsonIds = new JSONObject(jsonStr);
            String locationIdStr = jsonIds.getString("location_id");
            String natureIdStr = jsonIds.getString("nature_id");
            String specialityIdStr = jsonIds.getString("speciality_id");
            String typeIdStr = jsonIds.getString("type_id");
            // 分离数组左右的 []
            locationIdStr = locationIdStr.substring(1, locationIdStr.length() - 1);
            if (locationIdStr.equals("")) {
                locationId = locationService.getAllId();
            } else {
                // 分离id
                String[] locationIds = locationIdStr.split(",");
                // 组装id数组
                locationId = new Integer[locationIds.length];
                for (int i = 0; i < locationId.length; i++) {
                    if (locationIds[i].trim().equals(""))
                        continue;
                    locationId[i] = Integer.parseInt(locationIds[i].trim());
                }
            }
            natureIdStr = natureIdStr.substring(1, natureIdStr.length() - 1);
            if (natureIdStr.equals("")) {
                natureId = natureService.getAllId();
            } else {
                // 分离id
                String[] natureIds = natureIdStr.split(",");
                // 组装id数组
                natureId = new Integer[natureIds.length];
                for (int i = 0; i < natureId.length; i++) {
                    if (natureIds[i].trim().equals(""))
                        continue;
                    natureId[i] = Integer.parseInt(natureIds[i].trim());
                }
            }
            specialityIdStr = specialityIdStr.substring(1, specialityIdStr.length() - 1);
            if (specialityIdStr.equals("")) {
                specialityId = specialityService.getAllId();
            } else {
                String[] specialityIds = specialityIdStr.split(",");
                specialityId = new Integer[specialityIds.length];
                for (int i = 0; i < specialityId.length; i++) {
                    if (specialityIds[i].trim().equals(""))
                        continue;
                    specialityId[i] = Integer.parseInt(specialityIds[i].trim());
                }
            }
            typeIdStr = typeIdStr.substring(1, typeIdStr.length() - 1);
            if (typeIdStr.equals("")) {
                typeId = typeService.getAllId();
            } else {
                String[] typeIds = typeIdStr.split(",");
                typeId = new Integer[typeIds.length];
                for (int i = 0; i < typeId.length; i++) {
                    if (typeIds[i].trim().equals(""))
                        continue;
                    typeId[i] = Integer.parseInt(typeIds[i].trim());
                }
            }
            colleges = collegeService.index(locationId, natureId, specialityId, typeId, keyword,
                    BaseService.buildPageable(page, size, BaseService.buildSort("id", "ASC")));
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
            collegeJson.put("cType", college.getcType().getName());
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
