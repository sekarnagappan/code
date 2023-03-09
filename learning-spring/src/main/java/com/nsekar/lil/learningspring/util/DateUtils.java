package com.nsekar.lil.learningspring.util;

import org.springframework.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class DateUtils {

    public Date createDateFromDateString(String dateString){

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        if(StringUtils.hasText(dateString)){
            try{
                date = (Date)format.parse(dateString);
            } catch (ParseException e) {
                date = new Date();
            }
        } else {
            date = new Date();
        }
        return date;
    }
}
