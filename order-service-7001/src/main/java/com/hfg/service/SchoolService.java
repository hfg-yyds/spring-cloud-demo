package com.hfg.service;

import com.hfg.entity.School;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Zero
 * @since 2022-03-29
 */
public interface SchoolService extends IService<School> {

    boolean updateSchool(School school);
}
