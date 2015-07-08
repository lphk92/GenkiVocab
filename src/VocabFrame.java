import java.awt.*;
import java.util.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class VocabFrame extends JFrame
{
	private String[] sources;
	private VocabEntry[] vocab;
	
	private VocabMenuBar menuBar;
	private VocabToolBar toolBar;
	private VocabPanel questionPanel;
	private VocabPanel answerPanel;
	
	public VocabFrame(String[] sources)
	{
		super("Genki Vocab");
		
		this.sources = sources;
		Arrays.sort(this.sources, new LastNumberComparator());
		
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setSize(500, 300);
		this.setLayout(new BorderLayout());
		
		this.initComponents();
	}
	
	public VocabPanel getJapanesePanel()
	{
		if (this.questionPanel.getDisplayType() != VocabPanel.ENGLISH)
		{
			return this.questionPanel;
		}
		else if (this.answerPanel.getDisplayType() != VocabPanel.ENGLISH)
		{
			return this.answerPanel;
		}
		else
		{
			return null;	
		}
	}
	
	public void enableListControls(boolean enabled)
	{
		this.menuBar.enableListControls(enabled);
		this.toolBar.enableListControls(enabled);
	}
	
	public VocabMenuBar getVocabMenuBar()
	{
		return this.menuBar;
	}
	
	public VocabToolBar getVocabToolBar()
	{ 
		return this.toolBar; 
	}
	
	public void setAnswerDisplayType(int displayType)
	{
		this.answerPanel.setDisplayType(displayType);
	}
	
	public void setQuestionDisplayType(int displayType)
	{
		this.questionPanel.setDisplayType(displayType);
	}
	
	public void setVocabList(VocabEntry[] vocab)
	{
		this.vocab = vocab;
		this.generateVocabCard();
	}	
	
	public void generateVocabCard()
	{
		Random rand = new Random();
		
		VocabEntry nextEntry = this.vocab[rand.nextInt(this.vocab.length)];
		this.questionPanel.setEntry(nextEntry);
		this.answerPanel.setEntry(nextEntry);
		this.answerPanel.hideVocab();
	}
	
	public void showAnswer()
	{
		this.answerPanel.showVocab();
	}
	
	public void swapPanels()
	{
		int temp = this.questionPanel.getDisplayType();
		this.questionPanel.setDisplayType(this.answerPanel.getDisplayType());
		this.answerPanel.setDisplayType(temp);
	}
	
	private void initComponents()
	{		
		this.questionPanel = new VocabPanel();
		this.answerPanel = new VocabPanel();
		this.answerPanel.hideVocab();
		
		this.toolBar = new VocabToolBar(this.sources);
		this.menuBar = new VocabMenuBar();
		
		this.setJMenuBar(this.menuBar);
		this.add(this.toolBar, BorderLayout.NORTH);
		this.add(this.questionPanel, BorderLayout.CENTER);	
		this.add(this.answerPanel, BorderLayout.SOUTH);
	}
	
	private class LastNumberComparator implements Comparator<String>
	{
		public int compare(String str1, String str2)
		{
			int last1 = Integer.parseInt(str1.split(" ")[1]);
			int last2 = Integer.parseInt(str2.split(" ")[1]);
			
			return last1 - last2;
		}
	}
}
