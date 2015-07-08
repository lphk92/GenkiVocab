import java.awt.event.ActionListener;
import javax.swing.*;

@SuppressWarnings("serial")
public class VocabMenuBar extends JMenuBar
{
	public static final String CREATE_LIST = "create";
	public static final String SAVE_LIST = "save";
	public static final String LOAD_LIST = "load";
	
	private JMenu displayMenu;
	private JRadioButton englishToJapaneseRadioButton;
	private JRadioButton japaneseToEnglishRadioButton;
	private JRadioButton kanaOnlyRadioButton;
	private JRadioButton kanjiOnlyRadioButton;
	private JRadioButton kanaAndKanjiRadioButton;
	private JRadioButton gothicFontRadioButton;
	private JRadioButton minchoFontRadioButton;
	
	private JMenu listMenu;
	private JCheckBoxMenuItem useListCheckBox;
	private JMenuItem createListMenuItem;
	private JMenuItem saveListMenuItem;
	private JMenuItem loadListMenuItem;
	
	public VocabMenuBar()
	{
		this.initComponents();
	}
	
	public boolean isUseListChecked()
	{
		return this.useListCheckBox.isSelected();
	}
	
	public void addLanguageGroupActionListener(ActionListener al)
	{
		this.englishToJapaneseRadioButton.addActionListener(al);
		this.japaneseToEnglishRadioButton.addActionListener(al);
	}
	
	public void addKanaGroupActionListener(ActionListener al)
	{
		this.kanaOnlyRadioButton.addActionListener(al);
		this.kanjiOnlyRadioButton.addActionListener(al);
		this.kanaAndKanjiRadioButton.addActionListener(al);
	}
	
	public void addFontGroupListener(ActionListener al)
	{
		this.gothicFontRadioButton.addActionListener(al);
		this.minchoFontRadioButton.addActionListener(al);
	}
	
	public void addUseListActionListener(ActionListener al)
	{
		this.useListCheckBox.addActionListener(al);
	}
	
	public void addCreateListActionListener(ActionListener al)
	{
		this.createListMenuItem.addActionListener(al);
	}
	
	public void addSaveListActionListener(ActionListener al)
	{
		this.saveListMenuItem.addActionListener(al);		
	}
	
	public void addLoadListActionListener(ActionListener al)
	{
		this.loadListMenuItem.addActionListener(al);		
	}
	
	public void enableListControls(boolean enabled)
	{
		this.loadListMenuItem.setEnabled(enabled);
		this.saveListMenuItem.setEnabled(enabled);
		this.createListMenuItem.setEnabled(enabled);
	}
	
	private void initComponents()
	{
		this.displayMenu = new JMenu("Display");
		
		this.englishToJapaneseRadioButton = new JRadioButton("English to Japanese");
		this.japaneseToEnglishRadioButton = new JRadioButton("Japanese to English");
		this.kanaOnlyRadioButton = new JRadioButton("Kana Only");
		this.kanjiOnlyRadioButton = new JRadioButton("Kanji Only");
		this.kanaAndKanjiRadioButton = new JRadioButton("Kana and Kanji");
		this.gothicFontRadioButton = new JRadioButton("Gothic Font");
		this.minchoFontRadioButton = new JRadioButton("Mincho Font");
		
		this.kanaOnlyRadioButton.setActionCommand(Integer.toString(VocabPanel.KANA));
		this.kanjiOnlyRadioButton.setActionCommand(Integer.toString(VocabPanel.KANJI));
		this.kanaAndKanjiRadioButton.setActionCommand(Integer.toString(VocabPanel.KANA_AND_KANJI));
		
		this.gothicFontRadioButton.setActionCommand(Integer.toString(FontHandler.GOTHIC));
		this.minchoFontRadioButton.setActionCommand(Integer.toString(FontHandler.MINCHO));

		ButtonGroup languageGroup = new ButtonGroup();
		languageGroup.add(this.englishToJapaneseRadioButton);
		languageGroup.add(this.japaneseToEnglishRadioButton);
		
		ButtonGroup kanaGroup = new ButtonGroup();
		kanaGroup.add(this.kanaOnlyRadioButton);
		kanaGroup.add(this.kanjiOnlyRadioButton);
		kanaGroup.add(this.kanaAndKanjiRadioButton);
		
		ButtonGroup fontGroup = new ButtonGroup();
		fontGroup.add(this.gothicFontRadioButton);
		fontGroup.add(this.minchoFontRadioButton);
		
		this.displayMenu.add(this.englishToJapaneseRadioButton);
		this.displayMenu.add(this.japaneseToEnglishRadioButton);
		this.displayMenu.add(new JSeparator());
		this.displayMenu.add(this.kanaOnlyRadioButton);
		this.displayMenu.add(this.kanjiOnlyRadioButton);
		this.displayMenu.add(this.kanaAndKanjiRadioButton);
		this.displayMenu.add(new JSeparator());
		this.displayMenu.add(this.gothicFontRadioButton);
		this.displayMenu.add(this.minchoFontRadioButton);
		
		this.japaneseToEnglishRadioButton.setSelected(true);
		this.kanaAndKanjiRadioButton.setSelected(true);
		this.gothicFontRadioButton.setSelected(true);
		
		this.listMenu = new JMenu("Lists");
		this.useListCheckBox = new JCheckBoxMenuItem("Use Current List");
		this.createListMenuItem = new JMenuItem("Create New List");
		this.saveListMenuItem = new JMenuItem("Save List");
		this.loadListMenuItem = new JMenuItem("Load List");
		
		this.createListMenuItem.setActionCommand(VocabMenuBar.CREATE_LIST);
		this.saveListMenuItem.setActionCommand(VocabMenuBar.SAVE_LIST);
		this.loadListMenuItem.setActionCommand(VocabMenuBar.LOAD_LIST);
		
		this.listMenu.add(this.useListCheckBox);
		this.listMenu.add(new JSeparator());
		this.listMenu.add(this.createListMenuItem);
		this.listMenu.add(this.saveListMenuItem);
		this.listMenu.add(this.loadListMenuItem);
		
		this.add(displayMenu);
		this.add(listMenu);
	}
}
