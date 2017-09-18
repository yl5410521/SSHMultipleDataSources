package com.alien.alipay.config;

import org.apache.struts2.ServletActionContext;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
	
 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {
	
	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String partner = "2088911917846105";
	// 商户的私钥
	public static String private_key = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBANFzzxZQ215b23b+nb/ymv/LxS85r+BPFmxt41XWfLfHaGgUbtGWfjKJKio8xPZ63div5p2531QywAXiflU60+z+MpeSXgaySYzcLv1acelYEGlZCeW78pBDG0EjUqH878sMXkKfUfmu1fRFEQnSa/t0fQMFzyEWDU55ZceXa/dZAgMBAAECgYBHFlorxceeb+i9q08ZDZsjxCIplBeoNs9yf51PzdtIeKpr4Lt38O7l8f3YlgV3bl4LJDBOSf6NUigwFx4viSbRxDx1OlzMMtn647CsvyqeALv39H3GLC/bUSkLOMN6ul2DJ3fm9qfQw4ShrtIEtYZaEDcTwARm5qv0CHAoJZcSLQJBAPZcwmCceBsylVrvVj4qRqfrX0MM+91No4E2TK6w3IumBlq2sVfdop1ohaox6nID1QwKUkPsAMWX1NNmKnXEOMMCQQDZpWh3+UFYHuBrz9vHUH7j6d+89KF6H0kG2oL6CfhiW5/sEyagyq12zsxDDzyRvBd2ZUVl6kY/dh+D3CK16i2zAkEA3pUB/R2BGjmCXU09ox65BX+PatnxOImrvtMbRv191SHkPdMvuQ4CnKrRkOpWQjqaIq20k69HJz69R13X4Nyu1wJBAL8hrFGXuhO54dmsC1TI0vYxv4M/zcLiUI5NPLJ6KP+veQyncMfJ1XW9e9H0Qnu1E2Gs47nxd8lpYZm5q13YDlMCQB6LAmc/pmBCDSlhm5gXr3jD92G2EDOFWHej1Yx4tb6ftVWP0JyiX/rcHFGqN2KFOOZf/ALeBd/I1+QDK/ctRZ0=";
	
	// 支付宝的公钥，无需修改该值
	public static String ali_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

	//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	

	// 调试用，创建TXT日志文件夹路径
	public static String log_path = ServletActionContext.getServletContext().getRealPath("/").replaceAll("\\\\", "/") + "/log/";

	
	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";
	
	// 签名方式 不需修改
	public static String sign_type = "RSA";

}
