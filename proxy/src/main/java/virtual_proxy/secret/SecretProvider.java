package virtual_proxy.secret;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class SecretProvider {
  public static String decryptAes(String str, String key) throws Exception {
    System.out.println("decrypting...");

    Cipher cipher = Cipher.getInstance("AES");
    SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
    cipher.init(Cipher.DECRYPT_MODE, secretKey);
    byte[] decPassword = cipher.doFinal(Base64.getDecoder().decode(str));
    String result = new String(decPassword, "UTF-8");
    Thread.sleep(3000);
    System.out.println("decrypt complete!!!");
    return result;
  }

  public static String encryptAes(String str, String key) throws Exception {
    Cipher cipher = Cipher.getInstance("AES");
    SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
    cipher.init(Cipher.ENCRYPT_MODE, secretKey);
    byte[] encPassword = cipher.doFinal(str.getBytes("UTF-8"));
    String result = Base64.getEncoder().encodeToString(encPassword);
    return result;
  }
}
