package model;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

public abstract class MyProperties implements Serializable{
	private static final long serialVersionUID = 9178095284417880259L;
	
	protected final Properties ini;
	protected final Properties prop;
	
	public MyProperties(){
		this.ini=new Properties();
		this.prop=new Properties();
	}
	
	public final Properties getPropertiesForSession(){
		return this.prop;
	}
	public final String getProperty(String key){
		if(ini.containsKey(key))
			return ini.getProperty(key);
		if(prop.contains(key))
			return prop.getProperty(key);
		return "";
	}
	
	public final void setProperty(String x,String y){
		String[] ss=x.split(".");
		String name=ss[ss.length-1].toLowerCase();
		name=String.valueOf(name.charAt(0)+'A'-'a')+name.substring(1);
		Class<?> clazz=this.getClass();
		try {
			Method m=clazz.getMethod("set"+name,String.class);
			m.invoke(this,y);
		} catch (NoSuchMethodException | SecurityException e) {
			System.err.println(String.format("��ǰ��(%s)û�и�����(%s)!",clazz.getSimpleName(),name));
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	
	public final void setUsername(String email){
		ini.setProperty("Username",email);
	}
	public final void setUserpassword(String password){
		ini.setProperty("Userpassword",password);
	}
	
	
	
}