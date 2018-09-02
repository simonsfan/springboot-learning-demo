package com.simons.cn.springbootdemo.dao.system;

import com.simons.cn.springbootdemo.bean.MovieInvalid;

public interface MovieInvalidMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MovieInvalid record);

    int insertSelective(MovieInvalid record);

    MovieInvalid selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MovieInvalid record);

    int updateByPrimaryKey(MovieInvalid record);
}