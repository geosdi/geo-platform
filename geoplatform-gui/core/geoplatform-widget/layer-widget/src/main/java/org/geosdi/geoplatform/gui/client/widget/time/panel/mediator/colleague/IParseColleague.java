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
package org.geosdi.geoplatform.gui.client.widget.time.panel.mediator.colleague;

import org.geosdi.geoplatform.gui.client.widget.time.panel.mediator.IDateOperation;
import org.geosdi.geoplatform.gui.client.widget.time.panel.mediator.IParseColleagueKey;
import org.geosdi.geoplatform.gui.client.widget.time.panel.mediator.IParseMediator;

import javax.inject.Inject;

/**
 * @author Vito Salvia- CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public interface IParseColleague {

    /**
     * @return {@link IParseColleagueKey}
     */
    IParseColleagueKey getKey();

    /**
     * @param value
     * @return {@link IParseColleagueKey}
     */
    IDateOperation getOperator(int value);

    /**
     * @param period
     * @return {@link Long}
     */
    Long execute(String period);

    abstract class AbstractColleague implements IParseColleague {

        protected IParseMediator mediator;

        public AbstractColleague(IParseMediator parseMediator) {
            parseMediator.registerColleague(this);
            this.mediator = parseMediator;
        }

        /**
         * @param period
         * @return {@link Long}
         */
        @Override
        public Long execute(String period) {
            //3Y1M1W1DT1H1M1S
            Long p = 0l;

            if (period.contains(getKey().getTimeLabel())) {
                String v = period.substring(0, period.indexOf(getKey().getTimeLabel()));
                p += Integer.parseInt(v) * getKey().getValue();
                this.mediator.setPeriodValue(period.substring(period.indexOf(getKey().getTimeLabel()) + 1));
                this.mediator.setParsedPeriod(v + " " + getKey().getLabel());
                this.mediator.addOperator(getOperator(Integer.parseInt(v)));
            }
            return p;
        }


    }


    class YearColleague extends AbstractColleague {

        @Inject
        public YearColleague(IParseMediator parseMediator) {
            super(parseMediator);
        }

        /**
         * @return {@link IParseColleagueKey}
         */
        @Override
        public IParseColleagueKey getKey() {
            return IParseColleagueKey.YEAR;
        }

        /**
         * @param value
         * @return {@link IParseColleagueKey}
         */
        @Override
        public IDateOperation getOperator(int value) {
            return IParseColleagueKey.YEAR.getOperatorInstance(value);
        }

    }

    class MonthColleague extends AbstractColleague {

        @Inject
        public MonthColleague(IParseMediator parseMediator) {
            super(parseMediator);
        }

        /**
         * @return {@link IParseColleagueKey}
         */
        @Override
        public IParseColleagueKey getKey() {
            return IParseColleagueKey.MONTH;
        }

        /**
         * @param value
         * @return {@link IParseColleagueKey}
         */
        @Override
        public IDateOperation getOperator(int value) {
            return IParseColleagueKey.MONTH.getOperatorInstance(value);
        }

        /**
         * @param period
         * @return {@link Long}
         */
        @Override
        public Long execute(String period) {
            if (!this.mediator.isInitTime())
                return super.execute(period);
            return 0l;
        }
    }

    class WeekColleague extends AbstractColleague {

        @Inject
        public WeekColleague(IParseMediator parseMediator) {
            super(parseMediator);
        }

        /**
         * @return {@link IParseColleagueKey}
         */
        @Override
        public IParseColleagueKey getKey() {
            return IParseColleagueKey.WEEK;
        }

        /**
         * @param value
         * @return {@link IParseColleagueKey}
         */
        @Override
        public IDateOperation getOperator(int value) {
            return IParseColleagueKey.WEEK.getOperatorInstance(value);
        }

    }

    class DayColleague extends AbstractColleague {

        @Inject
        public DayColleague(IParseMediator parseMediator) {
            super(parseMediator);
        }

        /**
         * @return {@link IParseColleagueKey}
         */
        @Override
        public IParseColleagueKey getKey() {
            return IParseColleagueKey.DAY;
        }

        /**
         * @param value
         * @return {@link IParseColleagueKey}
         */
        @Override
        public IDateOperation getOperator(int value) {
            return IParseColleagueKey.DAY.getOperatorInstance(value);
        }

    }

    class HourColleague extends AbstractColleague {


        @Inject
        public HourColleague(IParseMediator parseMediator) {
            super(parseMediator);
        }

        /**
         * @return {@link IParseColleagueKey}
         */
        @Override
        public IParseColleagueKey getKey() {
            return IParseColleagueKey.HOUR;
        }

        /**
         * @param value
         * @return {@link IParseColleagueKey}
         */
        @Override
        public IDateOperation getOperator(int value) {
            return IParseColleagueKey.HOUR.getOperatorInstance(value);
        }


    }

    class MinuteColleague extends AbstractColleague {

        @Inject
        public MinuteColleague(IParseMediator parseMediator) {
            super(parseMediator);
        }

        /**
         * @return {@link IParseColleagueKey}
         */
        @Override
        public IParseColleagueKey getKey() {
            return IParseColleagueKey.MINUTE;
        }

        /**
         * @param value
         * @return {@link IParseColleagueKey}
         */
        @Override
        public IDateOperation getOperator(int value) {
            return IParseColleagueKey.MINUTE.getOperatorInstance(value);
        }

        /**
         * @param period
         * @return {@link Long}
         */
        @Override
        public Long execute(String period) {
            if (this.mediator.isInitTime())
                return super.execute(period);
            return 0l;
        }


    }

    class SecondColleague extends AbstractColleague {

        @Inject
        public SecondColleague(IParseMediator parseMediator) {
            super(parseMediator);
        }

        /**
         * @return {@link IParseColleagueKey}
         */
        @Override
        public IParseColleagueKey getKey() {
            return IParseColleagueKey.SECOND;
        }

        /**
         * @param value
         * @return {@link IParseColleagueKey}
         */
        @Override
        public IDateOperation getOperator(int value) {
            return IParseColleagueKey.SECOND.getOperatorInstance(value);
        }


    }

}
