import java.awt.*;
import javax.swing.*;

import java.util.*;
import java.util.Map.Entry;

public class FontHandler
{
	public static final int GOTHIC = 1;
	public static final int MINCHO = 2;
	
	public static final int BIG_SIZE = 38;
	public static final int SMALL_SIZE = 18;
	
	private static final String GOTHIC_LOC = "/fonts/ttf-japanese-gothic.ttf";
	private static final String MINCHO_LOC = "/fonts/ttf-japanese-mincho.ttf";
	
	private static HashMap<JComponent, Integer> components;
	
	static
	{
		components = new HashMap<JComponent, Integer>();
	}
	
	public static void addComponent(JComponent component, int size)
	{
		components.put(component, size);
	}
	
	public static void setFont(int fontType)
	{
		Font font = new Font("Arial", Font.PLAIN, 18);
		if (fontType == FontHandler.GOTHIC)
		{
			font = FontHandler.getFont(FontHandler.GOTHIC_LOC);
		}
		else if (fontType == FontHandler.MINCHO)
		{
			font = FontHandler.getFont(FontHandler.MINCHO_LOC);
		}
		else
		{
			// TODO: Throw an exception
		}
		
		Iterator<Entry<JComponent, Integer>> iter = components.entrySet().iterator();
		while(iter.hasNext())
		{
			Entry<JComponent, Integer> entry = (Entry<JComponent, Integer>)iter.next();
			((JComponent)entry.getKey()).setFont(font.deriveFont(((Integer)entry.getValue()).floatValue()));
		}
	}
	
	private static Font getFont(String location)
	{		
		try
		{
			return Font.createFont(Font.TRUETYPE_FONT, FontHandler.class.getResourceAsStream(location));
		}
		catch (Exception ex)
		{
			//TODO: Handle font exception
			ex.printStackTrace();
			return new Font("Arial", Font.PLAIN, 18);
		}
	}
}
