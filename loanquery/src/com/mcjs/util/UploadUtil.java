package com.mcjs.util;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.mcjs.service.DataImportService;
import com.mcjs.service.StateFundService;


public class UploadUtil {
	private StateFundService stateFundService=null;
	private DataImportService dataImportService=null;
	@SuppressWarnings({ "rawtypes", "deprecation" })
	public HashMap<String, Object> uploadFiles(HttpServletRequest resquest,HttpServletResponse response){
		
		HashMap<String, Object> map = new HashMap<String,Object>();
		
		String fileName;
		
		try{
			resquest.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			
			String realPath = resquest.getRealPath("/");

			String dirPath = realPath + "/upload";
			
			File dirFile = new File(dirPath);
			if(!dirFile.exists()){
				dirFile.mkdirs();
			}
			
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			
			List items = upload.parseRequest(resquest);
			if(null != items)
			{
				Iterator itr = items.iterator();
				while(itr.hasNext()){
					FileItem item = (FileItem)itr.next();
					if(item.isFormField()){
						continue;
					} else{

						String name = item.getName();
						int i = name.lastIndexOf(".");
						String ext = name.substring(i, name.length());
						
						
						fileName = new Date().getTime() + ext;
						File saveFile = new File(dirPath,fileName);
						item.write(saveFile);
						
						map.put("name", item.getName());
						map.put("newName", fileName);
						map.put("size", item.getSize());
						map.put("url", "upload/"+fileName);
						
					}
				}
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return map;
		
	}
	
	
	// java���
	public static void main(String[] args){
		
		System.out.println("�װ���ͬѧ�ǣ�������Ϻ� ���Ұ�����  ^_^");
		
	}
	
}
