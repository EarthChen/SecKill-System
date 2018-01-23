package com.earthchen.seckill.dao;

import com.earthchen.seckill.domain.SecKillUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface SecKillUserDao {

    @Select("select * from seckill_user where id = #{id}")
    SecKillUser getById(@Param("id") long id);


}
