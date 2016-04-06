package org.globaltester.service;

/**
 * Listener interface that allows {@link GtService}s to notify interested
 * entities about status changes of that specific {@link GtService}.
 * 
 * @author amay
 *
 */
public interface GtServiceListener {

	/**
	 * This method is called whenever the {@link GtService} associated with
	 * changes its state
	 * 
	 * @param service affected service
	 * @param newStatus new Status of the affected service
	 */
	void notifyStatusChanged(GtService service, boolean newStatus);

}
