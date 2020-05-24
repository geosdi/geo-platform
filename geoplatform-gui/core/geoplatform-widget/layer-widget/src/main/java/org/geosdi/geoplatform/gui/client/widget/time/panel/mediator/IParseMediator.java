package org.geosdi.geoplatform.gui.client.widget.time.panel.mediator;

import com.google.common.collect.Maps;
import org.geosdi.geoplatform.gui.client.widget.time.panel.mediator.colleague.IParseColleague;

import javax.inject.Singleton;
import java.util.Map;

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

    @Singleton
    class ParseMediator implements IParseMediator {


        private Map<IParseColleagueKey, IParseColleague> colleagueMap = Maps.newLinkedHashMap();
        private String periodValue;

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
                period += p.execute(this.periodValue);
            }
            return period;
        }

        public void setPeriodValue(String periodValue) {
            this.periodValue = periodValue;
        }

    }

}

