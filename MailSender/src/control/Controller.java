package control;

import java.io.IOException;

import javax.mail.MessagingException;

public interface Controller {

	/**
	 * ���ò�������
	 */
	void setProperty(String x,String y);
	/**
	 * ��ȡ��������
	 */
	String getProperty(String x);
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
	 * ��ʼ��Session�Ա�����
	 */
	void init();
	/**
	 * �������ж�ȡ�û��ʺź����룬���ӷ�����
	 * @throws MessagingException 
	 */
	void connect() throws MessagingException;
	
	/**
	 * �������ʼ�
	 */
	model.Mail createMail();
	
	/**
	 * �����ʼ�
	 * @throws MessagingException 
	 */
	void sendMail(model.Mail mail) throws MessagingException;
	
}
