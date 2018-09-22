
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
	
	private void outMethod(){
		int param = 1;
		
		class innerClass{
			public void innerMethod(){
				System.out.println("innerMethod...."+param);
			}
		}
	}
	
	public static void main(String[] args) {
		System.out.println(trycatchfinally());
		
		Test test = new Test();
		test.outMethod();
	
	}
	
	public static int trycatchfinally(){
		
		try{
			return 1;
		}catch(Exception exception){
			exception.printStackTrace();
			return 2;
		}finally {
			System.out.println("finally");
			return 3;
		}
	}
	
	public void testFinal(){
		int intT = 1;
		final int intFinal = 1;
		final String finalA = "abc";

		String result = test1();
		switch (result){
            case  "a":

                default:

        }

	}

	public String test1(){
	    return "result";
    }
}