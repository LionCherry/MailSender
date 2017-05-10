package main;

public class MailSender {
	static public void main(String[] args){
		System.out.println("start!");
		main.Debugger debugger=new main.Debugger();
		debugger.startShowingDebugMsg();
		model.MyProperties mprop=new model.SMTPProperties();
		model.TextMacro textMacro=new model.MyTextMacro();
		control.Controller controller=new control.MyController(mprop,textMacro);
		view.Frame frame=new view.Frame(debugger, controller);
		frame.setVisible(true);
		System.out.println("end!");
	}
}
