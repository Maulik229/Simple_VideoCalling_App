import java.util.ArrayList;

import org.bytedeco.javacv.Frame;
public class H323 implements Runnable {
	static Frame Image;
	static byte[] sound;
	static ArrayList packet=new ArrayList();
@SuppressWarnings({ "rawtypes", "unchecked" })
public H323(Frame image, byte[] array) {
		this.Image=image;
		this.sound=array.clone();
		packet.add(0,"2");
		packet.add(1,"Padding"); 
		packet.add(2,"X"); 
		packet.add(3,"CSCR Count"); 
		packet.add(4,"Marker");
		packet.add(5,"Payload Type");
		packet.add(6,"0000"); 
		packet.add(7,"TimeStamp"); 
		packet.add(8,"SSRC"); 
		packet.add(9,"CSRC"); 
		packet.add(10,"HeaderExtension"); 
}
@Override
public void run() {
	System.out.println("H323");
	Sonet S=new Sonet(Image,sound);
	Thread thread=new Thread(new Sonet(Image,sound));
	thread.setName("T4");
	thread.start();
	while(thread.isAlive()){
	Image=Image;sound=sound;}
	
}
public static void main(String[] args){
	H323 h=new H323(Image, sound);
	Thread thread=new Thread(new H323(Image,sound));
	thread.setName("T3");
}
}


