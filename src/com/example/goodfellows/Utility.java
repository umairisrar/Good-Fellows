package com.example.goodfellows;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utility {

	private static Pattern pattern;
    private static Matcher matcher;
    
    private static final String UserName_PATTERN = 
    		 "^[_0-9]$";
            
    		//"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            //+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    
    public static boolean validate(String email) {
        pattern = Pattern.compile(UserName_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
 
    }
    
    public static boolean isNotNull(String txt){
        return txt!=null && txt.trim().length()>0 ? true: false;
    }
}
