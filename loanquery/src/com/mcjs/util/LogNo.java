package com.mcjs.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * JAVA锟芥本锟斤拷锟皆讹拷锟斤拷锟斤拷泄锟斤拷锟侥讹拷锟斤拷锟斤拷(锟斤拷锟斤拷) 锟斤拷傻母锟绞斤拷锟� 200908010001 前锟芥几位为锟斤拷前锟斤拷锟斤拷锟斤拷,锟斤拷锟斤拷锟斤拷位为系统锟斤拷锟斤拷锟斤拷锟斤拷锟酵的憋拷锟�原锟斤拷:
 * 1.锟斤拷取锟斤拷前锟斤拷锟节革拷式锟斤拷值; 2.锟斤拷取锟侥硷拷,锟较次憋拷诺锟街�1锟斤拷为锟斤拷前锟剿次憋拷诺锟街�(锟铰碉拷一锟斤拷锟斤拷锟斤拷麓锟�锟斤拷始锟斤拷锟�
 */

public class LogNo {

	public String getNumber(){
		SerialNumber serial = new FileEveryDaySerialNumber(5,"EveryDaySerialNumber.dat");
		String logno=serial.getSerialNumber();
		return logno;
		
	}
	
//	public static void main(String[] args) throws InterruptedException {
//		SerialNumber serial = new FileEveryDaySerialNumber(5,
//				"EveryDaySerialNumber.dat");
//		while (true) {
//			System.out.println(serial.getSerialNumber());
//			TimeUnit.SECONDS.sleep(2);
//		}
//	}
}

abstract class SerialNumber {

	public synchronized String getSerialNumber() {
		return process();
	}

	protected abstract String process();
}

abstract class EveryDaySerialNumber extends SerialNumber {

	protected final static SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyyMMdd");
	protected DecimalFormat df = null;

	public EveryDaySerialNumber(int width) {
		if (width < 1) {
			throw new IllegalArgumentException(
					"Parameter length must be great than 1!");
		}
		char[] chs = new char[width];
		for (int i = 0; i < width; i++) {
			chs[i] = '0';
		}
		df = new DecimalFormat(new String(chs));
	}

	protected String process() {
		Date date = new Date();
		int n = getOrUpdateNumber(date, 1);
		return format(date) + format(n);
	}

	protected String format(Date date) {
		return sdf.format(date);
	}

	protected String format(int num) {
		return df.format(num);
	}

	/**
	 * 锟斤拷锟斤拷锟斤拷泻牛锟酵�憋拷锟斤拷鲁志没锟斤拷娲�拷械锟斤拷锟斤拷锟�
	 * 
	 * @param current
	 *            锟斤拷前锟斤拷锟斤拷锟斤拷
	 * @param start
	 *            锟斤拷始锟斤拷锟斤拷锟斤拷锟�
	 * @return 锟斤拷锟斤拷锟铰碉拷锟斤拷锟叫猴拷
	 */
	protected abstract int getOrUpdateNumber(Date current, int start);
}

class FileEveryDaySerialNumber extends EveryDaySerialNumber {

	/**
	 * 锟街久伙拷锟芥储锟斤拷锟侥硷拷
	 */
	private File file = null;

	/**
	 * 锟芥储锟侥分革拷锟斤拷
	 */
	private final static String FIELD_SEPARATOR = ",";

	public FileEveryDaySerialNumber(int width, String filename) {
		super(width);
		file = new File(filename);
	}

	@Override
	protected int getOrUpdateNumber(Date current, int start) {
		String date = format(current);
		int num = start;
		if (file.exists()) {
			List<String> list = FileUtil.readList(file);
			String[] data = list.get(0).split(FIELD_SEPARATOR);
			if (date.equals(data[0])) {
				num = Integer.parseInt(data[1]);
			}
		}
		FileUtil.rewrite(file, date + FIELD_SEPARATOR + (num + 1));
		return num;
	}
}

class FileUtil {

	public static void rewrite(File file, String data) {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(file));
			bw.write(data);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static List<String> readList(File file) {
		BufferedReader br = null;
		List<String> data = new ArrayList<String>();
		try {
			br = new BufferedReader(new FileReader(file));
			for (String str = null; (str = br.readLine()) != null;) {
				data.add(str);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return data;
	}
}
