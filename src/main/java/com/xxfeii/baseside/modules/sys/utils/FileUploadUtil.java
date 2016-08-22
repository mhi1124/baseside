package com.xxfeii.baseside.modules.sys.utils;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @ClassName FileUploadUtil
 * @Description 文件上传工具类
 * @author davi
 * @date 2016年8月11日
 *
 */
public class FileUploadUtil implements Serializable{
	
	private static Logger log = LoggerFactory.getLogger(FileUploadUtil.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static String uploadFile(HttpServletRequest req, HttpServletResponse resp){
		String savePath = req.getSession().getServletContext().getRealPath("/ROOT/image");
		File file = new File(savePath);
		//上传时生成的临时文件保存目录
        String tempPath = req.getSession().getServletContext().getRealPath("/ROOT/temp");
        File tmpFile = new File(tempPath);
        if (!tmpFile.exists()) {
            //创建临时目录
            tmpFile.mkdirs();
        }
		if(!file.exists() && !file.isDirectory()){
			file.mkdirs();
		}
		 //使用Apache文件上传组件处理文件上传步骤：
        //1、创建一个DiskFileItemFactory工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(10240000);
		//设置上传时生成的临时文件的保存目录
        factory.setRepository(tmpFile);
		//2、创建一个文件上传解析器
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("utf-8");
		String newFilePath = "";
		try {
			List<FileItem> list = upload.parseRequest(req);
			for(FileItem item : list){
				//如果fileitem中封装的是普通输入项的数据
				if(item.isFormField()){
					continue;
				}else{//如果fileitem中封装的是上传文件
					 //得到上传的文件名称，                               
					String filename = item.getName();
					if(filename==null || filename.trim().equals("")){
                        continue;
                    }
					//处理获取到的上传文件的文件名的路径部分，只保留文件名部分
                    filename = filename.substring(filename.lastIndexOf("\\")+1);
                    newFilePath = savePath + "\\" + filename;
                    //保存
                    item.write(new File(newFilePath));
                    //删除处理文件上传时生成的临时文件
                    item.delete();
                    return newFilePath;
				}
				
			}
			
		} catch (FileUploadBase.FileSizeLimitExceededException e) {
			return  "单个文件超出最大值！！！";
        }catch (FileUploadBase.SizeLimitExceededException e) {
        	return "上传文件的总的大小超出限制的最大值！！！";
        }catch (Exception e) {
            e.printStackTrace();
            return  "文件上传失败！";
        }
		
		return "";
	}
	
	/**
	 * springmvc 文件上传
	 * @param file
	 * @param respath 上传路径
	 * @param type 模块
	 * @return
	 */
	public static String uploadImg(MultipartFile file,String respath,String type){
		String path = respath + type;
		String fileName = file.getOriginalFilename();
		String imgPath = "";
		if(StringUtils.isNotEmpty(fileName)){
			File targetFile = new File(path ,fileName);
			if(!targetFile.exists()){
				targetFile.mkdirs();
			}
			imgPath=type+fileName;
			try {
				file.transferTo(targetFile);
			} catch (IllegalStateException e) {
				log.error(e.getMessage());
			} catch (IOException e) {
				log.error(e.getMessage());
			}
		}
		return imgPath;
	}
}
