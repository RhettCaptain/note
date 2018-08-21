package com.worksap.stm2017.controller;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.worksap.stm2017.dao.DaoFactory;
import com.worksap.stm2017.dao.RosterDao;

@Controller
public class WeekListener implements Runnable{
	private Thread t;
	private int lastDay;
	private DaoFactory factory;
	
	@Autowired
	public WeekListener(DaoFactory factory){
		t = new Thread(this);
		Date date = new Date();
		lastDay = date.getDay();
		this.factory = factory;
		t.start();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			Date date = new Date();
			if(date.getDay() == 1 && lastDay == 0){
				RosterDao rosterDao = factory.getRosterDao();
				rosterDao.shiftRoster();
			}
			lastDay = date.getDay();
			try {
				TimeUnit.HOURS.sleep(8);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
