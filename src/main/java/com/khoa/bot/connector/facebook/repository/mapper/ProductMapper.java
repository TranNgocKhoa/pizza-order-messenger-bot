package com.khoa.bot.connector.facebook.repository.mapper;

import com.khoa.bot.connector.facebook.entity.Option;
import com.khoa.bot.connector.facebook.entity.Product;
import com.khoa.bot.connector.facebook.entity.ProductOption;
import com.khoa.bot.connector.facebook.entity.enums.OptionType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<Product> selectProductList(@Param("offset") long offset, @Param("limit") long limit);

    Product selectProduct(@Param("productId") long productId);

    List<ProductOption> selectProductOptionList(@Param("productId") long productId, @Param("optionType") OptionType optionType);

    ProductOption selectProductOption(@Param("productId") long productId, @Param("optionId") long optionId);

    Option selectOption(@Param("optionId") long optionId);
}
