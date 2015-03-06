package com.example.teamlab.myapplication.util;

import android.text.format.DateFormat;
import android.util.Log;

import com.example.teamlab.myapplication.BuildConfig;
import com.example.teamlab.myapplication.ExampleApplication;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;

/**
 * ログを出力するためのクラス
 * <p/>
 * 参考: http://wada811.blogspot.com/2013/04/android-log-util.html
 * Created by hyata on 2014/06/20.
 */
public class LogUtil {
    //タグ
    private static final String TAG = "LogUtil";
    //ログ・ファイルのパス
    private static final String LOG_FILE_NAME;

    static {
        LOG_FILE_NAME = "example_" + getCurrentDateTime("yyyyMMdd_kkmmss") + ".log";
    }

    /**
     * 現在時刻を文字列で返す
     *
     * @param format フォーマット
     * @return 現在時刻の文字列
     */
    private static CharSequence getCurrentDateTime(String format) {
        return DateFormat.format(format, Calendar.getInstance());
    }

    /**
     * ログタイプ
     */
    enum LogType {
        VERBOSE,
        DEBUG,
        INFO,
        WARN,
        ERROR
    }

    /**
     * ログをLogCatとFileに出力する
     *
     * @param type    ログのタイプ
     * @param message 出力する文字列
     * @param e       エラーオブジェクト
     * @return 結果
     */
    private static int outputLog(LogType type, String message, Throwable e) {
        //デバック時のみ出力する
        if (!BuildConfig.DEBUG) {
            return -1;
        }

        outputLogFile(type, message, e);

        //タイプによってLogCatへの出力を変更
        int result;
        switch (type) {
            case VERBOSE:
                result = Log.v(TAG, message);
                break;
            case DEBUG:
                result = Log.d(TAG, message);
                break;
            case INFO:
                result = Log.i(TAG, message);
                break;
            case WARN:
                if (e == null) {
                    result = Log.w(TAG, message);
                } else {
                    result = Log.w(TAG, message, e);
                }
                break;
            case ERROR:
                if (e == null) {
                    result = Log.e(TAG, message);
                } else {
                    result = Log.e(TAG, message, e);
                }
                break;
            default:
                result = -1;
                break;
        }
        return result;
    }

    /**
     * ログをLogCatとFileに出力する
     *
     * @param type    ログのタイプ
     * @param message 出力する文字列
     * @return 結果
     */
    private static int outputLog(LogType type, String message) {
        return outputLog(type, message, null);
    }

    /**
     * ログをFileに出力する
     *
     * @param type    ログのタイプ
     * @param message 出力する文字列
     * @param e       エラーオブジェクト
     */
    private static void outputLogFile(LogType type, String message, Throwable e) {
        StringBuilder builder = new StringBuilder();
        //時刻
        builder.append(getCurrentDateTime("yyyy/MM/dd,kk:mm:ss"));
        //タイプ
        builder.append("[");
        builder.append(type.toString());
        builder.append("]");
        //メッセージ
        builder.append(message);

        if (e != null) {
            //エラー処理
            builder.append("[");
            builder.append(e.getClass().getSimpleName());
            builder.append(":");
            builder.append(e.getMessage());
            builder.append("]");
        }
        //改行
        builder.append(System.getProperty("line.separator"));

        File fileDir;
        fileDir = ExampleApplication.getAppContext().getExternalFilesDir("log");
        //ファイル出力
        File logFile = new File(fileDir, LOG_FILE_NAME);
        if (logFile.getParentFile().mkdir()) {
            Log.i(TAG, "LogFile mkdir");
        }
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(logFile, true), "UTF-8"));
            writer.write(builder.toString());
            writer.flush();
        } catch (FileNotFoundException ex) {
            Log.e(TAG, "FileNotFoundException:" + LOG_FILE_NAME, e);

        } catch (UnsupportedEncodingException ex) {
            Log.e(TAG, "UnsupportedEncodingException:", e);

        } catch (IOException ex) {
            Log.e(TAG, "IOException:", ex);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException ex) {
                    Log.e(TAG, "IOException:", ex);
                }
            }
        }

    }

    public static void v() {
        outputLog(LogType.VERBOSE, getMetaInfo());
    }

    public static void v(String message) {
        outputLog(LogType.VERBOSE, getMetaInfo() + null2str(message));
    }

    public static void d() {
        outputLog(LogType.DEBUG, getMetaInfo());
    }

    public static void d(String message) {
        outputLog(LogType.DEBUG, getMetaInfo() + null2str(message));
    }

    public static void i() {
        outputLog(LogType.INFO, getMetaInfo());
    }

    public static void i(String message) {
        outputLog(LogType.INFO, getMetaInfo() + null2str(message));
    }

    public static void w(String message) {
        outputLog(LogType.WARN, getMetaInfo() + null2str(message));
    }

    public static void w(String message, Throwable e) {
        outputLog(LogType.WARN, getMetaInfo() + null2str(message), e);
    }

    public static void e(String message) {
        outputLog(LogType.ERROR, getMetaInfo() + null2str(message));
    }

    public static void e(String message, Throwable e) {
        outputLog(LogType.ERROR, getMetaInfo() + null2str(message), e);
    }

    public static void e(Throwable e) {
        outputLog(LogType.ERROR, getMetaInfo(), e);
    }

    private static String null2str(String string) {
        if (string == null) {
            return "(null)";
        }
        return string;
    }

    /**
     * ログ呼び出し元のメタ情報を取得する
     *
     * @return [className#methodName:line]
     */
    private static String getMetaInfo() {
        // スタックトレースから情報を取得 // 0: VM, 1: Thread, 2: LogUtil#getMetaInfo, 3: LogUtil#d など, 4: 呼び出し元
        final StackTraceElement element = Thread.currentThread().getStackTrace()[4];
        return LogUtil.getMetaInfo(element);
    }

    /**
     * スタックトレースからクラス名、メソッド名、行数を取得する
     *
     * @return [className#methodName:line]
     */
    public static String getMetaInfo(StackTraceElement element) {
        /* クラス名、メソッド名、行数を取得 */
        final String fullClassName = element.getClassName();
        final String simpleClassName = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
        final String methodName = element.getMethodName();
        final int lineNumber = element.getLineNumber();
        /* メタ情報 */
        final String metaInfo = "[" + simpleClassName + "#" + methodName + ":" + lineNumber + "]";
        return metaInfo;
    }
}
