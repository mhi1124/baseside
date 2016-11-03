package com.xxfeii.baseside.util.imag;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.lang.StringUtils;

import com.drew.imaging.jpeg.JpegMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

public class ExifTester {

	public static void main(String[] args) throws Exception {
		File jpegFile = new File("/logo/8d575fa671cd461286329deb9511c3aa.png");
		InputStream is = new FileInputStream(jpegFile);  
        byte[] bt = new byte[2];  
        is.read(bt);  
        System.out.println(bytesToHexString(bt)); 
		System.out.println("释放修改过图片："+isModifyImg(jpegFile));
		//System.out.println(f);
		Metadata metadata = JpegMetadataReader.readMetadata(jpegFile);
		for (Directory directory : metadata.getDirectories()) {
			for (Tag tag : directory.getTags()) {
				String tagName = tag.getTagName();
				String desc = tag.getDescription();
				if (tagName.equals("Image Height")) {
					// 图片高度
					System.out.println("图片高度:" + desc);
				} else if (tagName.equals("Image Width")) {
					// 图片宽度
					System.out.println("图片宽度:" + desc);
				} else if (tagName.equals("Date/Time Original")) {
					// 拍摄时间
					System.out.println("拍摄时间:" + desc);
				} else if (tagName.equals("GPS Altitude")) {
					// 海拔
					System.out.println("海拔:" + desc);
				} else if (tagName.equals("GPS Latitude")) {
					// 纬度
					System.out.println("纬度:" + desc);
				} else if (tagName.equals("GPS Longitude")) {
					// 经度
					System.out.println("经度:" + desc);
				}
			}
			for (String error : directory.getErrors()) {
				System.err.println("ERROR: " + error);
			}
		}

	}

	/**
	 * 把文件转成字符串
	 * @param file
	 * @return
	 * @throws IOException
	 */
	private static String readFileContent(File file) throws IOException {

		FileInputStream inputStream = new FileInputStream(file);

		String content = "";
		StringBuilder sb = new StringBuilder();

		// 创建一个长度为1024的竹筒
		byte[] bbuf = new byte[1024];
		// 用于保存实际读取的字节数
		int hasRead = 0;
		// 使用循环来重复“取水”的过程
		while ((hasRead = inputStream.read(bbuf)) > 0) {
			content = new String(bbuf, 0, hasRead);
			sb.append(content);
			// 取出"竹筒"中(字节),将字节数组转成字符串输入
		}
		inputStream.close();

		return sb.toString();
	}
	
	/**
	 * 判断图片是否PS修改
	 * @param file
	 * @return
	 * @throws IOException
	 */
	private static boolean isModifyImg(File file) throws IOException{
		String imgString = readFileContent(file);
		return StringUtils.contains(imgString, "Photoshop");
	}
	
	
	public static String bytesToHexString(byte[] src) {  
        StringBuilder stringBuilder = new StringBuilder();  
        if (src == null || src.length <= 0) {  
            return null;  
        }  
        for (int i = 0; i < src.length; i++) {  
            int v = src[i] & 0xFF;  
            String hv = Integer.toHexString(v);  
            if (hv.length() < 2) {  
                stringBuilder.append(0);  
            }  
            stringBuilder.append(hv);  
        }  
        return stringBuilder.toString();  
    }  
}
