package lib.framework.hollo.utils;

import android.text.TextUtils;
import android.util.Log;

/**
 * Created by orson on 16/3/3.
 */
public class MyLog {
    public static final int VERBOSE = 1;
    public static final int DEBUG = 2;
    public static final int INFO = 3;
    public static final int WARN = 4;
    public static final int ERROR = 5;
    public static final int NOTHING = 6;
    public static final int LEVEL = VERBOSE;
    public static final String SEPARATOR = ",";
    public static boolean isShow = false;

    public static void v(String message) {

        if (isShow && LEVEL <= VERBOSE) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            String tag = getDefaultTag(stackTraceElement);
            Log.v(tag, getLogInfo(stackTraceElement, message));
        }
    }

    public static void v(String tag, String message) {
        if (isShow && LEVEL <= VERBOSE) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            if (TextUtils.isEmpty(tag)) {
                tag = getDefaultTag(stackTraceElement);
            }
            Log.v(tag, getLogInfo(stackTraceElement, message));
        }
    }

    public static void d(String message) {
        if (isShow && LEVEL <= DEBUG) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            String tag = getDefaultTag(stackTraceElement);
            Log.d(tag, getLogInfo(stackTraceElement, message));
        }
    }

    public static void d(String tag, String message) {
        if (isShow && LEVEL <= DEBUG) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            if (TextUtils.isEmpty(tag)) {
                tag = getDefaultTag(stackTraceElement);
            }
            Log.d(tag, getLogInfo(stackTraceElement,message));
        }
    }

    public static void i(String message) {
        if (isShow && LEVEL <= INFO) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            String tag = getDefaultTag(stackTraceElement);
            Log.i(tag, getLogInfo(stackTraceElement, message));
        }
    }

    public static void i(String tag, String message) {
        if (isShow && LEVEL <= INFO) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            if (TextUtils.isEmpty(tag)) {
                tag = getDefaultTag(stackTraceElement);
            }
            Log.i(tag, getLogInfo(stackTraceElement, message));
        }
    }

    public static void w(String message) {
        if (isShow && LEVEL <= WARN) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            String tag = getDefaultTag(stackTraceElement);
            Log.w(tag, getLogInfo(stackTraceElement, message));
        }
    }

    public static void w(String tag, String message) {
        if (isShow && LEVEL <= WARN) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            if (TextUtils.isEmpty(tag)) {
                tag = getDefaultTag(stackTraceElement);
            }
            Log.w(tag, getLogInfo(stackTraceElement, message));
        }
    }

    public static void e(String tag, String message) {
        if (isShow && LEVEL <= ERROR) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            if (TextUtils.isEmpty(tag)) {
                tag = getDefaultTag(stackTraceElement);
            }
            Log.e(tag, getLogInfo(stackTraceElement, message));
        }
    }

    /**
     * 获取默认的TAG名称.
     * 比如在MainActivity.java中调用了日志输出.
     * 则TAG为MainActivity
     */
    public static String getDefaultTag(StackTraceElement stackTraceElement) {
        String fileName = stackTraceElement.getFileName();
        String stringArray[] = fileName.split("\\.");
        String tag = stringArray[0];
        return tag;
    }

    /**
     * 输出日志所包含的信息
     */
    public static String getLogInfo(StackTraceElement stackTraceElement, String message) {
        StringBuilder logInfoStringBuilder = new StringBuilder();
        // 获取线程名
        String threadName = Thread.currentThread().getName();
        // 获取线程ID
        long threadID = Thread.currentThread().getId();
        // 获取文件名.即xxx.java
        String fileName = stackTraceElement.getFileName();
        // 获取类名.即包名+类名
        String className = stackTraceElement.getClassName();
        // 获取方法名称
        String methodName = stackTraceElement.getMethodName();
        // 获取生日输出行数
        int lineNumber = stackTraceElement.getLineNumber();

        logInfoStringBuilder.append("\n-------------------------------------- ");
        logInfoStringBuilder.append("\nthreadID=" + threadID).append(SEPARATOR);
        logInfoStringBuilder.append("\nthreadName=" + threadName).append(SEPARATOR);
        logInfoStringBuilder.append("\nfileName=" + fileName).append(SEPARATOR);
        logInfoStringBuilder.append("\nclassName=" + className).append(SEPARATOR);
        logInfoStringBuilder.append("\nmethodName=" + methodName).append(SEPARATOR);
        logInfoStringBuilder.append("\nlineNumber=" + lineNumber);
        logInfoStringBuilder.append("\nmessage=" + message);
        logInfoStringBuilder.append("\n ------------------------------------\n");
        return logInfoStringBuilder.toString();
    }
}
