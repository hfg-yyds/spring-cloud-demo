package com.hfg.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
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
@ApiModel(value="Resume对象", description="")
public class Resume implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private Integer id;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "出生日期")
    private String birthday;

    @ApiModelProperty(value = "工作年限")
    private String workYear;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "目前状态")
    private String status;

    @ApiModelProperty(value = "简历名称")
    @TableField("resumeName")
    private String resumename;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "创建日期")
    @TableField("createTime")
    private LocalDateTime createtime;

    @ApiModelProperty(value = "头像")
    @TableField("headPic")
    private String headpic;

    @ApiModelProperty(value = "是否删除 默认值0-未删除 1-已删除")
    @TableField("isDel")
    private Integer isdel;

    @ApiModelProperty(value = "简历更新时间")
    @TableField("updateTime")
    private LocalDateTime updatetime;

    @ApiModelProperty(value = "用户ID")
    @TableField("userId")
    private Integer userid;

    @ApiModelProperty(value = "是否为默认简历 0-默认 1-非默认")
    @TableField("isDefault")
    private Integer isdefault;

    @ApiModelProperty(value = "最高学历")
    @TableField("highestEducation")
    private String highesteducation;

    @ApiModelProperty(value = "投递附件简历确认 0-需要确认 1-不需要确认")
    @TableField("deliverNearByConfirm")
    private Integer delivernearbyconfirm;

    @ApiModelProperty(value = "简历被拒绝次数")
    @TableField("refuseCount")
    private Integer refusecount;

    @ApiModelProperty(value = "被标记为可面试次数")
    @TableField("markCanInterviewCount")
    private Integer markcaninterviewcount;

    @ApiModelProperty(value = "已通知面试次数")
    @TableField("haveNoticeInterCount")
    private Integer havenoticeintercount;

    @ApiModelProperty(value = "一句话介绍自己")
    @TableField("oneWord")
    private String oneword;

    @ApiModelProperty(value = "居住城市")
    @TableField("liveCity")
    private String livecity;

    @ApiModelProperty(value = "简历得分")
    @TableField("resumeScore")
    private Integer resumescore;

    @ApiModelProperty(value = "用户身份1-学生 2-工人")
    @TableField("userIdentity")
    private Integer useridentity;

    @ApiModelProperty(value = "人才搜索-开放简历 0-关闭，1-打开，2-简历未达到投放标准被动关闭 3-从未设置过开放简历")
    @TableField("isOpenResume")
    private Integer isopenresume;


}
