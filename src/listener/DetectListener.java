package listener;

import java.io.File;
import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.MessageBox;

import util.SCDetector;
import view.MainWindow;

public class DetectListener implements SelectionListener {
	MainWindow mainWindow;

	public DetectListener(MainWindow mw) {
		mainWindow = mw;
	}

	private void error(String msg) {
		MessageBox mb = new MessageBox(mainWindow.getShell(), SWT.OK
				| SWT.ICON_ERROR);
		mb.setMessage(msg);
		mb.open();
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		// TODO Auto-generated method stub
		File file = new File(mainWindow.getPath().getText());
		if (!file.exists()) {
			error("文件不存在");
			return;
		}

		String ret = null;
		try {
			ret = SCDetector.getDetector().detect(file);
		} catch (IOException e1) {
			error("检测时错误");
			return;
		}

		mainWindow.getResult().setText(ret);
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub

	}
}
