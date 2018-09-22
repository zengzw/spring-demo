package com.test.tools;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Function {
	public static String getReallyTel(String tel) {

		if (tel != null) {

			tel = tel.trim();

			DESPlus des = new DESPlus("de2eW3feee");

			if (!isPhone(tel) && tel.length() > 20) {
				try {
					tel = des.decrypt(tel);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}
		return tel;
	}

	public static String getDesTel(String tel) {

		if (tel != null) {

			tel = tel.trim();

			DESPlus des = new DESPlus("de2eW3feee");

			if (isPhone(tel)) {
				try {
					tel = des.encrypt(tel);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return tel;
	}
	


    /**
     * 电话号码验证
     *
     * @param  str
     * @return 验证通过返回true
     */
    public static boolean isPhone(String str) {
        Pattern p1 = null,p2 = null;
        Matcher m = null;
        boolean b1 = false;
        boolean b2 = false;
        p1 = Pattern.compile("^[0][0-9]{2,3}-[0-9]{5,10}$");  // 验证带区号的

        p2 = Pattern.compile("^[1][0-9]{10}$"); // 验证手机号

        m = p1.matcher(str);
        b1 = m.matches();

        m = p2.matcher(str);
        b2 = m.matches();

        if(b1){
        	return true;
        }

        if(b2){
        	return true;
        }

        return false;

    }

}