package com.zs.ssh.utils;

import com.zs.ssh.model.FileImage;

public interface FileUpload {
	//实现文件的上传，并返回新的名字
	public String uploadFile(FileImage fileImage);
}
