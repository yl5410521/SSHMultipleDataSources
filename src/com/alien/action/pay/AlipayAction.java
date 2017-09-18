package com.alien.action.pay;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.hibernate.criterion.Criterion;

import com.alien.action.BaseAction;
import com.alien.alipay.util.AlipayNotify;

@ParentPackage("home")
public class AlipayAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//交易状态
	private String trade_status;
	//商户订单
	private String out_trade_no;
	//交易订单
	private String trade_no;

	@SuppressWarnings({ "rawtypes", "unused" })
	public String pay() throws IOException {
		PrintWriter out =getResponse().getWriter();	
		// 获取支付宝POST过来反馈信息
				Map<String, String> params = new HashMap<String, String>();
				Map requestParams = getRequest().getParameterMap();
				for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
					String name = (String) iter.next();
					String[] values = (String[]) requestParams.get(name);
					String valueStr = "";
					for (int i = 0; i < values.length; i++) {
						valueStr = (i == values.length - 1) ? valueStr + values[i]
								: valueStr + values[i] + ",";
					}
					// 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
					// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
					params.put(name, valueStr);
				}
		// 交易状态
		trade_status = null;
		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		//商户订单号

		out_trade_no = new String(getRequest().getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

		//支付宝交易号

		trade_no = new String(getRequest().getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

		//交易状态
		trade_status = new String(getRequest().getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

		// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

		if (AlipayNotify.verify(params)) {// 验证成功
			// ////////////////////////////////////////////////////////////////////////////////////////
			// 请在这里加上商户的业务逻辑程序代码
			// ——请根据您的业务逻辑来编写程序（以下代码仅作参考）——

			if (trade_status.equals("TRADE_FINISHED")) {
				// 判断该笔订单是否在商户网站中已经做过处理
				// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				// 如果有做过处理，不执行商户的业务程序

				// 注意：
				// 该种交易状态只在两种情况下出现
				// 1、开通了普通即时到账，买家付款成功后。
				// 2、开通了高级即时到账，从该笔交易成功时间算起，过了签约时的可退款时限（如：三个月以内可退款、一年以内可退款等）后。
			} else if (trade_status.equals("TRADE_SUCCESS")) {
				// 判断该笔订单是否在商户网站中已经做过处理
				// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				// 如果有做过处理，不执行商户的业务程序

				// 注意：
				// 该种交易状态只在一种情况下出现——开通了高级即时到账，买家付款成功后。
			}
			 out.print("success"); 
			// ——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
			System.out.println("支付成功+success"); // 请不要修改或删除
			String shoporder="SP";
			String repairorder="WX";
			/*if(containsAny(out_trade_no,shoporder)==true){
				orderCode=orderCodeService.findorderdesc(out_trade_no);
				boolean flag=orderCodeService.updateorderstatus(orderCode.getId());
				if(flag==true){
					System.out.println("更新订单成功");
					tradingRecords=new TradingRecords();
					tradingRecords.setId(Math.random()+"");
					tradingRecords.setCreateDate(new Date());
					tradingRecords.setModifyDate(new Date());
					tradingRecords.setTrading(trade_no);
					tradingRecords.getOrderCode().setId(orderCode.getId());
					tradingRecords.setStatus("交易成功");
					tradingRecordsService.save(tradingRecords);
					parmsList.add(Restrictions.eq("orderCode.id",orderCode.getId()));
					pager = shoppingCartService.findPager(ShoppingCart.class, pager, null,parms(parmsList));
					List<ShoppingCart> shoppingCarts=(List<ShoppingCart>) pager.getResult();
					JSONArray json = new JSONArray();
					float sum = 0;
					JSONObject jos= new JSONObject();
					for(ShoppingCart shop:shoppingCarts){
						JSONObject jo = new JSONObject();
						jo.put("name", shop.getCommodity().getName());
						jo.put("item", shop.getCommodity().getItemdesc());
						sum+=shop.getNumber()*shop.getCommodity().getCurrentprice();
						json.put(jo);
					}
					jos.put("sumprice", String.valueOf(sum));
					jos.put("code", orderCode.getCode());
					jos.put("status", "success");
					try {
						return JsonGo(json.toString());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
					return ajaxJson(Status.error, "数据加载失败");
					
				}else{
					System.out.println("更新订单失败");
				}
				
			}else if(containsAny(out_trade_no,repairorder)==true){
				orderCode=orderCodeService.findorderdesc(out_trade_no);
				boolean flag=orderCodeService.updaterepairstatus(orderCode.getId());
				if(flag==true){
					System.out.println("更新订单成功");
					tradingRecords=new TradingRecords();
					tradingRecords.setId(Math.random()+"");
					tradingRecords.setCreateDate(new Date());
					tradingRecords.setModifyDate(new Date());
					tradingRecords.setTrading(trade_no);
					tradingRecords.getOrderCode().setId(orderCode.getId());
					tradingRecords.setStatus("交易成功");
					tradingRecordsService.save(tradingRecords);
					
				}else{
					System.out.println("更新订单失败");
				}
			}*/
			
			
			// ////////////////////////////////////////////////////////////////////////////////////////
		} else {// 验证失败
			System.out.println("fail");
			out.print("fail"); 
		}

		return null;

	}

	public String getTrade_status() {
		return trade_status;
	}

	public void setTrade_status(String trade_status) {
		this.trade_status = trade_status;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getTrade_no() {
		return trade_no;
	}

	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}

	
	
	
	

}
