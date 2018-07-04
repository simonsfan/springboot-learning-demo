package com.simons.cn.springbootdemo.dao.system;


import com.simons.cn.springbootdemo.bean.ActivitySystemVariable;

//@Mapper
public interface ActivitySystemVariableMapper {

//    @Delete("delete from activity_system_variable where id=#{id}")
    int deleteByPrimaryKey(Integer id);

/*    @Insert("insert into activity_system_variable(name, svkey, value, memo, typecode, catalog, createtime) values (#{name},#{svKey},#{value},#{memo},#{typeCode},#{cataLog}, now() )")*/
    int insert(ActivitySystemVariable record);

/*    @Select("select id, name, svkey, value, memo, typecode, catalog, createtime from activity_system_variable where id = #{id}")
    @Results({@Result(column = "svkey", property = "svKey"),
            @Result(column = "typecode", property = "typeCode"),
            @Result(column = "catalog", property = "cataLog"),
            @Result(column = "createtime", property = "createTime", javaType = Date.class)})*/
    ActivitySystemVariable selectByPrimaryKey(Integer id);

//    @Update("update activity_system_variable set svkey=#{svKey}, value=#{value} where id=#{id}")
    int updateByPrimaryKey(ActivitySystemVariable systemVariable);

//    @Select("select * from activity_system_variable where svkey=#{key}")
    ActivitySystemVariable getSystemVariableBySvKey(String key);
}
