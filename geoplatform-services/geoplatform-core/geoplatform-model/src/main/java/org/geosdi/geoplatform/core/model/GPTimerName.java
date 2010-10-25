/**
 * 
 */
package org.geosdi.geoplatform.core.model;

/**
 * @author giuseppe
 * 
 */
public enum GPTimerName {

	TIMER_A, TIMER_B, TIMER_C, TIMER_TEST;

	static public GPTimerName getDefault() {
		return TIMER_C;
	}
}
