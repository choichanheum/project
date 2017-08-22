package work.util;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

/** 공통 기능 클래스 */ 
public class Utility {
	/** 현재날짜 조회 */
	public static String getCurrentDate() {
		return getCurrentDate("yyyy.MM.dd");
	}
	
	public static String getCurrentDate(String pattern) {
		return getCurrentDate(pattern, Locale.KOREA);
	}

	public static String getCurrentDate(String pattern, Locale locale) {
		return new SimpleDateFormat(pattern, locale).format(new Date());
	}
	
	/** 보안문자 발급 : 숫자 */
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
	
	/** 보안문자 발급 : 영문자 - 기본4자리 영문대문자 */
	public static String getSecureCodeString() {
		return getSecureCodeString(4);
	}

	/** 보안문자 발급 : 영문자 - 지정길이 영문대문자 */
	public static String getSecureCodeString(int length) {
		return getSecureCodeString(length, true);
//		StringBuilder secureCode = new StringBuilder();
//		Random random = new Random((long)(System.nanoTime() * Math.random()));
//		for (int index=0; index < length; index++) {
//			secureCode.append((char)(random.nextInt(26) + 65));
//		}
//		return secureCode.toString();
	}

	/** 보안문자 발급 : 영문자 - 지정길이 영문 대소문자 선택 */
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
	
	/** 보안문자 변환 : 기본 2자리 이후 변환 */
	public static String convertSecureString(String data) {
		return convertSecureString(data, 2);
	}
	
	/** 보안문자 변환 : 지정자리 이후 변환 */
	public static String convertSecureString(String data, int length) {
		StringBuilder convertSecure = new StringBuilder();
		convertSecure.append(data.substring(0, length));
		for (int index=length; index < data.length(); index++) {
			convertSecure.append("*");
		}
		return convertSecure.toString();
	}

	/** 보안문자 변환 : 지정자리 이후 지정길이 변환 */
	public static String convertSecureString(String data, int startPosition, int length) {
		StringBuilder convertSecure = new StringBuilder();
		convertSecure.append(data.substring(0, startPosition-1));
		for (int index=0; index < length; index++) {
			convertSecure.append("*");
		}
		convertSecure.append(data.substring(startPosition+length-1));
		return convertSecure.toString();
	}
	
	/** 연락처(휴대폰) 보안문자 변환 : 앞에서 변환, 뒷자리 미변환 */
	public static String convertSecureStringLast(String data, int length) {
		StringBuilder convertSecure = new StringBuilder();
		int endPosition = data.length() - length;
		for (int index=0; index < endPosition; index++) {
			convertSecure.append("*");
		}
		convertSecure.append(data.substring(endPosition));
		return convertSecure.toString();
	}
	
	/** 정수형 숫자 천단위 컴마 표기 문자열 반환 */
	public static String getNumberString(long number) {
		return NumberFormat.getInstance().format(number);
	}

	/** 실수형 숫자 천단위 컴마 표기 문자열 반환 */
	public static String getNumberString(double number) {
		return NumberFormat.getInstance().format(number);
	}
	
	/** 정수형 통화 숫자 천단위 컴마, 통화기호 표기 문자열 반환 */
	public static String getCurrencyString(long number) {
		return NumberFormat.getCurrencyInstance().format(number);
	}

	/** 실수형 통화 숫자 천단위 컴마, 통화기호 표기 문자열 반환 */
	public static String getCurrencyString(double number) {
		return NumberFormat.getCurrencyInstance().format(number);
	}

	/** 정수형 통화 숫자 천단위 컴마, 통화기호, 로케일 지정 표기 문자열 반환 */
	public static String getCurrencyString(long number, Locale locale) {
		return NumberFormat.getCurrencyInstance(locale).format(number);
	}

	/** 실수형 통화 숫자 천단위 컴마, 통화기호, 로케일 지정 표기 문자열 반환 */
	public static String getCurrencyString(double number, Locale locale) {
		return NumberFormat.getCurrencyInstance(locale).format(number);
	}
	
	
	/** Utility 테스트 */
	public static void main(String[] args) {
		System.out.println("\n## 현재날짜");
		System.out.println(Utility.getCurrentDate());
		System.out.println(Utility.getCurrentDate("yyyy/MM/dd [a]hh:mm"));
		System.out.println(Utility.getCurrentDate("yyyy/MM/dd [a]hh:mm", Locale.US));
		System.out.println(Utility.getCurrentDate("[a]hh:mm", Locale.CHINA));
		
		System.out.println("\n## 보안문자 : 숫자");
		System.out.println(Utility.getSecureCodeNumber());
		System.out.println(Utility.getSecureCodeNumber(6));
		System.out.println(Utility.getSecureCodeNumber(8));

		System.out.println("\n## 보안문자 : 영문대문자");
		System.out.println(Utility.getSecureCodeString());
		System.out.println(Utility.getSecureCodeString(6));
		System.out.println(Utility.getSecureCodeString(8));
		
		System.out.println("\n## 보안문자 : 영문대소문자");
		System.out.println(Utility.getSecureCodeString(6, true));
		System.out.println(Utility.getSecureCodeString(6, false));
		
		System.out.println("\n## 보안문자변환");
		String data = "happyday2017";
		System.out.println("data : " + data);
		System.out.println(Utility.convertSecureString(data));
		System.out.println(Utility.convertSecureString(data, 4));
		System.out.println(Utility.convertSecureString(data, 6));
		System.out.println(Utility.convertSecureString(data, 6, 3));
		
		System.out.println("\n## 보안문자변환 : 전화번호 표기");
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
		
		System.out.println("\n## 숫자 천단위 컴마 표기");
		System.out.println(Utility.getNumberString(data1));
		System.out.println(Utility.getNumberString(data2));
		
		System.out.println("\n## 숫자 통화단위 기호, 천단위 컴마 표기");
		System.out.println(Utility.getCurrencyString(data1));
		System.out.println(Utility.getCurrencyString(data2));
		
		System.out.println("\n## 숫자 통화단위 기호, 천단위 컴마, 로케일 지정 표기");
		System.out.println(Utility.getCurrencyString(data1, Locale.US));
		System.out.println(Utility.getCurrencyString(data1, Locale.CHINA));
		System.out.println(Utility.getCurrencyString(data2, Locale.JAPAN));
	}
	
}
