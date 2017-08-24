package work.util;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

/** ���� ��� Ŭ���� */ 
public class Utility {
	/** ���糯¥ ��ȸ */
	public static String getCurrentDate() {
		return getCurrentDate("yyyy.MM.dd");
	}
	
	public static String getCurrentDate(String pattern) {
		return getCurrentDate(pattern, Locale.KOREA);
	}

	public static String getCurrentDate(String pattern, Locale locale) {
		return new SimpleDateFormat(pattern, locale).format(new Date());
	}
	
	/** ���ȹ��� �߱� : ���� */
	public static String getSecureCodeNumber() {
		return getSecureCodeNumber(4);
	}
	
	public static String getSecureCodeNumber(int length) {
		StringBuilder secureCode = new StringBuilder();
		Random random = new Random((long)(System.nanoTime() * Math.random()));
		for (int index=0; index < length; index++) {
			secureCode.append(random.nextInt(10));
		}
		return secureCode.toString();
	}
	
	/** ���ȹ��� �߱� : ������ - �⺻4�ڸ� �����빮�� */
	public static String getSecureCodeString() {
		return getSecureCodeString(4);
	}

	/** ���ȹ��� �߱� : ������ - �������� �����빮�� */
	public static String getSecureCodeString(int length) {
		return getSecureCodeString(length, true);
//		StringBuilder secureCode = new StringBuilder();
//		Random random = new Random((long)(System.nanoTime() * Math.random()));
//		for (int index=0; index < length; index++) {
//			secureCode.append((char)(random.nextInt(26) + 65));
//		}
//		return secureCode.toString();
	}

	/** ���ȹ��� �߱� : ������ - �������� ���� ��ҹ��� ���� */
	public static String getSecureCodeString(int length, boolean isUpperCase) {
		int asciiCode = 65;
		if (!isUpperCase) {
			asciiCode = 97;
		}
		
		StringBuilder secureCode = new StringBuilder();
		Random random = new Random((long)(System.nanoTime() * Math.random()));
		for (int index=0; index < length; index++) {
			secureCode.append((char)(random.nextInt(26) + asciiCode));
		}
		return secureCode.toString();
	}
	
	/** ���ȹ��� ��ȯ : �⺻ 2�ڸ� ���� ��ȯ */
	public static String convertSecureString(String data) {
		return convertSecureString(data, 2);
	}
	
	/** ���ȹ��� ��ȯ : �����ڸ� ���� ��ȯ */
	public static String convertSecureString(String data, int length) {
		StringBuilder convertSecure = new StringBuilder();
		convertSecure.append(data.substring(0, length));
		for (int index=length; index < data.length(); index++) {
			convertSecure.append("*");
		}
		return convertSecure.toString();
	}

	/** ���ȹ��� ��ȯ : �����ڸ� ���� �������� ��ȯ */
	public static String convertSecureString(String data, int startPosition, int length) {
		StringBuilder convertSecure = new StringBuilder();
		convertSecure.append(data.substring(0, startPosition-1));
		for (int index=0; index < length; index++) {
			convertSecure.append("*");
		}
		convertSecure.append(data.substring(startPosition+length-1));
		return convertSecure.toString();
	}
	
	/** ����ó(�޴���) ���ȹ��� ��ȯ : �տ��� ��ȯ, ���ڸ� �̺�ȯ */
	public static String convertSecureStringLast(String data, int length) {
		StringBuilder convertSecure = new StringBuilder();
		int endPosition = data.length() - length;
		for (int index=0; index < endPosition; index++) {
			convertSecure.append("*");
		}
		convertSecure.append(data.substring(endPosition));
		return convertSecure.toString();
	}
	
	/** ������ ���� õ���� �ĸ� ǥ�� ���ڿ� ��ȯ */
	public static String getNumberString(long number) {
		return NumberFormat.getInstance().format(number);
	}

	/** �Ǽ��� ���� õ���� �ĸ� ǥ�� ���ڿ� ��ȯ */
	public static String getNumberString(double number) {
		return NumberFormat.getInstance().format(number);
	}
	
	/** ������ ��ȭ ���� õ���� �ĸ�, ��ȭ��ȣ ǥ�� ���ڿ� ��ȯ */
	public static String getCurrencyString(long number) {
		return NumberFormat.getCurrencyInstance().format(number);
	}

	/** �Ǽ��� ��ȭ ���� õ���� �ĸ�, ��ȭ��ȣ ǥ�� ���ڿ� ��ȯ */
	public static String getCurrencyString(double number) {
		return NumberFormat.getCurrencyInstance().format(number);
	}

	/** ������ ��ȭ ���� õ���� �ĸ�, ��ȭ��ȣ, ������ ���� ǥ�� ���ڿ� ��ȯ */
	public static String getCurrencyString(long number, Locale locale) {
		return NumberFormat.getCurrencyInstance(locale).format(number);
	}

	/** �Ǽ��� ��ȭ ���� õ���� �ĸ�, ��ȭ��ȣ, ������ ���� ǥ�� ���ڿ� ��ȯ */
	public static String getCurrencyString(double number, Locale locale) {
		return NumberFormat.getCurrencyInstance(locale).format(number);
	}
	
	
	/** Utility �׽�Ʈ */
	public static void main(String[] args) {
		System.out.println("\n## ���糯¥");
		System.out.println(Utility.getCurrentDate());
		System.out.println(Utility.getCurrentDate("yyyy/MM/dd [a]hh:mm"));
		System.out.println(Utility.getCurrentDate("yyyy/MM/dd [a]hh:mm", Locale.US));
		System.out.println(Utility.getCurrentDate("[a]hh:mm", Locale.CHINA));
		
		System.out.println("\n## ���ȹ��� : ����");
		System.out.println(Utility.getSecureCodeNumber());
		System.out.println(Utility.getSecureCodeNumber(6));
		System.out.println(Utility.getSecureCodeNumber(8));

		System.out.println("\n## ���ȹ��� : �����빮��");
		System.out.println(Utility.getSecureCodeString());
		System.out.println(Utility.getSecureCodeString(6));
		System.out.println(Utility.getSecureCodeString(8));
		
		System.out.println("\n## ���ȹ��� : ������ҹ���");
		System.out.println(Utility.getSecureCodeString(6, true));
		System.out.println(Utility.getSecureCodeString(6, false));
		
		System.out.println("\n## ���ȹ��ں�ȯ");
		String data = "happyday2017";
		System.out.println("data : " + data);
		System.out.println(Utility.convertSecureString(data));
		System.out.println(Utility.convertSecureString(data, 4));
		System.out.println(Utility.convertSecureString(data, 6));
		System.out.println(Utility.convertSecureString(data, 6, 3));
		
		System.out.println("\n## ���ȹ��ں�ȯ : ��ȭ��ȣ ǥ��");
		String mobile = "010-1234-2773";
		System.out.println("mobile : " + mobile);
		System.out.println(Utility.convertSecureStringLast(mobile, 4));
		
		System.out.println();
		mobile = "016-364-1234";
		System.out.println("mobile : " + mobile);
		System.out.println(Utility.convertSecureStringLast(mobile, 4));
		
		System.out.println();
		mobile = "016-364-1234";
		System.out.println("mobile : " + mobile);
		System.out.println(Utility.convertSecureStringLast(mobile, 8));
	
		int data1 = 12345678;
		double data2 = 12345678.345;
		
		System.out.println("\n## ���� õ���� �ĸ� ǥ��");
		System.out.println(Utility.getNumberString(data1));
		System.out.println(Utility.getNumberString(data2));
		
		System.out.println("\n## ���� ��ȭ���� ��ȣ, õ���� �ĸ� ǥ��");
		System.out.println(Utility.getCurrencyString(data1));
		System.out.println(Utility.getCurrencyString(data2));
		
		System.out.println("\n## ���� ��ȭ���� ��ȣ, õ���� �ĸ�, ������ ���� ǥ��");
		System.out.println(Utility.getCurrencyString(data1, Locale.US));
		System.out.println(Utility.getCurrencyString(data1, Locale.CHINA));
		System.out.println(Utility.getCurrencyString(data2, Locale.JAPAN));
	}
	
}
