import java.io.*;
import java.util.*;

public class DataHandler
{
	private File folder;
	private final String dataFileLocation = "/vocab/files.dat";
	private HashMap<String, ArrayList<VocabEntry>> data;
	
	public DataHandler()
	{
		data = new HashMap<String, ArrayList<VocabEntry>>();
	}
	
	public DataHandler(String directoryPath) throws IOException
	{
		this();
		this.setSourceDirectory(directoryPath);		
	}
	
	public String[] getDataSources() 
	{ 
		return this.data.keySet().toArray(new String[this.data.keySet().size()]); 
	}
	
	public VocabEntry[] getDataFromSource(String source) 
	{ 
		return this.data.get(source).toArray(new VocabEntry[this.data.get(source).size()]); 
	}
	
	public void setSourceDirectory(String directoryPath) throws IOException
	{
		this.folder = new File(directoryPath);
		
		if (!this.folder.isDirectory())
		{
			throw new IOException("Given directory path \"" + directoryPath + "\" is not a valid directory.");
		}
	}
	
	public void readData() throws IOException
	{			
		ArrayList<VocabEntry> vocab = new ArrayList<VocabEntry>();
		
		InputStream fstream = this.getClass().getResourceAsStream(this.dataFileLocation);
		BufferedReader reader = new BufferedReader(new InputStreamReader(fstream));		
		
		while (reader.ready())
		{
			String line = reader.readLine();
			System.out.println(">>> Reading - " + line);
			BufferedReader fin = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(line), "UTF8"));
			
			while (fin.ready())
			{
				vocab.add(VocabEntry.parseCSVLine(fin.readLine()));
			}
			
			this.data.put(this.formatFileName(line.substring(line.lastIndexOf("Genki"))), vocab);
		}
		
		// Code for native file reading (not in JAR)
		/*for (File f : folder.listFiles(new CSVFilter()))
		{			
			System.out.println("Reading --- " + f);
			BufferedReader fin = new BufferedReader(new FileReader(f));
			
			while(fin.ready())
			{
				vocab.add(VocabEntry.parseCSVLine(fin.readLine()));
			}
			
			this.data.put(this.formatFileName(f.getName()), vocab);
		}*/
	}
	
	private String formatFileName(String fileName)
	{
		return "Genki " + fileName.replaceAll("Genki", "").replaceAll("\\.csv", "");
	}
	
	/*private class CSVFilter implements FileFilter
	{
		public boolean accept(File pathname)
		{
			String extension = pathname.getPath().substring(pathname.getPath().length() - 3);
			if (extension.equals("csv"))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
	}*/
}
