package com.simons.cn.springbootdemo.service.weixin;

import com.simons.cn.springbootdemo.bean.Movie;

import java.util.List;
import java.util.Map;

/**
 * 项目名称：springbootdemo
 * 类名称：com.simons.cn.springbootdemo.service.Weixin
 * 类描述：
 * 创建人：simonsfan
 * 创建时间：2018/7/4 14:38
 */
public interface IndexService {

    List<Movie> selectAll(Map<String,Object> movie);

    void insertBatch(List<Movie> list);

    void addMovie(Movie movie);

    void deleteById(String id);

    Movie getByPrimarykey(String id);

    void updateById(Map<String,Object> map);
}
