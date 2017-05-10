package model;

import java.util.*;

public class MyTextMacro implements TextMacro {

	private ArrayList<String[]> data=
			new ArrayList<String[]>();


	@Override
	public void setData(String msg) {
		data.clear();
		String[] tmp=msg.split(TextMacro.rowSpacer);
		for(String s:tmp){
			if(s!=null && s.length()>0){
				String[] tmp2=s.split(TextMacro.columnSpacer);
				data.add(tmp2);
			}
		}
	}
	
	@Override
	public int cols() {
		if(data.size()==0) return 1;
		return data.get(0).length;
	}

	@Override
	public int rows() {
		return data.size();
	}

	@Override
	public String[] getData(int index) {
		if(index>=this.rows())
			return new String[]{};
		return data.get(index);
	}

	@Override
	public String solve(String msg, int index) {
		if(index>=this.rows())
			return msg;
		String res=msg;
		int m=this.cols();
		for(int i=0;i<TextMacro.columnLabels.length&&i<m;i++){
			String spacer=TextMacro.leftSpacer
					+TextMacro.columnLabels[i]
					+TextMacro.rightSpacer;
			String y=data.get(index)[i];
			res=res.replace(spacer,y);
		}
		return res;
	}

}
