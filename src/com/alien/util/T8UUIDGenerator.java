package com.alien.util;

import java.io.Serializable;
import java.util.Properties;
import java.util.Random;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.type.Type;

public class T8UUIDGenerator implements IdentifierGenerator ,Configurable{
    
     @SuppressWarnings("unused")
	private String sep = "";
     private String str = "abcdefghijklmnopqrstuvwxyz";
    
     public Serializable generate(SessionImplementor session, Object obj)
        {
              
            long time = System.currentTimeMillis();
            String timeStr = time+""; 
            StringBuilder sb = new StringBuilder(timeStr); 
            Random ram = new Random();
            for(int i=0;i<3;i++){ 
            	int strNum = Math.abs(ram.nextInt(str.length())); 
            	int timeNum = Math.abs(ram.nextInt(timeStr.length()));  
            	sb.insert(timeNum, str.charAt(strNum));
            	
            }
             
            return sb.toString();
        }

     public void configure(Type type, Properties params, Dialect d) {
            sep = PropertiesHelper.getString("separator", params, "");
     }
}
