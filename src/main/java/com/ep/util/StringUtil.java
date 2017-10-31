package com.ep.util;

/**
 * Created by 吴晓海 on 2017/10/26.
 */
public class StringUtil {
    public static final String BLANK = "";

    public static final String LINE_SEPARATOR = "\r\n";

    public static final String LINE_SEPARATOR_JSP = "<br>";

    /**
     * 一次性判断多个或单个对象为空。
     * @param objects
     * @author cai niao
     * @return 只要有一个元素为Blank，则返回true
     */
    public static boolean isBlank(Object...objects){
        Boolean result = false ;
        for (Object object : objects) {
            if(object == null || "".equals(object.toString().trim())
                    || "null".equals(object.toString().trim())
                    || "[null]".equals(object.toString().trim())
                    || "[]".equals(object.toString().trim())){
                result = true ;
                break ;
            }
        }
        return result ;
    }
    /**
     * 一次性判断多个或单个对象不为空。
     * @param objects
     * @author zhou-baicheng
     * @return 只要有一个元素不为Blank，则返回true
     */
    public static boolean isNotBlank(Object...objects){
        return !isBlank(objects);
    }

}
