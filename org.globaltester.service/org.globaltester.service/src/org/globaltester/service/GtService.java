package org.globaltester.service;

/**
 * Services (mostly background servers) provided by GlobalTester that should be
 * handled through the ServiceDashboard or similar common code.
 * 
 * @author amay
 *
 */
public interface GtService {

	/**
	 * User readable name of the Service
	 * @return
	 */
	String getName();

	/**
	 * 
	 * @return whether the service is currently running or not
	 */
	boolean isRunning();
	

}
