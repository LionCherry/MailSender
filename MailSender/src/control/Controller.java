package control;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;

public interface Controller {

	model.TextMacro getTextMacro();
	/**
	 * 设置参数配置
	 */
	void setProperty(String x,String y);
	/**
	 * 获取参数配置
	 */
	String getProperty(String x);
	/**
	 * 获取参数配置
	 */
	List<String> getPropertyKeys();
	/**
	 * 配置文件名称
	 */
	final String propertyFileName="property.ini";
	/**
	 * 保存参数配置到配置文件
	 * @throws IOException 
	 */
	void saveProperty(String fileName) throws IOException;
	/**
	 * 从配置文件读取参数配置
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	void loadProperty(String fileName) throws IOException, ClassNotFoundException;

	/**
	 * 从配置中读取用户帐号和密码，连接服务器
	 * @throws MessagingException 
	 */
	void connect() throws MessagingException;
	/**
	 * 关闭连接
	 * @throws MessagingException 
	 */
	void close() throws MessagingException;
	
	/**
	 * 创建新邮件
	 * @throws MessagingException 
	 * @throws UnsupportedEncodingException 
	 */
	model.Mail createMail() throws UnsupportedEncodingException, MessagingException;
	
	/**
	 * 发送邮件
	 * @throws MessagingException 
	 */
	void sendMail(model.Mail mail) throws MessagingException;
	
}
