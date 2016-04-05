package org.globaltester.service;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

public class Activator implements BundleActivator {

	private static Activator defaultInstance;
	private static BundleContext context;
	private static ServiceTracker<GtService, GtService> factoryTracker;

	@Override
	public void start(BundleContext bundleContext) throws Exception {
		context = bundleContext;
		defaultInstance = this;
		factoryTracker = new ServiceTracker<>(context, GtService.class, null);
		factoryTracker.open();
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		if (factoryTracker != null) {
			factoryTracker.close();
			factoryTracker = null;
		}
		defaultInstance = null;
		context = null;
	}

	public static BundleContext getContext() {
		return context;
	}

	/**
	 * Returns a {@link Collection} of available {@link ProtocolFactory}
	 * objects. One for each registered OSGi service.
	 * 
	 * @return
	 */
	public static GtService[] getAvailableGtServices() {
		GtService[] emptyArray = new GtService[0];
		if (factoryTracker == null) {
			return emptyArray;
		}

		return factoryTracker.getServices(emptyArray);
	}

	public static Activator getDefault() {
		return defaultInstance;
	}

}
