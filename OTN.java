import java.util.ArrayList;

import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.Frame;
public class OTN implements Runnable{
	static Frame Image;
	static byte[] sound;
	 static ArrayList data=new ArrayList();
public OTN(Frame image, byte[] sound) {
	this.Image=image;
	this.sound=sound;
	data.add(0,Image);
	data.add(1,sound);
	}
@Override
public void run() {
	CanvasFrame frame = new CanvasFrame("Copy");
	System.out.println("OTN");
	 while (frame.isVisible() && Image != null){
		 
		 try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}
		 frame.showImage(Image);
		 }
	 		
	 frame.dispose();
	    }

public static void main(String[] args){
	OTN O=new OTN(Image,sound);
	
}
}