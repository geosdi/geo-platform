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
package org.geosdi.geoplatform.support.quartz.task;

import org.geosdi.geoplatform.logger.support.annotation.GeoPlatformLog;
import org.geosdi.geoplatform.support.quartz.task.repository.GPTaskRepository;
import org.quartz.*;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public abstract class GPBaseJobTask implements JobTask {

    @GeoPlatformLog
    protected Logger logger;

    /**
     * @param scheduler
     * @param triggerKey
     * @return {@link Boolean}
     */
    protected final Boolean unscheduleJob(Scheduler scheduler, TriggerKey triggerKey) throws Exception {
        checkArgument(scheduler != null, "The Parameter Scheduler must not be null.");
        checkArgument(triggerKey != null, "The Parameter TriggerKey must not be null.");
        boolean unscheduled = false;
        try {
            unscheduled = scheduler.unscheduleJob(triggerKey);
            logger.debug("\n\n#################Unscheduling Trigger : {}\n",
                    triggerKey);
        } catch (SchedulerException se) {
            se.printStackTrace();
            logger.error("\n@@@@@@@@@@@@@@@@@@@@@@SchedulerException : "
                    + "{} - for Trigger : {}", se, triggerKey);
        }
        return unscheduled;
    }

    /**
     * @param scheduler
     * @param triggerKeys
     * @return {@link Boolean}
     * @throws Exception
     */
    protected final Boolean unscheduleJobs(Scheduler scheduler, List<TriggerKey> triggerKeys) throws Exception {
        checkArgument(scheduler != null, "The Parameter Scheduler must not be null.");
        checkArgument(((triggerKeys != null) && !(triggerKeys.isEmpty())),
                "The Parameter TriggerKeys must not be null or Emty.");
        boolean unscheduled = false;
        try {
            unscheduled = scheduler.unscheduleJobs(triggerKeys);
            logger.debug("\n\n#################Unscheduling Triggers : {}\n",
                    triggerKeys);
        } catch (SchedulerException se) {
            se.printStackTrace();
            logger.error("\n@@@@@@@@@@@@@@@@@@@@@@SchedulerException : "
                    + "{}", se);
        }
        return unscheduled;
    }

    /**
     * @param scheduler
     * @param trigger
     * @throws Exception
     */
    protected final void scheduleJob(Scheduler scheduler, Trigger trigger) throws Exception {
        checkArgument(scheduler != null, "The Parameter Scheduler must not be null.");
        checkArgument(trigger != null, "The Parameter Trigger must not be null.");
        try {
            scheduler.scheduleJob(trigger);
            logger.debug("\n\n#####################Scheduling Trigger : "
                    + "{}\n", trigger.getKey());
        } catch (SchedulerException se) {
            se.printStackTrace();
            logger.error("\n@@@@@@@@@@@@@@@@@@@@@@SchedulerException : "
                    + "{} - for Trigger : {}", se, trigger.getKey());
        }
    }

    /**
     * @param scheduler
     * @param jobDetail
     * @param trigger
     * @throws Exception
     */
    protected final void scheduleJob(Scheduler scheduler, JobDetail jobDetail, Trigger trigger) throws Exception {
        checkArgument(scheduler != null, "The Parameter Scheduler must not be null.");
        checkArgument(jobDetail != null, "The Parameter JobDetail must not be null.");
        checkArgument(trigger != null, "The Parameter Trigger must not be null.");
        try {
            scheduler.scheduleJob(jobDetail, trigger);
            logger.debug("\n\n#####################Scheduling JobDetail : {} "
                    + "with Trigger : {}\n", jobDetail, trigger.getKey());
        } catch (SchedulerException se) {
            se.printStackTrace();
            logger.error("\n@@@@@@@@@@@@@@@@@@@@@@SchedulerException : "
                    + "{} - for Trigger : {}", se, trigger.getKey());
        }
    }

    /**
     * @param scheduler
     * @param jobDetail
     * @param triggersForJob
     * @param replace
     * @throws Exception
     */
    protected final void scheduleJob(Scheduler scheduler, JobDetail jobDetail, Set<? extends Trigger> triggersForJob,
            boolean replace) throws Exception {
        checkArgument(scheduler != null, "The Parameter Scheduler must not be null.");
        checkArgument(jobDetail != null, "The Parameter JobDetail must not be null.");
        checkArgument(((triggersForJob != null) && !(triggersForJob.isEmpty())),
                "The Parameter TriggersForJob must not be null and not be Empty.");
        try {
            scheduler.scheduleJob(jobDetail, triggersForJob, replace);
            logger.debug("\n\n#####################Scheduling JobDetail : {} "
                    + "with Triggers Set : {}\n", jobDetail, triggersForJob);
        } catch (SchedulerException se) {
            se.printStackTrace();
            logger.error("\n@@@@@@@@@@@@@@@@@@@@@@SchedulerException : "
                    + "{} - for JobDetail : {}", se, jobDetail);
        }
    }

    /**
     * @param scheduler
     * @param triggersAndJobs
     * @param replace
     * @throws Exception
     */
    protected final void scheduleJobs(Scheduler scheduler, Map<JobDetail, Set<? extends Trigger>> triggersAndJobs,
            boolean replace) throws Exception {
        checkArgument(scheduler != null, "The Parameter Scheduler must not be null.");
        checkArgument(((triggersAndJobs != null) && !(triggersAndJobs.isEmpty())),
                "The Parameter TriggersForJob must not be null and not be Empty.");
        try {
            scheduler.scheduleJobs(triggersAndJobs, replace);
            logger.debug("\n\n####################Scheduling triggersAndJobs: "
                    + "{}\n", triggersAndJobs);
        } catch (SchedulerException se) {
            se.printStackTrace();
            logger.error("\n@@@@@@@@@@@@@@@@@@@@@@SchedulerException : "
                    + "{}", se);
        }
    }

    @PostConstruct
    protected final void registerTask() {
        GPTaskRepository.registerJobTask(getJobTaskKey(), this);
    }

    /**
     * @return {@link org.geosdi.geoplatform.support.quartz.task.JobTask.JobTaskKey}
     */
    protected abstract JobTaskKey getJobTaskKey();
}
