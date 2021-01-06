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
package org.geosdi.geoplatform.gui.client.widget.time.panel.mediator;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.geosdi.geoplatform.gui.client.widget.time.panel.mediator.colleague.IParseColleague;

import javax.inject.Singleton;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Vito Salvia- CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public interface IParseMediator {

    /**
     * @param colleague
     */
    void registerColleague(IParseColleague colleague);

    /**
     * @param s
     * @return {@link Long}
     */
    Long calculatePeriod(String s);

    /**
     * @param periodValue
     */
    void setPeriodValue(String periodValue);

    /**
     * @return {@link Boolean}
     */
    boolean isInitTime();

    /**
     * @return
     */
    String getParsedPeriod();

    /**
     * @param parsedPeriod
     */
    void setParsedPeriod(String parsedPeriod);

    /**
     *
     */
    void reset();

    /**
     * @param dateOperation
     */
    void addOperator(IDateOperation dateOperation);

    /**
     * @param date1
     * @return {@link Date}
     */
    Date getNextDate(Date date1);

    @Singleton
    class ParseMediator implements IParseMediator {


        private Map<IParseColleagueKey, IParseColleague> colleagueMap = Maps.newLinkedHashMap();
        private String periodValue;
        private String parsedPeriod = "";
        private boolean initTime;
        private List<IDateOperation> operationList = Lists.newArrayList();

        /**
         * @return {@link Boolean}
         */
        public boolean isInitTime() {
            return initTime;
        }

        /**
         * @param colleague
         */
        @Override
        public void registerColleague(IParseColleague colleague) {
            if (!this.colleagueMap.containsKey(colleague.getKey())) {
                this.colleagueMap.put(colleague.getKey(), colleague);
            }
        }

        /**
         * @param s
         * @return
         */
        public Long calculatePeriod(String s) {
            //s = "P3Y1M1W1DT1H1M1S";
            //s = "P1Y1M1W1DT1H30M1S";
            this.periodValue = s.replace("P", "");
            Long period = 0l;
            for (IParseColleague p : this.colleagueMap.values()) {
                if (this.periodValue.charAt(0) == 'T') {
                    this.initTime = Boolean.TRUE;
                    this.periodValue = this.periodValue.replace("T", "");
                }
                period += p.execute(this.periodValue);
            }
            return period;
        }

        /**
         * @param periodValue
         */
        public void setPeriodValue(String periodValue) {
            this.periodValue = periodValue;
        }

        /**
         * @return
         */
        public String getParsedPeriod() {
            return this.parsedPeriod;
        }

        /**
         * @param parsedPeriod
         */
        public void setParsedPeriod(String parsedPeriod) {
            this.parsedPeriod = this.parsedPeriod.concat(" ").concat(parsedPeriod).concat(" - ");
            this.parsedPeriod = this.parsedPeriod.substring(0, this.parsedPeriod.lastIndexOf("-"));
        }

        /**
         *
         */
        @Override
        public void reset() {
            this.initTime = false;
            this.periodValue = null;
            this.parsedPeriod = "";
            this.operationList.clear();
        }

        /**
         * @param dateOperation
         */
        public void addOperator(IDateOperation dateOperation) {
            this.operationList.add(dateOperation);
        }

        /**
         * @param date
         * @return {@link Date}
         */
        public Date getNextDate(Date date) {
            for (IDateOperation operation : this.operationList) {
                date = operation.changeDate(date);
            }
            return date;
        }

    }

}

