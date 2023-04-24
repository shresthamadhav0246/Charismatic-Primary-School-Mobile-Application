package com.bawp.charismaticprimaryschool.utill;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Util {
    public static final String DATABASE_NAME = "db_student_notes";
    public static final int VERSION =1;
    public static final String TABLE_NAME = "student_notes";

    public static final String KEY_ID ="id";
    public static final String KEY_NAME= "notes";
    public static final String KEY_DESCRIPTION ="description";
    public static final String KEY_CREATED_DATE = "createdDate";
    public static final String KEY_USER_NAME = "username";

    public static String formatDate(Date date){
        SimpleDateFormat simpleDateFormat =(SimpleDateFormat) SimpleDateFormat.getDateInstance();
        simpleDateFormat.applyPattern("EEE, MMM d");
        return simpleDateFormat.format(date);
    }

    public static Date formatString(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        try {
            return sdf.parse(dateString);
        } catch (ParseException e) {
            Log.e("Error", "Unable to parse date: " + dateString);
            return null;
        }
    }

//    public static Date fromTimestamp(Long value){
//        return value == null ? null: new Date(value);
//    }
//
//    public static Long dateToTimestamp(Date date){
//        return date == null ? null : date.getTime();
//    }

}
