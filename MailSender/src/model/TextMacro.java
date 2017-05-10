package model;

public interface TextMacro {

	static public final String columnSpacer="\t";
	static public final String rowSpacer="\n";
	
	static public final String leftSpacer="%";
	static public final String rightSpacer="%";
	
	static public final char[] columnLabels=new String(
			"ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
	
	/**
	 * ��������
	 */
	public void setData(String msg);
	/**
	 * ����
	 */
	public int cols();
	/**
	 * ����
	 */
	public int rows();
	
	/**
	 * ��ȡһ�е�����(0~n-1)
	 */
	public String[] getData(int index);
	
	/**
	 * �����ַ���(0~n-1)
	 */
	public String solve(String msg,int index);
	
	
}
