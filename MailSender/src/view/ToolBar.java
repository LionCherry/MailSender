package view;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.MessagingException;
import javax.swing.*;

import control.Controller;
import main.Helper;
import model.Mail;
import model.TextMacro;

public final class ToolBar extends Content {
	private static final long serialVersionUID = -800095694161787037L;
	
	
	public ToolBar(){
		super();
		this.setLayout(new GridBagLayout());
		this.show=true;
	}
	
	private JPanel[] panels=new JPanel[50];
	private JComponent[] cs=new JComponent[50];
	private int[] cis=new int[50];
	private int imax=0;
	private boolean show;

	public void setShow(boolean show){
		this.setShowWithoutRefresh(show);
		this.refreshDeep();
	}
	protected void setShowWithoutRefresh(boolean show){
		this.show=show;
	}
	public boolean getShow(){
		return this.show;
	}

	@Override
    public void refreshDeep(){
    	this.clear();
    	this.setup();
    	this.refresh();
    }
    
	public void clear(){
		for (int i=0;i<imax;i++){
			if (panels[i]!=null){
				if (cs[i]!=null)
					panels[i].remove(cs[i]);
				this.remove(panels[i]);
			}
			cs[i]=panels[i]=null;
		}
	}
	
	enum Statu{
		wait,connected
	}
	private Statu statu=Statu.wait;

	public void setup(){
		int i=0,y=0,w,W=6,lef=0,j=0;
		if(this.getShow()){
			//============================================================================
			cs[i]=new JLabel("========================"+Helper.Name+" "+Helper.Version+"========================");
			((JLabel)cs[i]).setFont(new Font("����",Font.PLAIN,18));
			panels[i]=Helper.createComponent(this,cs[i],lef,y,W,1,false);
			i++;y++;
			//============================================================================
			cs[i]=new JLabel("��������");
			panels[i]=Helper.createComponent(this,cs[i],lef,y,w = 6,1,false);
			i++;y++;
			//============================================================================
			if(statu!=Statu.connected){
				java.util.List<String> keys=this.frame.controller.getPropertyKeys();
				for(String key:keys){
					cs[i]=new JLabel(key);
					panels[i]=Helper.createComponent(this,cs[i],lef,y,w = 3,1,false);
					cis[++j]=i;
					i++;
					if(statu==Statu.connected){
						cs[i]=new JLabel(this.frame.controller.getProperty(key));
					}else{
						cs[i]=new JTextField(20);
						((JTextField)cs[i]).setText(this.frame.controller.getProperty(key));
					}
					panels[i]=Helper.createComponent(this,cs[i],w,y,W-w,1,false);
					cis[++j]=i;
					i++;y++;
				}
			}
			//connect��ť
			cs[i]=new JLabel(statu==Statu.connected?
					"<< disconnect >>"
					:">>> connect <<<");
			panels[i]=Helper.createComponent(this,cs[i],lef,y,w = 6,1,false);
			panels[i].addMouseListener(new Listener(){
				public void mouseReleased(MouseEvent e){
					//~~~~~~~~~~~~~~
					if(statu==Statu.connected){
						//�Ͽ�����
						try{
							ToolBar.this.frame.controller.close();
							ToolBar.this.frame.debug("�ɹ��Ͽ�����");
							statu=Statu.wait;
						}catch(MessagingException ee){
							ee.printStackTrace();
							ToolBar.this.frame.debug("�Ͽ�����ʧ��");
							return;
						}
					}else{
						//���ӵ�������
						for(int j=1;j<=ToolBar.this.frame.controller.getPropertyKeys().size();j++){
							String key=((JLabel)cs[cis[j*2-1]]).getText();
							String value=((JTextField)cs[cis[j*2]]).getText();
							ToolBar.this.frame.controller.setProperty(key,value);
							((JTextField)cs[cis[j*2]]).setText(ToolBar.this.frame.controller.getProperty(key));
						}
						try{
							ToolBar.this.frame.controller.saveProperty(Controller.propertyFileName);
							ToolBar.this.frame.debug("�����ļ�����ɹ�");
						}catch(IOException ee){
							ee.printStackTrace();
							ToolBar.this.frame.debug("�����ļ�����ʧ��");
							return;
						}
						try{
							ToolBar.this.frame.controller.connect();
							ToolBar.this.frame.debug("���ӳɹ�");
							statu=Statu.connected;
						}catch(MessagingException ee){
							ee.printStackTrace();
							ToolBar.this.frame.debug("����ʧ��");
							return;
						}
					}
					ToolBar.this.frame.refreshDeep();
				}
			});//*/
			i++;y++;
			if(statu==Statu.connected){
				//�ʼ�����
				//---------------------------
				cs[i]=new JLabel("�ռ���");
				panels[i]=Helper.createComponent(this,cs[i],lef,y,w = 1,1,false);
				i++;
				cs[i]=new JTextField(20);
				panels[i]=Helper.createComponent(this,cs[i],w,y,W-w,1,false);
				cis[++j]=i;
				i++;y++;
				//---------------------------
				cs[i]=new JLabel("����");
				panels[i]=Helper.createComponent(this,cs[i],lef,y,w = 1,1,false);
				i++;
				cs[i]=new JTextField(20);
				panels[i]=Helper.createComponent(this,cs[i],w,y,W-w,1,false);
				cis[++j]=i;
				i++;y++;
				//---------------------------
				cs[i]=new JLabel("����");
				panels[i]=Helper.createComponent(this,cs[i],lef,y,w = 1,1,false);
				i++;
				cs[i]=new JTextField(20);
				panels[i]=Helper.createComponent(this,cs[i],w,y,W-w,1,false);
				cis[++j]=i;
				i++;y++;
				//---------------------------
				cs[i]=new JLabel("����");
				panels[i]=Helper.createComponent(this,cs[i],lef,y,w = 1,1,false);
				i++;
				cs[i]=new JTextField(20);
				panels[i]=Helper.createComponent(this,cs[i],w,y,W-w,1,false);
				cis[++j]=i;
				i++;y++;
				//---------------------------
				cs[i]=new JLabel("����");
				panels[i]=Helper.createComponent(this,cs[i],lef,y,w = 6,1,false);
				i++;y++;
				cs[i]=new JTextArea(10,48);
				((JTextArea)cs[i]).setLineWrap(true);
				((JTextArea)cs[i]).setWrapStyleWord(true);
				panels[i]=Helper.createComponent(this,cs[i],lef,y,w = 6,1,true);
				cis[++j]=i;
				i++;y++;
				//---------------------------
				cs[i]=new JLabel("����");
				panels[i]=Helper.createComponent(this,cs[i],lef,y,w = 6,1,false);
				i++;y++;
				cs[i]=new JTextArea(4,48);
				((JTextArea)cs[i]).setLineWrap(true);
				((JTextArea)cs[i]).setWrapStyleWord(true);
				panels[i]=Helper.createComponent(this,cs[i],lef,y,w = 6,1,false);
				cis[++j]=i;
				i++;y++;
				//---------------------------
				cs[i]=new JLabel("�ı��꣨��Tab����ÿ�У�");
				panels[i]=Helper.createComponent(this,cs[i],lef,y,w = 6,1,false);
				i++;y++;
				cs[i]=new JTextArea(6,48);
				((JTextArea)cs[i]).setLineWrap(true);
				((JTextArea)cs[i]).setWrapStyleWord(true);
				panels[i]=Helper.createComponent(this,cs[i],lef,y,w = 6,1,false);
				cis[++j]=i;
				i++;y++;
				//---------------------------
				cs[i]=new JLabel(">> >> ���� >> >>");
				panels[i]=Helper.createComponent(this,cs[i],lef,y,w = 6,1,false);
				panels[i].addMouseListener(new Listener(){
					public void mouseReleased(MouseEvent e){
						int J=0;//con.getPropertyKeys().size()*2;
						Controller con=ToolBar.this.frame.controller;
						TextMacro macro=con.getTextMacro();
						macro.setData(((JTextArea)cs[cis[J+7]]).getText());
						int success=0;
						for(int i=0;i<macro.rows();i++){
							try {
								con.close();
								con.connect();
								ToolBar.this.frame.debug("�������ӳɹ�");
							} catch (MessagingException e1) {
								e1.printStackTrace();
								ToolBar.this.frame.debug("��������ʧ��");
							}
							ToolBar.this.frame.debug("��ʼ����(%d/%d)",i+1,macro.rows());
							Mail mail;
							try {
								mail = con.createMail();
							} catch (UnsupportedEncodingException | MessagingException ee) {
								ToolBar.this.frame.debug("����ķ���������");
								ee.printStackTrace();
								continue;
							}
							//make the mail
							//�ռ���
							String receiver=((JTextField)cs[cis[J+1]]).getText();
							receiver=macro.solve(receiver,i);
							String[] tmp=receiver.split(";");
							try {
								for(String t:tmp) mail.insertReceiver(RecipientType.TO,t);
							//	ToolBar.this.frame.debug("�����ռ��˳ɹ�");
							} catch (MessagingException ee) {
								ee.printStackTrace();
								ToolBar.this.frame.debug("�����ռ���ʧ��");
								continue;
							} catch (UnsupportedEncodingException ee) {
								ee.printStackTrace();
								ToolBar.this.frame.debug("�ռ��˵�ַ����");
								continue;
							}
							//����
							String receiver_cs=((JTextField)cs[cis[J+2]]).getText();
							receiver_cs=macro.solve(receiver_cs,i);
							tmp=receiver_cs.split(";");
							try {
								for(String t:tmp) mail.insertReceiver(RecipientType.CC,t);
							//	ToolBar.this.frame.debug("���볭����Ա�ɹ�");
							} catch (MessagingException ee) {
								ee.printStackTrace();
								ToolBar.this.frame.debug("���볭����Աʧ��");
								continue;
							} catch (UnsupportedEncodingException ee) {
								ee.printStackTrace();
								ToolBar.this.frame.debug("������Ա��ַ����");
								continue;
							}
							//����
							String receiver_ms=((JTextField)cs[cis[J+3]]).getText();
							receiver_ms=macro.solve(receiver_ms,i);
							tmp=receiver_ms.split(";");
							try {
								for(String t:tmp) mail.insertReceiver(RecipientType.BCC,t);
							//	ToolBar.this.frame.debug("����������Ա�ɹ�");
							} catch (MessagingException ee) {
								ee.printStackTrace();
								ToolBar.this.frame.debug("����������Աʧ��");
								continue;
							} catch (UnsupportedEncodingException ee) {
								ee.printStackTrace();
								ToolBar.this.frame.debug("������Ա��ַ����");
								continue;
							}
							//����
							String subject=((JTextField)cs[cis[J+4]]).getText();
							subject=macro.solve(subject,i);
							try {
								mail.setSubject(subject);
							//	ToolBar.this.frame.debug("�����ʼ�����ɹ�");
							} catch (MessagingException ee) {
								ee.printStackTrace();
								ToolBar.this.frame.debug("�����ʼ�����ʧ��");
								continue;
							}
							//����
							String content=((JTextArea)cs[cis[J+5]]).getText();
							content=macro.solve(content,i);
							try {
								mail.append(content);
							//	ToolBar.this.frame.debug("����ʼ����ݳɹ�");
							} catch (MessagingException ee) {
								ee.printStackTrace();
								ToolBar.this.frame.debug("����ʼ�����ʧ��");
								continue;
							}
							//����
							String files=((JTextArea)cs[cis[J+6]]).getText();
							files=macro.solve(files,i);
							tmp=files.split(";");
							try {
								for(String t:tmp){
									mail.insertFile(t);
									if(t!=null && t.length()>0)
										ToolBar.this.frame.debug(String.format("���븽��(%s)�ɹ�",t));
								}
							} catch (MessagingException ee) {
								ee.printStackTrace();
								ToolBar.this.frame.debug("���븽��ʧ��");
								continue;
							} catch (UnsupportedEncodingException ee) {
								ee.printStackTrace();
								ToolBar.this.frame.debug("������������");
								continue;
							}
							try {
								mail.saveChanges();
							//	ToolBar.this.frame.debug("�ʼ����ɳɹ�");
							} catch (MessagingException ee) {
								ee.printStackTrace();
								ToolBar.this.frame.debug("�ʼ�����ʧ��");
							}
							//send the mail
							try {
								con.sendMail(mail);
								ToolBar.this.frame.debug("���ͳɹ�");
							} catch (MessagingException ee) {
								ee.printStackTrace();
								ToolBar.this.frame.debug("����ʧ��");
								continue;
							}
							success++;
						}
						ToolBar.this.frame.debug("�ɹ�%d/%d��",success,macro.rows());
						if(success>=macro.rows()){
							((JTextField)cs[cis[J+1]]).setText("");
							((JTextField)cs[cis[J+2]]).setText("");
							((JTextField)cs[cis[J+3]]).setText("");
							((JTextField)cs[cis[J+4]]).setText("");
							((JTextArea)cs[cis[J+5]]).setText("���ͳɹ���");
							((JTextArea)cs[cis[J+6]]).setText("");
							((JTextArea)cs[cis[J+7]]).setText("");
						}
					}
				});
				i++;y++;
			}
			
		}
		//============================================================================
		//empty
	//	cs[i]=new JLabel("");
	//	panels[i]=Helper.createComponent(this,cs[i],lef,y,W,1,true);
	//	i++;y++;
		//============================================================================
		cs[i]=this.getShow()?
				new JLabel(">")
				:new JLabel("<<");
		panels[i]=Helper.createComponent(this,cs[i],W,0,1,y,true);
		panels[i].addMouseListener(new Listener(){
			public void mouseReleased(MouseEvent e){
				ToolBar.this.setShow(!ToolBar.this.getShow());
			}
		});
		//============================================================================
		//imax
		imax=i;
	}
	
	
	

}
