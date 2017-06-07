package com.magic.crius.enums;

import java.util.HashMap;
import java.util.Map;

public enum PayMethod {

	WANG_YING(1,"网银存款"),
	ZHI_FU_BAO(2,"支付宝电子支付"),
	WEI_XIN(3,"微信电子支付"),
	GUI_TAI(4,"柜员机现金存款"),
	GUI_YUAN(5,"柜员机转账"),
	BANK_GUI_TAI(6,"银行柜台存款"),
	ANOTHER(7,"其他支付");
	
	PayMethod(int method, String methodName){
		this.method=method;
		this.methodName=methodName;
	}
	
   static Map<Integer,PayMethod> payMethodMap=new HashMap<>();
	
	static{
		 for (PayMethod payMethod : PayMethod.values()) {
			 payMethodMap.put(payMethod.getMethod(), payMethod);
	        }
	}

	private int  method;
	private String methodName;
	
	
   
	
	public int getMethod() {
		return method;
	}




	public void setMethod(int method) {
		this.method = method;
	}




	public String getMethodName() {
		return methodName;
	}




	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}




	public static PayMethod getState(int method){
		return payMethodMap.get(method);
	}




	public static Map<Integer, PayMethod> getPayMethodMap() {
		return payMethodMap;
	}
	
	
}
