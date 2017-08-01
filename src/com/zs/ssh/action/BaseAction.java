package com.zs.ssh.action;

import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.zs.ssh.model.FileImage;
import com.zs.ssh.service.AccountService;
import com.zs.ssh.service.CategoryService;
import com.zs.ssh.service.ProductService;
import com.zs.ssh.utils.FileUpload;

@Controller
@Scope("prototype")   //非单例的
public class BaseAction<T> extends ActionSupport implements RequestAware,SessionAware,ApplicationAware,ModelDriven<T> {
	
	private static final long serialVersionUID = 1L;
	@Autowired
	protected CategoryService categoryService;
	@Autowired
	protected AccountService accountService;
	@Autowired
	protected ProductService productService;
	//上传文件工具类
	@Autowired
	protected FileUpload fileUpload;
	//这个不能定义为private,因为子类都需要调用的
	protected Map<String, Object> application;
	protected Map<String, Object> session;
	protected Map<String, Object> request;
	//获取从easy ui发送请求的page和rows参数
	protected int page;
	protected int rows;
	protected Map<String, Object> pageMap = null;
	protected String ids;
	protected InputStream inputStream;
	//显示添加用户下拉列表的用户名
	protected List<T> jsonList = null;
	//打包的图片
	protected FileImage fileImage;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public Map<String, Object> getPageMap() {
		return pageMap;
	}

	public void setPageMap(Map<String, Object> pageMap) {
		this.pageMap = pageMap;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	public List<T> getJsonList() {
		return jsonList;
	}

	public void setJsonList(List<T> jsonList) {
		this.jsonList = jsonList;
	}
	
	public FileImage getFileImage() {
		return fileImage;
	}

	public void setFileImage(FileImage fileImage) {
		this.fileImage = fileImage;
	}

	@Override
	public void setApplication(Map<String, Object> application) {
		this.application = application;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	protected T model;
	
	@Override
	public T getModel() {
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		Class clazz = (Class) type.getActualTypeArguments()[0];
		try {
			model = (T) clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
}
