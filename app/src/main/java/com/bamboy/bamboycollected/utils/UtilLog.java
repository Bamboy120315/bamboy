package com.bamboy.bamboycollected.utils;

import android.os.Environment;
import android.util.Log;

import com.bamboy.bamboycollected.BuildConfig;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 日志系统
 *
 * 只需要写一个参数即可
 * 同时会打印所在类、方法、行号
 * 且只在DeBug模式会打印
 *
 * Created by Bamboy on 2017/3/24.
 */
@SuppressWarnings("unused")
public class UtilLog {
	/**
	 * 日志标签
	 */
	private final String TAG = "bamboyLog";
	/**
	 * 日志保存路径
	 */
	private final String LOG_PATH = Environment.getExternalStorageDirectory()
			+ "/bamboy/BamboyLog.txt";

	/**
	 * Drawing toolbox
	 */
	private final char TOP_LEFT_CORNER = '╔';
	private final char BOTTOM_LEFT_CORNER = '╚';
	private final char MIDDLE_CORNER = '╟';
	private final char HORIZONTAL_DOUBLE_LINE = '║';
	private final String DOUBLE_DIVIDER = "════════════════════════════════════════════";
	private final String SINGLE_DIVIDER = "────────────────────────────────────────────";
	private final String TOP_BORDER = TOP_LEFT_CORNER + DOUBLE_DIVIDER
			+ DOUBLE_DIVIDER;
	private final String BOTTOM_BORDER = BOTTOM_LEFT_CORNER
			+ DOUBLE_DIVIDER + DOUBLE_DIVIDER;
	private final String MIDDLE_BORDER = MIDDLE_CORNER + SINGLE_DIVIDER
			+ SINGLE_DIVIDER;

	public String getFileLineMethod() {
		StackTraceElement traceElement = new Exception()
				.getStackTrace()[1];
		StringBuffer toStringBuffer = new StringBuffer("[")
				.append(traceElement.getFileName()).append(" | ")
				.append(traceElement.getLineNumber()).append(" | ")
				.append(traceElement.getMethodName()).append("()").append("]");
		return toStringBuffer.toString();
	}

	/**
	 * 获取文件名称
	 *
	 * @return
	 */
	public String getFileName() {
		StackTraceElement traceElement = new Exception()
				.getStackTrace()[1];
		return traceElement.getFileName();
	}

	/**
	 * 获取函数名称
	 *
	 * @return
	 */
	public String getMethodName() {
		StackTraceElement traceElement = new Exception()
				.getStackTrace()[1];
		return traceElement.getMethodName();
	}

	/**
	 * 获取行号
	 *
	 * @return
	 */
	public int getLineNumber() {
		StackTraceElement traceElement = new Exception()
				.getStackTrace()[1];
		return traceElement.getLineNumber();
	}

	/**
	 * 获取包名
	 *
	 * @return
	 */
	public String getPackage() {
		StackTraceElement traceElement = new Exception()
				.getStackTrace()[1];
		return traceElement.getClassName();
	}

	/**
	 * 获取当前时间
	 *
	 * @return
	 */
	public String getTime() {
		SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyy.MM.dd.HH:mm:ss", Locale.getDefault());
		Date curDate = new Date(System.currentTimeMillis());
		String str = formatter.format(curDate);
		return str;
	}

	/**
	 * 写日志
	 *
	 * @param fileName
	 * @param content
	 */
	public synchronized void saveToLogcalFile(String fileName,
			String content) {
		// try {
		// // 打开一个随机访问文件流，按读写方式
		// RandomAccessFile randomFile = new RandomAccessFile(fileName, "rw");
		// // 文件长度，字节数
		// long fileLength = randomFile.length();
		// // 将写文件指针移到文件尾。
		// randomFile.seek(fileLength);
		// String s2=new String(content.getBytes("GBK"),"iso8859-1");
		// randomFile.writeBytes(s2);
		// randomFile.close();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
	}

	/**
	 * 写日志
	 *
	 * @param URL
	 * @param content
	 */
	public void saveToSeverFile(String URL, String content) {

	}

	private String getLoginfo(){
		StackTraceElement traceElement = new Exception()
				.getStackTrace()[1];
		StringBuffer sb = new StringBuffer("[")
				.append(traceElement.getClassName()).append(" | 行号：")
				.append(traceElement.getLineNumber()).append(" | 方法：")
				.append(traceElement.getMethodName()).append("()").append("]");
		return sb.toString();
	}

	public void e(String msg) {
		if (false == BuildConfig.DEBUG) {
			return;
		}
		String content = msg + "," + getLoginfo() + getTime();
		Log.e(TAG, content);
	}

	public void e(String msg, boolean flag) {
		if (false == BuildConfig.DEBUG) {
			return;
		}
		String content = msg + "," + getLoginfo() + getTime();
		Log.e(TAG, content);
		if (false == flag) {
			return;
		}
		saveToLogcalFile(LOG_PATH, content);
	}

	public void e(String msg, String addTag, boolean flag) {
		if (false == BuildConfig.DEBUG) {
			return;
		}
		String content = msg + "," + getLoginfo() + getTime();
		Log.e(TAG + addTag, content);
		if (false == flag) {
			return;
		}
		saveToLogcalFile(LOG_PATH, content);
	}

	public void e(String msg, String addTag) {
		if (false == BuildConfig.DEBUG) {
			return;
		}
		String content = msg + "," + getLoginfo() + getTime();
		Log.e(TAG + addTag, content);
	}

	public void i(String msg) {
		if (false == BuildConfig.DEBUG) {
			return;
		}
		String logInfo = getLoginfo();
		String content = msg + "," + logInfo + getTime();
		Log.i(TAG, TOP_BORDER);
		Log.i(TAG, HORIZONTAL_DOUBLE_LINE + " " + logInfo);

		Log.i(TAG, MIDDLE_BORDER);
		Log.i(TAG, HORIZONTAL_DOUBLE_LINE + " " + msg);
		Log.i(TAG, BOTTOM_BORDER);
	}

	public void i(String msg, boolean flag) {
		if (false == BuildConfig.DEBUG) {
			return;
		}
		String content = msg + "," + getLoginfo() + getTime();
		Log.i(TAG, content);
		if (false == flag) {
			return;
		}
		saveToLogcalFile(LOG_PATH, content);
	}

	public void i(String msg, String addTag) {
		if (false == BuildConfig.DEBUG) {
			return;
		}
		String content = msg + "," + getLoginfo() + getTime();
		Log.i(TAG + addTag, content);

	}

	public void i(String msg, String addTag, boolean flag) {
		if (false == BuildConfig.DEBUG) {
			return;
		}
		String content = msg + "," + getLoginfo() + getTime();
		Log.i(TAG + addTag, content);
		if (false == flag) {
			return;
		}
		saveToLogcalFile(LOG_PATH, content);
	}

	public void w(String msg) {
		if (false == BuildConfig.DEBUG) {
			return;
		}
		String content = msg + "," + getLoginfo() + getTime();
		Log.w(TAG, content);
	}

	public void w(String msg, boolean flag) {
		if (false == BuildConfig.DEBUG) {
			return;
		}
		String content = msg + "," + getLoginfo() + getTime();
		Log.w(TAG, content);
		if (false == flag) {
			return;
		}
		saveToLogcalFile(LOG_PATH, content);
	}

	public void w(String msg, String addTag) {
		if (false == BuildConfig.DEBUG) {
			return;
		}
		String content = msg + "," + getLoginfo() + getTime();
		Log.w(TAG + addTag, content);

	}

	public void w(String msg, String addTag, boolean flag) {
		if (false == BuildConfig.DEBUG) {
			return;
		}
		String content = msg + "," + getLoginfo() + getTime();
		Log.w(TAG + addTag, content);
		if (false == flag) {
			return;
		}
		saveToLogcalFile(LOG_PATH, content);
	}

}