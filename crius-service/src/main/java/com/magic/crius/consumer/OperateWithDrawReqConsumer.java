package com.magic.crius.consumer;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.magic.crius.vo.OperateWithDrawReq;

@Service
public class OperateWithDrawReqConsumer {

	
	private ExecutorService userOutMoneyTaskPool = new ThreadPoolExecutor(10, 20, 3, TimeUnit.SECONDS,
			new ArrayBlockingQueue<Runnable>(10), new ThreadPoolExecutor.DiscardPolicy());
	private ExecutorService userOutMoneyHistoryTaskPool = Executors.newSingleThreadExecutor();
	
	
    
	public void init(){
		
		summaryCalculate();
	}



	private void summaryCalculate() {
		// TODO Auto-generated method stub
		final String currentHour="2017052011";
		for (int i = 0; i < 4; i++) {
			userOutMoneyTaskPool.execute(new Runnable() {
				@Override
				public void run() {
					userOutMoneyculate(currentHour);
				}
			});
		}
		
		userOutMoneyHistoryTaskPool.execute(new Runnable() {
			@Override
			public void run() {
				repairUserDetailHistoryTask(currentHour);
			}

			

		});
	}
	
	private void userOutMoneyculate(String currentHour) {
		// TODO Auto-generated method stub
		int countNum = 0;
		
	    List<OperateWithDrawReq> reqList=null;//redisService.getReqList(currentHour);//sssss_2017052011
		while (reqList != null  && countNum++ < 10) {
			saveSummaryDetail(reqList);
			//reqList=redisService.getReqList(currentHour);
		}
	}
	
	private void saveSummaryDetail(List<OperateWithDrawReq> reqList) {
		// TODO Auto-generated method stub
		
	}



	private void repairUserDetailHistoryTask(String currentHour) {
		// TODO Auto-generated method stub
		
	}
}
