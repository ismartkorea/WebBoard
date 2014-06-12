package com.webboard.common;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.log4j.Logger;

public class CommUtil {
	
	public CommUtil() {
	}
	
    static Logger logger = Logger.getLogger( CommUtil.class );
    
		/**
		 * String형 Null 체크 메소드.
		 * @param str
		 * @return
		 */
		public static String strCheckNull(String str) {
			try {
				if(str == null) {
					str = "";
					return str;
				}
			} catch(Exception ex) {
				ex.printStackTrace();
			}
			return str;
		}
		/**
		 * Int형 Null 체크 메소드.
		 * @param str
		 * @return
		 */
		public static int intCheckNull(String str) {
			int number = 0;
			try {

				if(str == null || str.equals("")) {
					return number;
				} else {
					number = Integer.parseInt(str);
					logger.debug(">>>> number is " + number);
					
				}
				
			} catch(Exception ex) {
				ex.printStackTrace();
			}
			
			return number;
		}
		
		/**
		 * String To Date Convet 메소드.
		 * @param str
		 * @return
		 */
		public static Date strToDate(String str) {
			Date date = new Date();
			try {
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				date = sdf.parse(str);
				
				return date;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return date;
			
		}
		/**
		 * Date To String Convert 메소드.
		 * @param date
		 * @return
		 */
		public static String dateToString(Date date) {
			
			String strDate = null;
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			strDate = sdf.format(date);
			
			return strDate;
		}
		/**
		 * Date To String Convert 메소드.
		 * @param date
		 * @return
		 */
		public static String timeStampToStr(Timestamp date) {
			
			String strDate = null;
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			strDate = sdf.format(date);
			
			return strDate;
		}
		/**
		 * 
		 * @param date
		 * @return
		 */
		public static String timeStampToStr(Timestamp date, String exp) {
			
			String strDate = null;
			
			if(exp == null || exp.equals("")) {
				exp = "yyyy-MM-dd";
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat(exp);
			strDate = sdf.format(date);
			
			return strDate;
		}		
		/**
		 * String To java.sql.Date Convert 메소드.
		 * @param str
		 * @return
		 */
		public static java.sql.Date strToSDate(String str) {
			java.util.Date utilDate = new Date();
			java.sql.Date sqlDate = null;
			try {
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				utilDate = sdf.parse(str);
				sqlDate = new java.sql.Date(utilDate.getTime());
				
				return sqlDate;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return sqlDate;			
		}
		
		/**
		 * 
		 * @return
		 */
		public static String getCurrentDate() {
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd",Locale.KOREA);
			Date currentDate = new Date();
			String sDate = sdf.format(currentDate);
			
			return sDate;
			
		}	
		 /**
		  *
		  */
		    public final static String translateJS(String s) {
		        if ( s == null ) return null;

		        StringBuffer buf = new StringBuffer();
		        char[] c = s.toCharArray();
		        int len = c.length;
		        for ( int i=0; i < len; i++) {
		            if ( c[i] == '&' )
		    buf.append("\"+&amp;+\"");
		            else if ( c[i] == '<' )
		    buf.append("\"+&lt;+\"");
		            else if ( c[i] == '>' )
		    buf.append("\"+&gt;+\"");
		            else if ( c[i] == '"' )
		    buf.append("\"+&quot;+\"");
		            else if ( c[i] == '\'')
		    buf.append("\"+&#039;+\"");
		            else buf.append(c[i]);
		        }
		        return buf.toString();
		 }
		    
		    /**
		     * String에 포함된 모든 newline문자(\n)를 변환하여 줌
		     * 디폴트는 "<BR>\n" 로 변환함
		     * @return the translated string.
		     * @param source String 변환할 string
		     */
		 public static String translateNewline(String source) {
			 
			 String DEFAULT_STRING = "<BR>\n";
			 return translateNewline(source, DEFAULT_STRING);
		  	
		 }

		    /**
		     * String에 포함된 모든 newline문자(\n)를 특정String으로 변환하여 줌
		     * @return the translated string.
		     * @param source String 변환할 string
		     * @param specialString newline문자를 대체할 string
		     */
		 public static String translateNewline(String source, String specialString) {

		  String result = "";
		  java.util.StringTokenizer st = new java.util.StringTokenizer(source, "\n");
		  while(st.hasMoreTokens())
		   result += st.nextToken() + specialString;
		  return result;
		 }
		    /**
		     * 문자열을 15자 만큼만 보여주고
		     * 그 길이에 초과되는 문자열일 경우 "..."를 덧붙여 보여준다.
		     * @return the translated string.
		     * @param s String 변환할 문자열
		     */
		 public static String fixLength(String input) {
			 return fixLength(input, 15, "...");
		 }

		    /**
		     * 문자열을 일정길이 만큼만 보여주고
		     * 그 길이에 초과되는 문자열일 경우 "..."를 덧붙여 보여준다.
		     * @return the translated string.
		     * @param s String 변환할 문자열
		     * @param limitLength int 문자열의 제한 길이
		     */
		 public static String fixLength(String input, int limit) {
			 return fixLength(input, limit, "...");
		 }

		/**
	     * 문자열을 일정길이 만큼만 보여주고
	     * 그 길이에 초과되는 문자열일 경우 특정문자를 덧붙여 보여준다.
	     * @return the translated string.
	     * @param s String 변환할 문자열
	     * @param limitLength int 문자열의 제한 길이
	     * @param postfix String 덧붙일 문자열
	     */
		 public static String fixLength(String input, int limit, String postfix) {
			  char[] charArray = input.toCharArray();
			  if (limit >= charArray.length)
			   return input;
			  return new String( charArray, 0, limit ).concat( postfix );
		 }

		 /**
		  * 문자열을 일정길이 만큼만 보여주고
		  * 그 길이에 초과되는 문자열일 경우 특정문자를 덧붙여 보여준다.
		  *
		  * 단 fixLength와의 차이는 제한길이의 기준이 char가 아니라 byte로
		  * 처리함으로해서 한글문제를 해결할수 있다.
		  *
		  * @return the translated string.
		  * @param input String 변환할 문자열
		  * @param limitByte int 문자열의 제한 길이(byte)
		  * @param postfix String 덧붙일 문자열
		  *
		  * @author Jangho Hwang 
		  */
		 public static String fixUnicodeLength(String input, int limitByte) {
		  return fixLength(input, limitByte, "...");
		 }
		 /**
		  *  중간에 -1을 해주는 것은. 21바이트짜리 한글 스트링을.
		  *  20으로 짤라서 String을 생성하면, 끝글자만 짤리는것이 아니라.
		  *  스트링자체가 완전 생성이 되지 않기 때문. 그러므로 길이가 0이면
		  *  -1한만큼 스트링을 생성하는 것이다.
		  * 
		  * @param input
		  * @param limitByte
		  * @param postfix
		  * @return
		  */
		 public static String fixUnicodeLength( String input, int limitByte, String postfix ) {
			  byte[] outputBytes = input.getBytes();
	
			  String output = outputBytes.length <= limitByte ? 
			   input : (new String( outputBytes, 0, limitByte ).length()==0 ? 
			    new String( outputBytes, 0, limitByte-1 ).concat( postfix ) : 
			    new String( outputBytes, 0, limitByte ) ).concat( postfix );
	
			  return output;
		 }

	    /**
	     * 문자열에서 특정 문자열을 치환한다.
	     * @return the translated string.
	     * @param source String 변환할 문자열
	     * @param keyStr String 치환 대상 문자열
	     * @param toStr String 치환될 문자열
	     */
	    public static String replaceStr(String source, String keyStr, String toStr) {
	     int startIndex = 0;
	     int curIndex = 0;
	     StringBuffer result = new StringBuffer();

	     while ( ( curIndex = source.indexOf(keyStr, startIndex) ) >= 0) {
	      result.append(source.substring(startIndex, curIndex))
	            .append(toStr);
	      startIndex = curIndex + keyStr.length();
	     }

	     if (startIndex <= source.length() )
	      result.append(source.substring(startIndex, source.length()));

	     return result.toString();

	    }

	    /**
	     * 문자열에서 특정 문자열을 치환한다.
	     * 문자열 배열의 차례대로 치환하되
	     * 더 이상 배열 값이 없으면 space 1칸으로 치환한다.
	     * @return the translated string.
	     * @param source String 변환할 문자열
	     * @param keyStr String 치환 대상 문자열
	     * @param toStr String[] 치환될 문자열 배열
	     */
	    public static String replaceStr(String source, String keyStr, String[] toStr) {
	     int startIndex = 0;
	     int curIndex = 0;
	     int i = 0;
	     StringBuffer result = new StringBuffer();
		 String specialString = null;
	
		     while ( ( curIndex = source.indexOf(keyStr, startIndex) ) >= 0) {
		      if (i < toStr.length )
		          specialString = toStr[i++];
		      else
		          specialString = " ";
		      result.append(source.substring(startIndex, curIndex))
		            .append(specialString);
		      startIndex = curIndex + keyStr.length();
		     }
	
		     if (startIndex <= source.length() )
		      result.append(source.substring(startIndex, source.length()));
	
		     return result.toString();
	    }


	    /**
	     * 날짜 문자열에서 delimiter가 표기된 형태로 보여준다.
	     * @return the translated string.
	     * @param date String 변환할 문자열
	     */
	    public static String printDate(String date) {
	    	if (date == null) return "";
	    	return date.substring(0,10).replace('-','/');
	    }

	    /**
	     * 날짜 문자열에서 delimiter가 표기된 형태로 보여준다.
	     * @return the translated string.
	     * @param date String 변환할 문자열
	     * @param seperator char delimiter
	     */
	    public static String printDate(String date, char seperator) {
	    	if (date == null) return "";
	    	return date.substring(0,10).replace('-', seperator);
	    }

	    /**
	     * 날짜시간 문자열에서 delimiter가 표기된 형태로 보여준다.
	     * @return the translated string.
	     * @param date String 변환할 문자열
	     */
	    public static String printDateTime(String date) {
	    	if (date == null) return "";
	 		return date.substring(0,16).replace('-','/');
	    }
		/**
		 * 금액 형태 표시.
		 * @param str
		 * @return
		 */
		public static String setStrComma(String str) {
			String result = "";
			DecimalFormat df = new DecimalFormat("###,###"); 
			if(str != null || str != "") {
				result = df.format(str);
			}
			return result;
		}
		/**
		 * 금액 형태 표시.
		 * @param num
		 * @return
		 */
		public static String setIntComma(int num) {
			String strNum = String.valueOf(num);
			DecimalFormat df = new DecimalFormat("###,###"); 
			return df.format(Integer.parseInt(strNum));
		}	

}
