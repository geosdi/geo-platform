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
package org.geosdi.geoplatform.experimental.el.query.task;

import org.geosdi.geoplatform.experimental.el.api.mapper.GPBaseMapper;
import org.geosdi.geoplatform.experimental.el.api.model.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class GPElasticSearchQueryTaskPrinter<D extends Document> extends Thread {

    protected static final Logger logger = LoggerFactory.getLogger(GPElasticSearchQueryTaskPrinter.class);
    //
    private final D value;
    protected final GPBaseMapper<D> mapper;
    private final AtomicInteger counter;

    public GPElasticSearchQueryTaskPrinter(D theValue, GPBaseMapper<D> theMapper, AtomicInteger theCounter) {
        this.value = theValue;
        this.mapper = theMapper;
        this.counter = theCounter;
    }

    @Override
    public void run() {
        try {
            print(this.value, this.counter);
        } catch (Exception ex) {
            logger.error("{} --------------------> generate error : {}\n",
                    Thread.currentThread().getName(), ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * @param value
     * @param counter
     * @throws Exception
     */
    protected void print(D value, AtomicInteger counter) throws Exception {
        logger.info("{} ------------------> print : \n{}\n",
                Thread.currentThread().getName(), mapper.writeAsString(value));
        counter.incrementAndGet();
    }
}
