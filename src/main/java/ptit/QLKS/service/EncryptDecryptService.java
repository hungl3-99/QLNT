package ptit.QLKS.service;

public interface EncryptDecryptService {
    public String encrypt(String Data) throws Exception;

    public String decrypt(String encryptedData) throws Exception;
}
