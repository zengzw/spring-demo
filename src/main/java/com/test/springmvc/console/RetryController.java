/*
 * @Project Name: springmvcdemo
 * @File Name: RetryController.java
 * @Package Name: com.test.springmvc.console
 * @Date: 2017年12月14日上午11:08:34
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */

package com.test.springmvc.console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.learn.springretry.RetryRemoteService;

/**
 * TODO
 * @author zengzw-1220
 * @date 2017年12月14日上午11:08:34
 * @see
 */
@RestController(value="/retry")
public class RetryController {
	
	@Autowired
	RetryRemoteService retryService;
	
	@RequestMapping(method=RequestMethod.GET)
    public Object view(){
        
		try {
			retryService.call();
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        return "success";
        
    }
}
