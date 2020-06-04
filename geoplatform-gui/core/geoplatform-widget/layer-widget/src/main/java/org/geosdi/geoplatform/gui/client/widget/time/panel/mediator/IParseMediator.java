package org.geosdi.geoplatform.gui.client.widget.time.panel.mediator;

import com.google.common.collect.Maps;
import org.geosdi.geoplatform.gui.client.widget.time.panel.mediator.colleague.IParseColleague;

import javax.inject.Singleton;
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

    @Singleton
    class ParseMediator implements IParseMediator {


        private Map<IParseColleagueKey, IParseColleague> colleagueMap = Maps.newLinkedHashMap();
        private String periodValue;
        private String parsedPeriod = "";
        private boolean initTime;

        /**
         * @return {@link Boolean}
         */
        public boolean isInitTime() {
            return initTime;
        }

        @Override
        public void registerColleague(IParseColleague colleague) {
            if (!this.colleagueMap.containsKey(colleague.getKey())) {
                this.colleagueMap.put(colleague.getKey(), colleague);
            }
        }

        public Long calculatePeriod(String s) {
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
        }


    }

}

