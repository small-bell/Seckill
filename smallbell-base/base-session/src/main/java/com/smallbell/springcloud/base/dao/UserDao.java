package com.smallbell.springcloud.base.dao;

import com.smallbell.springcloud.base.dao.po.UserPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by SmallBell on 2020/7/18.
 */
@Repository
public interface UserDao extends JpaRepository<UserPO, Long>, JpaSpecificationExecutor<UserPO>
{
    UserPO findByUserId(Long id);


    /**
     * 根据loginName 做查询
     *
     * @param loginName 登录名称
     * @return 用户列表
     */
    List<UserPO> findAllByUsername(String loginName);

}
