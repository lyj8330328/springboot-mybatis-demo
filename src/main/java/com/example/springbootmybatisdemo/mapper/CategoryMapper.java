package com.example.springbootmybatisdemo.mapper;

import com.example.springbootmybatisdemo.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface CategoryMapper {
    @Select("select * from category")
    List<Category> findAll();

    @Insert("insert into category(name) values (#{name})")
    int save(Category category);

    @Delete(" delete from category where id= #{id} ")
     void delete(int id);

    @Select("select * from category where id= #{id} ")
    Category get(int id);

    @Update("update category set name=#{name} where id=#{id} ")
    int update(Category category);
}
