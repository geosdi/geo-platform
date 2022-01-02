/*
 *
 *    geo-platform
 *    Rich webgis framework
 *    http://geo-platform.org
 *   ====================================================================
 *
 *   Copyright (C) 2008-2022 geoSDI Group (CNR IMAA - Potenza - ITALY).
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
package org.geosdi.geoplatform.connector.geoserver.model.styles.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.time.ZoneOffset.UTC;
import static java.time.ZonedDateTime.ofInstant;

/**
 * @author Giuseppe La Scaleia - CNR IMAA geoSDI Group
 * @email giuseppe.lascaleia@geosdi.org
 */
public class LocalDateTimeTypeAdapter extends XmlAdapter<String, LocalDateTime> {

    private static final Logger logger = LoggerFactory.getLogger(LocalDateTimeTypeAdapter.class);
    //
    private static final String PATTERN = "yyyy-MM-dd HH:mm:ss.SSS z";
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(PATTERN);

    /**
     * @param s
     * @return {@link LocalDateTime}
     * @throws Exception
     */
    @Override
    public LocalDateTime unmarshal(String s) throws Exception {
        try {
            return ((s != null) && !(s.trim().isEmpty()) ? LocalDateTime.parse(s, dateTimeFormatter) : null);
        } catch (Exception exception) {
            logger.warn("###################Impossible format : {} with Pattern : {}\n", s, PATTERN);
            return null;
        }
    }

    /**
     * @param localDateTime
     * @return {@link String}
     * @throws Exception
     */
    @Override
    public String marshal(LocalDateTime localDateTime) throws Exception {
        return ((localDateTime != null) ? dateTimeFormatter.format(ofInstant(localDateTime.toInstant(UTC), UTC)) : null);
    }
}