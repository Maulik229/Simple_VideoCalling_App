import java.util.ArrayList;

import org.bytedeco.javacv.Frame;
@SuppressWarnings("unchecked")
public class Sonet implements Runnable {
	static Frame Image;
	static byte[] sound;
	 static ArrayList frame=new ArrayList();
	public Sonet(Frame image, byte[] sound){
		this.Image=image;
		this.sound=sound.clone();

frame.add(0,"A1");
frame.add(1,"A2");
frame.add(2,"J0/Z0");
frame.add(3,"B1");
frame.add(4,"E1");
frame.add(5,"F1");
frame.add(6,"D1");
frame.add(7,"D2");
frame.add(8,"D3"); //SECTION OVERHEAD
frame.add(9,"H1");
frame.add(10,"H2");
frame.add(11,"H3");
frame.add(12,"B2");
frame.add(13,"K1");
frame.add(14,"K2");
frame.add(15,"D4");
frame.add(16,"D5");
frame.add(17,"D6");
frame.add(18,"D7");
frame.add(19,"D8");
frame.add(20,"D9");
frame.add(21,"D10");
frame.add(22,"D11");
frame.add(23,"D12");
frame.add(24,"S1/Z1");
frame.add(25,"M0 or M1/Z1");
frame.add(26,"E2");//LINE OVERHEAD
frame.add(27,"J1");
frame.add(28,"B3");
frame.add(29,"C2");
frame.add(30,"G1");
frame.add(31,"F2");
frame.add(32,"H4");
frame.add(33,"Z3");
frame.add(34,"Z4");
frame.add(35,"Z5");//PATH OVERHEAD
frame.add(36,"VT2");


	@SuppressWarnings("rawtypes")
	ArrayList Vt=new ArrayList();
	Vt.add(0,image);
	Vt.add(1,sound);
}
	@Override
	public void run() {
		OTN O=new OTN(Image,sound);
		Thread thread=new Thread(new OTN(Image,sound));
		thread.setName("T5");
		thread.start();
		while(thread.isAlive()==false)
		{Image=Image;sound=sound;}
			System.out.println("Sonet");
	}
	public static void main(String[] args){
		Sonet S=new Sonet(Image, sound);
		Thread thread=new Thread(new Sonet(Image,sound));
		
	}
	
}