package com.magic.crius.enums;

import java.util.HashMap;
import java.util.Map;
/**
 * 入款类型
 */
public enum FlowType {

	SHOU_DONG(1,"人工存款"),
	CHONG_FU(2,"负数额度归零"),
	RU_KUAN_CUN_WU(3,"存款优惠"),
	FU_SHU_HUI_CHONG(4,"取消提款"),
	KOU_CHU(5,"活动优惠"),
	FANG_QI(6,"返点优惠"),
	TI_YU(7,"体育投注余额"),
	ANOTHER(8,"其他出款"),
	HUI_KUAN_YOU_HUI(9,"汇款优惠");
	
	
	
	FlowType(int method, String methodName){
		this.method=method;
		this.methodName=methodName;
	}
	
   static Map<Integer,FlowType> payMethodMap=new HashMap<>();
	
	static{
		 for (FlowType payMethod : FlowType.values()) {
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




	public static FlowType getState(int method){
		return payMethodMap.get(method);
	}




	public static Map<Integer, FlowType> getPayMethodMap() {
		return payMethodMap;
	}
	
	
}
