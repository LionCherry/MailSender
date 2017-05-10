package model;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public abstract class MyProperties implements Serializable{
	private static final long serialVersionUID = 9178095284417880259L;
	
	protected final Properties ini;
	protected final Properties prop;
	
	public MyProperties(){
		this.ini=new Properties();
		this.prop=new Properties();
		ini.setProperty("Username","");
		ini.setProperty("Userpassword","");
	}
	public final List<String> getPropertyKeys(){
		List<String> res=new ArrayList<String>();
		for(Object o:ini.keySet()) res.add((String)o);
		for(Object o:prop.keySet()) res.add((String)o);
		return res;
	}
	public final Properties getPropertiesForSession(){
		return this.prop;
	}
	public final String getProperty(String key){
		if(ini.containsKey(key))
			return ini.getProperty(key);
		if(prop.containsKey(key))
			return prop.getProperty(key);
		return "";
	}
	
	public final void setProperty(String x,String y){
		String[] ss=x.split("\\.");
		if(ss.length==0) ss=new String[]{x};
		String name=ss[ss.length-1].toLowerCase();
		name=""+(char)(name.charAt(0)+'A'-'a')+name.substring(1);
		Class<?> clazz=this.getClass();
		try {
			Method m=clazz.getMethod("set"+name,String.class);
			m.invoke(this,y);
		} catch (NoSuchMethodException | SecurityException e) {
			System.err.println(String.format("当前类(%s)没有该属性(%s)!",clazz.getSimpleName(),name));
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
