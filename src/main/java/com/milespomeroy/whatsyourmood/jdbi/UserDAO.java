package com.milespomeroy.whatsyourmood.jdbi;

import com.milespomeroy.whatsyourmood.core.User;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(UserMapper.class)
public interface UserDAO {
    @SqlQuery("SELECT phone FROM users")
    List<String> findAll();

    @SqlQuery("SELECT phone, name FROM users WHERE phone = :phone")
    User findUserByPhone(@Bind("phone") String phone);

    void close();
}

