package org.globaltester.service.ui.views;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Display;
import org.globaltester.service.Activator;
import org.globaltester.service.GtService;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;

/**
 * {@link IStructuredContentProvider} that essentially delivers the array of
 * {@link GtService}s that the
 * 
 * @author amay
 *
 */
class ServiceDashboardContentProvider implements IStructuredContentProvider {

	private ServiceListener serviceListener;

	public ServiceDashboardContentProvider(StructuredViewer viewer) {
		serviceListener = new ServiceListener(){

			@Override
			public void serviceChanged(ServiceEvent event) {
				Display.getDefault().asyncExec(new Runnable() {

					@Override
					public void run() {
						viewer.refresh();
					}
					
				});
				
			}
			
		};
		
		Activator.getContext().addServiceListener(serviceListener);
		
	}

	@Override
	public void inputChanged(Viewer v, Object oldInput, Object newInput) {
	}

	@Override
	public void dispose() {
		Activator.getContext().removeServiceListener(serviceListener);
	}

	@Override
	public Object[] getElements(Object parent) {
		return Activator.getAvailableGtServices();
	}
}