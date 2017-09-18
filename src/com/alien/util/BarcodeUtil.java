package com.alien.util;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import org.jbarcode.JBarcode;
import org.jbarcode.JBarcodeFactory;
import org.jbarcode.encode.Code39Encoder;
import org.jbarcode.paint.BaseLineTextPainter;
import org.jbarcode.paint.TextPainter;
import org.jbarcode.paint.WideRatioCodedPainter;
import org.jbarcode.util.ImageUtil;
/** 
 * 2015-2-9 
 * @author yaolin
 * 条形码生成工具
 */  
public class BarcodeUtil {  

    private static final int BARCODE_HEIGHT = 30;
    private static final int BARCODE_DPI = ImageUtil.DEFAULT_DPI;
    private static final String FONT_FAMILY = "consola";
    private static final int FONT_SIZE = 32;
 
    private static JBarcode jbc = null;
 
    static JBarcode getJBarcode() {
        if (jbc == null) {
            jbc = JBarcodeFactory.getInstance().createCode128();
            jbc.setTextPainter(CustomTextPainter.getInstance());
            jbc.setBarHeight(BARCODE_HEIGHT);
            //jbc.setEncoder(Code39Encoder.getInstance());  
            jbc.setPainter(WideRatioCodedPainter.getInstance());  
            //jbc.setTextPainter(BaseLineTextPainter.getInstance());  
//  	      jbc.setBarHeight(40);//设置条码高度
//  	      jbc.setWideRatio(5);//设置条吗的粗细
            jbc.setShowCheckDigit(false); 
            
        }
        return jbc;
    }
 
  
    
    public static void createBarcode(String message,String path) {
    	try  
	    {
	    	 // f.createNewFile();
    		System.out.println(path);
    		File f=new File(path);
	    	FileOutputStream localFileOutputStream = new FileOutputStream(f);  
	    	 createBarcode(message, localFileOutputStream);
	      localFileOutputStream.close();  
	    }  
	    catch (Exception localException)  
	    {  
	      localException.printStackTrace();  
	    }  
    }
 
    
    public static void createBarcode(String message, OutputStream os) {
        try {
        	
            BufferedImage image = getJBarcode().createBarcode(message);
            ImageUtil.encodeAndWrite(image, ImageUtil.PNG, os, BARCODE_DPI, BARCODE_DPI);
            os.flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
 
    /**
     * 自定义的 TextPainter， 允许定义字体，大小等等。
     */
    static class CustomTextPainter implements TextPainter {
        public static CustomTextPainter instance = new CustomTextPainter();
 
        public static CustomTextPainter getInstance() {
            return instance;
        }
 
        @Override
        public void paintText(BufferedImage barCodeImage, String text, int width) {
            Graphics g2d = barCodeImage.getGraphics();
 
            Font font = new Font(FONT_FAMILY, Font.PLAIN, FONT_SIZE * width);
            g2d.setFont(font);
            FontMetrics fm = g2d.getFontMetrics();
            int height = fm.getHeight();
            int center = (barCodeImage.getWidth() - fm.stringWidth(text)) / 2;
 
            g2d.setColor(Color.WHITE);
            g2d.fillRect(0, 0, barCodeImage.getWidth(), barCodeImage.getHeight() * 1 / 20);
            g2d.fillRect(0, barCodeImage.getHeight() - (height * 9 / 10), barCodeImage.getWidth(), (height * 9 / 10));
            g2d.setColor(Color.BLACK);
            g2d.drawString(text, center, barCodeImage.getHeight() - (height / 10) - 2);
        }
    }
 
//    public static void main(String[] args) {
//    	OneBarcodeUtil.createBarcode("CGDD-150207-00001", new File("F:\\CGDD-150207-00001.png"));
//    }
//	
  
}  
