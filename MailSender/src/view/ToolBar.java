package view;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.MessagingException;
import javax.swing.*;

import control.Controller;
import main.Helper;
import model.Mail;

public final class ToolBar extends Content {
	private static final long serialVersionUID = -800095694161787037L;
	
	
	public ToolBar(){
		super();
		this.setLayout(new GridBagLayout());
		this.show=true;
		this.setup();
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
		int i=0,y=0,w,W=6,lef=1,j=0;
		if(this.getShow()){
			//============================================================================
			cs[i]=new JLabel("============="+Helper.Name+" "+Helper.Version+"=============");
			((JLabel)cs[i]).setFont(new Font("����",Font.PLAIN,18));
			panels[i]=Helper.createComponent(this,cs[i],lef,y,W,1,false);
			i++;y++;
			//============================================================================
			cs[i]=new JLabel("��������");
			panels[i]=Helper.createComponent(this,cs[i],lef,y,w = 6,1,false);
			i++;y++;
			//============================================================================
			{
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
			}
			if(statu==Statu.connected){
				//�ʼ�����
				int J=j+1;
				//---------------------------
				cs[i]=new JLabel("�ռ���");
				panels[i]=Helper.createComponent(this,cs[i],lef,y,w = 6,1,false);
				i++;y++;
				cs[i]=new JTextField(20);
				panels[i]=Helper.createComponent(this,cs[i],lef,y,w = 6,1,false);
				cis[++j]=i;
				i++;y++;
				//---------------------------
				cs[i]=new JLabel("����");
				panels[i]=Helper.createComponent(this,cs[i],lef,y,w = 6,1,false);
				i++;y++;
				cs[i]=new JTextField(20);
				panels[i]=Helper.createComponent(this,cs[i],lef,y,w = 6,1,false);
				cis[++j]=i;
				i++;y++;
				//---------------------------
				cs[i]=new JLabel("����");
				panels[i]=Helper.createComponent(this,cs[i],lef,y,w = 6,1,false);
				i++;y++;
				cs[i]=new JTextField(20);
				panels[i]=Helper.createComponent(this,cs[i],w,y,w = 6,1,false);
				cis[++j]=i;
				i++;y++;
				//---------------------------
				cs[i]=new JLabel("����");
				panels[i]=Helper.createComponent(this,cs[i],lef,y,w = 6,1,false);
				i++;y++;
				cs[i]=new JTextArea(10,20);
				panels[i]=Helper.createComponent(this,cs[i],w,y,w = 6,1,false);
				cis[++j]=i;
				i++;y++;
				//---------------------------
				cs[i]=new JLabel("����");
				panels[i]=Helper.createComponent(this,cs[i],lef,y,w = 6,1,false);
				i++;y++;
				cs[i]=new JTextArea(5,20);
				panels[i]=Helper.createComponent(this,cs[i],w,y,w = 6,1,false);
				cis[++j]=i;
				i++;y++;
				//---------------------------
				cs[i]=new JLabel(">> >> ���� >> >>");
				panels[i]=Helper.createComponent(this,cs[i],lef,y,w = 6,1,false);
				panels[i].addMouseListener(new Listener(){
					public void mouseReleased(MouseEvent e){
						Controller con=ToolBar.this.frame.controller;
						int J=con.getPropertyKeys().size()*2;
						Mail mail=con.createMail();
						//make the mail
						String receiver=((JTextField)cs[cis[J+1]]).getText();
						String[] tmp=receiver.split(";");
						try {
							for(String t:tmp) mail.insertReceiver(RecipientType.TO,t);
							ToolBar.this.frame.debug("�����ռ��˳ɹ�");
						} catch (MessagingException ee) {
							ee.printStackTrace();
							ToolBar.this.frame.debug("�����ռ���ʧ��");
							return;
						}
						//send the mail
						try {
							con.sendMail(mail);
							ToolBar.this.frame.debug("���ͳɹ�");
						} catch (MessagingException ee) {
							ee.printStackTrace();
							ToolBar.this.frame.debug("����ʧ��");
							return;
						}
					}
				});
				i++;y++;
			}
			
		}
		//============================================================================
		//empty
		cs[i]=new JLabel("");
		panels[i]=Helper.createComponent(this,cs[i],lef,y,6,1,true);
		i++;y++;
		//============================================================================
		cs[i]=this.getShow()?
				new JLabel(">")
				:new JLabel("<<");
		panels[i]=Helper.createComponent(this,cs[i],0,0,lef,y,true);
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
