

package com.smallbell.springcloud.sharding.jdbc.demo.dao;


import com.smallbell.springcloud.sharding.jdbc.demo.entity.User;

import java.util.List;

public interface UserRepository
{
    Long insert(User user);

    void delete(Long userId);

    @SuppressWarnings("unchecked")
    List<User> selectAll();
}
