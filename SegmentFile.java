import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class SegmentFile {
	public static void segmentFile(File f) throws IOException {
	int partCounter = 1;  //Counter which will be incremented at each part
	int sizeOfFiles = 1024 * 5; //size of fragmented file is 5kb
	byte[] buffer = new byte[sizeOfFiles]; //buffer for temp storage of created segment

		try (BufferedInputStream bis = new BufferedInputStream(
				new FileInputStream(f))) {
			
		String name = f.getName(); // get the name of the file to be segmented

		int tmp = 0;
		while ((tmp = bis.read(buffer)) > 0) {
		// files are fragmented here and names are given
		File newFile = new File(f.getParent(), name + "."
					+ String.format("%03d", partCounter++));
		String S=newFile.getName();//give the name to newly created segment
		System.out.println(S);
		//save the newly created file
		try (FileOutputStream out = new FileOutputStream(newFile)) {
					out.write(buffer, 0, tmp);
				}
			}
		}
}
	
	public static void main(String[] args) throws IOException {
		//location of the original file
		segmentFile(new File("C:\\Users\\Maulik_Patel\\Desktop\\Answers.txt"));
		}
}
