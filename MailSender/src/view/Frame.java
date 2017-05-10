package view;

import java.awt.*;
import javax.swing.*;

import main.Debugger;

public final class Frame extends JFrame implements Refreshable{
	private static final long serialVersionUID = 2703663016877310106L;
	
	
	private	Panel panel;
		public void setPanelWithOutRefresh(Panel panel){
			this.getContentPane().add(BorderLayout.CENTER,
					(this.panel=panel).setFrame(this));
		}
	private	ToolBar toolbar;
	@SuppressWarnings("unused")
	private	Listener listener;
	private final Debugger debugger;
		public void debug(String msg){debugger.debug(msg);this.refresh();}
		public void debug(String format,Object... args){debugger.debug(format,args);this.refresh();}
		protected void drawDebug(Graphics g){if(this.panel!=null)this.debugger.draw(this.panel,g);}
	protected control.Controller controller;
	
	
	public Frame(Debugger debugger,control.Controller controller){
		//operation
		this.controller=controller;
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension scrSize=Toolkit.getDefaultToolkit().getScreenSize();
		Insets scrInsets=Toolkit.getDefaultToolkit().getScreenInsets(GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration());
		this.setBounds(scrInsets.left,scrInsets.top,scrSize.width*10/20,scrSize.height*9/10);
		//Panel&&ToolBar
		this.getContentPane().add(BorderLayout.CENTER,
				(this.panel=new Panel(){
					private static final long serialVersionUID = 2448507397202059204L;
					@Override protected void draw(Graphics g) {}
				}).setFrame(this));
		this.getContentPane().add(BorderLayout.WEST,
				(this.toolbar=new ToolBar()).setFrame(this));
		this.toolbar.setup();
		//listener
		(this.listener=new MyListener().addTo(this).addTo(this.panel).addTo(toolbar)).setFrame(this);
		//debugger
		this.debugger=debugger;
		new Thread(){
			public void run(){
				long dt=100;
				while(true){
					Frame.this.refresh(dt);
					try {
						Thread.sleep(dt);
					} catch (InterruptedException e) {
					}
				}
			}
		}.start();
	}

	@Override
	public void refresh(){
		if(this.panel!=null)
			this.panel.refresh();
		if(this.toolbar!=null)
			this.toolbar.refresh();
	}
	@Override
	public void refresh(double dt){
		if(this.panel!=null)
			this.panel.refresh(dt);
		if(this.toolbar!=null)
			this.toolbar.refresh(dt);
	}
	@Override
	public void refreshDeep() {
		if(this.panel!=null)
			this.panel.refreshDeep();
		if(this.toolbar!=null)
			this.toolbar.refreshDeep();
	}


	
	
}
