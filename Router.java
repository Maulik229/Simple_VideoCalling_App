import java.math.BigInteger;
import java.util.ArrayList;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

public class Router {
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
public ArrayList packet(String data){
	@SuppressWarnings("rawtypes")
	
	/* again we will create an IP packet in form of Array*/
	ArrayList packet=new ArrayList();
	packet.add(0,"4520"); // version, header length, type of service
	packet.add(1,"5c03"); // total lenght(header+data)
	packet.add(2,"0000"); //identification
	packet.add(3,"2000"); //flags, fragment offset
	packet.add(4,"0306"); //time to live, protocol
	packet.add(5,"0000"); // checksum, initially 0, will put after counting it
	packet.add(6,"728a82ef"); //192.168.01.11, source IP
	packet.add(7,"788103be"); //202.172.10.22, Destination IP

		Object s1 = packet.get(6);
		Object s2 = packet.get(7);
		/* here will split the IP addresses into two bytes for checksum
		 * calculation*/
	    final int mid = ((String) s1).length() / 2;// split in equal parts
	    String part1 = ((String) s1).substring(0, mid);
	    String part2 = ((String) s1).substring(mid, ((String) s1).length());
	    String part3 = ((String) s2).substring(0, mid);
	    String part4 = ((String) s2).substring(mid, ((String) s2).length());
	    
	    BigInteger binary = BigInteger.ZERO;
	    /* here we will convert all hex numbers into binary and add them*/
	     for(int i=0;i<=5;i++){
	    	 String bin=hexToBin((String) packet.get(i));
	    	 BigInteger number = new BigInteger(bin, 2);
	    	 binary=binary.add(number);
	    	
	     }
	     /*Special code for IP addresses addition as we have to split
	      * them first into two byes */
	     String bin1=hexToBin((String) part1);
    	 BigInteger number1 = new BigInteger(bin1, 2);
    	 binary=binary.add(number1);// addition
    	 String bin2=hexToBin((String) part2);
    	 BigInteger number2 = new BigInteger(bin2, 2);
    	 binary=binary.add(number2);// addition
    	 String bin3=hexToBin((String) part3);
    	 BigInteger number3 = new BigInteger(bin3, 2);
    	 binary=binary.add(number3);//addition
    	 String bin4=hexToBin((String) part4);
    	 BigInteger number4 = new BigInteger(bin4, 2);
    	 binary=binary.add(number4); //addition
    	 String checksum= binary.toString();
    	 //convert to hex
    	 String hex=Integer.toHexString(Integer.parseInt(checksum));
    	 packet.add(5,hex);
    	 packet.add(8,data);
    	 System.out.println(packet);
    	 return packet;
}
	@SuppressWarnings("unused")
	/* Routine for hex to binary conversation*/
	private String hexToBin(String hex){
	    String bin = "";
	    String fragment;
	    int index;
	   // hex = hex.trim();
	   for(int i = 0; i < hex.length(); i++){
	        index = Integer.parseInt(""+hex.charAt(i),16);
	        fragment = Integer.toBinaryString(index);

	        while(fragment.length() < 4){
	            fragment = "0" + fragment;
	        }
	        bin += fragment;
	    }
	    return bin;
	}
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		Router R=new Router();
		Switch Sw= new Switch();
		ArrayList Rframe=new ArrayList(Sw.Eframe());
		String data=(String) Rframe.get(5);
		R.packet(data);
	} 
}
