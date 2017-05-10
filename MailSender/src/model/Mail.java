package model;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.Message.RecipientType;

public interface Mail {
	
	
	public MimeMessage getMail();

	/**
	 * ��������
	 * @throws MessagingException 
	 */
	public void setSubject(String subject) throws MessagingException;
	
	/**
	 * �����ռ���
	 * @throws MessagingException 
	 * @throws UnsupportedEncodingException 
	 */
	public void insertReceiver(RecipientType to,String receiveMail) throws UnsupportedEncodingException, MessagingException;
	
	/**
	 * ��������
	 * @throws MessagingException 
	 */
	public void append(String msg) throws MessagingException;
	/**
	 * ����ͼƬ
	 * ����Ψһ���
	 * @throws MessagingException 
	 */
	public String appendPicture(String fileName) throws MessagingException;
	/**
	 * ����
	 * @throws MessagingException 
	 */
	public void nextLine() throws MessagingException;
	
	/**
	 * ���븽��
	 * @throws MessagingException 
	 * @throws UnsupportedEncodingException 
	 */
	public void insertFile(String fileName) throws MessagingException, UnsupportedEncodingException;
	
	/**
	 * �������
	 * @throws MessagingException 
	 */
	public void saveChanges() throws MessagingException;
	
	
}
