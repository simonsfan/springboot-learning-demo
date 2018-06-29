package com.simons.cn.springbootdemo.system;

import com.simons.cn.springbootdemo.bean.ActivitySystemVariable;
import com.simons.cn.springbootdemo.dao.system.ActivitySystemVariableMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 项目名称：springbootdemo
 * 类名称：com.simons.cn.springbootdemo.system
 * 类描述：
 * 创建人：simonsfan
 * 创建时间：2018/6/27 11:54
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivitySystemVariableMapperTest {

    @Autowired
    private ActivitySystemVariableMapper systemVariableMapper;

    @Test
    public void deleteByPrimaryKey() {
        systemVariableMapper.deleteByPrimaryKey(257);
    }

    @Test
    public void insert() {
        ActivitySystemVariable systemVariable = new ActivitySystemVariable();
        systemVariable.setCataLog((byte) 0);
        systemVariable.setTypeCode("test");
        systemVariable.setSvKey("key1");
        systemVariable.setValue("value1");
        systemVariableMapper.insert(systemVariable);
    }

    @Test
    public void updateByPrimaryKey() {
        ActivitySystemVariable systemVariable = new ActivitySystemVariable();
        systemVariable.setSvKey("key1");
        systemVariable.setValue("value2");
        systemVariable.setId(1);
        systemVariableMapper.updateByPrimaryKey(systemVariable);
    }
}
