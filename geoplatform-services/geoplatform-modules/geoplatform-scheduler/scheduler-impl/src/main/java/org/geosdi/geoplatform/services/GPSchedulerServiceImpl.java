/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2011 geoSDI Group (CNR IMAA - Potenza - ITALY).
 *
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version. This program is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General
 * Public License along with this program. If not, see http://www.gnu.org/licenses/
 *
 * ====================================================================
 *
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library. Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 *
 * As a special exception, the copyright holders of this library give you permission
 * to link this library with independent modules to produce an executable, regardless
 * of the license terms of these independent modules, and to copy and distribute
 * the resulting executable under terms of your choice, provided that you also meet,
 * for each linked independent module, the terms and conditions of the license of
 * that module. An independent module is a module which is not derived from or
 * based on this library. If you modify this library, you may extend this exception
 * to your version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 *
 */
package org.geosdi.geoplatform.services;

import javax.jws.WebService;
import org.geosdi.geoplatform.core.dao.GPAccountDAO;
import org.geosdi.geoplatform.jobs.EmailJob;
import org.geosdi.geoplatform.core.model.GPUser;
import org.geosdi.geoplatform.jobs.EmailTask;
import org.geosdi.geoplatform.jobs.GroupJobType;
import org.geosdi.geoplatform.jobs.TempAccountExpireJob;
import org.quartz.CalendarIntervalScheduleBuilder;
import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Vincenzo Monteverde
 * @email vincenzo.monteverde@geosdi.org - OpenPGP key ID 0xB25F4B38
 */
@WebService(endpointInterface = "org.geosdi.geoplatform.services.GPSchedulerService")
public class GPSchedulerServiceImpl implements GPSchedulerService, InitializingBean {
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    @Autowired
    private GPAccountDAO accountDAO;
    //
    private EmailTask emailTask;
    //
    private Scheduler scheduler;
    private JobDetail jobSendEmail;
    private JobDetail jobTempAccount;
    
    public void setAccountDAO(GPAccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    /**
     * @param emailTask the emailTask to set
     */
    public void setEmailTask(EmailTask emailJob) {
        this.emailTask = emailJob;
    }
    
    @Override
    public void sendEmail(GPUser user) {
        // Trigger the job to run once
        Trigger trigger = TriggerBuilder.newTrigger().
                withIdentity("sendEmailTrigger", GroupJobType.EMAIL.toString()). // KEY email.sendEmailTrigger
                withDescription("Runs once immediately").
                startNow().
                forJob(jobSendEmail).
                build();
        trigger.getJobDataMap().put(EmailJob.USER, user);
        
        try {
            logger.info("\n*** Fire trigger for sending email...");
            scheduler.scheduleJob(trigger);
        } catch (SchedulerException ex) {
            logger.error("SchedulerException", ex.getMessage());
        }
    }
    
    @Override
    public void checkTempAccount() {
        Trigger trigger = TriggerBuilder.newTrigger().
                withIdentity("checkTempAccountTrigger", GroupJobType.TEMP_ACCOUNT.toString()).
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
        this.createJobSendEmail();
    }
    
    private void createJobSendEmail() throws SchedulerException {
        // Define the job and tie it to EmailJob class
        jobSendEmail = JobBuilder.newJob(EmailJob.class).
                withIdentity("sendEmailJob", GroupJobType.EMAIL.toString()). // KEY email.sendEmailJob
                withDescription("Send a confirmation email to new user").
                storeDurably().
                build();
        jobSendEmail.getJobDataMap().put(EmailJob.EMAIL_TASK, emailTask);

        // Add job to the scheduler for execute when trigger will be fired
        scheduler.addJob(jobSendEmail, false);
    }
    
    private void createDataTempAccountExpired() throws SchedulerException {
        jobTempAccount = JobBuilder.newJob(TempAccountExpireJob.class).
                //        JobDetail job = JobBuilder.newJob(TempAccountExpireJob.class).
                withIdentity("tempAccountExpireJob", GroupJobType.TEMP_ACCOUNT.toString()).
                withDescription("Disable temp account if the threshold is expired").
                storeDurably().
                build();
        jobTempAccount.getJobDataMap().put("accountDAO", accountDAO);
        
        Trigger trigger = TriggerBuilder.newTrigger().
                withIdentity("TempAccountExpireJobTrigger").
                withDescription("Runs one at week").
                startAt(DateBuilder.evenHourDateAfterNow()).
                withSchedule(CalendarIntervalScheduleBuilder.calendarIntervalSchedule().
                withIntervalInWeeks(1).
                withMisfireHandlingInstructionFireAndProceed()).
                build();
        
        scheduler.scheduleJob(jobTempAccount, trigger);
    }
}
