package kr.co.fishing.bko.common.utils;

import java.util.Locale;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

@Component
public class ContextUtil implements ApplicationContextAware {
	
	private ApplicationContext context = null;
	
	@Autowired
	private MessageSourceAccessor message;
	
	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.context = context;
	}

	/** 메시지 취득 */
	public String getMessage(String name){
		return message.getMessage(name, Locale.getDefault());
	}
	
	/** 메시지 취득 */
	public String getMessage(String name, String[] params){
		return message.getMessage(name, params, Locale.getDefault());
	}
	
	public Object getBean(String name){
        return context.getBean(name);
    }
}
