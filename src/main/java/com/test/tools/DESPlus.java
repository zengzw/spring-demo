package com.test.tools;

import com.sun.crypto.provider.SunJCE;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.Security;

public class DESPlus
{
 private static String strDefaultKey = "national";

 private Cipher encryptCipher = null;

 private Cipher decryptCipher = null;

  public static String byteArr2HexStr(byte[] arrB)
  {
   int iLen = arrB.length;

   StringBuffer sb = new StringBuffer(iLen * 2);
   for (int i = 0; i < iLen; i++) {
     int intTmp = arrB[i];

     while (intTmp < 0) {
       intTmp += 256;
      }

     if (intTmp < 16) {
       sb.append("0");
      }
     sb.append(Integer.toString(intTmp, 16));
    }
   return sb.toString();
  }

  public static byte[] hexStr2ByteArr(String strIn) {
		byte[] arrB = strIn.getBytes();
		int iLen = arrB.length;
		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i = i + 2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}

  public DESPlus()
    throws Exception
  {
   this(strDefaultKey);
  }

  public DESPlus(String strKey)
  {
   Security.addProvider(new SunJCE());
    try
    {
     Key key = getKey(strKey.getBytes());
     this.encryptCipher = Cipher.getInstance("DES");
     this.encryptCipher.init(1, key);
     this.decryptCipher = Cipher.getInstance("DES");
     this.decryptCipher.init(2, key);
    } catch (Exception e) {
     e.printStackTrace();
    }
  }

  public byte[] encrypt(byte[] arrB)
  {
     byte[] s = (byte[])null;
    try {
       s = this.encryptCipher.doFinal(arrB);
    } catch (IllegalBlockSizeException e) {
       e.printStackTrace();
    } catch (BadPaddingException e) {
       e.printStackTrace();
    }
     return s;
  }

  public String encrypt(String strIn)
  {
     return byteArr2HexStr(encrypt(strIn.getBytes()));
  }

  public byte[] decrypt(byte[] arrB)
  {
     byte[] s = (byte[])null;
    try {
       s = this.decryptCipher.doFinal(arrB);
    } catch (IllegalBlockSizeException e) {
       e.printStackTrace();
    } catch (BadPaddingException e) {
       e.printStackTrace();
    }
     return s;
  }

  public String decrypt(String strIn)
  {
     return new String(decrypt(hexStr2ByteArr(strIn)));
  }

  private Key getKey(byte[] arrBTmp)
  {
     byte[] arrB = new byte[8];

     for (int i = 0; (i < arrBTmp.length) && (i < arrB.length); i++) {
       arrB[i] = arrBTmp[i];
    }

     Key key = new SecretKeySpec(arrB, "DES");
     return key;
  }
}