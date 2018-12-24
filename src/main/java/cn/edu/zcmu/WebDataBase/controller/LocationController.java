package cn.edu.zcmu.WebDataBase.controller;

import cn.edu.zcmu.WebDataBase.entity.Location;
import cn.edu.zcmu.WebDataBase.service.BaseService;
import cn.edu.zcmu.WebDataBase.service.LocationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 地区 控制层
 */
@Controller
@RequestMapping("/location")
public class LocationController extends BaseController<Location, Integer> {
    @Resource
    private LocationService locationService;

    @Override
    public BaseService<Location, Integer> getService() {
        return locationService;
    }

    @Autowired
    public LocationController(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * 获取地区列表
     */
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ObjectNode list() {
        json = mapper.createObjectNode();
        List<Location> locations = (List<Location>) locationService.findAll();
        ArrayNode locationJsons = mapper.createArrayNode();
        for (Location location : locations) {
            ObjectNode locationJson = mapper.createObjectNode();
            locationJson.put("id", location.getId());
            locationJson.put("name", location.getName());
            locationJsons.add(locationJson);
        }
        json.set("locations", locationJsons);
        return json;
    }

}
