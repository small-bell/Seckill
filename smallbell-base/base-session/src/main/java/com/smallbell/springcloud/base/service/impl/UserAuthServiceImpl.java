package com.smallbell.springcloud.base.service.impl;

import com.smallbell.springcloud.base.dao.UserDao;
import com.smallbell.springcloud.base.dao.po.UserPO;
import com.smallbell.springcloud.common.dto.UserDTO;
import com.smallbell.springcloud.common.result.RestOut;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserAuthServiceImpl
{

    @Autowired(required = false)
    private UserDao userDao;


    public RestOut<UserDTO> getUser(Long id)
    {
        UserPO userPO = userDao.findByUserId(id);
        if (userPO != null)
        {
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(userPO, userDTO);
            return RestOut.success(userDTO);
        } else
        {
            return RestOut.error("未找到指定用户" );
        }
    }

}
