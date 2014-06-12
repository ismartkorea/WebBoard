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
		 * String�� Null üũ �޼ҵ�.
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
		 * Int�� Null üũ �޼ҵ�.
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
		 * String To Date Convet �޼ҵ�.
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
		 * Date To String Convert �޼ҵ�.
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
		 * Date To String Convert �޼ҵ�.
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
		 * String To java.sql.Date Convert �޼ҵ�.
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
		     * String�� ���Ե� ��� newline����(\n)�� ��ȯ�Ͽ� ��
		     * ����Ʈ�� "<BR>\n" �� ��ȯ��
		     * @return the translated string.
		     * @param source String ��ȯ�� string
		     */
		 public static String translateNewline(String source) {
			 
			 String DEFAULT_STRING = "<BR>\n";
			 return translateNewline(source, DEFAULT_STRING);
		  	
		 }

		    /**
		     * String�� ���Ե� ��� newline����(\n)�� Ư��String���� ��ȯ�Ͽ� ��
		     * @return the translated string.
		     * @param source String ��ȯ�� string
		     * @param specialString newline���ڸ� ��ü�� string
		     */
		 public static String translateNewline(String source, String specialString) {

		  String result = "";
		  java.util.StringTokenizer st = new java.util.StringTokenizer(source, "\n");
		  while(st.hasMoreTokens())
		   result += st.nextToken() + specialString;
		  return result;
		 }
		    /**
		     * ���ڿ��� 15�� ��ŭ�� �����ְ�
		     * �� ���̿� �ʰ��Ǵ� ���ڿ��� ��� "..."�� ���ٿ� �����ش�.
		     * @return the translated string.
		     * @param s String ��ȯ�� ���ڿ�
		     */
		 public static String fixLength(String input) {
			 return fixLength(input, 15, "...");
		 }

		    /**
		     * ���ڿ��� �������� ��ŭ�� �����ְ�
		     * �� ���̿� �ʰ��Ǵ� ���ڿ��� ��� "..."�� ���ٿ� �����ش�.
		     * @return the translated string.
		     * @param s String ��ȯ�� ���ڿ�
		     * @param limitLength int ���ڿ��� ���� ����
		     */
		 public static String fixLength(String input, int limit) {
			 return fixLength(input, limit, "...");
		 }

		/**
	     * ���ڿ��� �������� ��ŭ�� �����ְ�
	     * �� ���̿� �ʰ��Ǵ� ���ڿ��� ��� Ư�����ڸ� ���ٿ� �����ش�.
	     * @return the translated string.
	     * @param s String ��ȯ�� ���ڿ�
	     * @param limitLength int ���ڿ��� ���� ����
	     * @param postfix String ������ ���ڿ�
	     */
		 public static String fixLength(String input, int limit, String postfix) {
			  char[] charArray = input.toCharArray();
			  if (limit >= charArray.length)
			   return input;
			  return new String( charArray, 0, limit ).concat( postfix );
		 }

		 /**
		  * ���ڿ��� �������� ��ŭ�� �����ְ�
		  * �� ���̿� �ʰ��Ǵ� ���ڿ��� ��� Ư�����ڸ� ���ٿ� �����ش�.
		  *
		  * �� fixLength���� ���̴� ���ѱ����� ������ char�� �ƴ϶� byte��
		  * ó���������ؼ� �ѱ۹����� �ذ��Ҽ� �ִ�.
		  *
		  * @return the translated string.
		  * @param input String ��ȯ�� ���ڿ�
		  * @param limitByte int ���ڿ��� ���� ����(byte)
		  * @param postfix String ������ ���ڿ�
		  *
		  * @author Jangho Hwang 
		  */
		 public static String fixUnicodeLength(String input, int limitByte) {
		  return fixLength(input, limitByte, "...");
		 }
		 /**
		  *  �߰��� -1�� ���ִ� ����. 21����Ʈ¥�� �ѱ� ��Ʈ����.
		  *  20���� ©�� String�� �����ϸ�, �����ڸ� ©���°��� �ƴ϶�.
		  *  ��Ʈ����ü�� ���� ������ ���� �ʱ� ����. �׷��Ƿ� ���̰� 0�̸�
		  *  -1�Ѹ�ŭ ��Ʈ���� �����ϴ� ���̴�.
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
	     * ���ڿ����� Ư�� ���ڿ��� ġȯ�Ѵ�.
	     * @return the translated string.
	     * @param source String ��ȯ�� ���ڿ�
	     * @param keyStr String ġȯ ��� ���ڿ�
	     * @param toStr String ġȯ�� ���ڿ�
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
	     * ���ڿ����� Ư�� ���ڿ��� ġȯ�Ѵ�.
	     * ���ڿ� �迭�� ���ʴ�� ġȯ�ϵ�
	     * �� �̻� �迭 ���� ������ space 1ĭ���� ġȯ�Ѵ�.
	     * @return the translated string.
	     * @param source String ��ȯ�� ���ڿ�
	     * @param keyStr String ġȯ ��� ���ڿ�
	     * @param toStr String[] ġȯ�� ���ڿ� �迭
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
	     * ��¥ ���ڿ����� delimiter�� ǥ��� ���·� �����ش�.
	     * @return the translated string.
	     * @param date String ��ȯ�� ���ڿ�
	     */
	    public static String printDate(String date) {
	    	if (date == null) return "";
	    	return date.substring(0,10).replace('-','/');
	    }

	    /**
	     * ��¥ ���ڿ����� delimiter�� ǥ��� ���·� �����ش�.
	     * @return the translated string.
	     * @param date String ��ȯ�� ���ڿ�
	     * @param seperator char delimiter
	     */
	    public static String printDate(String date, char seperator) {
	    	if (date == null) return "";
	    	return date.substring(0,10).replace('-', seperator);
	    }

	    /**
	     * ��¥�ð� ���ڿ����� delimiter�� ǥ��� ���·� �����ش�.
	     * @return the translated string.
	     * @param date String ��ȯ�� ���ڿ�
	     */
	    public static String printDateTime(String date) {
	    	if (date == null) return "";
	 		return date.substring(0,16).replace('-','/');
	    }
		/**
		 * �ݾ� ���� ǥ��.
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
		 * �ݾ� ���� ǥ��.
		 * @param num
		 * @return
		 */
		public static String setIntComma(int num) {
			String strNum = String.valueOf(num);
			DecimalFormat df = new DecimalFormat("###,###"); 
			return df.format(Integer.parseInt(strNum));
		}	

}
