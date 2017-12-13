package com.zs.ssh.utils.impl;

import java.io.File;
import java.io.FilenameFilter;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.zs.ssh.model.FileImage;
import com.zs.ssh.utils.FileUpload;

//文件上传工具类的具体实现
@Component
public class FileUploadUtil implements FileUpload {
	
	private String filePath;
	
	@Value("#{prop.basePath+prop.filePath}") 
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	@Value("#{prop.basePath+prop.bankImagePath}")
	private String bankImagePath;
	
	private String getExtension(String fileName){
		return FilenameUtils.getExtension(fileName);
	}
	
	//生成UUID随机数，作为新的变量名
	private String newFileName(String fileName){
		String ext = getExtension(fileName);
		return UUID.randomUUID().toString()+"."+ext;
	}

	@Override
	public String uploadFile(FileImage fileImage) {
		System.out.println("filePath:"+filePath);
		//获取随机的文件名
		String pic = newFileName(fileImage.getFilename());
		System.out.println("pic:"+pic);
		try {
			//new File(String parent,String child),parent是父路径，child是文件名
			FileUtil.copyFile(fileImage.getFile(), new File(filePath, pic));
			return pic;
		} catch (Exception e) {
			throw new RuntimeException();
		}finally {
			fileImage.getFile().delete();
		}
	}
	
	public String[] getBankImage(){
		System.out.println("bankImagePath:"+bankImagePath);
		String[] list = new File(bankImagePath).list(new FilenameFilter() {
			//只接受后缀为gif格式的照片
			@Override
			public boolean accept(File dir, String name) {
				System.out.println("dir:"+dir+" ,name:"+name);
				return name.endsWith(".gif");
			}
		});
		return list;
	}

}
