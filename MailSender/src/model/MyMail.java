package model;

import java.io.UnsupportedEncodingException;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class MyMail implements Mail{

	private final MimeMessage mail;
	private MimeMultipart content,files;
	
	public MyMail(Session session){
		mail=new MimeMessage(session);
		content=new MimeMultipart();
		files=new MimeMultipart();
	}
	
	@Override
	public MimeMessage getMail() {
		return mail;
	}

	@Override
	public void setSubject(String subject) throws MessagingException {
		mail.setSubject(subject);
	}

	@Override
	public void insertReceiver(RecipientType type,String receiveMail) throws UnsupportedEncodingException, MessagingException {
		mail.addRecipient(type, new InternetAddress(receiveMail,receiveMail, "UTF-8"));
	}
	
	
	@Override
	public void append(String msg) throws MessagingException {
		MimeBodyPart text=new MimeBodyPart();
		text.setContent(msg,"text/html;charset=UTF-8");
		content.addBodyPart(text);
	}

	private int picture_index=0;
	@Override
	public String appendPicture(String fileName) throws MessagingException {
		String res=String.valueOf(++picture_index);
		//this.append("<img src='"+res+"'/>");
		MimeBodyPart image=new MimeBodyPart();
		DataHandler dh=new DataHandler(new FileDataSource(fileName));
        image.setDataHandler(dh);
        image.setContentID(res);
		content.addBodyPart(image);
		return res;
	}

	@Override
	public void nextLine() throws MessagingException {
		this.append("<br/>");
	}

	@Override
	public void insertFile(String fileName) throws MessagingException, UnsupportedEncodingException {
		MimeBodyPart attachment=new MimeBodyPart();
        DataHandler dh=new DataHandler(new FileDataSource(fileName));
        attachment.setDataHandler(dh);
        attachment.setFileName(MimeUtility.encodeText(dh.getName()));
        files.addBodyPart(attachment);
	}
	
	
	@Override
	public void saveChanges() throws MessagingException{
		MimeMultipart mm=new MimeMultipart();
		//---
		MimeBodyPart _content=new MimeBodyPart();
//		content.setSubType("mixed");
		_content.setContent(content);
		mm.addBodyPart(_content);
		//---
		MimeBodyPart _files=new MimeBodyPart();
//		files.setSubType("mixed");
		_files.setContent(files);
		mm.addBodyPart(_files);
		mm.setSubType("mixed");
		//---
		mail.setContent(mm);
		mail.saveChanges();
	}

}
