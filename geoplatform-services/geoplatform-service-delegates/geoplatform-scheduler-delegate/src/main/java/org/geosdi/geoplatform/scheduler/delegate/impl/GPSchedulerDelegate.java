/**
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2021 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 *   This program is free software: you can redistribute it and/or modify it
 *   under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version. This program is distributed in the
 *   hope that it will be useful, but WITHOUT ANY WARRANTY; without
 *   even the implied warranty of MERCHANTABILITY or FITNESS FOR
 *   A PARTICULAR PURPOSE. See the GNU General Public License
 *   for more details. You should have received a copy of the GNU General
 *   Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 *   ====================================================================
 *
 *   Linking this library statically or dynamically with other modules is
 *   making a combined work based on this library. Thus, the terms and
 *   conditions of the GNU General Public License cover the whole combination.
 *
 *   As a special exception, the copyright holders of this library give you permission
 *   to link this library with independent modules to produce an executable, regardless
 *   of the license terms of these independent modules, and to copy and distribute
 *   the resulting executable under terms of your choice, provided that you also meet,
 *   for each linked independent module, the terms and conditions of the license of
 *   that module. An independent module is a module which is not derived from or
 *   based on this library. If you modify this library, you may extend this exception
 *   to your version of the library, but you are not obligated to do so. If you do not
 *   wish to do so, delete this exception statement from your version.
 */
package org.geosdi.geoplatform.scheduler.delegate.impl;

import java.util.List;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import org.geosdi.geoplatform.core.dao.GPAccountDAO;
import org.geosdi.geoplatform.core.model.GPUser;
import org.geosdi.geoplatform.scheduler.delegate.api.SchedulerDelegate;
import org.geosdi.geoplatform.scheduler.quartz.jobs.EmailJob;
import org.geosdi.geoplatform.scheduler.quartz.jobs.EmailModificationJob;
import org.geosdi.geoplatform.scheduler.quartz.jobs.EmailRegistrationJob;
import org.geosdi.geoplatform.scheduler.quartz.jobs.EmailUserCreationNotificationJob;
import org.geosdi.geoplatform.scheduler.quartz.jobs.GroupJobType;
import org.geosdi.geoplatform.scheduler.quartz.jobs.TempAccountExpireJob;
import org.geosdi.geoplatform.scheduler.quartz.task.EmailTask;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
@Component(value = "gpSchedulerDelegate")
public class GPSchedulerDelegate implements SchedulerDelegate {

    private static final Logger logger = LoggerFactory.getLogger(
            GPSchedulerDelegate.class);
    //
    @Resource(name = "accountDAO")
    private GPAccountDAO accountDAO;
    //
    @Resource(name = "emailTask")
    private EmailTask emailTask;
    //
    private Scheduler scheduler;
    private JobDetail jobTempAccount;
    private JobDetail jobEmailRegistration;
    private JobDetail jobEmailCreationNotification;
    private JobDetail jobEmailModification;

    @Override
    public void sendEmailRegistration(GPUser user) {
        // Trigger the job to run once
        Trigger trigger = TriggerBuilder.newTrigger().
                withIdentity("EmailRegistrationTrigger",
                        GroupJobType.EMAIL.toString()). // KEY email.EmailRegistrationTrigger
                withDescription("Runs once immediately").
                startNow().
                forJob(jobEmailRegistration).
                build();
        trigger.getJobDataMap().put(EmailJob.USER, user);

        try {
            logger.info("\n*** Fire trigger for sending registration email...");
            scheduler.scheduleJob(trigger);
        } catch (SchedulerException ex) {
            logger.error("SchedulerException", ex.getMessage());
        }
    }

    @Override
    public void sendEmailModification(GPUser user, String previousEmail,
            String newPlainPassword) {
        // Trigger the job to run once
        Trigger trigger = TriggerBuilder.newTrigger().
                withIdentity("EmailModificationTrigger",
                        GroupJobType.EMAIL.toString()). // KEY email.EmailModificationTrigger
                withDescription("Runs once immediately").
                startNow().
                forJob(jobEmailModification).
                build();
        trigger.getJobDataMap().put(EmailJob.USER, user);
        trigger.getJobDataMap().put(EmailModificationJob.PREVIOUS_EMAIL,
                previousEmail);
        trigger.getJobDataMap().put(EmailModificationJob.NEW_PLAIN_PASSWORD,
                newPlainPassword);

        try {
            logger.info("\n*** Fire trigger for sending modification email...");
            scheduler.scheduleJob(trigger);
        } catch (SchedulerException ex) {
            logger.error("SchedulerException: {}", ex.getMessage());
        }
    }

    @Override
    public void sendEmailUserCreationNotification(List<String> emailRecipient,
            String createdUserName) {
        // Trigger the job to run once
        Trigger trigger = TriggerBuilder.newTrigger().
                withIdentity("emailUserCreationNotificationTrigger",
                        GroupJobType.EMAIL.toString()). // KEY email.EmailRegistrationTrigger
                withDescription("Runs once immediately").
                startNow().
                forJob(jobEmailCreationNotification).
                build();
        trigger.getJobDataMap().put(
                EmailUserCreationNotificationJob.CREATED_USERNAME,
                createdUserName);
        trigger.getJobDataMap().put(
                EmailUserCreationNotificationJob.EMAIL_RECIPIENT, emailRecipient);
        try {
            logger.info(
                    "\n*** Fire trigger for sending user creation notification email...");
            scheduler.scheduleJob(trigger);
        } catch (SchedulerException ex) {
            logger.error("SchedulerException", ex.getMessage());
        }
    }

    @Override
    public void checkTempAccount() {
        Trigger trigger = TriggerBuilder.newTrigger().
                withIdentity("checkTempAccountTrigger",
                        GroupJobType.TEMP_ACCOUNT.toString()).
                withDescription("Runs once immediately").
                startNow().
                forJob(jobTempAccount).
                build();
        try {
            logger.info("\n*** Fire trigger for check temp account...");
            scheduler.scheduleJob(trigger);
        } catch (SchedulerException ex) {
            logger.error("SchedulerException", ex.getMessage());
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // Scheduler setted in quatrz.propeties file
        scheduler = StdSchedulerFactory.getDefaultScheduler();

        scheduler.start();

        this.createDataTempAccountExpired();
        this.createEmailRegistrationJob();
        this.createEmailModificationJob();
        this.createEmailUserCreationNotificationJob();
    }

    private void createDataTempAccountExpired() throws SchedulerException {
        jobTempAccount = JobBuilder.newJob(TempAccountExpireJob.class).
                withIdentity("tempAccountExpireJob",
                        GroupJobType.TEMP_ACCOUNT.toString()).
                withDescription(
                        "Disable temp account if the threshold is expired").
                storeDurably().
                requestRecovery().
                build();
        jobTempAccount.getJobDataMap().put("accountDAO", accountDAO);

        Trigger trigger = TriggerBuilder.newTrigger().
                withIdentity("TempAccountExpireJobTrigger").
                withDescription("Runs at 5:00AM every day").
                withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(5, 0).
                        withMisfireHandlingInstructionFireAndProceed()).
                build();

        scheduler.scheduleJob(jobTempAccount, trigger);
    }

    private void createEmailRegistrationJob() throws SchedulerException {
        // Define the job and tie it to EmailRegistrationJob class
        jobEmailRegistration = JobBuilder.newJob(EmailRegistrationJob.class).
                withIdentity("EmailRegistrationJob",
                        GroupJobType.EMAIL.toString()). // KEY email.EmailRegistrationJob
                withDescription("Send a confirmation email to new user").
                storeDurably().
                build();
        jobEmailRegistration.getJobDataMap().put(EmailRegistrationJob.EMAIL_TASK,
                emailTask);

        // Add job to the scheduler for execute when trigger will be fired
        scheduler.addJob(jobEmailRegistration, false);
    }

    private void createEmailUserCreationNotificationJob() throws
            SchedulerException {
        // Define the job and tie it to EmailUserCreationNotificationJob class
        jobEmailCreationNotification = JobBuilder.newJob(
                EmailUserCreationNotificationJob.class).
                withIdentity("EmailUserCreationNotificationJob",
                        GroupJobType.EMAIL.toString()). // KEY email.EmailRegistrationJob
                withDescription("Send a notification email to a recipient list").
                storeDurably().
                build();
        jobEmailCreationNotification.getJobDataMap().put(
                EmailRegistrationJob.EMAIL_TASK, emailTask);

        // Add job to the scheduler for execute when trigger will be fired
        scheduler.addJob(jobEmailCreationNotification, false);
    }

    private void createEmailModificationJob() throws SchedulerException {
        // Define the job and tie it to EmailJob class
        jobEmailModification = JobBuilder.newJob(EmailModificationJob.class).
                withIdentity("EmailModificationJob",
                        GroupJobType.EMAIL.toString()). // KEY email.EmailModificationJob
                withDescription("Send an email to a user for modification").
                storeDurably().
                build();
        jobEmailModification.getJobDataMap().put(EmailModificationJob.EMAIL_TASK,
                emailTask);

        // Add job to the scheduler for execute when trigger will be fired
        scheduler.addJob(jobEmailModification, false);
    }

    @PreDestroy
    public void destroyScheduler() throws SchedulerException {
        scheduler.shutdown();
    }

}
