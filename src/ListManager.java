import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

@SuppressWarnings("serial")
public class ListManager extends JFrame
{	
	private JToolBar toolBar;
	private JButton deleteButton;
	private JList vocabList;
	private DefaultListModel vocabListModel;
	
	public ListManager(String name)
	{
		super(name);
		
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		this.setSize(400, 300);
		
		this.initComponents();
	}
	
	public void addVocabEntry(VocabEntry entry)
	{
		this.vocabListModel.addElement(entry);
	}
	
	public void clear()
	{
		this.vocabListModel.clear();
	}
	
	public boolean isClear()
	{
		return this.vocabListModel.size() == 0;
	}
	
	public VocabEntry[] getEntries()
	{
		ArrayList<VocabEntry> entries = new ArrayList<VocabEntry>();
		for (Object entry : this.vocabListModel.toArray())
		{
			entries.add((VocabEntry)entry);
		}
		return entries.toArray(new VocabEntry[entries.size()]);
	}
	
	private void initComponents()
	{		
		this.toolBar = new JToolBar();
		
		this.deleteButton = new JButton("Delete");
		this.deleteButton.addActionListener(new DeleteButtonListener());
		
		this.toolBar.add(this.deleteButton);
		this.toolBar.setFloatable(false);
		
		this.vocabListModel = new DefaultListModel();
		this.vocabList = new JList(vocabListModel);
		FontHandler.addComponent(this.vocabList, FontHandler.SMALL_SIZE);
		
		JScrollPane scrollPane = new JScrollPane(this.vocabList);
		scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		this.add(this.toolBar, BorderLayout.NORTH);
		this.add(scrollPane, BorderLayout.CENTER);
	}
	
	private class DeleteButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent ae)
		{
			System.out.println("Delete!");
			ArrayList<Integer> indices = new ArrayList<Integer>();
			for (int i : ListManager.this.vocabList.getSelectedIndices())
			{
				indices.add(i);
			}
			Collections.sort(indices, Collections.reverseOrder());
			for (int i : indices)
			{
				ListManager.this.vocabListModel.remove(i);
			}
		}
	}
}
