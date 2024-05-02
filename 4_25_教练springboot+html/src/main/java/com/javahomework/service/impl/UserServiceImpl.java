package com.javahomework.service.impl;

import com.javahomework.entity.User;
import com.javahomework.mapper.UserMapper;
import com.javahomework.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author com
 * @since 2024-04-25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
