package org.globaltester.service.ui.commands;

import java.util.Iterator;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.PlatformUI;
import org.globaltester.service.GtService;

public class StartStopServiceHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		String startOrStop = event
		        .getParameter("org.globaltester.service.ui.parameter.startOrStop");
		
		
		ISelection iSel = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
				.getSelectionService().getSelection();
		
		// check type of selection
		if (!(iSel instanceof IStructuredSelection)) {
			return null;
		}
		
		//handle every selected GtService
		Iterator<?> selectionIter = ((IStructuredSelection) iSel).iterator();
					while (selectionIter.hasNext()) {
						Object curElem = (Object) selectionIter.next();
						if (curElem instanceof GtService) {
							switch (startOrStop) {
								case "start":
									((GtService) curElem).start();
									break;
								case "stop":
									((GtService) curElem).stop();
									break;
							
							}
						}
					}
		
		return null;
	}

}
