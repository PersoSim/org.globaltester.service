package org.globaltester.service.ui.views;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * {@link IStructuredContentProvider} that essentially delivers the array of
 * {@link GtService}s that the
 * 
 * @author amay
 *
 */
class ServiceDashboardContentProvider implements IStructuredContentProvider {

	@Override
	public void inputChanged(Viewer v, Object oldInput, Object newInput) {
	}

	@Override
	public void dispose() {
	}

	@Override
	public Object[] getElements(Object parent) {
		return new String[] { "One", "Two", "Three" };
	}
}