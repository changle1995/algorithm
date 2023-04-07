package com.changle.algorithm.sort;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;

import java.util.Date;
import java.util.Set;

/**
 * QuartzTest
 *
 * @author changle05@meituan.com
 * @version 1.0
 * @date 2023-04-07
 */
@Slf4j
public class QuartzTest {

    @Test
    public void test() throws Exception {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        JobDetail jobDetail =
                JobBuilder.newJob(SimpleJob.class).withIdentity("JobDetail name", "JobDetail group")
                        .withDescription("JobDetail desc").build();

        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("Trigger name", "Trigger group")
                .withDescription("Trigger desc")
                .startAt(new Date())
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1).withRepeatCount(10))
                .withPriority(1)
                .build();

        Trigger trigger2 = TriggerBuilder.newTrigger().withIdentity("Trigger name2", "Trigger group2")
                .withDescription("Trigger desc2")
                .startAt(new Date())
                .endAt(new Date())
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1).withRepeatCount(10))
                .forJob(jobDetail)
                .withPriority(100)
                .build();

        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.scheduleJob(trigger2);

        scheduler.start();

        log.info("job triggers: {}", scheduler.getTriggersOfJob(jobDetail.getKey()));

        Set<TriggerKey> triggerKeys = scheduler.getTriggerKeys(GroupMatcher.anyGroup());
        log.info("triggerKeys: {}", triggerKeys);
        Set<JobKey> jobKeys = scheduler.getJobKeys(GroupMatcher.anyGroup());
        log.info("jobKeys: {}", jobKeys);

        Thread.sleep(5 * 1000);

//        scheduler.deleteJob(jobDetail.getKey());
        triggerKeys = scheduler.getTriggerKeys(GroupMatcher.anyGroup());
        log.info("triggerKeys: {}", triggerKeys);
        jobKeys = scheduler.getJobKeys(GroupMatcher.anyGroup());
        log.info("jobKeys: {}", jobKeys);

        scheduler.unscheduleJob(trigger.getKey());
        triggerKeys = scheduler.getTriggerKeys(GroupMatcher.anyGroup());
        log.info("triggerKeys: {}", triggerKeys);
        jobKeys = scheduler.getJobKeys(GroupMatcher.anyGroup());
        log.info("jobKeys: {}", jobKeys);

    }

    public static class SimpleJob implements Job {

        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {
            JobDetail jobDetail = context.getJobDetail();
            Trigger trigger = context.getTrigger();
            log.info("================================");
            log.info("{}", jobDetail.getKey());
            log.info("{}", jobDetail.getDescription());
            log.info("{}", trigger.getKey());
            log.info("{}", trigger.getDescription());
            log.info("================================");
        }
    }
}
