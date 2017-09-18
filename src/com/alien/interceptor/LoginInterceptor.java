package com.alien.interceptor;

import java.util.Map;

import com.alien.entity.Admin;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor
{
 
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext context = invocation.getInvocationContext();
		Map<String, Object> session = context.getSession();
		Admin admin = (Admin) session.get("loginAdmin");
		ActionProxy  actionProxy=context.getActionInvocation().getProxy();
		//剔除不需要登陆的方法
		if(actionProxy.getActionName().equals("admin")){
			if(actionProxy.getMethod().equals("login")||actionProxy.getMethod().equals("out")){
				return invocation.invoke();
			}
		}
		if(admin==null){
			return "admin_loginerror";
		}else{
			
		}
		return invocation.invoke();

	}
}
