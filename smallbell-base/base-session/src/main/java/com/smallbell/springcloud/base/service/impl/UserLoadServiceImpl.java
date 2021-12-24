package com.smallbell.springcloud.base.service.impl;

import com.smallbell.springcloud.base.dao.SysUserDao;
import com.smallbell.springcloud.base.dao.UserDao;
import com.smallbell.springcloud.base.dao.po.SysUserPO;
import com.smallbell.springcloud.base.dao.po.UserPO;
import com.smallbell.springcloud.common.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

/**
 * Created by SmallBell on 2020/7/18.
 */

@Slf4j
public class UserLoadServiceImpl
{


    private UserDao userDao;

    private SysUserDao sysUserDao;


    public UserLoadServiceImpl(UserDao userDao, SysUserDao sysUserDao)
    {
        this.userDao = userDao;
        this.sysUserDao = sysUserDao;
    }

    /**
     * 装载前端的用户
     *
     * @param userId
     * @return
     */
    public UserDTO loadFrontEndUser(Long userId)
    {
        UserPO userPO = userDao.findByUserId(userId);
        if (userPO != null)
        {
            UserDTO dto = new UserDTO();

            BeanUtils.copyProperties(userPO, dto);

            return dto;
        }
        return null;
    }

    public UserDTO loadBackEndUser(Long userId)
    {
        SysUserPO userPO = sysUserDao.getOne(userId);
        if (userPO != null)
        {
            UserDTO dto = new UserDTO();

            BeanUtils.copyProperties(userPO, dto);

            return dto;
        }
        return null;
    }


}
