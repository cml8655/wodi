package com.cml.wodi.crash;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.text.format.DateFormat;
import android.widget.Toast;

import com.cml.wodi.util.LogUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * UncaughtException handler,when UncaughtException occur,save log and send error.
 *
 * @author user
 */
public class CrashHandler implements UncaughtExceptionHandler {

    // system UncaughtException handler
    private UncaughtExceptionHandler mDefaultHandler;

    private Context mContext;
    // to save device info and error info
    private Map<String, String> mInfos = new HashMap<String, String>();

    public CrashHandler() {
    }


    /**
     * 初期化
     *
     * @param context
     */
    public void init(Context context) {
        mContext = context;

        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();

        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * when the UncaughtException occur deal with it
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex) && mDefaultHandler != null) {

            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                LogUtil.e("error : ", e);
            }
            // exit the process
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }

    /**
     * Custom error handling, collect error information to send error reports
     * and other operations are completed in this.
     *
     * @param ex
     * @return true:if handle　the exception;else return　false.
     */

    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }
        // use Toast to view error info
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(mContext, "Sorry, Muji Passport encountered an error.", Toast.LENGTH_LONG)
                        .show();
                Looper.loop();
            }
        }.start();
        // collect device parameter information
        collectDeviceInfo(mContext);
        // saved log
        saveCrashInfo2File(ex);
        return true;
    }

    /**
     * collect device info
     *
     * @param ctx
     */
    public void collectDeviceInfo(Context ctx) {
        //collect app version data
        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(),
                    PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionName = pi.versionName == null ? "null"
                        : pi.versionName;
                String versionCode = pi.versionCode + "";
                mInfos.put("versionName", versionName);
                mInfos.put("versionCode", versionCode);
            }
        } catch (NameNotFoundException e) {
            LogUtil.e("an error occured when collect package info", e);
        }

        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                mInfos.put(field.getName(), field.get(null).toString());
                LogUtil.d(field.getName() + " : " + field.get(null));
            } catch (Exception e) {
                LogUtil.e("an error occured when collect crash info", e);
            }
        }
    }

    /**
     * save log to file
     *
     * @param ex
     * @return return filename
     */
    private String saveCrashInfo2File(Throwable ex) {

        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : mInfos.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key + "=" + value + "\n");
        }

        //write the crash log
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();

        String result = writer.toString();
        sb.append(result);

        return writeLogIntoFile(sb);
    }

    /**
     * save file as "com-crash-{time}-{timestamp}.log" into /sdcard/crash/
     *
     * @param sb log StringBuffer
     * @return return filename　saved failed
     */
    private String writeLogIntoFile(StringBuffer sb) {

        long timestamp = System.currentTimeMillis();
        CharSequence time = DateFormat.format("yyyy-MM-dd-kk-mm-ss", new Date());

        String fileName = String.format("com-crash-%s-%d.log", time, timestamp);
        try {

            if (Environment.getExternalStorageState().equals(
                    Environment.MEDIA_MOUNTED)) {
                //write
                String path = Environment.getExternalStorageDirectory()
                        .getPath() + "/crash/";
                File dir = new File(path);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                FileOutputStream fos = new FileOutputStream(path + fileName);
                fos.write(sb.toString().getBytes());
                fos.close();
            }
            return fileName;
        } catch (FileNotFoundException exception) {
            //file save sdcard failed
            LogUtil.e("file to write is no found", exception);
            try {
                //try save file into phone
                FileOutputStream fos = mContext.openFileOutput(fileName, Context.MODE_APPEND);
                fos.write(sb.toString().getBytes());
                fos.close();
                return fileName;
            } catch (FileNotFoundException e) {
                //failed to save into phone, give up
                LogUtil.e("file to write is no found", e);
                return null;
            } catch (IOException e) {
                //failed to save into phone, give up
                LogUtil.e("file to write is no found", e);
                return null;
            }
        } catch (IOException exception) {
            //failed to save into phone， give up
            LogUtil.e("crash log file save failed", exception);
            return null;
        }
    }
}
