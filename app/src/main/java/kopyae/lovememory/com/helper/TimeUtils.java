package kopyae.lovememory.com.helper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ZMN on 8/31/17.
 **/

public class TimeUtils {

    public static String now() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        Date now = new Date();
        return sdf.format(now);
    }

    public static String date() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        Date now = new Date();
        return sdf.format(now);
    }

    public static String time() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.US);
        Date now = new Date();
        return sdf.format(now);
    }

    public static String dateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        if (date == null) {
            return "";
        } else {
            return sdf.format(date);
        }
    }
}
