import java.io.IOException;
import java.util.Hashtable;

public class NCLEXDriver {
	Hashtable<String, Student> hash;
	ExcelParsing excelParsing;
	public NCLEXDriver() throws IOException{
		excelParsing = new ExcelParsing();
		hash = excelParsing.parse();
	}
	
	
	public static void main(String[] args) throws IOException{
		new NCLEXDriver();
		
	}

}
