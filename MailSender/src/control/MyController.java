package control;

import java.io.*;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;

import model.Mail;
import model.MyMail;

public class MyController implements Controller{
	private model.MyProperties mprop;

	private model.TextMacro textMacro;
	public MyController(model.MyProperties mprop,model.TextMacro textMacro){
		this.mprop=mprop;
		this.textMacro=textMacro;
		if(mprop!=null)
			try{
				this.loadProperty(Controller.propertyFileName);
			}catch(Throwable t){t.printStackTrace();}
	}
	
	@Override
	public model.TextMacro getTextMacro(){
		return this.textMacro;
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
	public List<String> getPropertyKeys(){
		return mprop.getPropertyKeys();
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
	public void connect() throws MessagingException {
		session=Session.getDefaultInstance(mprop.getPropertiesForSession());
		session.setDebug(true);
		transport=session.getTransport();
		String username=mprop.getProperty("Username");
		String password=mprop.getProperty("Userpassword");
		transport.connect(username,password);
	}
	@Override
	public void close() throws MessagingException {
		if(transport!=null)
			transport.close();
	}
	
	@Override
	public Mail createMail() throws UnsupportedEncodingException, MessagingException{
		Mail res=new MyMail(this.session);
		String username=this.mprop.getProperty("Username");
		res.getMail().setFrom(new InternetAddress(username,username,"UTF-8"));
		return res;
	}

	@Override
	public void sendMail(Mail mail) throws MessagingException {
		transport.sendMessage(mail.getMail(), mail.getMail().getAllRecipients());
	}

}
