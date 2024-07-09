package com.company.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.company.domain.Product;

@Mapper
public interface ProductMapper {

    @Select("SELECT * FROM product")
    public List<Product> getProductList();

    @Select("SELECT * FROM product WHERE pno = #{pno}")
    public Product getProduct(int pno);

    @Insert("INSERT INTO product (cate, pname, pcontent, img1, img2, img3, resdate, hits) " +
            "VALUES (#{cate}, #{pname}, #{pcontent}, #{img1}, #{img2}, #{img3}, #{resdate}, #{hits})")
    @Options(useGeneratedKeys = true, keyProperty = "pno")
    public void insert(Product product);

    @Update("UPDATE product SET cate = #{cate}, pname = #{pname}, pcontent = #{pcontent}, " +
            "img1 = #{img1}, img2 = #{img2}, img3 = #{img3}, resdate = #{resdate}, hits = #{hits} " +
            "WHERE pno = #{pno}")
    public void update(Product product);

    @Delete("DELETE FROM product WHERE pno = #{pno}")
    public void delete(int pno);
}