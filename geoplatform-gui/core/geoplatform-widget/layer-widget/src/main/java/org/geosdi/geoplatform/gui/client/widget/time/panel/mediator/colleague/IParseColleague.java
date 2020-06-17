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
