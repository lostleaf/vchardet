package view;

import listener.ConvertListener;
import listener.DetectListener;
import listener.OpenListener;

import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class MainWindow extends ApplicationWindow {

	private Text path;
	private Label result;
	private Button detect, convert, open;

	public MainWindow() {
		super(null);

	}

	@Override
	protected Control createContents(Composite parent) {
		parent.setLayout(null);

		path = new Text(parent, SWT.SINGLE | SWT.BORDER);
		path.setBounds(50, 50, 350, 30);

		open = new Button(parent, SWT.PUSH);
		open.setBounds(420, 50, 50, 30);
		open.setText("打开");

		result = new Label(parent, SWT.NONE);
		result.setBounds(50, 100, 300, 30);

		detect = new Button(parent, SWT.PUSH);
		detect.setBounds(300, 175, 70, 30);
		detect.setText("检测");

		convert = new Button(parent, SWT.PUSH);
		convert.setBounds(400, 175, 70, 30);
		convert.setText("转UTF-8");

		open.addSelectionListener(new OpenListener(this));
		detect.addSelectionListener(new DetectListener(this));
		convert.addSelectionListener(new ConvertListener(this));

		return parent;
	}

	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText("检测转换器");
		shell.setSize(500, 250);
		shell.setLocation(200, 200);
	}

	@Override
	public void create() {
		// TODO Auto-generated method stub
		this.setShellStyle(SWT.CLOSE | SWT.TITLE);
		super.create();
	}

	public Text getPath() {
		return path;
	}

	public Label getResult() {
		return result;
	}
}
