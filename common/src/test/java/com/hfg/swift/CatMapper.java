package com.hfg.swift;

import com.hfg.entity.Cat;
import com.hfg.po.CatPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @Author: Zero
 * @Date: 2022/4/20 12:26
 * @Description:
 */
@Mapper
public interface CatMapper {

    CatMapper INSTANCE = Mappers.getMapper(CatMapper.class);

    @Mappings({
            @Mapping(source = "catName", target = "name"),
            @Mapping(source = "catAge",target = "age")
    })
    CatPO carToCarDto(Cat cat);
}
