import java.util.*;
import java.util.zip.CRC32;
import java.util.zip.Checksum;
public class Source {
	

	
	public ArrayList<String> tcp(){
		ArrayList<String> tcp=new ArrayList<String>();
		tcp.add(0,"1"); //ACK=1
		tcp.add(1,"167");//SequenceNumber=167
		tcp.add(2,"0"); //AcknowledgeNumber=0
		return tcp;
		}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList<String> frame(){
		// will create an ethernet frame in form of array
		ArrayList<String> frame=new ArrayList<String>();
		frame.add(0,"aa aa aa aa aa aa aa");//Premeable
		frame.add(1,"ab");//SFD
		frame.add(2,"co-a8-00-01-08-bc");//Destiantion MAC address
		frame.add(3,"co-bd-0a-01-10-0b");// Source MAC address
		frame.add(4,"05 dc");//EtherType
		frame.add(5,"Actual Data which needs to be sent to Destination "
				+ "which is a fragment of a whole file");//Payload, 
		/*here actual fragmented data will be embedded, but sake of simplicity 
		I have put generel line*/
		// now we will calculate checksum value for the frame
		StringBuilder sb = new StringBuilder();
		ArrayList<String> temp= new ArrayList<String>(frame);//temporary add frame to another list
		temp.remove(0);//remove first two elements from temp list
		temp.remove(0);
		/* Beacause checksume is counted only over sourceMAC, destinationMAc, etherType
		  and Payload fiels, so we have to discard first two fields */
		for (Object s : temp)
		{
			sb.append(s); //will append those four fiels as one string
			sb.append(" ");
		}
		String input = sb.toString();
		byte bytes[] = input.getBytes(); // byte conversation for the string
		Checksum checksum = new CRC32(); //in-built java utility for CRC32 checksum calculation
		// update the current checksum with the specified array of bytes
		checksum.update(bytes, 0, bytes.length);
		// get the current checksum value
		int checksumValue = (int) checksum.getValue();
		//System.out.println("The checksum value is "+checksumValue);
		String h = Integer.toHexString(checksumValue); //Convert to hex
		//System.out.println("Hex value is " + h);
		frame.add(6,h); // add checksum to the frame
		return frame;
}
public static void main(String[] args) {
		Destination D=new Destination();
		
		Source EF=new Source();
		
		if(D.tcp1().equals("1")){
			EF.tcp().add(0,"0"); //now SYN=0
			EF.tcp().add(1,"167"); //SeqNum=167
			EF.tcp().add(2,"499");// AckNum=499
			System.out.println("Received Acknowlegdement Packet: SYN=0, ACK=1");
			System.out.println("Handshake Completed!!!");
		}
		
		
}}
