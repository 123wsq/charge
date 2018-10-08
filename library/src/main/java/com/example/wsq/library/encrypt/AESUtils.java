package com.example.wsq.library.encrypt;

import com.example.wsq.library.utils.LogUtils;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AESUtils {

	/**
	 * 随机生成密钥的数据源
	 */
//	 private static final String KEY_SOURCE = "qwertyuiopasdfghjklzxcvbnm1234567890";
	private static final String KEY_SOURCE = "SmoA2436.6342aOMs";

	/**
	 * 生成指定类型的AESkey的长度
	 *
	 * @param type
	 *            AES类型
	 * @return key
	 */
	public static String createAESKey(AESType type) {
		int length = type.value / 8;
		StringBuffer keySB = new StringBuffer();
		SecureRandom random = new SecureRandom();
		int sourceL = KEY_SOURCE.length();
		for (int i = 0; i < length; i++) {
			int index = random.nextInt(sourceL);
			keySB.append(KEY_SOURCE.charAt(index));
		}
		return keySB.toString();
	}

	public static byte[] encryptAES2Base64(String plaintext, String key, AESType type) {
		return base64Encode(encrypt(plaintext, key, type.value));
	}

	public static byte[] decryptBase64DES(String ciphertext, String key, AESType type) {
		byte[] result = parseHexStr2Byte(ciphertext);
		return base64Decode(decrypt(result, key, type.value));
	}
	/**
	 * AES加密，返回秘文
	 *
	 * @param plaintext
	 *            明文
	 * @param key
	 *            加密key
	 * @param type
	 *            加密类型
	 * @return 秘文
	 */
	public static String encryptAES(String plaintext, String key, AESType type) {
		byte[] result = encrypt(plaintext, key, type.value);
		LogUtils.d("======"+ Arrays.toString(result));
		return parseByte2HexStr(result);
	}
	/**
	 * AES解密
	 *
	 * @param ciphertext
	 *            秘文
	 * @param key
	 *            加密key
	 * @param type
	 *            加密类型
	 * @return 明文
	 */
	public static String decryptAES(String ciphertext, String key, AESType type) {
		byte[] result = parseHexStr2Byte(ciphertext);
		byte[] plainByte = decrypt(result, key, type.value);
		try {
			return new String(plainByte, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}
	/**
	 * 加密
	 *
	 * @param content
	 *            需要加密的内容
	 * @param password
	 *            加密密码
	 * @return
	 */
	public static byte[] encrypt(String content, String password, int aesLength) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(aesLength, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			// 创建密码器
			Cipher cipher = Cipher.getInstance("AES");
			byte[] byteContent = content.getBytes("utf-8");
			// 初始化
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] result = cipher.doFinal(byteContent);
			return result; // 加密
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 解密
	 *
	 * @param content
	 *            待解密内容
	 * @param password
	 *            解密密钥
	 * @param aesLength
	 *            AES加密类型，128、192、256
	 * @return
	 */
	public static byte[] decrypt(byte[] content, String password, int aesLength) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(aesLength, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(content);
			return result; // 加密
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 将二进制转换成16进制
	 *
	 * @param buf
	 * @return
	 */
	private static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}
	/**
	 * 将16进制转换为二进制
	 *
	 * @param hexStr
	 * @return
	 */
	private static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
					16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	private static byte[] base64Encode(final byte[] input) {
		return android.util.Base64.encode(input, android.util.Base64.NO_WRAP);
	}
	private static byte[] base64Decode(final byte[] input) {
		return android.util.Base64.decode(input, android.util.Base64.NO_WRAP);
	}
	// 测试
	public static void main(String[] args) {
		String key = createAESKey(AESType.AES_192);
		System.out.println("密钥：" + key);
		String plaintext = "app_type=android&device=86938103120306&phone=13524587170";
		String ciphertext = encryptAES(plaintext, key, AESType.AES_192);
		System.out.println("秘文：" + ciphertext);
		plaintext = decryptAES(ciphertext, key, AESType.AES_192);
		System.out.println("明文：" + plaintext);
	}
}
