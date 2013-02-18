package listener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.MessageBox;

import util.SCDetector;
import view.MainWindow;

public class ConvertListener implements SelectionListener {

	MainWindow mainWindow;

	public ConvertListener(MainWindow m) {
		mainWindow = m;
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

		StringBuffer sb = new StringBuffer();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(file), ret));
			String line;
			while ((line = reader.readLine()) != null)
				sb.append(line + "\n");
		} catch (UnsupportedEncodingException e1) {
			error("编码错误，不支持的编码");
			return;
		} catch (IOException e1) {
			error("读取文件错误");
			return;
		}

		try {
			reader.close();
		} catch (IOException e1) {
		}

		try {
			PrintWriter writer = new PrintWriter(file, "utf-8");
			writer.write(sb.toString());
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e1) {
			
			e1.printStackTrace();
		}

		MessageBox mb = new MessageBox(mainWindow.getShell(),
				SWT.ICON_INFORMATION | SWT.YES);
		mb.setMessage("转换成功");
		mb.open();

	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub

	}

}
