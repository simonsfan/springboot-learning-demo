package com.simons.cn.springbootdemo.controller;

import com.simons.cn.springbootdemo.bean.Movie;
import com.simons.cn.springbootdemo.bean.UrlInfo;
import com.simons.cn.springbootdemo.service.Weixin.IndexService;
import org.apache.poi.ss.usermodel.*;
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

    @Autowired
    private UrlInfo urlInfo; //常量封装类-测试

    @Autowired
    private IndexService indexService;

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
                    Row row = sheet.getRow(rowNum);
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
                    movie.setPasswd(passwd);

                 /*   Row row = sheet.getRow(rowNum);
                    Cell cell2 = row.getCell(2);
                    Cell cell3 = row.getCell(3);
                    if (cell2 == null || cell3 == null) {
                        continue;
                    }
                    String name = this.getCellValue(cell2);
                    String original = this.getCellValue(cell3);
//                当你沉睡时链接:https://pan.baidu.com/s/1i67IQK9 密码:en3s
                    String moviename = name.substring(0, name.indexOf("链接"));

                    Movie movie = new Movie();
                    movie.setName(moviename);
                    movie.setType("电影");
                    movie.setLink(name);
                    movie.setOriginal(original);
                    String passwd = name.substring(name.lastIndexOf(":") + 1).trim();
                    movie.setPasswd(passwd);
*/
                    list.add(movie);
                }
                indexService.insertBatch(list);
            }
        } catch (Exception e) {
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
    @RequestMapping("/index")
    public String doDefaultView(Model model, Movie movie) {
        List<Movie> movies = indexService.selectAll(movie);
        model.addAttribute("movieobj", movie);
        model.addAttribute("movielist", movies);
        return "/index";
    }

   @RequestMapping("/")
    public String login() {
        return "login";
    }


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

    public static void main(String[] args) {
        String name = "当你沉睡时链接:https://pan.baidu.com/s/1i67IQK9 密码:en3s";
        String substring = name.substring(0, name.indexOf("链接"));
        String passwd = name.substring(name.lastIndexOf(":") + 1).trim();
        System.out.println("name="+substring);
        System.out.println("pass="+passwd);
    }
}
