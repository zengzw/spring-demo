
/*
 * @Project Name: springmvcdemo
 * @File Name: Test.java
 * @Package Name: 
 * @Date: 2018年5月10日下午6:06:48
 * @Creator: zengzw-1220
 * @line------------------------------
 * @修改人: 
 * @修改时间: 
 * @修改内容: 
 */
/**
 * TODO
 * @author zengzw-1220
 * @date 2018年5月10日下午6:06:48
 * @see
 */
public class Test {
	
	public void say(){
		synchronized (this) {
			System.out.println("---");
		}
	}
}
