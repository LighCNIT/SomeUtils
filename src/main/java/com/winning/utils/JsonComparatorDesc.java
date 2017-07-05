package com.winning.utils;

import java.util.Comparator;

import com.alibaba.fastjson.JSONObject;

public class JsonComparatorDesc implements Comparator<JSONObject>
{

    String dateName = "";

    public JsonComparatorDesc(String dateName)
    {
        this.dateName = dateName;
    }

    public int compare(JSONObject json1, JSONObject json2)
    {
        String date1 = json1.getString(dateName);
        String date2 = json2.getString(dateName);
        if (date1.compareTo(date2) < 0)
        {
            return 1;
        }
        else if (date1.compareTo(date2) > 0)
        {
            return -1;
        }
        return 0;
    }
}
