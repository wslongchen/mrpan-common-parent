package com.mrpan.common.dms.utils;

/**
 * Created by mrpan on 2016/11/6.
 */
public class NameValuePair
{
    protected String name;
    protected String value;

    public NameValuePair()
    {
    }

    public NameValuePair(String name)
    {
        this.name = name;
    }

    public NameValuePair(String name, String value)
    {
        this.name = name;
        this.value = value;
    }

    public String getName()
    {
        return this.name;
    }

    public String getValue()
    {
        return this.value;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setValue(String value)
    {
        this.value = value;
    }
}

