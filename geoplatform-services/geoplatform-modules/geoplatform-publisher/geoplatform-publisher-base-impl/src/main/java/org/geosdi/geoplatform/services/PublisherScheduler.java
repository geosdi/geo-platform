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
package org.geosdi.geoplatform.services;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
@Service("publisherScheduler")
public class PublisherScheduler implements InitializingBean {

    public final static String PUBLISHER_GROUP = "publisherGroup";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private Scheduler scheduler;
    private JobDetail cleanerJobTifDetail;
    private JobDetail cleanerJobShpDetail;

    private void generateCleanerJobs() {
        this.cleanerJobTifDetail = JobBuilder.newJob(PublisherTifCleanerJob.class)
                .withIdentity(PublisherTifCleanerJob.PUBLISHER_TIF_CLEANER_JOB, PublisherScheduler.PUBLISHER_GROUP)
                .withDescription("Clean the unpublished tif from Geoserver and from the disk")
                .storeDurably(true)
                .requestRecovery()
                .build();
        this.cleanerJobShpDetail = JobBuilder.newJob(PublisherShpCleanerJob.class)
                .withIdentity(PublisherShpCleanerJob.PUBLISHER_SHP_CLEANER_JOB, PublisherScheduler.PUBLISHER_GROUP)
                .withDescription("Clean the unpublished shp from Geoserver")
                .storeDurably(true)
                .requestRecovery()
                .build();
        try {
            this.scheduler.addJob(this.cleanerJobTifDetail, true);
            this.scheduler.addJob(this.cleanerJobShpDetail, true);
        } catch (SchedulerException ex) {
            logger.error("Error adding publisher cleaner jobs: " + ex);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        scheduler = StdSchedulerFactory.getDefaultScheduler();
        getScheduler().start();
        this.generateCleanerJobs();
    }

    /**
     * @return the scheduler
     */
    public Scheduler getScheduler() {
        return scheduler;
    }

    /**
     * @return the jobDetail
     */
    public JobDetail getCleanerJobTifDetail() {
        return cleanerJobTifDetail;
    }

    /**
     * @return the cleanerJobShpDetail
     */
    public JobDetail getCleanerJobShpDetail() {
        return cleanerJobShpDetail;
    }
}
