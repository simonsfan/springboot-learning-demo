package com.simons.cn.springbootdemo.service.Weixin;

import com.simons.cn.springbootdemo.bean.Movie;
import com.simons.cn.springbootdemo.dao.system.MovieMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Movie> selectAll(Movie movie) {
        return movieMapper.selectAll(movie);
    }

    @Override
    public void insertBatch(List<Movie> list) {
        movieMapper.insertBatch(list);
    }
}
