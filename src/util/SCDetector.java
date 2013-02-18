package util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.mozilla.intl.chardet.nsDetector;
import org.mozilla.intl.chardet.nsICharsetDetectionObserver;

public class SCDetector {
	private boolean found = false;
	private String encoding = null;
	public static final int zh = 2, zh_CN = 3, zh_TW = 4;
	int language;
	public static SCDetector det = new SCDetector();

	public SCDetector(int language) {
		this.language = language;
	}

	public SCDetector() {
		this(zh);
	}

	public String detect(File inputFile) throws IOException {

		nsDetector det = new nsDetector(language);
		det.Init(new nsICharsetDetectionObserver() {
			public void Notify(String charset) {
				found = true;
				encoding = charset;
			}
		});
		BufferedInputStream imp = new BufferedInputStream(new FileInputStream(
				inputFile));

		byte[] buf = new byte[1024];
		int len;
		boolean done = false;
		boolean isAscii = true;

		while ((len = imp.read(buf, 0, buf.length)) != -1) {
			if (isAscii)
				isAscii = det.isAscii(buf, len);

			if (!isAscii && !done)
				done = det.DoIt(buf, len, false);
		}
		det.DataEnd();

		if (isAscii) {
			encoding = "ASCII";
			found = true;
		}

		if (!found) {
			String prob[] = det.getProbableCharsets();

			if (prob.length > 0) {
				// 在没有发现情况下，则取第一个可能的编码
				encoding = prob[0];
			} else {
				imp.close();
				return null;
			}
		}

		imp.close();
		return encoding.equals("windows-1252") ? "utf-16" : encoding;
	}

	public String detect(String fileName) throws IOException {
		return detect(new File(fileName));
	}

	public static SCDetector getDetector() {
		return det;
	}

	public static void main(String args[]) throws IOException {
		if (args.length == 0) {
			System.out.println("Usage SCDetector file");
			return;
		}

		SCDetector det = new SCDetector();
		for (int i = 0; i < args.length; i++) {
			File file = new File(args[i]);
			System.out.println(file + "   " + det.detect(file));
		}
	}
}
