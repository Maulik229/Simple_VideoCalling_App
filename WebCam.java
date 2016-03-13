
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.Port;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;

import org.bytedeco.javacv.*;
import java.io.ByteArrayOutputStream;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.List;

public class WebCam implements Runnable  {
	private static Frame Image;
	static byte[] array = new byte[10000000];
	
	public WebCam(Frame image){
		this.Image=image;
		}
	
	public void run() {
	System.out.println("Call Started!!!");
	    }
	
public static class Sound implements Runnable {
	AudioFormat format = new AudioFormat(8000.0f, 16, 1, true, true);
    TargetDataLine microphone;
    SourceDataLine speakers;
		public void run() {
			{
	        try {
	        	 
	        	 microphone = AudioSystem.getTargetDataLine(format);

	            byte[] data = new byte[microphone.getBufferSize()];
	           
	            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
	            microphone = (TargetDataLine) AudioSystem.getLine(info);
	            microphone.open(format);

	            ByteArrayOutputStream out = new ByteArrayOutputStream();
	            int numBytesRead;
	            int CHUNK_SIZE = 2048;
	           // byte[] data = new byte[microphone.getBufferSize()];
	            microphone.start();

	            int bytesRead = 0;
	            DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, format);
	            speakers = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
	            speakers.open(format);
	            speakers.start();
	            while (bytesRead < 1000000) {
	                numBytesRead = microphone.read(data, 0, CHUNK_SIZE);
	                bytesRead += numBytesRead;
	                // write the mic data to a stream for use later
	                out.write(data, 0, numBytesRead); 
	                array=data.clone();
	                // write mic data to stream for immediate playback
	                speakers.write(data, 0, numBytesRead);
	            }
	            speakers.drain();
	            speakers.close();
	            microphone.close();
	        } catch (LineUnavailableException e) {
	            e.printStackTrace();
	        }
		}
			
		} 
	}
    
	public static void main(String[] args)  throws Exception  {
		//Sound s=new Sound();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Thread thread1 =new Thread(new WebCam(Image));
		thread1.setName("T1");
		Thread thread2 =new Thread(new Sound());
		thread1.setName("T2");
		
    	Thread thread=new Thread(new H323(Image,array));
    	thread.setName("T3");
		FrameGrabber grabber = FrameGrabber.createDefault(0);
        grabber.start();
        Frame grabbedImage = grabber.grab();
        CanvasFrame frame = new CanvasFrame("WebCam", CanvasFrame.getDefaultGamma() / grabber.getGamma());
       while (frame.isVisible() && (grabbedImage = grabber.grab()) != null) {
        	Image=grabbedImage;
        	frame.showImage(grabbedImage);
        	H323 h323=new H323(Image,array);

        	//System.out.println(thread1.isAlive());
        	while(thread1.isAlive()==false&&thread2.isAlive()==false){
        		thread1.start();
        		thread2.start();
            	thread.start();
        	}
        	  } frame.dispose();
        grabber.stop();
        
       
    }
		
	
}

