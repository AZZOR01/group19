package com.javahomework.controller;

import com.javahomework.common.Result;
import com.javahomework.entity.Orders;
import com.javahomework.entity.Reservation;
import com.javahomework.entity.User;
import com.javahomework.service.IOrdersService;
import com.javahomework.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author com
 * @since 2024-04-28
 */
@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private IOrdersService iOrdersService;

    @Autowired
    private IUserService iUserService;

    @PostMapping("/add")
    public Result add(@RequestBody HashMap map) {
        Integer userId = (Integer) map.get("userId");
        Integer star = (Integer) map.get("star");
        Integer num = (Integer) map.get("num");
        Integer money = (Integer) map.get("money");

        User user = iUserService.getById(userId);
        user.setNum(user.getNum() + num);
        Integer newStar = user.getStar() > star ? user.getStar() : star;
        user.setStar(newStar);
        long epochSecond = LocalDateTime.now().plusMonths(6).toEpochSecond(ZoneOffset.UTC);
        user.setExpiration(epochSecond);
        iUserService.updateById(user);

        Orders orders = new Orders();
        orders.setId(System.currentTimeMillis() / 1000);
        orders.setUserId(userId);
        orders.setName(user.getName());
        orders.setFrequency(num);
        orders.setMoney(Double.valueOf(money));
        iOrdersService.save(orders);

        return Result.suc();
    }

    @GetMapping("/list")
    public Result list() {
        return Result.suc(iOrdersService.list());
    }

    //删除
    @GetMapping("/delete")
    public Result delete(@RequestParam String id) {
        System.out.println(id);
        return iOrdersService.removeById(id) ? Result.suc() : Result.fail();
    }

}
