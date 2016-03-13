import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.zip.CRC32;
import java.util.zip.Checksum;


public class Switch {
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayList Eframe() {
		Source S= new Source();
		ArrayList Eframe=new ArrayList();
		Eframe=S.frame();
		return Eframe;
	}
	@SuppressWarnings("rawtypes")
public boolean checksumCount(@SuppressWarnings("rawtypes") ArrayList framelist, String checksum2){
		StringBuilder sb = new StringBuilder();
		ArrayList temp=new ArrayList(framelist);
		
		temp.remove(temp.size()-1);
		temp.remove(0);
		temp.remove(0);
		temp.remove(3); //Made Some changes to original data to get wrong checksum
		for (Object s : temp)
		{
			sb.append(s);
			sb.append(" ");
		}
		String input = sb.toString();
		
		//System.out.println(input);

		byte bytes[] = input.getBytes();
		Checksum checksum = new CRC32();
		
		checksum.update(bytes, 0, bytes.length);
		
		long checksumValue = checksum.getValue();
		
		String h = Integer.toHexString((int) checksumValue);
			
		if(h.equals(checksum2))
		return true;
		else
		return false;
}
	
	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	public void lookUpTable(Object DestMAC){
		/*first we will create a table which consist of MAC address and associated port numbers*/
		Map map = new HashMap<>(); //map utility to crated table
		/* took some ramdom values and create the table*/
		map.put("co-a8-00-01-08-bc", "2040");
		map.put("co-ed-1a-02-20-0d", "2060");
		map.put("co-6d-2a-04-30-b0", "2070");
		map.put("co-b7-3a-07-45-ab", "2080");
		map.put("co-b9-4a-07-44-10", "2090");
		map.put("co-a5-5a-10-23-5a", "2010");
		map.put("co-ef-6a-13-23-9a", "2020");
		map.put("co-ab-7a-14-40-ba", "2030");
		map.put("co-oa-8a-17-25-10", "2000");
		map.put("co-bd-0a-01-10-0b", "2050");
		Set keys = map.keySet();
		// Loop over String keys.
		for (Object key : keys) {
			if (map.containsKey(DestMAC)) //will find the MAC entry in the table
			    System.out.println(map.get(DestMAC));// if found will print associated port number
				break;
		    }
		
		
	}
	
	@SuppressWarnings({ "rawtypes" })
	public static void main(String[] args) {
		
		Switch Sw=new Switch();
		ArrayList framelist=new ArrayList();
		framelist=Sw.Eframe();
		String checksum=(String) framelist.get(6);
		/* Status whether checksum is correct or wrong*/
		boolean status=Sw.checksumCount(framelist,checksum);
		
		if(status==true){
			Sw.lookUpTable(framelist.get(3)); //if status is true which procceed to lookup
		}
		else // if status is false then generate error
			System.out.println("The frame is Corrupted!!!");
			System.out.println("The frame will be discarded!!!");
	}



}
