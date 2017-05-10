package model;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.Message.RecipientType;

public interface Mail {
	
	
	public MimeMessage getMail();

	/**
	 * 设置主题
	 * @throws MessagingException 
	 */
	public void setSubject(String subject) throws MessagingException;
	
	/**
	 * 插入收件人
	 * @throws MessagingException 
	 * @throws UnsupportedEncodingException 
	 */
	public void insertReceiver(RecipientType to,String receiveMail) throws UnsupportedEncodingException, MessagingException;
	
	/**
	 * 加入文字
	 * @throws MessagingException 
	 */
	public void append(String msg) throws MessagingException;
	/**
	 * 加入图片
	 * 返回唯一编号
	 * @throws MessagingException 
	 */
	public String appendPicture(String fileName) throws MessagingException;
	/**
	 * 换行
	 * @throws MessagingException 
	 */
	public void nextLine() throws MessagingException;
	
	/**
	 * 插入附件
	 * @throws MessagingException 
	 * @throws UnsupportedEncodingException 
	 */
	public void insertFile(String fileName) throws MessagingException, UnsupportedEncodingException;
	
	/**
	 * 保存更改
	 * @throws MessagingException 
	 */
	public void saveChanges() throws MessagingException;
	
	
}
