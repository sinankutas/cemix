package com.arneca.evyap.api;/*
 * Created by  on 25.01.2021.
 */

public class DataModel {

    public String title;
    public String value;
    public boolean isRedActive;

    public DataModel(String t,  String c, boolean isRA )
    {
        title=t;
        value=c;
        isRedActive=isRA;
    }
}
