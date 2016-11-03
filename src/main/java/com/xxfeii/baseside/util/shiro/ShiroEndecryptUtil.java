package com.xxfeii.baseside.util.shiro;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;

import com.xxfeii.baseside.modules.sys.entity.User;

/**
 * shiro 加密工具
 * @author 
 * @create 2016年11月2日
 */
public class ShiroEndecryptUtil {

	/** 
     * 对密码进行md5加密,并返回密文和salt，包含在User对象中 
     * @param username 用户名 
     * @param password 密码
     * @param hashIterations 迭代次数 
     * @return UserEntity对象，包含密文和salt 
     */ 
    public static User md5Password(String accountName,String password,int hashIterations){ 
        SecureRandomNumberGenerator secureRandomNumberGenerator=new SecureRandomNumberGenerator(); 
        String salt= secureRandomNumberGenerator.nextBytes().toHex(); 
        //组合username,两次迭代，对密码进行加密 
        String password_cryto = new Md5Hash(password,accountName+salt,hashIterations).toBase64(); 
        User user=new User();
        user.setPassword(password_cryto); 
        user.setSalt(salt); 
        user.setAccountName(accountName); 
        return user; 
    }
    
    public static void main(String[] args) {
    	User user = md5Password("577341666@qq.com","123456",2);
		System.out.println(user.getSalt()+";"+user.getPassword());
	}
}
