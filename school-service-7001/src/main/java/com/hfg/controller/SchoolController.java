package com.hfg.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.hfg.config.R;
import com.hfg.entity.School;
import com.hfg.entity.SchoolType;
import com.hfg.mapper.SchoolMapper;
import com.hfg.utils.ImagesUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Zero
 * @since 2022-03-29
 */
@RestController
@RequestMapping("/school")
@Api(value = "学校测试控制器")
public class SchoolController {
    @Resource
    private SchoolMapper schoolMapper;

    @SneakyThrows
    @PutMapping("/insert")
    @ApiOperation(value = "插入一个学校对象")
    public R insert(@RequestBody School school1) {
        System.out.println(school1.toString());
        School school = new School();
        school.setSchoolAge(123);
        school.setSchoolMoney(4522232556l);
        school.setSchoolIncome(new BigDecimal("2.33335554"));
        school.setSchoolExcellent(true);
        school.setSchoolDate(LocalDate.now());
        school.setSchoolTime(LocalTime.now());
        school.setSchoolDatetime(LocalDateTime.now());

        school.setSchoolBlob(ImagesUtils.getImgStr("D:\\photo.jpg"));
        school.setSchoolClob(ImagesUtils.getTextStr("D:\\text.txt"));

        school.setSchoolEnum(SchoolType.University);

        school.setCreateTime(LocalDateTime.now());
        school.setUpdateTime(LocalDateTime.now());
        int insert = schoolMapper.insert(school);
        System.out.println("insert"+insert);
        System.out.println("学校Id"+school.getSchoolId());
        return insert>0?R.ok().data("test","插入成功"):R.ok().data("test","插入失败");
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "根据id删除一个学校")
    public R delete(@PathVariable String id) {
        int i = schoolMapper.deleteById(id);
        return i>0?R.ok().data("Results","删除成功"):R.ok().data("Results","删除失败");
    }

    @PostMapping("/update/{id}")
    @ApiOperation("根据学校id进行修改")
    public R update(@PathVariable String id) {
        System.out.println("请求进来了");
        School school = new School();
        school.setSchoolId("1");
        school.setSchoolAge(23);
        int i = schoolMapper.updateById(school);
        return i>0?R.ok().data("Results","更新成功"):R.ok().data("Results","更新失败");
    }

    @GetMapping("/getSchoolLists/{current}/{size}")
    @ApiOperation(value = "得到所有的学校消息,使用MP自带分页")
    public R getSchoolLists(@PathVariable Long current, @PathVariable Long size) {
        QueryWrapper<School> queryWrapper = new QueryWrapper<>();
        Page<School> schoolPage = new Page<>(current,size);
        IPage<School> schoolIPage = schoolMapper.selectPage(schoolPage, queryWrapper);
        List<School> schoolList = schoolIPage.getRecords();
        for (int i = 0; i < schoolList.size(); i++) {
            School school = schoolList.get(i);
            byte[] schoolBlob = school.getSchoolBlob();
            System.out.println(schoolBlob);
        }
        return R.ok().data("schoolList",schoolList);
    }

    @GetMapping("/getSchoolLists2/{current}/{size}")
    @ApiOperation(value = "得到所有的学校消息,使用MP自带分页")
    public R getSchoolLists2(@PathVariable Integer current, @PathVariable Integer size) {
        PageHelper.startPage(current,size);
        List<School> schoolList = schoolMapper.selectList(null);
        for (int i = 0; i < schoolList.size(); i++) {
            School school = schoolList.get(i);
            byte[] schoolBlob = school.getSchoolBlob();
            System.out.println(schoolBlob);
        }
        return R.ok().data("schoolList",schoolList);
    }



}

