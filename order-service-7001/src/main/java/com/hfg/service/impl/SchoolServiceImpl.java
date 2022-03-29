package com.hfg.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hfg.entity.School;
import com.hfg.mapper.SchoolMapper;
import com.hfg.service.SchoolService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zero
 * @since 2022-03-29
 */
@Service
public class SchoolServiceImpl extends ServiceImpl<SchoolMapper, School> implements SchoolService {

    @Autowired
    private BaseMapper<School> baseMapper;
    @Override
    public boolean updateSchool(School school) {
        QueryWrapper<School> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("school_id","1508676782997712897");
        int update = baseMapper.update(school, queryWrapper);
        System.out.println(update);
        return true;
    }
}
