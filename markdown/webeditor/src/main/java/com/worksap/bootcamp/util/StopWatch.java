package com.worksap.bootcamp.util;

import org.springframework.context.ApplicationListener;
import org.springframework.web.context.support.ServletRequestHandledEvent;


public class StopWatch implements ApplicationListener<ServletRequestHandledEvent> {
	@Override
	public void onApplicationEvent(ServletRequestHandledEvent event) {
		System.out.format(
				"Your request[%s] take %dmsec...%n",
				event.getRequestUrl(),
				event.getProcessingTimeMillis());
	}
}
