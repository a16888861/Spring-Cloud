package com.lucky.kali.common.util;

import com.lucky.kali.common.Scheduled.TaskScheduleModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Cron构建工具类
 */
public class CronUtil {

    private static final Logger log = LoggerFactory.getLogger(CronUtil.class);

    /**
     * 方法摘要：构建Cron表达式
     *
     * @param taskScheduleModel cron表达体
     * @return String cron表达式
     */
    public static String createCronExpression(TaskScheduleModel taskScheduleModel) {
        StringBuilder cronExp = new StringBuilder();

        if (null == taskScheduleModel.getJobType()) {
            /*执行周期未配置*/
            log.info("执行周期未配置");
        }

        if (null != taskScheduleModel.getSecond()
                && null == taskScheduleModel.getMinute()
                && null == taskScheduleModel.getHour()) {
            /*每隔几秒*/
            if (taskScheduleModel.getJobType() == 0) {
                cronExp.append("0/").append(taskScheduleModel.getSecond());
                cronExp.append(" ");
                cronExp.append("* ");
                cronExp.append("* ");
                cronExp.append("* ");
                cronExp.append("* ");
                cronExp.append("?");
            }

        }

        if (null != taskScheduleModel.getSecond()
                && null != taskScheduleModel.getMinute()
                && null == taskScheduleModel.getHour()) {
            /*每隔几分钟*/
            if (taskScheduleModel.getJobType() == 4) {
                cronExp.append("* ");
                cronExp.append("0/").append(taskScheduleModel.getMinute());
                cronExp.append(" ");
                cronExp.append("* ");
                cronExp.append("* ");
                cronExp.append("* ");
                cronExp.append("?");
            }

        }

        if (null != taskScheduleModel.getSecond()
                && null != taskScheduleModel.getMinute()
                && null != taskScheduleModel.getHour()) {
            /*秒*/
            cronExp.append(taskScheduleModel.getSecond()).append(" ");
            /*分*/
            cronExp.append(taskScheduleModel.getMinute()).append(" ");
            /*小时*/
            cronExp.append(taskScheduleModel.getHour()).append(" ");

            /*每天*/
            if (taskScheduleModel.getJobType() == 1) {
                /*日*/
                cronExp.append("* ");
                /*月*/
                cronExp.append("* ");
                /*周*/
                cronExp.append("?");
            }

            /*按每周*/
            else if (taskScheduleModel.getJobType() == 3) {
                /*一个月中第几天*/
                cronExp.append("? ");
                /*月份*/
                cronExp.append("* ");
                /*周*/
                Integer[] weeks = taskScheduleModel.getDayOfWeeks();
                for (int i = 0; i < weeks.length; i++) {
                    if (i == 0) {
                        cronExp.append(weeks[i]);
                    } else {
                        cronExp.append(",").append(weeks[i]);
                    }
                }

            }

            /*按每月*/
            else if (taskScheduleModel.getJobType() == 2) {
                /*一个月中的哪几天*/
                Integer[] days = taskScheduleModel.getDayOfMonths();
                for (int i = 0; i < days.length; i++) {
                    if (i == 0) {
                        cronExp.append(days[i]);
                    } else {
                        cronExp.append(",").append(days[i]);
                    }
                }
                /*月份*/
                cronExp.append(" * ");
                /*周*/
                cronExp.append("?");
            }

        } else {
            log.info("时或分或秒参数未配置");
        }
        return cronExp.toString();
    }

    /**
     * 方法摘要：生成计划的详细描述
     *
     * @param taskScheduleModel cron表达体
     * @return String
     */
    public static String createDescription(TaskScheduleModel taskScheduleModel) {
        StringBuilder description = new StringBuilder();

        if (null != taskScheduleModel.getSecond()
                && null != taskScheduleModel.getMinute()
                && null != taskScheduleModel.getHour()) {
            /*按每天*/
            if (taskScheduleModel.getJobType() == 1) {
                description.append("每天");
                description.append(taskScheduleModel.getHour()).append("时");
                description.append(taskScheduleModel.getMinute()).append("分");
                description.append(taskScheduleModel.getSecond()).append("秒");
                description.append("执行");
            }

            /*按每周*/
            else if (taskScheduleModel.getJobType() == 3) {
                if (taskScheduleModel.getDayOfWeeks() != null && taskScheduleModel.getDayOfWeeks().length > 0) {
                    StringBuilder days = new StringBuilder();
                    for (int i : taskScheduleModel.getDayOfWeeks()) {
                        days.append("周").append(i);
                    }
                    description.append("每周的").append(days).append(" ");
                }
                if (null != taskScheduleModel.getSecond()
                        && null != taskScheduleModel.getMinute()
                        && null != taskScheduleModel.getHour()) {
                    description.append(",");
                    description.append(taskScheduleModel.getHour()).append("时");
                    description.append(taskScheduleModel.getMinute()).append("分");
                    description.append(taskScheduleModel.getSecond()).append("秒");
                }
                description.append("执行");
            }

            /*按每月*/
            else if (taskScheduleModel.getJobType() == 2) {
                /*选择月份*/
                if (taskScheduleModel.getDayOfMonths() != null && taskScheduleModel.getDayOfMonths().length > 0) {
                    StringBuilder days = new StringBuilder();
                    for (int i : taskScheduleModel.getDayOfMonths()) {
                        days.append(i).append("号");
                    }
                    description.append("每月的").append(days).append(" ");
                }
                description.append(taskScheduleModel.getHour()).append("时");
                description.append(taskScheduleModel.getMinute()).append("分");
                description.append(taskScheduleModel.getSecond()).append("秒");
                description.append("执行");
            }

        }
        return description.toString();
    }

    /**
     * 参考例子
     */
    public static void main(String[] args) {
        /*执行时间：每天的12时12分12秒 start*/
        TaskScheduleModel taskScheduleModel = new TaskScheduleModel();

        /*按每秒*/
        taskScheduleModel.setJobType(0);
        taskScheduleModel.setHour(0);
        taskScheduleModel.setMinute(0);
        taskScheduleModel.setSecond(30);
        String cronExp = createCronExpression(taskScheduleModel);
        log.info(cronExp);

        /*按每分钟*/
        taskScheduleModel.setJobType(4);
        taskScheduleModel.setHour(0);
        taskScheduleModel.setMinute(8);
        taskScheduleModel.setSecond(0);
        String cronExpp = createCronExpression(taskScheduleModel);
        log.info(cronExpp);

        /*按每天*/
        taskScheduleModel.setJobType(1);
        /*时*/
        Integer hour = 12;
        /*分*/
        Integer minute = 12;
        /*秒*/
        Integer second = 12;
        taskScheduleModel.setHour(hour);
        taskScheduleModel.setMinute(minute);
        taskScheduleModel.setSecond(second);
        String cropExp = createCronExpression(taskScheduleModel);
        log.info(cropExp + ":" + createDescription(taskScheduleModel));
        //执行时间：每天的12时12分12秒 end

        /*每周的哪几天执行*/
        taskScheduleModel.setJobType(3);
        Integer[] dayOfWeeks = new Integer[3];
        dayOfWeeks[0] = 1;
        dayOfWeeks[1] = 2;
        dayOfWeeks[2] = 3;
        taskScheduleModel.setDayOfWeeks(dayOfWeeks);
        cropExp = createCronExpression(taskScheduleModel);
        log.info(cropExp + ":" + createDescription(taskScheduleModel));

        /*每月的哪几天执行*/
        taskScheduleModel.setJobType(2);
        Integer[] dayOfMonths = new Integer[3];
        dayOfMonths[0] = 1;
        dayOfMonths[1] = 21;
        dayOfMonths[2] = 13;
        taskScheduleModel.setDayOfMonths(dayOfMonths);
        cropExp = createCronExpression(taskScheduleModel);
        log.info(cropExp + ":" + createDescription(taskScheduleModel));

    }

}