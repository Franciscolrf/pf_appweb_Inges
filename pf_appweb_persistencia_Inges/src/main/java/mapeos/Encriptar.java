package mapeos;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class Encriptar {

    private static final String ALGORITHM = "AES";
    // Mantén esta clave constante y asegúrate de que tenga exactamente 16 caracteres para AES
    private static final String KEY = "1234567890123456";

    public static String encriptar(String password) throws Exception {
        SecretKey key = getKey();
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrypted = cipher.doFinal(password.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static String desencriptar(String encryptedPassword) throws Exception {
        SecretKey key = getKey();
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedValue = Base64.getDecoder().decode(encryptedPassword);
        byte[] decrypted = cipher.doFinal(decodedValue);
        return new String(decrypted);
    }

    // Usamos la misma clave fija para encriptar y desencriptar
    private static SecretKey getKey() {
        return new SecretKeySpec(KEY.getBytes(), ALGORITHM);
    }
}
