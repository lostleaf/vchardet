import org.eclipse.swt.widgets.Display;

import view.MainWindow;

public class Main {
	public static void main(String args[]) {
		MainWindow mw = new MainWindow();
		mw.setBlockOnOpen(true);
		mw.open();
		Display.getCurrent().dispose();
	}
}
