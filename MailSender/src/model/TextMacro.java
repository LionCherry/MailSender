package model;

public interface TextMacro {

	static public final String columnSpacer="\t";
	static public final String rowSpacer="\n";
	
	static public final String leftSpacer="%";
	static public final String rightSpacer="%";
	
	static public final char[] columnLabels=new String(
			"ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
	
	/**
	 * 设置数据
	 */
	public void setData(String msg);
	/**
	 * 列数
	 */
	public int cols();
	/**
	 * 行数
	 */
	public int rows();
	
	/**
	 * 获取一行的数据(0~n-1)
	 */
	public String[] getData(int index);
	
	/**
	 * 处理字符串(0~n-1)
	 */
	public String solve(String msg,int index);
	
	
}
