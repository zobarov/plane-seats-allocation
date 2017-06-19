/**
 * 
 */
package com.gav.j17.airplaine.srv;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author alex
 *
 */
@Component
public class SeatsScheduleEngine implements ApplicationListener<ApplicationReadyEvent> {
	@Override
	public void onApplicationEvent(ApplicationReadyEvent readyEvent) {
	
	}

}
