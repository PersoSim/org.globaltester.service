package org.globaltester.service.ui.views;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.globaltester.service.GtService;
import org.globaltester.service.ui.Activator;
import org.osgi.framework.Bundle;

/**
 * LabelProvider for the {@link ServiceDashboardView}
 * 
 * @author amay
 *
 */
class ServiceDashboardLabelProvider extends LabelProvider implements ITableLabelProvider {

	private static Image IMG_RUNNING = null;
	private static Image IMG_STOPPED = null;
	
	private void initImages() {
		Bundle bundle = Activator.getDefault().getBundle();
		
		if (IMG_RUNNING == null) {
			Path path = new Path("icons/running.png");
			URL url = FileLocator.find(bundle, path, null);

			try {
				IMG_RUNNING = new Image(null, url.openStream());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if (IMG_STOPPED == null) {
			Path path = new Path("icons/stopped.png");
			URL url = FileLocator.find(bundle, path, null);

			try {
				IMG_STOPPED = new Image(null, url.openStream());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	public ServiceDashboardLabelProvider() {
		initImages();
	}

	public String getColumnText(Object obj, int index) {
		if (! (obj instanceof GtService)) return null;
		GtService service = (GtService) obj;
		
		switch (index) {
		case 0:
			return service.getName();
		case 1:
			return service.isRunning() ? "running" : "stopped";
		default:
			return null;
		}
	}

	public Image getColumnImage(Object obj, int index) {
		if (! (obj instanceof GtService)) return null;
		GtService service = (GtService) obj;
		
		switch (index) {
		case 1:
			if (service.isRunning()) {
				return IMG_RUNNING;
			} else {
				return IMG_STOPPED;
			}
		default:
			return null;
		}
	}
}