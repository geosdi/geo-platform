package org.geosdi.geoplatform.support.async.spring.task.notification.dispatcher;

import org.geosdi.geoplatform.support.async.spring.task.notification.GPAsyncNotificationTask;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface GPNotificationMessageDispatcher<NotificationMessage extends GPAsyncNotificationTask.GPNotificationMessage> {

    /**
     * @param theNotificationMessage
     * @throws Exception
     */
    void dispatchMessage(NotificationMessage theNotificationMessage) throws Exception;
}