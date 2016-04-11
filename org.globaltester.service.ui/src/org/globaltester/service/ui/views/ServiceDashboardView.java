package org.globaltester.service.ui.views;


import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ColumnPixelData;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.globaltester.service.GtService;


/**
 * Eclipse workbench view that show status and allows access to
 * {@link GtService}s.
 */
public class ServiceDashboardView extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "org.globaltester.service.ui.views.ServiceDashboardView";

	private TableViewer viewer;

	/**
	 * The constructor.
	 */
	public ServiceDashboardView() {
	}

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		viewer.setContentProvider(new ServiceDashboardContentProvider(viewer));
		viewer.setLabelProvider(new ServiceDashboardLabelProvider());
		viewer.setSorter(new ViewerSorter());
		viewer.setInput(getViewSite());
		
		createColumns();

		// Create the help context id for the viewer's control
		PlatformUI.getWorkbench().getHelpSystem().setHelp(viewer.getControl(), "org.globaltester.service.ui.viewer");
		getSite().setSelectionProvider(viewer);
		
		//create the context menu
	    MenuManager menuManager = new MenuManager();
	    Menu menu = menuManager.createContextMenu(viewer.getControl());
	    viewer.getControl().setMenu(menu);
	    getSite().registerContextMenu(menuManager, viewer);
		
	}
	
	/**
	 * Create the columns used in the table viewer.
	 */
	private void createColumns() {
		TableLayout layout = new TableLayout();
		viewer.getTable().setLayout(layout);
		viewer.getTable().setHeaderVisible(true);
		viewer.getTable().setLinesVisible(true);

		int colNr = 0;
		TableColumn tcName = new TableColumn(viewer.getTable(), SWT.NONE, colNr++);
		tcName.setText("Gt Service");
		layout.addColumnData(new ColumnWeightData(800));
		
		
		TableColumn tcStatus = new TableColumn(viewer.getTable(), SWT.NONE, colNr++);
		tcStatus.setText("Status");
		layout.addColumnData(new ColumnPixelData(100));
		
		
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}
}
