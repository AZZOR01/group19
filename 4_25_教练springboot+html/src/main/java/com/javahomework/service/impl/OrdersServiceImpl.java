package com.javahomework.service.impl;

import com.javahomework.entity.Orders;
import com.javahomework.mapper.OrdersMapper;
import com.javahomework.service.IOrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author com
 * @since 2024-04-28
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements IOrdersService {

}
