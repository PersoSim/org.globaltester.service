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
	 * 
	 * @return
	 */
	String getName();

	/**
	 * 
	 * @return whether the service is currently running or not
	 */
	boolean isRunning();

	/**
	 * Register a new {@link GtServiceListener} that is notified about relevant
	 * changes of this instance
	 * 
	 * @param listener
	 */
	void registerServiceListener(GtServiceListener listener);

	/**
	 * Unregister the given {@link GtServiceListener}.
	 * <p/>
	 * If the listener was not known nothing happens.
	 * 
	 * @param listener
	 */
	void unregisterServiceListener(GtServiceListener listener);

	/**
	 * Start the implemented service (i.e. make publicly available)
	 */
	void start();

	/**
	 * Stops the implemented service (i.e. it is no longer accessible after the
	 * call returns)
	 */
	void stop();

}
