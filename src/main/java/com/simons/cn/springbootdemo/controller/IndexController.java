package com.simons.cn.springbootdemo.controller;

import com.simons.cn.springbootdemo.bean.Movie;
import com.simons.cn.springbootdemo.bean.UrlInfo;
import com.simons.cn.springbootdemo.service.Weixin.IndexService;
import com.simons.cn.springbootdemo.util.GuavaRateLimiterService;
import com.simons.cn.springbootdemo.util.Result;
import com.simons.cn.springbootdemo.util.ResultUtil;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：springbootdemo
 * 类名称：IndexController
 * 类描述：测试springboot修改项目属性
 * 创建人：simonsfan
 * 创建时间：2018/6/25 17:33
 */
@Controller
public class IndexController {

    private static final Logger log= LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private UrlInfo urlInfo; //常量封装类-测试

    @Autowired
    private IndexService indexService;

    @Autowired
    private GuavaRateLimiterService rateLimiterService;

    /**
     * 导入功能，暂时先实现一个最简单的版本，后期完善
     */
    @PostMapping("/import")
    @ResponseBody
    public String importMovie(MultipartFile fileParam) {
        if (fileParam == null) {
            return "文件为空";
        }
        File cardExcel = null;
        Workbook wb = null;
        String path = IndexController.class.getClassLoader().getResource("/").getPath();
        path = path.substring(0, path.indexOf("WEB-INF") + "WEB-INF".length()) + "/" + fileParam.getOriginalFilename();
        cardExcel = new File(path);

        try {
            FileCopyUtils.copy(fileParam.getBytes(), cardExcel);

            wb = WorkbookFactory.create(new FileInputStream(cardExcel));
            List<Movie> list = new ArrayList<>();
            int totalNum = 0;
            for (int index = 0; index < wb.getNumberOfSheets(); index++) {
                Sheet sheet = wb.getSheetAt(index);
                if (sheet == null) {
                    continue;
                }
                if (sheet.getLastRowNum() > 0) {
                    totalNum += sheet.getLastRowNum() + 1;
                }
                for (int rowNum = 1; rowNum < sheet.getPhysicalNumberOfRows(); rowNum++) {  //rowNum取1因为有一行表头
                    /*Row row = sheet.getRow(rowNum);
                    Cell cell0 = row.getCell(0);
                    Cell cell1 = row.getCell(1);
                    Cell cell2 = row.getCell(2);
                    Cell cell3 = row.getCell(3);
                    if (cell0 == null || cell1 == null || cell2 == null || cell3 == null) {
                        continue;
                    }
                    String name = this.getCellValue(cell0);
                    String type = this.getCellValue(cell1);
                    String link = this.getCellValue(cell2);
                    String original = this.getCellValue(cell3);

                    Movie movie = new Movie();
                    movie.setName(name);
                    movie.setType(type);
                    movie.setLink(link);
                    movie.setOriginal(original);
                    String passwd = link.substring(link.lastIndexOf(":") + 1).trim();
                    movie.setPasswd(passwd);*/

                    Row row = sheet.getRow(rowNum);
                    Cell cell2 = row.getCell(2);
                    Cell cell3 = row.getCell(3);
                    if (cell2 == null || cell3 == null) {
                        continue;
                    }
                    String name = this.getCellValue(cell2);
                    String original = this.getCellValue(cell3);

                    if(!name.contains("链接")){
                        log.info("没有链接字段的="+name);
                        continue;
                    }
                    String moviename = name.substring(0, name.indexOf("链接"));

                    Movie movie = new Movie();
                    movie.setName(moviename);
                    movie.setType("电影");
                    movie.setLink(name);
                    movie.setOriginal(original);
                    String passwd = name.substring(name.lastIndexOf(":") + 1).trim();
                    movie.setPasswd(passwd);
                    list.add(movie);
                }
                indexService.insertBatch(list);
            }
            cardExcel.delete();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("导入失败异常="+e);
            return "导入失败";
        }
        return "导入成功";
    }

    public static String getCellValue(Cell cell) {
        if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            Double d = cell.getNumericCellValue();
            return String.valueOf(d.intValue());
        }
        return String.valueOf(cell.getStringCellValue());
    }

    /**
     * 电影后台首页展示页
     *
     * @param model
     * @param movie
     * @return
     */
    @RequestMapping("/movie/update")
    public String updateMovieById(Model model, Movie movie) {
        List<Movie> movies = indexService.selectAll(movie);
        model.addAttribute("movieobj", movie);
        model.addAttribute("movielist", movies);
        return "/index";
    }

    /**
     * 电影后台首页展示页
     *
     * @param model
     * @param movie
     * @return
     */
    @RequestMapping("/index")
    public String doDefaultView(Model model, Movie movie) {
        List<Movie> movies = indexService.selectAll(movie);
        model.addAttribute("movieobj", movie);
        model.addAttribute("movielist", movies);
        return "/index";
    }

/*   @RequestMapping("/")
    public String login() {
        return "login";
    }*/


    /**
     * 测试全局异常统一处理
     *
     * @return
     */
    @GetMapping(value = "/hello")
    @ResponseBody
    public String hello() {
        if (true) {
            throw new IllegalArgumentException("我发生异常啦……");
        }
        return "hello";
    }

    /**
     * 测试RateLimiter限流
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/ratelimiter")
    public Result testRateLimiter(){
        if(rateLimiterService.tryAcquire()){
            log.info("成功获取许可");
            return ResultUtil.success1(1001,"成功获取许可");
        }
        log.info("未获取到许可");
        return ResultUtil.success1(1002,"未获取到许可");
    }

}



















