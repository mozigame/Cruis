package com.magic.crius.enums;

import java.util.HashMap;
import java.util.Map;
/**
 * 出款类型
 */
public enum OutType {

	SHOU_DONG(1,"手动申请出款"),
	CHONG_FU(2,"重复出款"),
	RU_KUAN_CUN_WU(3,"公司入款存误"),
	FU_SHU_HUI_CHONG(4,"公司负数回冲"),
	KOU_CHU(5,"扣除非法下注派彩"),
	FANG_QI(6,"放弃存款优惠"),
	TI_YU(7,"体育投注余额"),
	ANOTHER(8,"其他出款"),
	;
	
	
	
	OutType(int method, String methodName){
		this.method=method;
		this.methodName=methodName;
	}
	
   static Map<Integer,OutType> payMethodMap=new HashMap<>();
	
	static{
		 for (OutType payMethod : OutType.values()) {
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




	public static OutType getState(int method){
		return payMethodMap.get(method);
	}




	public static Map<Integer, OutType> getPayMethodMap() {
		return payMethodMap;
	}
	
	
}
