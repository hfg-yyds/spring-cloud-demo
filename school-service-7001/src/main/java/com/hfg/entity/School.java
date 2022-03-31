package com.hfg.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.sql.Blob;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Zero
 * @since 2022-03-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="School对象", description="")
@TableName(value = "school")
public class School implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "学校编号")
    @TableId(value = "school_id", type = IdType.ID_WORKER_STR)
    private String schoolId;

    @ApiModelProperty(value = "学校建校多少年")
    private Integer schoolAge;

    @ApiModelProperty(value = "学习账上多少钱")
    private Long schoolMoney;

    @ApiModelProperty(value = "学校本年度创收")
    private BigDecimal schoolIncome;

    @ApiModelProperty(value = "学校是否评优")
    private Boolean schoolExcellent;

    @ApiModelProperty(value = "学校创建日期")
    private LocalDate schoolDate;

    @ApiModelProperty(value = "学校创建时间")
    private LocalTime schoolTime;

    @ApiModelProperty(value = "学校创建日期时间")
    private LocalDateTime schoolDatetime;

    @ApiModelProperty(value = "学校图片简介")
    private String schoolBlob;

    @ApiModelProperty(value = "学校文字简介")
    private String schoolClob;

    @ApiModelProperty(value = "学校类型")
    private SchoolType schoolEnum;

    @ApiModelProperty(value = "数据创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "数据更新时间")
    private LocalDateTime updateTime;

}
