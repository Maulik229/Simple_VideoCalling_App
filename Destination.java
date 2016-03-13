import java.util.ArrayList;


public class Destination{
	static ArrayList tcp1=new ArrayList();
	public String tcp1(){
		
		tcp1.add(0,"1");
		tcp1.add(1,"498");
		tcp1.add(2,"168");
		tcp1.add(3,"1");
		String s=(String) tcp1.get(3);
		return s;
		
	
}	 
	@SuppressWarnings({ "rawtypes", "unchecked" })
public static void main(String[] args){
		Source S =new Source();
		Destination D=new Destination();
		ArrayList temp=new ArrayList();
		temp=S.tcp();
		if(temp.get(0)=="1"){
			temp.add(0,"1");
			temp.add(1,"498");
			temp.add(2,"168");
			temp.add(3,"1");
			System.out.println("TCP SYN=1 Received!!!");
			System.out.println("Sending ACK=1 ......");
			tcp1=temp;
			S.main(args);
		}
}}