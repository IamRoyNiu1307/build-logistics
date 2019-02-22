package com.zoo.logistics.controller;


import com.zoo.logistics.entity.Admin;
import com.zoo.logistics.entity.Car;
import com.zoo.logistics.entity.EquipmentReceipt;
import com.zoo.logistics.entity.Order;
import com.zoo.logistics.service.CarService;
import com.zoo.logistics.service.EquipmentReceiptService;
import com.zoo.logistics.service.LogService;
import com.zoo.logistics.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/equipmentReceipt")
public class EquipmentReceiptController {

    @Autowired
    private EquipmentReceiptService equipmentReceiptService;
    @Autowired
    private CarService carService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private LogService logService;

    @RequestMapping("/page")
    public String toDeliveryPage(){
        return "equipment-receipt";
    }

    /**
     * 查找交接单
     * @param map
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/searchEquipmentReceipt")
    public Map searchEquipmentReceipt(@RequestBody Map map, HttpServletRequest request){
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        Map resultMap = equipmentReceiptService.selectEquipmentReceiptByLicenseNumber(map.get("licenseNumber").toString(), admin.getStationId());
        return resultMap;
    }

    /**
     * 确认交接单
     * @param map
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/confirmEquipmentReceipt")
    public String confirmEquipmentReceipt(@RequestBody Map map, HttpServletRequest request){
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        int equipmentReceiptId = Integer.parseInt(map.get("equipmentReceiptId").toString());
        //更新日志信息
        List<Order> orderList = orderService.selectByEquipmentReceiptId(equipmentReceiptId);
        logService.batchUpdate(orderList,"【"+admin.getStation().getStationName()+"】已入库");
        //更改包裹状态
        orderService.confirmEquipmentReceipt(equipmentReceiptId,admin.getStationId());
        //更改车辆状态
        Car car = carService.selectByEquipmentReceiptId(equipmentReceiptId);
        int carStatus = car.getCarStatus()==2?3:1;
        car.setCarStatus(carStatus);
        carService.updateByPrimaryKeySelective(car);
        //更改交接单状态
        EquipmentReceipt equipmentReceipt = equipmentReceiptService.selectByPrimaryKey(equipmentReceiptId);
        equipmentReceipt.setStatus(1);
        equipmentReceipt.setFinishDate(new Date());
        equipmentReceiptService.updateByPrimaryKeySelective(equipmentReceipt);

        return "success";
    }
}
