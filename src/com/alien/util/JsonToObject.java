/**
 * 
 */
package com.alien.util;

import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author yl541_000
 *@createTime2015年11月12日
 */
public class JsonToObject {
    /** 
     * traverseJson 
     * 遍历的最简单直接的方式就是用递归， 
     * json是一个map与array的混合体： 
     * map中可以有map也可以有array； 
     * array中可以有array也可以有map； 
     * 所以递归之前，对于某一个节点，你不能断定他是JSONObject还是JSONArray 
     * @param json 
     * @return 
     */  
    public static Object traverseJson(Object json) {  
        // check null  
        if (json == null) {  
            return null;  
        }  
          
        try {  
              
        if (json instanceof JSONObject) {// if json is a Map  
              
            JSONObject retObj = new JSONObject();  
            JSONObject jsonObj = (JSONObject)json;  
            Iterator it = jsonObj.keys();  
            while (it.hasNext()) {  
                String key = (String)it.next();  
                Object value = jsonObj.get(key);  
                // TODO: do something here  
                retObj.put(key, traverseJson(value));  
            }  
            return retObj;  
              
        } else if (json instanceof JSONArray) {// if json is an Array  
              
            JSONArray retArr = new JSONArray();  
            JSONArray jsonArr = (JSONArray)json;  
            int len = jsonArr.length();  
            for (int i = 0; i < len; ++i) {  
                // TODO: do something here  
                retArr.put(traverseJson(jsonArr.get(i)));  
            }  
            return retArr;  
              
        } else {// if json is just a raw element  
              
            // TODO: do something here  
            return json;  
              
        }  
          
        } catch (Exception e) {  
            // deal Exception or throw it  
        }  
          
        return null;  
    }  
}
