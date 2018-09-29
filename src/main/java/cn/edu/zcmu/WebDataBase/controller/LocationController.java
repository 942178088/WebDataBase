package cn.edu.zcmu.WebDataBase.controller;

import cn.edu.zcmu.WebDataBase.entity.Location;
import cn.edu.zcmu.WebDataBase.service.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 地区 控制层
 */
@Controller
@RequestMapping("/location")
public class LocationController extends BaseController {
    @Resource
    private LocationService locationService;

    /**
     * 获取地区列表
     *
     * @return json
     */
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Location> list() {
        List<Location> locations = locationService.findAll();
        return locations;
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
            locationService.testAddData();
            objectNode.put("status", 0);
        } catch (Exception e) {
            objectNode.put("status", 1);
            objectNode.put("message", e.getMessage());
        }
        return objectNode;
    }
}
