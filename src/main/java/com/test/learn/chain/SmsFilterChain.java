package com.test.learn.chain;

import com.test.springmvc.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zengzw
 * @date 2019-04-25 17:10:37
 */
public class SmsFilterChain {

    private int index = 0;

    private List<ISmsFilter> list = new ArrayList<>();

    public void addFilter(ISmsFilter filter){
        this.list.add(filter);
    }


    public List<String> lstError = new ArrayList<>();


    public boolean doFilter(User user){
        if(index < list.size()){
            boolean result =   list.get(index++).filter(user,this);
            System.out.println("---result:"+result);
            return result;
        }

        System.out.println("----end---");
        return false;
    }


    public static void main(String[] args) {
        ISmsFilter smA = new SmsA();
        ISmsFilter smB = new SmsB();
        ISmsFilter smC = new SmsC();

        SmsFilterChain chain = new SmsFilterChain();
        chain.addFilter(smA);
        chain.addFilter(smB);
        chain.addFilter(smC);

        User u = new User();
        u.setName("kktalk");
        System.out.println(chain.doFilter(u));
        System.out.println(chain.lstError);
    }
}
