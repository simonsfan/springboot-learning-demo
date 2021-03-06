package com.simons.cn.springbootdemo.service.weixin;

import com.simons.cn.springbootdemo.bean.Movie;
import com.simons.cn.springbootdemo.dao.system.MovieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 项目名称：springbootdemo
 * 类名称：com.simons.cn.springbootdemo.service.Weixin
 * 类描述：
 * 创建人：simonsfan
 * 创建时间：2018/7/4 14:38
 */
@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private MovieMapper movieMapper;

    @Override
    public List<Movie> selectAll(Map<String,Object> movie) {
        return movieMapper.selectAll(movie);
    }

    @Override
    public void insertBatch(List<Movie> list) {
        movieMapper.insertBatch(list);
    }

    @Override
    public void addMovie(Movie movie) {
        movie.setOriginal("collection");
        movie.setType("movie");
        movie.setPasswd("no");
        movie.setCreateTime(new Date());
        movieMapper.insert(movie);
    }

    @Override
    public void deleteById(String id) {
        movieMapper.deleteByPrimaryKey(Long.parseLong(id));
    }

    @Override
    public Movie getByPrimarykey(String id){
        return movieMapper.selectByPrimaryKey(Long.parseLong(id));
    }

    @Override
    public void updateById(Map<String, Object> map) {
        movieMapper.updateById(map);
    }
}
