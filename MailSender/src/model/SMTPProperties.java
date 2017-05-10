package model;

public class SMTPProperties extends MyProperties{
	private static final long serialVersionUID = -8815236455984570901L;
	
	public SMTPProperties(){
		super();
		prop.setProperty("mail.transport.protocol","smtp");
		prop.setProperty("mail.smtp.host","");
		prop.setProperty("mail.smtp.auth", "true");
		ini.setProperty("SSL", "false");
		prop.setProperty("mail.smtp.socketFactory.port","465");
		prop.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	    prop.setProperty("mail.smtp.socketFactory.fallback", "false");
	}
		
	public void setHost(String host){
		prop.setProperty("mail.smtp.host",host);
	}
	public void setSsl(String ssl){
		ini.setProperty("SSL",ssl);
	}
	public void setPort(String port){
		prop.setProperty("mail.smtp.socketFactory.port",port);
	}
}
