import java.io.File;
import javax.swing.filechooser.FileFilter;

public class GenkiFileFilter extends FileFilter
{
	public boolean accept(File pathname)
	{
		String extension = pathname.getPath().substring(pathname.getPath().length() - 5);
		if (extension.equals("genki") || pathname.isDirectory())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public String getDescription()
	{
		return "*.genki (Genki Vocab files)";
	}
}
