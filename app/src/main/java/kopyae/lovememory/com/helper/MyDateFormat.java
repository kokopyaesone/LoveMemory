package kopyae.lovememory.com.helper;

import android.text.TextUtils;

import java.text.SimpleDateFormat;

public class MyDateFormat {

    public SimpleDateFormat DATE_FORMAT_YMD;
    public SimpleDateFormat DATE_FORMAT_YMD_TEXT;
    public SimpleDateFormat DATE_FORMAT_YMD_T_TEXT;
    public SimpleDateFormat DATE_FORMAT_YMD_HMS;
    public SimpleDateFormat DATE_FORMAT_DMY;
    public SimpleDateFormat DATE_FORMAT_DMY_HMS_Z;
    public SimpleDateFormat PATIENT_VISIT_TIME_FORMAT;


    private String result;

    public MyDateFormat() {
        DATE_FORMAT_YMD = new SimpleDateFormat("yyyy-MM-dd");
        DATE_FORMAT_YMD_TEXT = new SimpleDateFormat("dd MMMM, yyyy");
        DATE_FORMAT_YMD_HMS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DATE_FORMAT_YMD_T_TEXT = new SimpleDateFormat("dd MMMM, yyyy HH:mm");
        DATE_FORMAT_DMY = new SimpleDateFormat("dd/MM/yyyy");
        DATE_FORMAT_DMY_HMS_Z = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        PATIENT_VISIT_TIME_FORMAT=new SimpleDateFormat("HH:mm");
    }

    public String removeTfromServerDate(String datetime) {
        return datetime.replace("T", " ");
    }

    public String removeTimeFromServerDate(String datetime) {

        if (TextUtils.isEmpty(datetime)) {
            return "";
        }

        if (datetime.contains("T")) {
            return datetime.split("T")[0];
        } else {
            return datetime;
        }

    }
}
