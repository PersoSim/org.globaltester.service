package org.globaltester.service.ui.commands;

import java.util.Iterator;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.PlatformUI;
import org.globaltester.service.GtService;
import org.globaltester.service.ui.Activator;

public class StartStopServiceHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		String startOrStop = event.getParameter("org.globaltester.service.ui.parameter.startOrStop");

		ISelection iSel = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService().getSelection();

		// check type of selection
		if (iSel instanceof IStructuredSelection) {

			// handle every selected GtService
			try {
				Iterator<?> selectionIter = ((IStructuredSelection) iSel).iterator();
				while (selectionIter.hasNext()) {
					Object curElem = selectionIter.next();
					if (curElem instanceof GtService) {
						if ("start".equals(startOrStop)) {
							((GtService) curElem).start();
						} else if ("stop".equals(startOrStop)) {
							((GtService) curElem).stop();
						}
					}
				}
			} catch (RuntimeException e) {
				ErrorDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Error", null,
						new Status(IStatus.ERROR, Activator.PLUGIN_ID, 1, e.getMessage(), e));
			}
		}

		return null;
	}

}
