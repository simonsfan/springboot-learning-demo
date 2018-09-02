package com.simons.cn.springbootdemo.dao.system;

import com.simons.cn.springbootdemo.bean.Movie;

import java.util.List;
import java.util.Map;

public interface MovieMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Movie record);

    int insertSelective(Movie record);

    Movie selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Movie record);

    int updateByPrimaryKey(Movie record);

    List<Movie> selectAll(Map<String,Object> movie);

    void insertBatch(List<Movie>list);

    List<Movie> findByName(String name);
}
