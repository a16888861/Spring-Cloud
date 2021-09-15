package com.lucky.kali.common.Scheduled;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * cron表达体
 *
 * @author Elliot
 */
@Data
@ApiModel(description = "cron表达体")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class TaskScheduleModel extends Model<TaskScheduleModel> {

    /**
     * 所选作业类型:
     * 1  -> 每天
     * 2  -> 每月
     * 3  -> 每周
     */
    Integer jobType;

    /**
     * 一周的哪几天
     */
    Integer[] dayOfWeeks;

    /**
     * 一个月的哪几天
     */
    Integer[] dayOfMonths;

    /**
     * 秒
     */
    Integer second;

    /**
     * 分
     */
    Integer minute;

    /**
     * 时
     */
    Integer hour;

}