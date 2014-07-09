package com.integri.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;

import com.integri.model.BusinessFunction;

public class AppUtils {
	
	
	/**
	 * returns command line arg as int   
	 * @param args
	 * @param index
	 * @param defaultValue
	 * @return
	 */
	public static int getCommandLineArgAsNumber(String args[],int index ,int defaultValue ){ 
		String arg = getCommandLineArg(args, index);
		int n =(NumberUtils.isNumber(arg) ) ? NumberUtils.toInt(arg): defaultValue ;
		return n;
	}


	/**
	 * returns command line argument   
	 * @param args  
	 * @param index
	 * @return
	 */
	public static String getCommandLineArg(String[] args, int index) { 
		String arg =(args!=null && args.length>index ) ? args[index] : null ;
		return StringUtils.defaultString(arg);
	}	
	
	
	/**
	 * returns List of sample data files found in given directory 
	 * @param context
	 * @param dirName
	 * @return List
	 */
	public static List<File> getSampleFiles(ApplicationContext context, String dirName){
		   
		File[] files = null;
		
		try {
			Resource dir = context.getResource("classpath:" + dirName);
			files = dir.getFile().listFiles();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new ArrayList<File>(Arrays.asList(files));
		
	}

	
	/**
	 * returns a random sample file from the available list
	 * @param files - lsit of files
	 * @return File
	 */
	public static File getRandomSampleFile(List<File> files){   
		  if (files==null){
			  return null;
		  }
		  
		  int size = (files!=null) ? files.size(): 0 ;
		  int index = (size>0) ? new Random().nextInt(size): 0 ;
		  return (index<files.size()) ? files.get(index) : null ;
		  
	  }
	

	/**
	 * @param file
	 * @param size
	 * @param randomFlag
	 * @return
	 */
	public static String readData(File file, int size, boolean randomFlag) {
		if (!file.exists()) {
			return null;
		}

		RandomAccessFile accessFile = null;
		
		/* total file size - (small files )*/ 
		int length = (int) file.length();
		
		/* the max possible start position for random data */ 
		int maxStartIndex = (randomFlag && (length > size)) ? (length-size):0;
		
		/* the start position to begin reading data*/  
		int startIndex = (maxStartIndex > 1) ? new Random().nextInt(maxStartIndex):0;
		
		int readSize = (size>length) ? length : size; 
		
		try { 
			accessFile = new RandomAccessFile(file, "r");
			accessFile.seek(startIndex);
			byte[] bytes = new byte[readSize];
			accessFile.read(bytes); 
			accessFile.close();
			return new String(bytes);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(accessFile);
		}

		return null;
	}

		
	    	  	
}
