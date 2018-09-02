package com.simons.cn.springbootdemo.dao.system;


import com.simons.cn.springbootdemo.bean.MovieRecord;

public interface MovieRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MovieRecord record);

    int insertSelective(MovieRecord record);

    MovieRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MovieRecord record);

    int updateByPrimaryKey(MovieRecord record);
}