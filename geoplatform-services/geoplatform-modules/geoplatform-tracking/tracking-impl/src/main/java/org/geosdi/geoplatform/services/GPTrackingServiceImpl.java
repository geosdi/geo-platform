/*
 *  geo-platform
 *  Rich webgis framework
 *  http://geo-platform.org
 * ====================================================================
 *
 * Copyright (C) 2008-2012 geoSDI Group (CNR IMAA - Potenza - ITALY).
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

import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.jws.WebService;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Nazzareno Sileno - CNR IMAA geoSDI Group
 * @email nazzareno.sileno@geosdi.org
 */
@WebService(endpointInterface = "org.geosdi.geoplatform.services.GPTrackingService")
public class GPTrackingServiceImpl implements GPTrackingService, InitializingBean {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private XMPPConnection connection;
    private ConnectionConfiguration config;
    @Autowired
    private TrackingScheduler scheduler;

    public GPTrackingServiceImpl(String host_xmpp_server, String port_xmpp_server,
            String username_xmpp_server, String password_xmpp_server) {
        config = new ConnectionConfiguration(host_xmpp_server, Integer.parseInt(port_xmpp_server));
        connection = new XMPPConnection(config);
        this.login(username_xmpp_server, password_xmpp_server);
    }

    private void login(String userName, String password) {
        try {
            logger.debug("Triyng to connect to the XMPP server");
            connection.connect();
            logger.debug("Triyng to login to the XMPP Server");
            connection.login(userName, password);
        } catch (XMPPException ex) {
            logger.error(ex.toString());
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
    }

    @Override
    public boolean unscribeLayerNotification(String username, String layerUUID) {
        TriggerKey triggerKey = new TriggerKey(username + ":" + layerUUID,
                TrackingScheduler.TRACKING_GROUP);
        return this.unscribeLayerNotification(triggerKey);
    }

    public boolean unscribeLayerNotification(TriggerKey triggerKey) {
        boolean result = Boolean.FALSE;
        try {
            result = this.scheduler.getScheduler().unscheduleJob(triggerKey);
            logger.debug("Job unscheduled: " + result);
        } catch (SchedulerException ex) {
            logger.error("Error unscheduling publisher cleaner job: " + ex);
        }
        return result;
    }

    @Override
    public void subscribeLayerNotification(String username, String emiteResource,
            String layerUUID, int secondToRefresh) {
        TriggerKey triggerKey = new TriggerKey(username + ":" + layerUUID,
                TrackingScheduler.TRACKING_GROUP);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.add(Calendar.SECOND, secondToRefresh);
        Trigger trigger = TriggerBuilder.newTrigger().forJob(
                this.scheduler.getSendMessageJobDetail()).
                withIdentity(triggerKey).
                withDescription("Runs after " + secondToRefresh + " seconds").
                startAt(calendar.getTime()).
                withSchedule(CalendarIntervalScheduleBuilder.calendarIntervalSchedule().
                withIntervalInSeconds(secondToRefresh).
                withMisfireHandlingInstructionFireAndProceed()).
                build();
        trigger.getJobDataMap().put(SendTrackingMessageJob.GP_TRACKING_SERVICE, this);
        String messageReceiver = username + "@" + this.connection.getHost() + '/' + emiteResource;
//        try {
//            connection.getRoster().createEntry(messageReceiver, user.getUsername(), null);
//        } catch (XMPPException ex) {
//            logger.error("Failed to add user: " + user.getUsername() + " to the service roster: " + ex);
//        }
        Message message = new Message(messageReceiver, Message.Type.normal);
        message.setSubject(XMPPSubjectServerEnum.LAYER_RELOAD.toString());
        message.setBody(layerUUID);
        trigger.getJobDataMap().put(SendTrackingMessageJob.MESSAGE, message);
        this.scheduleTrigger(triggerKey, trigger);
        logger.debug("Subscribed layer: " + layerUUID + " notification");
    }

    private void scheduleTrigger(TriggerKey triggerKey, Trigger trigger) {
        try {
            if (this.scheduler.getScheduler().checkExists(triggerKey)) {
                this.scheduler.getScheduler().rescheduleJob(triggerKey, trigger);
                logger.debug("Re-Scheduled layer notification");
            } else {
                this.scheduler.getScheduler().scheduleJob(trigger);
                logger.debug("Scheduled layer notification");
            }
        } catch (SchedulerException ex) {
            logger.error("Error adding tracking job: " + ex);
        }
    }

    protected XMPPConnection getConnection() {
        return this.connection;
    }
}