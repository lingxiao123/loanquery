package com.mcjs.util;

public class Convert {
	public static String SpiltStr(String key)
    {
    	String[] _in = key.split(",");
    	String _key = "";
        for (String str : _in) {
        	_key = _key + ",'" + str + "'";
		}
        return _key.substring(1);
    }
}
