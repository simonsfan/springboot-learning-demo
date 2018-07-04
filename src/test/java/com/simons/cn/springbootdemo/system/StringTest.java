package com.simons.cn.springbootdemo.system;

import com.simons.cn.springbootdemo.bean.Movie;
import com.simons.cn.springbootdemo.dao.system.MovieMapper;
import com.simons.cn.springbootdemo.service.Weixin.IndexService;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：springbootdemo
 * 类名称：com.simons.cn.springbootdemo.system
 * 类描述：
 * 创建人：simonsfan
 * 创建时间：2018/7/4 15:35
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StringTest {

    @Autowired
    private IndexService indexService;
    @Autowired
    private MovieMapper movieMapper;

    @Test
    public void batchInsert() {
        String pass = "【百度云】https://pan.baidu.com/s/1DAkGuFkN8z9GIAWtswLThQ 密码: jkke";
        int i = pass.lastIndexOf(":");
        String passwd = pass.substring(i + 1);
        List<Movie> list = new ArrayList<>();
        for (int j = 0; j < 5; j++) {
            Movie movie = new Movie();
            movie.setName("simons1" + j);
            movie.setType("电影");
            movie.setLink("【百度云】https://pan.baidu.com/s/1DAkGuFkN8z9GIAWtswLThQ 密码: jkke");
            movie.setOriginal("批量插入");
            movie.setPasswd(passwd);
            list.add(movie);
        }
        indexService.insertBatch(list);
    }


    @Test
    public void testsub() {
        List<Movie> movies = movieMapper.findByName("燃烧".trim() + "%");
        String replymsg = "";
        if (CollectionUtils.isNotEmpty(movies)) {
            for (Movie movie : movies) {
                replymsg =replymsg+  movie.getName()+movie.getLink() + "\n\n";
            }
        }
        System.out.println(replymsg);
    }
}
