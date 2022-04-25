package ptit.QLKS.service.impl;

import org.springframework.stereotype.Service;
import ptit.QLKS.service.EncryptDecryptService;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Service
public class EncryptDecryptServiceImpl implements EncryptDecryptService {
    private SecretKeySpec skeySpec;
    private static final String ENCRYPT_DECRYPT_CANDIDATE_LINK_ALGO = "AES";
    private static final String ENCRYPT_DECRYPT_CANDIDATE_LINK_SECRET_KEY = "Thats my Skil Se";
    public EncryptDecryptServiceImpl() {

        super();
        skeySpec = new SecretKeySpec(
                ENCRYPT_DECRYPT_CANDIDATE_LINK_SECRET_KEY.getBytes(),
                "AES");

    }

    @Override
    public String encrypt(final String data) throws Exception {

        Cipher c = Cipher.getInstance(ENCRYPT_DECRYPT_CANDIDATE_LINK_ALGO);
        c.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encVal = c.doFinal(data.getBytes());
        String encryptedValue = Base64.getEncoder().encodeToString(encVal);
        return encryptedValue;

    }

    @Override
    public String decrypt(final String encryptedData) throws Exception {

        Cipher c = Cipher.getInstance(ENCRYPT_DECRYPT_CANDIDATE_LINK_ALGO);
        c.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] decordedValue = Base64.getDecoder().decode(encryptedData);
        byte[] decValue = c.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;

    }
}
