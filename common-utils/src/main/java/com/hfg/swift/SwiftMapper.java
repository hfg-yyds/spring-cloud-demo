package com.hfg.swift;

import com.hfg.entity.Cat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import com.hfg.po.CatPO;

/**
 * @Author: Zero
 * @Date: 2022/4/20 12:26
 * @Description:MAPStruct使用
 */
@Mapper
public interface SwiftMapper {

    SwiftMapper INSTANCE = Mappers.getMapper(SwiftMapper.class);

    @Mapping(source = "catName", target = "name")
    @Mapping(source = "catAge",target = "age")
    CatPO carToCarDto(Cat cat);

}
