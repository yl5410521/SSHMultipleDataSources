package com.alien.util; 

import java.io.BufferedInputStream; 
import java.io.File; 
import java.io.FileInputStream; 
import java.io.IOException; 
import java.net.MalformedURLException; 
import java.net.UnknownHostException; 

import jcifs.smb.SmbException; 
import jcifs.smb.SmbFile; 
import jcifs.smb.SmbFileOutputStream; 

public class Smb { 


//1. 导入jcifs-1.3.14.1.jar架包,创建类Smb 
// 
//2. 声明属性 

private String url = "smb://administrator:cqyhzl2016@192.168.1.10/software/";   
private SmbFile smbFile = null; 
private SmbFileOutputStream smbOut = null; 
private static Smb smb = null; //共享文件协议 

//3. 得到Smb和连接的方法 

public static synchronized Smb getInstance(String url) { 
  if (smb == null) 
   return new Smb(url); 
  return smb; 
} 

/** 
  * @param url服务器路径 
  */ 
private Smb(String url) { 
  this.url = url; 
  this.init(); 
} 

public void init() { 
  try { 
   System.out.println("开始连接...url：" + this.url); 
   smbFile = new SmbFile(this.url); 
   smbFile.connect(); 
   System.out.println("连接成功...url：" + this.url); 
  } catch (MalformedURLException e) { 
   e.printStackTrace(); 
   System.out.print(e); 
  } catch (IOException e) { 
   e.printStackTrace(); 
   System.out.print(e); 
  } 
} 

//4. 上传文件的方法 

/** 
  * 上传文件到服务器 
  */ 
public int uploadFile(File file) { 
  int flag = -1; 
  BufferedInputStream bf = null; 
  try { 
   this.smbOut = new SmbFileOutputStream(this.url + "/" + file.getName(), false); 
   bf = new BufferedInputStream(new FileInputStream(file)); 
   byte[] bt = new byte[8192]; 
   int n = bf.read(bt); 
   while (n != -1) { 
    this.smbOut.write(bt, 0, n); 
    this.smbOut.flush(); 
    n = bf.read(bt); 
   } 
   flag = 0; 
   System.out.println("文件传输结束..."); 
  } catch (SmbException e) { 
   e.printStackTrace(); 
   System.out.println(e); 
  } catch (MalformedURLException e) { 
   e.printStackTrace(); 
   System.out.println(e); 
  } catch (UnknownHostException e) { 
   e.printStackTrace(); 
   System.out.println("找不到主机...url：" + this.url); 
  } catch (IOException e) { 
   e.printStackTrace(); 
   System.out.println(e); 
  } finally { 
   try { 
    if (null != this.smbOut) 
     this.smbOut.close(); 
    if (null != bf) 
     bf.close(); 
   } catch (Exception e2) { 
    e2.printStackTrace(); 
   } 
  } 

  return flag; 
} 

//5. 在main方法里面测试 

public static void main(String[] args) { 
  //服務器地址 格式為 smb://电脑用户名:电脑密码@电脑IP地址/IP共享的文件夹 
  String remoteUrl = "smb://administrator:cqyhzl2016@192.168.1.10/software/";  
  String localFile = "F:/wordfilter.txt";  //本地要上传的文件 
  File file = new File(localFile); 
  Smb smb = Smb.getInstance(remoteUrl); 
  smb.uploadFile(file);// 上传文件 
} 
}