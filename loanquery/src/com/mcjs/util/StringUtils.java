package com.mcjs.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sun.misc.BASE64Encoder;

/**
 * 
 * 日期工具类
 * DateUtil
 * 创建人:xuchengfei 
 * 时间：2016年3月24日-下午9:24:21 
 * @version 1.0.0
 *
 */
public class StringUtils {
	
	/**
	 * 判断一个元素是否为空，如果为空true 否则false
	 * 方法名：isEmpty
	 * 创建人：xuchengfei 
	 * 时间：2016年3月24日-下午11:49:00 
	 * 手机:1564545646464
	 * @return boolean
	 * @exception 
	 * @since  1.0.0
	 */
	public static boolean isEmpty(String content){
		return null!=content && "".equals(content);
	}
	
	/**
	 * 字符串非空判断
	 * 方法名：isNotEmpty
	 * 创建人：xuchengfei 
	 * 时间：2016年3月25日-上午12:00:00 
	 * 手机:1564545646464
	 * @param content
	 * @return boolean
	 * @exception 
	 * @since  1.0.0
	 */
	public static boolean isNotEmpty(String content){
		return !isEmpty(content);
	}

	
	/**
	 * 判断当年是不是闰年，如果是true否则false
	 * 方法名：isLeapYear
	 * 创建人：xuchengfei 
	 * 时间：2016年3月24日-下午9:27:57 
	 * 手机:1564545646464
	 * @param year
	 * @return boolean
	 * @exception 
	 * @since  1.0.0
	 */
	public static boolean isLeapYear(int year){
		return (year%4==0 && year%100!=0)  ||  year%400==0;
	}
	
	/**
	 * 获取一年中某个月的天数
	 * 方法名：getMonthDay
	 * 创建人：xuchengfei 
	 * 时间：2016年3月24日-下午9:30:15 
	 * 手机:1564545646464
	 * @param year
	 * @param month
	 * @return int
	 * @exception 
	 * @since  1.0.0
	 */
	public static int getMonthDay(int year,int month){
		int result = 31;	
		switch (month) {
//				case 1:
//				case 3:
//				case 5:
//				case 7:
//				case 8:
//				case 10:
//				case 12:
//					result = 31;
//				break;
				case 4:
				case 6:
				case 9:
				case 11:
					result = 30;
				break;
			case 2:
				result = StringUtils.isLeapYear(year)?29:28;
				break;
			default:
				result = 31;
				break;
		}
		return result;
	}
	
	
	
	/**
	 * pattern:
	 *  理论上讲日期格式表达式包含全部26个英文字母的大小写，不过它们中的一些字母只是被预留了，并没有确切的含义。目前有效的字母及它们所代表的含义如下：
			    中文解释含义：
			G：年代标识，表示是公元前还是公元后
			y：年份
			M：月份
			d：日
			h：小时，从1到12，分上下午
			H：小时，从0到23
			m：分钟
			s：秒
			S：毫秒
			E：一周中的第几天，对应星期几，第一天为星期日，于此类推
			z：时区
			D：一年中的第几天
			F：这一天所对应的星期几在该月中是第几次出现
			w：一年中的第几个星期
			W：一个月中的第几个星期
			a：上午/下午标识
			k：小时，从1到24
			K：小时，从0到11，区分上下午
			    在日期格式表达式中出现的所有字母，在进行日期格式化操作后，都将被其所代表的含义对应的属性值所替换，并且对某些字母来说，重复次数的不同，格式化后的结果也会有所不同。请看下面的例子：
	 * 字符串格式编程日期
	 * 方法名：stringToDate
	 * 创建人：xuchengfei 
	 * 时间：2016年3月24日-下午10:56:12 
	 * 手机:1564545646464
	 * @param dateString
	 * @param pattern
	 * @return
	 * @throws ParseException Date
	 * @exception 
	 * @since  1.0.0
	 */
	public static Date stringToDate(String dateString,String pattern) throws ParseException{
		return new SimpleDateFormat(pattern).parse(dateString);
	}
	
	/**
	 * 字符串格式编程日期
	 * 方法名：stringToDate
	 * 创建人：xuchengfei 
	 * 时间：2016年3月24日-下午10:56:12 
	 * 手机:1564545646464
	 * @param dateString
	 * @param pattern
	 * @return
	 * @throws ParseException Date
	 * @exception 
	 * @since  1.0.0
	 */
	public static String dateToString(Date date,String pattern) throws ParseException{
		return new SimpleDateFormat(pattern).format(date);
	}
	
	/**
	 * 讲一个数字个格式化成为你的需要的金额【数字会四舍五入】<br/>--
	 * eg:doubleToString(12.5698,"#.##")===12.57<br/>
	 * eg:doubleToString(12.5698,"0.00")===12.57<br/>
	 * eg:doubleToString(12,"0.00")===12.00<br/>
	 * eg:doubleToString(12,"#.##")===12<br/>
	 * 方法名：doubleToString
	 * 创建人：xuchengfei 
	 * 时间：2016年3月24日-下午11:12:20 
	 * 手机:1564545646464
	 * @param num
	 * @param pattern
	 * @return
	 * @throws ParseException String
	 * @exception 
	 * @since  1.0.0
	 */
	public static String doubleToString(Double num,String pattern) throws ParseException{
		return new DecimalFormat(pattern).format(num);
	}
	
	/**
	 * 把传入的数转换为中文金额大写形式
	 * @param flag int 标志位，1 表示转换整数部分，0 表示转换小数部分
	 * @param s String 要转换的字符串
	 * @return 转换好的带单位的中文金额大写形式
	 */
	public static String numFormat(String s,int flag) {
		int sLength = s.length();
		// 货币大写形式
		String bigLetter[] = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
		// 货币单位
		String unit[] = {"元", "拾", "佰", "仟", "万", 
				// 拾万位到仟万位
				"拾", "佰", "仟",
				// 亿位到万亿位
				"亿", "拾", "佰", "仟", "万"};
		String small[] = {"分", "角"};
		// 用来存放转换后的新字符串
		String newS = "";
		// 逐位替换为中文大写形式
		for(int i = 0; i < sLength; i ++) {
			if(flag == 1) {
				// 转换整数部分为中文大写形式（带单位）
				newS = newS + bigLetter[s.charAt(i) - 48] + unit[sLength - i - 1];
			} else if(flag == 2) {
				// 转换小数部分（带单位）
				newS = newS + bigLetter[s.charAt(i) - 48] + small[sLength - i - 1];
			}
		}
		return newS;
	}
	
	/**
	 * 替换字符串中所有的空格
	 * 方法名：replaceAllTrim
	 * 创建人：xuchengfei 
	 * 时间：2016年3月24日-下午11:35:27 
	 * 手机:1564545646464
	 * @param content
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public static String replaceAllTrim(String content){
		return content.replaceAll("//s*", "");
	}
	
	/**
	 * 判断一个字符是不是中文
	 * 方法名：isChineseChar
	 * 创建人：xuchengfei 
	 * 时间：2016年3月24日-下午11:38:02 
	 * 手机:1564545646464
	 * @param c
	 * @return boolean
	 * @throws UnsupportedEncodingException 
	 * @exception 
	 * @since  1.0.0
	 */
	public static boolean isChineseChar(char c) {
		try {
			return String.valueOf(c).getBytes("GBK").length>1;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 截取==我z中国 subString("我z中国",3)===我z<br/>
	 * 方法名：subString
	 * 创建人：xuchengfei 
	 * 时间：2016年3月24日-下午11:47:52 
	 * 手机:1564545646464
	 * @param string
	 * @param count
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public static String subString(String string,int count){
		if(isEmpty(string))return "";
		int start=0;
		StringBuilder builder = new StringBuilder();
		for (int i = start; i < count; i++) {
			char c=string.charAt(i);
			builder.append(c);
			if(isChineseChar(c)){//判断一个字符是不是汉子
				--count;
			}
		}
		return builder.toString();
	}
	
	
	/**
	 * 获取文件的后缀
	 * 方法名：getExt
	 * 创建人：xuchengfei 
	 * 时间：2016年3月26日-下午9:18:39 
	 * 手机:1564545646464
	 * @param path
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public static String getExt(String path){
		if(isEmpty(path))return path;
		String ext = path.substring(path.lastIndexOf(".")+1);
		return ext;
	}
	
	/**
	 * 获取带有点的后缀
	 * 方法名：getExtPonit
	 * 创建人：xuchengfei 
	 * 时间：2016年3月26日-下午9:20:17 
	 * 手机:1564545646464
	 * @param path
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public static String getExtPonit(String path){
		if(isEmpty(path))return path;
		String ext = path.substring(path.lastIndexOf("."));
		return ext;
	}
	
	/**
	 * 获取文件的名称
	 * 方法名：getFileName
	 * 创建人：xuchengfei 
	 * 时间：2016年3月26日-下午9:19:11 
	 * 手机:1564545646464
	 * @param path
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public static String getFileName(String path){
		if(isEmpty(path))return path;
		String filename = path.substring(path.lastIndexOf("/")+1, path.lastIndexOf("."));
		return filename;
	}
	
	/**
	 * 验证码，文件随机数
	 * 随即生产随即数，
	 * 可以用来生产tocken字符串等.
	 * @param length 生成长度
	 * @return 随即数字符串.
	 */
	public static String getRandomString(int length) {
		StringBuffer bu = new StringBuffer();
		String[] arr = { "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c",
				"d", "e", "f", "g", "h", "i", "j", "k", "m", "n", "p", "q",
				"r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C",
				"D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "P",
				"Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
		Random random = new Random();
		while (bu.length() < length) {
			String temp = arr[random.nextInt(57)];
			if (bu.indexOf(temp) == -1) {
				bu.append(temp);
			}
		}
		return bu.toString();
	}
	
	/**
	 * 获取随机文件名
	 * 方法名：getNewFileName
	 * 创建人：xuchengfei 
	 * 时间：2016年3月26日-下午9:42:54 
	 * 手机:1564545646464
	 * @param filename
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public static String getNewFileName(String filename){
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+"_"+getRandomString(5)+getExtPonit(filename);
	}
	
	/**
	 * 根据用户ID获取文件随机名
	 * 方法名：getNewFileName
	 * 创建人：xuchengfei 
	 * 时间：2016年3月26日-下午9:44:04 
	 * 手机:1564545646464
	 * @param filename
	 * @param userId
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public static String getNewFileName(String filename,Integer userId){
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+"_"+getRandomString(5)+"_"+userId+getExtPonit(filename);
	}
	
	/**
	 * 
	 * 方法名：md5
	 * 创建人：xuchengfei 
	 * 时间：2016年3月26日-下午9:45:12 
	 * 手机:1564545646464
	 * @param src
	 * @return byte[]
	 * @exception 
	 * @since  1.0.0
	 */
	public static byte[] md5(String src) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] md = md5.digest(src.getBytes());
			return md;
		} catch (Exception e) {
		}
		return null;
	}
	
	/**
	 * MD5加载
	 * 方法名：md5Base64
	 * 创建人：xuchengfei 
	 * 时间：2016年3月26日-下午9:45:49 
	 * 手机:1564545646464
	 * @param str
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public static String md5Base64(String str) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			return base64Encode(md5.digest(str.getBytes()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String base64Encode(byte[] b) {
		if (b == null) {
			return null;
		}
		return new BASE64Encoder().encode(b);
	}
	
	/**
	 * 凯撒密码加密
	 * 方法名：encryption
	 * 创建人：xuchengfei 
	 * 时间：2016年3月26日-下午9:51:59 
	 * 手机:1564545646464
	 * @param str
	 * @param k
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public static String encryption(String str,int k){
		String string = "";
		for (int i = 0; i < str.length(); i++) {
			char c= str.charAt(i);
			if(c>='a' && c<='z'){
				c += k%26;
				if(c<'a'){
					c+=26;
				}
				if(c>'z'){
					c-=26;
				}
			}else if(c>='A' && c<='Z'){
				c+=k%26;
				if(c<'A'){
					c+=26;
				}
				if(c>'Z'){
					c-=26;
				}
			}
			string+=c;
		}
		return string;
	}
	
	/**
	 * 凯撒密码解密
	 * 方法名：dencryption
	 * 创建人：xuchengfei 
	 * 时间：2016年3月26日-下午9:52:04 
	 * 手机:1564545646464
	 * @param str
	 * @param n
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public static String dencryption(String str,int n){
		String string = "";
		int k = Integer.parseInt("-"+n);
		for (int i = 0; i < str.length(); i++) {
			char c= str.charAt(i);
			if(c>='a' && c<='z'){
				c += k%26;
				if(c<'a'){
					c+=26;
				}
				if(c>'z'){
					c-=26;
				}
			}else if(c>='A' && c<='Z'){
				c+=k%26;
				if(c<'A'){
					c+=26;
				}
				if(c>'Z'){
					c-=26;
				}
			}
			string+=c;
		}
		return string;
	}
	
	
	/**
	 * 根据后缀判断是不是图片
	 * 方法名：isImage
	 * 创建人：xuchengfei 
	 * 时间：2016年3月26日-下午11:55:12 
	 * 手机:1564545646464
	 * @param ext
	 * @return boolean
	 * @exception 
	 * @since  1.0.0
	 */
	public static boolean isImage(String ext) {
		return ext.toLowerCase().matches("jpg|gif|bmp|png|jpeg");
	}
	
	/**
	 * 根据后缀判断是不是offce文档
	 * 方法名：isImage
	 * 创建人：xuchengfei 
	 * 时间：2016年3月26日-下午11:55:12 
	 * 手机:1564545646464
	 * @param ext
	 * @return boolean
	 * @exception 
	 * @since  1.0.0
	 */
	public static boolean isDoc(String ext) {
		return ext.toLowerCase().matches("doc|docx|xls|xlsx|pdf|txt|ppt|pptx");
	}
	
	/**
	 * 根据后缀判断是不是音频
	 * 方法名：isImage
	 * 创建人：xuchengfei 
	 * 时间：2016年3月26日-下午11:55:12 
	 * 手机:1564545646464
	 * @param ext
	 * @return boolean
	 * @exception 
	 * @since  1.0.0
	 */
	public static boolean isVideo(String ext) {
		return ext.toLowerCase().matches("mp4|flv|mp3");
	}

	
	/**
	 * 替换标签符号位转义符号
	 * 方法名：htmlEncode
	 * 创建人：xuchengfei 
	 * 时间：2016年3月26日-下午11:56:51 
	 * 手机:1564545646464
	 * @param txt
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public static String htmlEncode(String txt) {
		if (null != txt) {
			txt = txt.replace("&", "&amp;").replace("&amp;amp;", "&amp;")
					.replace("&amp;quot;", "&quot;").replace("\"", "&quot;")
					.replace("&amp;lt;", "&lt;").replace("<", "&lt;")
					.replace("&amp;gt;", "&gt;").replace(">", "&gt;")
					.replace("&amp;nbsp;", "&nbsp;");
		}
		return txt;
	}
	

	/**
	 * 整数的转换与0补齐
	 * 
	 * @param str
	 *            转换的数字
	 * @param length
	 *            转换的长度。不够0补齐.
	 * @return
	 */
	public static String formatNO(int str, int length) {
		float ver = Float.parseFloat(System
				.getProperty("java.specification.version"));
		String laststr = "";
		if (ver < 1.5) {
			try {
				NumberFormat formater = NumberFormat.getNumberInstance();
				formater.setMinimumIntegerDigits(length);
				laststr = formater.format(str).toString().replace(",", "");
			} catch (Exception e) {
				System.out.println("格式化字符串时的错误信息：" + e.getMessage());
			}
		} else {
			Integer[] arr = new Integer[1];
			arr[0] = new Integer(str);
			laststr = String.format("%0" + length + "d", arr);
		}
		return laststr;
	}
	/***
	 * 字符串数组转换成字符串
	 * 
	 * @param strings
	 * @return
	 */
	public static String arrToString(String[] strings, String separtor) {
		StringBuffer buffer = new StringBuffer();
		if (strings != null) {
			for (String string : strings) {
				buffer.append(string + separtor);
			}
			String result = buffer.toString();
			return result.substring(0, result.length() - 1);
		} else {
			return "";
		}
	}
	
	/** 首字母转换为大写 **/
	public static String toUpperCaseFirst(String text) {
		return text.substring(0, 1).toUpperCase() + text.substring(1);
	}

	/** 首字母转换为小写 **/
	public static String toLowerCaseFirst(String text) {
		return text.substring(0, 1).toLowerCase() + text.substring(1);
	}
	/**
	 * @作用:判断是否为数字
	 */
	public static boolean isNumeric(String str) {
		Matcher isNum = Pattern.compile("(-|\\+)?[0-9]+(.[0-9]+\\+)?").matcher(
				str);
		return isNum.matches();
	}
	
	
	/**
	 * 判断字符串是否都是数字组成
	 * 
	 * @param numString
	 * @return
	 */
	public static boolean isNumber(String numString) {
		return StringUtils.isNumeric(numString);
	}

	/**
	 * 邮箱验证 方法名：isEmail 创建人：xuchengfei 时间：2014年6月6日-下午3:14:46
	 * 
	 * @param str
	 * @return boolean
	 * @exception
	 * @since 1.0.0
	 */
	public static boolean isEmail(String email) {
		boolean flag = false;
		try {
			String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(email);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 验证手机号码
	 * 
	 * @param mobiles
	 * @return
	 */
	public static boolean isMobile(String mobiles) {
		boolean flag = false;
		try {
			Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
			Matcher m = p.matcher(mobiles);
			flag = m.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 网络地址验证 方法名：isHomepage 创建人：xuchengfei 时间：2014年6月6日-下午3:15:10
	 * 
	 * @param str
	 * @return boolean
	 * @exception
	 * @since 1.0.0
	 */
	public static boolean isHomepage(String str) {
		String regex = "http://(([a-zA-z0-9]|-){1,}\\.){1,}[a-zA-z0-9]{1,}-*";
		return match(regex, str);
	}

	private static boolean match(String regex, String str) {
		Pattern pattern = Pattern.compile(regex);// 将给定的正则表达式编译到具有给定标志的模式中
		Matcher matcher = pattern.matcher(str);// 模式进行匹配字符�?
		return matcher.matches();
	}
	
	/**
	 * 
	 * 方法名：listToString
	 * 创建人：xuchengfei 
	 * 时间：2016年3月27日-上午12:19:48 
	 * 手机:1564545646464
	 * @param params
	 * @param sepator
	 * @return String
	 * @exception 
	 * @since  1.0.0
	 */
	public static String listToString(List<String> params, String sepator) {
		if (params.size() > 0) {
			StringBuffer buffer = new StringBuffer();
			for (String string : params) {
				buffer.append(string + sepator);
			}
			String result = buffer.toString();
			return result.substring(0, result.length() - 1);
		} else {
			return "";
		}
	}

	
	public static void main(String[] args) throws ParseException {
		
//		List<String> idStrings= new ArrayList<>();
//		idStrings.add("1");
//		idStrings.add("2");
//		idStrings.add("3");
//		idStrings.add("4");
		
//		System.out.println(listToString(idStrings, "#"));
//		String text="<script>alert(1)</script>";
//		System.out.println(htmlEncode(text));
		
//		System.out.println(formatNO(1,3));
//		System.out.println(formatNO(123,6));
		
		
		//字符串处理,文件名和后缀 路径的问题---项目中的文件上传--URL路径设计理念---磁盘路径是一样
//		String path="/ccc/keke.mp4";
//		System.out.println(getNewFileName(path));
//		System.out.println(getNewFileName(path,88));
//		System.out.println(getNewFileName(path,78));
		
		//加盐加密 --凯撒密码
//		System.out.println(md5Base64(md5Base64("keke_123456")));
//		System.out.println(md5Base64("keke_123456"));
//		
//		System.out.println(encryption("我ab课cdZ",10));
//		System.out.println(dencryption("我cd课ef", 10));
		
		
		//百度---字符串验证---数字，金额，邮箱，中文，
		
		
		
//		
//		
//		System.out.println(getExt(path));
//		System.out.println(getExtPonit(path));
//		System.out.println(getFileName(path));
//		
//		//随机文件名，订单号----文件名处理处理，文件重命名---经验之谈
//		
//		//毫无意义---随机或者UUID的方式
//		Random random =new Random();
//		System.out.println(random.nextInt(1000));
//		
//		
//		System.out.println(getRandomString(10));
//		System.out.println(getRandomString(14));
//		System.out.println(getRandomString(5));
//		System.out.println(getRandomString(35));
//		System.out.println(getRandomString(50));
//		
//		//真正的uuid  机器码+时间+ip地址+ UUID
//		System.out.println(UUID.randomUUID().toString());
//		System.out.println(UUID.randomUUID().toString());
//		System.out.println(UUID.randomUUID().toString());
//		
		
		//结合业务---有意义
//		20A160C315E1215D36-d2114A5-1.jpg
		//Random随机数
		
		
		//System---获取操作系统里面的一些常量信息
//		System.out.println(System.getProperty("os.name"));
//		System.out.println(System.getProperty("java.home"));
//		System.out.println(System.getProperty("user.dir"));
//		
//		//ctrl+shift+o--导入包
//		Properties properties=System.getProperties();
//		Enumeration<Object> enumeration=properties.elements();
//		while (enumeration.hasMoreElements()) {
//			Object object = (Object) enumeration.nextElement();
//			System.out.println(object);
//		}
//		
//		
//		Enumeration<?> enumeration2= properties.propertyNames();
//		while (enumeration.hasMoreElements()) {
//			Object object = (Object) enumeration2.nextElement();
//			System.out.println(object+"====="+System.getProperty(String.valueOf(object)));
//		}
		
		
		
		//RunTime
//		Runtime runtime = Runtime.getRuntime();
//		long max=runtime.maxMemory()/1024/1024;
//		long total=runtime.totalMemory()/1024/1024;
//		long free=runtime.freeMemory()/1024/1024;
//		System.out.println("运行时的对象:"+runtime.availableProcessors());
//		System.out.println("最大内存数"+max);//
//		System.out.println("空闲的内存数"+free);
//		System.out.println("总内存数"+total);
//		System.out.println("剩余内存大小："+(max-total+free));
		
		
		//Math函数---数学--三角函数，绝对性，平方根，大小运算，四舍五入 乘方
////		Math.toDegrees(hudu)//弧度转成角度
//		System.out.println(Math.toDegrees(3.14));//角度转成弧度
//		System.out.println(Math.toRadians(90));
//		圆的周长==2πr
//		180 = π
//		90 =90π/180--直角三角形
		//正弦---对边/斜边
//		System.out.println(Math.sin(Math.toRadians(30)));
//		System.out.println(Math.sin(Math.toRadians(60)));
//		//余弦---邻边/斜边
//		System.out.println(Math.cos(Math.toRadians(30)));
//		System.out.println(Math.cos(Math.toRadians(60)));
//		
//		//反余弦
//		System.out.println(Math.acos(Math.toRadians(30)));
////		System.out.println(Math.acos(Math.toRadians(60)));
//		//反正弦
//		System.out.println(Math.asin(Math.toRadians(30)));
////		System.out.println(Math.asin(Math.toRadians(60)));
//		
//		//正切
//		System.out.println(Math.tan(Math.toRadians(30)));
		
		//通用--字符串 Math
//		double s = 21.2554;
//		System.out.println(Math.floor(s));//21
//		System.out.println(Math.ceil(s));//22
//		
//		
//		//四舍五入--无法保留小数
//		double c =3.569;
//		System.out.println(Math.round(c));
//		String cstr= new DecimalFormat("#.##").format(c);
//		System.out.println(cstr);
//		
//		//平方根
//		System.out.println(Math.sqrt(3));
//		//立方根
//		System.out.println(Math.cbrt(27));
		//次幂
//		System.out.println(Math.exp(3));
		
//		//a的b次方
//		System.out.println(Math.pow(3, 2));//3*3
//		System.out.println(Math.pow(3, 3));//3*3*3
		
//		3*3+2*2+1*1 --阶乘求和
//		3*2*1+2*+1*1	
//		
//		System.out.println(Math.PI);//π--圆周率
//		System.out.println(Math.E);//自然指数
		
		//最大值 最小值
//		Math.min(12, 1);//1
//		Math.max(12, 1);//12
		
		
//		//绝对值
//		System.out.println(Math.abs(-2));
//		System.out.println(Math.abs(2));
		
		
		
		//指数应用的场景--
		
		
		
		//java基础数据数字--布尔类型
		//byte short int long float double char 数字类型
		//boolean 布尔
	
		
		
		//BigDecimal 在讲一次
//		BigDecimal b1= new BigDecimal(1);
//		BigDecimal b2= new BigDecimal(3);
//		BigDecimal b3=b1.add(b2);//相加
//		BigDecimal b3=b1.subtract(b2);//相减
//		BigDecimal b3=b1.multiply(b2);//相乘
//		BigDecimal b3=b1.divide(b2,18,BigDecimal.ROUND_HALF_UP);//相除
//		System.out.println(b3);
		
//		System.out.println(1/3d);
		
		
		//Set和List
	}
}