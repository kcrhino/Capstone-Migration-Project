	import org.apache.pdfbox.pdmodel.PDDocument;
	import org.apache.pdfbox.pdmodel.PDDocumentInformation;

	import java.io.File;
	import java.io.IOException;
	import java.text.DateFormat;
	import java.util.Calendar;

	import org.apache.xmpbox.xml.XmpParsingException;
	
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.SQLException;
	import java.util.Properties;

	public final class ExtractMetadata {
		ExtractMetadata()
	    {
	        // utility class
	    }
             static final String dbClassName = "com.mysql.cj.jdbc.Driver";
             static final String CONNECTION = "jdbc:mysql://127.0.0.1/sqlpdf";

	    /**
	     * This is the main method.
	     *
	     * @param args The command line arguments.
	     *
	     * @throws IOException If there is an error parsing the document.
	     * @throws XmpParsingException
	     */
	    public static void main(String[] args) throws IOException, XmpParsingException, ClassNotFoundException,SQLException
	    {
	    	System.out.println("Start pdf Extraction");
				// As of right now, I just enter in the file by hand
	            try (PDDocument document = PDDocument.load(new File("")))
	            {
	    	    	
	                	
	                    PDDocumentInformation information = document.getDocumentInformation();
	                    if (information != null)
	                    {
	                        showDocumentInformation(information);
	              
	            //TEST DB
	            System.out.println(dbClassName);
	            // Class.forName(xxx) loads the jdbc classes and
	            // creates a drivermanager class factory
	            Class.forName(dbClassName);
	            
	  		  PreparedStatement prepareStatement = null;
	  		  String Title = information.getTitle();
	  		  String Author = information.getAuthor();
	  		  //I placed the Abstract in "subject" because SQL complains about it
	  		  String KeyWords = information.getKeywords();
	  		  String Abstract = information.getSubject();
	          
	  		  
	          System.out.println("Inputting Credentials");
	          Properties p = new Properties();
	    	  p.put("user","Your User Name");
	    	  p.put("password","Your Password");

	    	    // Now try to connect
	    	    System.out.println("Connecting");
	    	    Connection conn = DriverManager.getConnection(CONNECTION,p);
	    	    System.out.println("Connected");
	    	     //Pass information through here with information.getinfo 
	    	    System.out.println("Inserting records!");
				//For JDBC you can choose each ? for each argument and then use (n, variable) for each ? used
				//Also, you might want to consider following your schema with the order of prepared statements
	    	    prepareStatement = conn.prepareStatement("Insert INTO pdfData VALUES (?, ?, ?, ?)");
	    	    prepareStatement.setString(1, Title);
	    	    prepareStatement.setString(2, Author);
	    	    prepareStatement.setString(3, KeyWords);
	    	    prepareStatement.setString(4, Abstract);
	    	    
	    	   prepareStatement.executeUpdate();
	    	    System.out.println("It worked !");
	    	    conn.close();
	                  }
	                }
	            }
	    
	        

	     static void showDocumentInformation(PDDocumentInformation information)
	    {
	    	System.out.println("Start Basic PDF Data");
	    	//For some reason, keywords comethrough in page count
	        display("Key Words=", information.getKeywords() );
	        display("Page creation date=", information.getCreationDate() );
	        display("Title:", information.getTitle());
	        display("Subject:", information.getSubject()); //<- Not used, but something to add later, important slot for any additional information
	        display("Author:", information.getAuthor());
	    }

		//Idea for later to consider when you want to time stamp the date of transfer
	    private static String format(Object o)
	    {
	        if (o instanceof Calendar)
	        {
	            Calendar cal = (Calendar) o;
	            return DateFormat.getDateInstance().format(cal.getTime());
	        }
	        else
	        {
	            return o.toString();
	        }
	    }
		
	    private static void display(String title, Object value)
	    {
	        if (value != null)
	        {
	            System.out.println(title + " " + format(value));
	        }
	    }
	    
	}
