package org.globaltester.service.ui.views;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.globaltester.service.GtService;

/**
 * LabelProvider for the {@link ServiceDashboardView}
 * 
 * @author amay
 *
 */
class ServiceDashboardLabelProvider extends LabelProvider implements ITableLabelProvider {

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
		case 0:
			return getImage(service);
		default:
			return null;
		}
	}

	public Image getImage(Object obj) {
		return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJ_ELEMENT);
	}
}