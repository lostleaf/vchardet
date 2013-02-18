package listener;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.FileDialog;

import view.MainWindow;

public class OpenListener implements SelectionListener {

	MainWindow mainWindow;

	public OpenListener(MainWindow mw) {
		mainWindow = mw;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		// TODO Auto-generated method stub
		FileDialog dialog = new FileDialog(mainWindow.getShell(), SWT.OPEN);
		mainWindow.getPath().setText(dialog.open());
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub

	}

}
