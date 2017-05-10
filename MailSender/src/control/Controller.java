package control;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;

public interface Controller {

	model.TextMacro getTextMacro();
	/**
	 * ���ò�������
	 */
	void setProperty(String x,String y);
	/**
	 * ��ȡ��������
	 */
	String getProperty(String x);
	/**
	 * ��ȡ��������
	 */
	List<String> getPropertyKeys();
	/**
	 * �����ļ�����
	 */
	final String propertyFileName="property.ini";
	/**
	 * ����������õ������ļ�
	 * @throws IOException 
	 */
	void saveProperty(String fileName) throws IOException;
	/**
	 * �������ļ���ȡ��������
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	void loadProperty(String fileName) throws IOException, ClassNotFoundException;

	/**
	 * �������ж�ȡ�û��ʺź����룬���ӷ�����
	 * @throws MessagingException 
	 */
	void connect() throws MessagingException;
	/**
	 * �ر�����
	 * @throws MessagingException 
	 */
	void close() throws MessagingException;
	
	/**
	 * �������ʼ�
	 * @throws MessagingException 
	 * @throws UnsupportedEncodingException 
	 */
	model.Mail createMail() throws UnsupportedEncodingException, MessagingException;
	
	/**
	 * �����ʼ�
	 * @throws MessagingException 
	 */
	void sendMail(model.Mail mail) throws MessagingException;
	
}
