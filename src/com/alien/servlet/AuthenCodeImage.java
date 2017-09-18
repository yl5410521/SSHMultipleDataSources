package com.alien.servlet;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import java.awt.*;
import java.awt.image.*;

import com.sun.image.codec.jpeg.*;
//import javax.imageio.*;
/**
 * <p>Title:通用web控件生成随机验证码 </p>
 * <p>Description: 生成随机验证码,在页面上的img标签中的src指向这个servlet的URL即可生成动态认证码。 </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author alien
 * @version 1.0
 */ 
public class AuthenCodeImage extends HttpServlet {
  private static final String CONTENT_TYPE = "image/jpeg"; // 输出的文件格式是jpeg ，关于HTTP输出类型的定义可参考相关文档说明
  private static final int WIDTH = 52;
  private static final int HEIGHT = 20;
  private static final int LENGTH = 2;
  public static final String SESSION_AUTHEN_CODE = "RandNo";  //设置保存的session对象，用户用户提交时进行匹配的操作。
  //Initialize global variables
  public void init() throws ServletException {
  }
  //Process the HTTP Get request
  @SuppressWarnings("restriction")
public void doGet(HttpServletRequest request, HttpServletResponse response) throws
      ServletException, IOException {
    response.setContentType(CONTENT_TYPE);  //定义输出的文件格式。
    OutputStream out = response.getOutputStream();
    int width = WIDTH;
    int height = HEIGHT;
    int length = LENGTH;
    /*String sessionName = request.getParameter("authenName");
    if (sessionName == null) {
      sessionName = SESSION_AUTHEN_CODE;
    }*/
    String sessionName = SESSION_AUTHEN_CODE;
    try {
      width = Integer.parseInt(request.getParameter("width"));
    }
    catch (Exception ex) {
      width = WIDTH;
    }
    try {
      height = Integer.parseInt(request.getParameter("heigth"));
    }
    catch (Exception ex) {
      height = HEIGHT;
    }
    try {
      length = Integer.parseInt(request.getParameter("length"));
    }
    catch (Exception ex) {
      length = LENGTH;
    }
    //取得随机整数
    Random ran = new Random();//System.currentTimeMillis()

    //取得一个随机数
    //String seed = (ran.nextInt() + "").substring(1, 1 + length);
    //生成缓冲图象
    BufferedImage image = new BufferedImage(width, height,
                                            BufferedImage.TYPE_INT_RGB);
    //取得绘图对象
    Graphics g = image.getGraphics();
    //填充背景色
   g.setColor(new Color(245, 245, 245));
    g.fillRect(0, 0, width, height);
    // 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
     g.setColor(getRandColor(160,200));
    for (int i=0;i<155;i++){
      int x = ran.nextInt(width);
      int y = ran.nextInt(height);
      int xl = ran.nextInt(12);
      int yl = ran.nextInt(12);
      g.drawLine(x,y,x+xl,y+yl);
    }
    //g.drawLine(0,0,99,99);
    //g.drawOval(12,12,34,34);
    //设置字体颜色
    g.setColor(Color.darkGray);
    //设置字体样式
    g.setFont(new Font("Times New Roman", Font.BOLD, 18));
    // 取随机产生的认证码(4位数字)
    String sRand="";
    for (int i=0;i<4;i++){
      String rand=String.valueOf(ran.nextInt(10));
      sRand+=rand;
      // 将认证码显示到图象中
      g.setColor(new Color(20+ran.nextInt(110),20+ran.nextInt(110),20+ran.nextInt(110)));//调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
      //写入指定文字
      g.drawString(rand,13*i+2,16);
    }
    //将取得的保存在session中
    request.getSession().setAttribute(sessionName, sRand);
    if(request.getSession().getAttribute(sessionName)==null){
    	request.getSession().setAttribute(sessionName, sRand);
    }
    g.dispose();
    JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
    out.flush();
    encoder.encode(image);
    out.close();
  }
 private Color getRandColor(int fc,int bc){//给定范围获得随机颜色
        Random random = new Random();
        if(fc>255) fc=255;
        if(bc>255) bc=255;
        int r=fc+random.nextInt(bc-fc);
        int g=fc+random.nextInt(bc-fc);
        int b=fc+random.nextInt(bc-fc);
        return new Color(r,g,b);
        }

  //Clean up resources
  public void destroy() {
  }
}
