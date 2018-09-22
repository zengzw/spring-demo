public class Test2 {

	public static void main(String[] args) throws InterruptedException {

	      System.out.println( 2 ^ 32);
	      
	    
	      Test2 test = new Test2();
	      test.test();
	}
    public  synchronized void test() throws InterruptedException{
    	this.wait();
    	

    }
}
