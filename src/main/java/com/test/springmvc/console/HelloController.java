package com.test.springmvc.console;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.jms.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.storm.command.list;
import org.apache.storm.shade.hiccup.form_helpers__init;
import org.hibernate.validator.internal.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.test.base.ResponseError;
import com.test.base.ResponseMessage;
import com.test.base.constants.BaseController;
import com.test.base.exception.BusinessRuntimeException;
import com.test.springmvc.group.First;
import com.test.springmvc.group.Second;
import com.test.springmvc.model.User;
import com.test.springmvc.model.UserMulti;
import com.test.springmvc.service.IUserService;

@Controller
@Scope("singleton")
public class HelloController extends BaseController{

    @Autowired
    IUserService userService;

    ThreadLocal<String> threadLocal = new ThreadLocal<String>();

    private static int count = 1;

    @RequestMapping(value="/welcome",method = RequestMethod.GET)
    public String printWelcome(ModelMap model) throws InterruptedException {
        String name = Thread.currentThread().getName();
        if(name.equals("qtp1196730970-58")){
            System.out.println("-----休息5秒");

          
        }
        HttpSession session = getRequest().getSession();
        if(session.getAttribute("kktalk") == null){
            System.out.println("-----set session ");
            session.setAttribute("kktalk", "kktalk");
        }else{
            System.err.println("session get:"+session.getAttribute("kktalk"));
        }

        if(threadLocal.get() == null){
            String value = name + RandomStringUtils.randomAlphabetic(5);
            System.out.println("------"+name +" is null,setting value!"+value);
            threadLocal.set(value);
        }else{
            System.out.println("-------"+name+" get value:"+threadLocal.get());
        }

        model.addAttribute("message", "Spring 3 MVC Hello World:"+Thread.currentThread().getId());
        threadLocal.remove();
        System.out.println("||||||||||||"+name+" :"+threadLocal.get());

        return "hello";

    }


    @RequestMapping(value="/test1",method = RequestMethod.GET)
    public String test1(ModelMap model) throws InterruptedException {
        String name = Thread.currentThread().getName();
        List<String> list = new ArrayList<String>();
        if(20 % count == 2){
            list.add("loop");
        }else if(20 % count == 0){
            list.add("test");
        }     

        list.add(name);
        ServiceProviderManager.add(list);

        count++;
        System.out.println("count: "+ count + "   " + JSON.toJSONString(list));
        RechargeManager.reacharge(name);

        model.addAttribute("message", "Spring 3 MVC Hello World:"+Thread.currentThread().getId());

        System.out.println("-------------------------------------------------");
        return "hello";

    }
    @RequestMapping(value="/test",method = RequestMethod.GET)
    public String test(ModelMap model) {
        String name = Thread.currentThread().getName();
        for(int i = 0; i<3; i++){
            count ++;
        }
        System.out.println(name +" count:"+count);
        model.addAttribute("message", "Spring 3 MVC Hello World:"+Thread.currentThread().getId());
        threadLocal.remove();
        return "hello";

    }

    private static String token = "phoenixtea";



    @RequestMapping(value="/successData",method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessage reponseData(ModelMap model) {

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("v1","value");
        result.put("v2","value");

        return successDataResponse("result", result);
    }

    @RequestMapping(value="/error",method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessage error(ModelMap model) {
        return errorResponse("user.id");
    }

    @RequestMapping(value="/success",method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessage success(ModelMap model) {
        return successResponse("user.id");
    }

    @RequestMapping(value="/setError",method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessage setError(ModelMap model) {
        ResponseMessage responseMessage = new ResponseMessage();

        ResponseError error = new ResponseError("user.age");
        error.setErrorMessage("错误的信息...");
        responseMessage.addError(error);

        ResponseError error1 = new ResponseError("user.name.empty");
        error1.setErrorMessage("错误的用户名信息...");
        responseMessage.addError(error1);

        return responseMessage;
    }

    @RequestMapping(value="/addFault",method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessage addFault(ModelMap model) {
        ResponseMessage responseMessage = new ResponseMessage(getMessageSourceService());
        responseMessage.addErrorMessage("测试fault。。。。");
        responseMessage.setFailure("user.age");

        return responseMessage;
    }


    @RequestMapping(value="/ex",method = RequestMethod.GET)
    public String exception(ModelMap model,HttpServletRequest req,HttpServletResponse res) {
        model.addAttribute("message", "Spring 3 MVC Hello World");
        /*   if(true){
            try{
            int i = 1/0;
            System.err.println(i);
            }catch(Exception e){
              ResponseError error = getResponseError(req, res, e);
                System.out.println();
            }
        }*/
        if(true){
            throw new BusinessRuntimeException("user.id","default message");
        }
        //        userService.getUserList(null);
        return "hello";

    }

    /**
     *  默认是根据Url去匹配view。
     *  如果有reutrn ModelView 或者 String 的话，是根据返回具体的视图对象去查找view
     *  
     *  ModelAttribute 是将返回对象放到user里头。
     */
    @ModelAttribute("user")
    @RequestMapping("/user")
    public User getUser(){

        User user = new User();
        user.setId(222);
        user.setName("Name");
        user.setNickName("nickName");

        return user;
    }


    /**
     *  生成的模型对象属性名为“简单类名（首字母小写）”+“List”
     *  stringList 页面访问通过stringList
     * @return
     */
    @RequestMapping("/userList") 
    @ModelAttribute
    public  List<String> test(){
        List<String> list = new ArrayList<String>();
        list.add("kktalk");
        list.add("jpush");
        return list;
    }

    @RequestMapping("/validate")
    public String validate(@Valid User user,BindingResult errors){

        if(errors.hasErrors()){
            System.out.println("错误啦.....");
            System.out.println(errors);
        }
        return "validate";
    }

    /**
     * 分组验证
     * @Validated(First.class)
     *  只验证First.class 组的字段
     *  验证两个的话，再加上其他组（Second.class）
     * @return
     */
    @RequestMapping("/validate-group")
    public String validate(@Validated({First.class,Second.class})UserMulti userMulti,BindingResult result){

        if(result.hasErrors()){
            System.out.println("-----has errors"+result.getErrorCount());
        }
        return "validate-group";

    }
    public static void main(String[] args) {
        System.out.println(0/1);
    }
}