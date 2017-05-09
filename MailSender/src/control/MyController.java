package control;

import java.io.*;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;

import model.Mail;
import model.MyMail;

public class MyController implements Controller{
	private model.MyProperties mprop;
	
	public MyController(){
	}
	
	@Override
	public void setProperty(String x, String y) {
		mprop.setProperty(x,y);
	}

	@Override
	public String getProperty(String x) {
		return mprop.getProperty(x);
	}

	@Override
	public void saveProperty(String fileName) throws IOException {
		try(FileOutputStream fos = new FileOutputStream(fileName,false);
	    ObjectOutputStream out = new ObjectOutputStream(fos);
				){
			out.writeObject(mprop);
			out.flush();
		}
	}

	@Override
	public void loadProperty(String fileName) throws IOException, ClassNotFoundException {
		try(FileInputStream fos = new FileInputStream(fileName);
		ObjectInputStream out = new ObjectInputStream(fos);
				){
			mprop=(model.MyProperties)out.readObject();
		}
	}
	

	private Session session;
	private Transport transport;

	@Override
	public void init(){
		session=Session.getDefaultInstance(mprop.getPropertiesForSession());
		session.setDebug(true);
	}
	
	@Override
	public void connect() throws MessagingException {
		transport=session.getTransport();
		transport.connect(mprop.getProperty("UserName"),mprop.getProperty("UserPassword"));
	}
	
	@Override
	public Mail createMail(){
		return new MyMail(this.session);
	}

	@Override
	public void sendMail(Mail mail) throws MessagingException {
		transport.sendMessage(mail.getMail(), mail.getMail().getAllRecipients());
		transport.close();
	}

}
