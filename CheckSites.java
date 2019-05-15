import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class CheckSites {

	public static void main(String[] args) {
		
		
		try {
						
			String line;
			int pos=-1;
			Properties prop = new Properties();
			String propFileName = System.getProperty("user.dir")+"/site.properties";
			InputStream iStream = new FileInputStream(propFileName);
			prop.load(iStream);
			if (iStream != null ) {
				String google = prop.getProperty("site.google");
				String mytest = prop.getProperty("site.mytest");
				String port = prop.getProperty("site.port");
				
				Process p = Runtime.getRuntime().exec("telnet "+ google + " "+ port );				
				BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
				while(( line = input.readLine()) != null ) {
					pos = line.indexOf("Connected");
					if (pos > -1) {
						break;
					}
				}
				input.close();
				if (pos > -1) {
					System.out.println("Check of site "+ google + " : Succed!");
				}
				else {
					System.out.println("Check of site "+ google + " : Failed!");
				}	
				
				pos=-1;
				p = Runtime.getRuntime().exec("telnet "+ mytest + " "+ port );				
				input = new BufferedReader(new InputStreamReader(p.getInputStream()));
				while(( line = input.readLine()) != null ) {
					pos = line.indexOf("Connected");
					if (pos > -1) {
						break;
					}
				}
				input.close();
				if (pos > -1) {
					System.out.println("Check of site "+ mytest + " : Succed!");
				}
				else {
					System.out.println("Check of site "+ mytest + " : Failed!");
				}	
				
				
			}
		}
		catch (Exception err) {
			err.printStackTrace();
		}
		
		
		
	}

}

