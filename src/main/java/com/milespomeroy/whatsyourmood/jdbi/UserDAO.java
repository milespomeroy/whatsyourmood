package com.milespomeroy.whatsyourmood.jdbi;

import org.skife.jdbi.v2.sqlobject.SqlQuery;

import java.util.List;

public interface UserDAO {
    @SqlQuery("SELECT phone FROM users")
    List<String> findAll();

    void close();
}

