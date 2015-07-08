import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class VocabPanel extends JPanel
{
	public static final int ENGLISH = 0;
	public static final int KANA = 1;
	public static final int KANJI = 2;
	public static final int KANA_AND_KANJI = 3;
	
	private int displayType;
	private VocabEntry entry;
	private JLabel label;
	
	public VocabPanel()
	{
		this(VocabPanel.ENGLISH);
	}
	
	public VocabPanel(int displayType)
	{
		this.displayType = displayType;		

		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		this.setLayout(new GridLayout(1, 1));	
		
		this.initComponents();
	}
	
	public int getDisplayType()
	{
		return this.displayType;
	}
	
	public void setDisplayType(int displayType)
	{
		this.displayType = displayType;
	}
	
	public VocabEntry getEntry()
	{
		return this.entry;
	}
	
	public void setEntry(VocabEntry newEntry)
	{
		if (newEntry != null)
		{
			this.entry = newEntry;
			switch(this.displayType)
			{
				case VocabPanel.ENGLISH: this.label.setText(entry.getEnglish());
					break;
					
				case VocabPanel.KANA: this.label.setText(entry.getKana());
					break;
					
				case VocabPanel.KANJI: this.label.setText(entry.getKanji().equals("") ? entry.getKana() : entry.getKanji());
					break;
					
				case VocabPanel.KANA_AND_KANJI: this.label.setText(entry.getKanji().equals("") ? entry.getKana() : entry.getKana() + "  (" + entry.getKanji() + ")");
					break;
					
				default: this.label = new JLabel("No Vocab Found");
			}
		}
	}
	
	public void showVocab()
	{
		this.label.setVisible(true);
	}
	
	public void hideVocab()
	{
		this.label.setVisible(false);
	}
	
	private void initComponents()
	{
		this.label = new JLabel();
		this.label.setHorizontalAlignment(JLabel.CENTER);
		this.label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		FontHandler.addComponent(this.label, FontHandler.BIG_SIZE);
		
		this.add(this.label);
	}
}
