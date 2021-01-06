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
package org.geosdi.geoplatform.support.quartz.task.repository;

import org.geosdi.geoplatform.support.quartz.task.JobTask;
import org.geosdi.geoplatform.support.quartz.task.exception.TaskException;

import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public interface TaskRepository {

    /**
     * <p>
     * Register Task with its {@link TaskKey} Key
     * </p>
     *
     * @param <K>
     * @param <T>
     * @param key
     * @param task
     * @throws TaskException
     */
    <K extends TaskKey, T extends Object> void registerJobTask(K key, T task)
            throws TaskException;

    /**
     *
     * @param <K>
     * @param <T>
     * @param key
     * @return
     * @throws TaskException
     */
    <K extends TaskKey, T extends JobTask> T getJobTask(K key) throws
            TaskException;

    /**
     *
     * @return {@link String} Repository Name
     */
    String getRepositoryName();

    /**
     * <p>
     * Task Key</p>
     */
    abstract class TaskKey extends RenderingHints.Key {

        private static final AtomicInteger val = new AtomicInteger(0);

        public TaskKey() {
            super(val.incrementAndGet());
        }

        @Override
        public String toString() {
            return getClass().getName() + " : privatekey = "
                    + super.intKey();
        }
    }
}
