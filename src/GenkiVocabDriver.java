import java.io.*;

import javax.swing.JOptionPane;

public class GenkiVocabDriver
{
	public static void main(String [] args) throws Throwable
	{
		try
		{
			Application app = new Application();
			app.run();
		}
		catch (Exception ex)
		{
			StringWriter error = new StringWriter();
			ex.printStackTrace(new PrintWriter(error));
			JOptionPane.showMessageDialog(null, "An error ocurred in the application. Please contact the developer for assistance.\n\n" + error.toString());
		}
		
		System.out.println("Done.");
	}
}
