package com.simons.cn.springbootdemo.util;

import com.simons.cn.springbootdemo.bean.Movie;
import com.simons.cn.springbootdemo.service.Weixin.IndexService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * 项目名称：springbootdemo
 * 类名称：FilePortUtil
 * 类描述：导入导出公用类封装
 * 创建人：simonsfan
 * 创建时间：2018/7/9 10:15
 */
@Service
public class FilePortUtil {

    private static final Logger log = LoggerFactory.getLogger(FilePortUtil.class);

    @Autowired
    private IndexService indexService;

    /**
     * 导出
     * 注意：泛型T类字段名和containBean字段名字的一致
     *
     * @param response
     * @param title       表名
     * @param headers     表头
     * @param list        数据集
     * @param containBean 数据集类型字段
     * @param <T>
     * @throws Exception
     */
    public static <T> void exportExcel(HttpServletResponse response, String title, String[] headers, List<T> list, List<String> containBean) throws Exception {
        HSSFWorkbook workbook = null;
        try {
            workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet(title);
            HSSFRow row = sheet.createRow(0);
            for (short i = 0; i < headers.length; i++) {
                HSSFCell cell = row.createCell(i);
                HSSFRichTextString text = new HSSFRichTextString(headers[i]);
                cell.setCellValue(text);
            }
            Iterator<T> it = list.iterator();
            int index = 0;
            while (it.hasNext()) {
                index++;
                row = sheet.createRow(index);
                T t = (T) it.next();
                Field[] fields = t.getClass().getDeclaredFields();
                if (CollectionUtils.isNotEmpty(containBean)) {
                    for (int j = 0; j < containBean.size(); j++) {
                        for (int i = 0; i < fields.length; i++) {
                            Field field = fields[i];
                            if (!field.getName().equals(containBean.get(j)))
                                continue;
                            setCellValue(t, field, row, j);
                        }
                    }
                } else {
                    for (int i = 0; i < fields.length; i++) {
                        Field field = fields[i];
                        setCellValue(t, field, row, i);
                    }
                }
            }
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + new String((title).getBytes(), "ISO8859-1") + ".xls");
            workbook.write(response.getOutputStream());
        } finally {
            if (workbook != null) {
                workbook.close();
            }
        }
    }

/*导出使用方式：
   String[] headers = new String[]{"积分商城订单号", "商品编码", "商品名称", "电子券号", "电子券金额", "电子券下发时间", "兑换手机", "消费金额", "消费状态"};

    List<String> values = new ArrayList<>();
    values.add("orderId");
    values.add("itemId");
    values.add("title");
    values.add("vcode");
    values.add("price");
    values.add("giveTime");
    values.add("phone");
    values.add("userAmount");
    values.add("isConsume");

    String title = "中国移动积分商城订单信息统计";
    List<PointsVirtualCodeInfoVo> list = ydjfService.getList(map);
    ExcelUtil.exportExcel(response,title,headers,list,values);*/


    /**
     * 设置每一行中的列
     *
     * @param t
     * @param field
     * @param row
     * @param index
     * @param <T>
     */
    private static <T> void setCellValue(T t, Field field, HSSFRow row, int index) {
        HSSFCell cell = row.createCell(index);
        Object value = invoke(t, field);
        String textValue = null;
        if (value != null) {
            if (value instanceof Date) {
                Date date = (Date) value;
                textValue = DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
            } else {
                textValue = value.toString();
            }
        }
        if (textValue != null) {
            cell.setCellValue(textValue);
        }
    }

    /**
     * 反射映射数据集字段
     *
     * @param t
     * @param field
     * @param <T>
     * @return
     */
    private static <T> Object invoke(T t, Field field) {
        try {
            String fieldName = field.getName();
            PropertyDescriptor pd = new PropertyDescriptor(fieldName, t.getClass());
            Method method = pd.getReadMethod();  //得到的是bean对应的get方法
            return method.invoke(t);
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 导入
     *
     * @param multipartFile
     * @param <T>
     * @return
     */
    public <T> int fileImport(MultipartFile multipartFile) {
        File file = null;
        Workbook workbook = null;
        int totalNum = 0;
        String path = FilePortUtil.class.getClassLoader().getResource("/").getPath();
        path = path.substring(0, path.indexOf("WEB-INF") + "WEB-INF".length()) + "/" + multipartFile.getOriginalFilename();
        file = new File(path);
        try {
            FileCopyUtils.copy(multipartFile.getBytes(), file);
            workbook = WorkbookFactory.create(new FileInputStream(file));
            List<Movie> list = new ArrayList<>();
            /*遍历sheet页*/
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                Sheet sheet = workbook.getSheetAt(i);
                if (sheet == null) {
                    continue;
                }
                /*统计导入的总条数，要是你的excell包含了表格，就不用加1了*/
                if (sheet.getLastRowNum() > 0) {
                    totalNum += sheet.getLastRowNum() + 1;
                }
                /*遍历行，这里j的初始值取1是因为包含了表头*/
                for (int j = 1; j < sheet.getPhysicalNumberOfRows(); j++) {
                    Row row = sheet.getRow(j);
                    Cell cell2 = row.getCell(2);
                    Cell cell3 = row.getCell(3);
                    if (cell2 == null || cell3 == null) {
                        continue;
                    }
                    String name = this.getCellValue(cell2);
                    String original = this.getCellValue(cell3);

                    if (!name.contains("链接")) {
                        log.info("没有链接字段的=" + name);
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
            file.delete();
            return totalNum;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("导入功能公用类异常exception={}", e);
        }
        return totalNum;
    }

    public String getCellValue(Cell cell) {
        if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            Double d = cell.getNumericCellValue();
            return String.valueOf(d.intValue());
        }
        return String.valueOf(cell.getStringCellValue());
    }


}
