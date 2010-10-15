package org.geosdi.geoplatform.gui.action;

import com.google.gwt.user.client.ui.AbstractImagePrototype;

public abstract class ToolbarApplicationAction extends GeoPlatformToolbarAction {

	private String buttonName;

	public ToolbarApplicationAction(String buttonName, AbstractImagePrototype image) {
		super(image);
		this.buttonName = buttonName;
	}

	/**
	 * @return the buttonName
	 */
	public String getButtonName() {
		return buttonName;
	}

	/**
	 * @param buttonName
	 *            the buttonName to set
	 */
	public void setButtonName(String buttonName) {
		this.buttonName = buttonName;
	}

}
