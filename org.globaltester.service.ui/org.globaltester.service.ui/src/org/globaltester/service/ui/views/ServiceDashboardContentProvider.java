package org.globaltester.service.ui.views;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Display;
import org.globaltester.service.Activator;
import org.globaltester.service.GtService;
import org.globaltester.service.GtServiceListener;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

/**
 * {@link IStructuredContentProvider} that essentially delivers the array of
 * {@link GtService}s that the
 * 
 * @author amay
 *
 */
class ServiceDashboardContentProvider implements IStructuredContentProvider, GtServiceListener {

	private StructuredViewer viewer;
	
	private ServiceTracker<GtService, GtService> serviceTracker;
	
	public ServiceDashboardContentProvider(StructuredViewer viewer) {
		this.viewer = viewer;
		
		
		serviceTracker = new ServiceTracker<>(Activator.getContext(), GtService.class, new ServiceTrackerCustomizer<GtService, GtService>(){

			@Override
			public GtService addingService(ServiceReference<GtService> reference) {
				GtService gtService = Activator.getContext().getService(reference);
				gtService.registerServiceListener(ServiceDashboardContentProvider.this);
				
				inputChanged(viewer, null, null);
				return gtService;
			}

			@Override
			public void modifiedService(ServiceReference<GtService> reference, GtService service) {
				inputChanged(viewer, null, null);
			}

			@Override
			public void removedService(ServiceReference<GtService> reference, GtService service) {
				inputChanged(viewer, null, null);
				service.unregisterServiceListener(ServiceDashboardContentProvider.this);
			}});
		serviceTracker.open();
		

		
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		
		Display.getDefault().asyncExec(new Runnable() {

			@Override
			public void run() {
				if (!viewer.getControl().isDisposed()) {
					viewer.refresh();
				}
			}
			
		});
		
	}

	@Override
	public void dispose() {
		serviceTracker.close();
	}

	@Override
	public Object[] getElements(Object parent) {
		return Activator.getAvailableGtServices();
	}

	@Override
	public void notifyStatusChanged(GtService service, boolean newStatus) {
		inputChanged(viewer, null, null);
	}
}