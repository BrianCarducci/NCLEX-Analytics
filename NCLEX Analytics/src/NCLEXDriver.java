import java.io.IOException;
import java.util.Hashtable;

public class NCLEXDriver {
	Hashtable<String, Student> hash;
	ExcelParsing excelParsing;
	public NCLEXDriver() throws IOException{
		excelParsing = new ExcelParsing();
		hash = excelParsing.parse();
		for (String key : hash.keySet()) {
		    System.out.println(key + ":" + hash.get(key).getFirstName() + ", " + hash.get(key).getLastName() + ", " + hash.get(key).getStudentId());
		}
	}
	
	
	public static void main(String[] args) throws IOException{
		new NCLEXDriver();
		
	}

}
