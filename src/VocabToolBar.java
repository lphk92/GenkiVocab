import javax.swing.*;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.*;

@SuppressWarnings("serial")
public class VocabToolBar extends JToolBar
{
	private JComboBox sourceComboBox;
	private JButton nextButton;
	private JButton showButton;
	private JButton addButton;
	
	public VocabToolBar(Object[] sources)
	{
		this.initComponents(sources);
	}
	
	public void enableListControls(boolean enabled)
	{
		this.addButton.setEnabled(enabled);
		this.sourceComboBox.setEnabled(enabled);
	}
	
	public void addSourceComboBoxListener(ActionListener al)
	{
		this.sourceComboBox.addActionListener(al);
	}
	
	public void addNextButtonListener(ActionListener al)
	{
		this.nextButton.addActionListener(al);
	}
	
	public void addShowButtonListener(ActionListener al)
	{
		this.showButton.addActionListener(al);
	}
	
	public void addAddButtonListener(ActionListener al)
	{
		this.addButton.addActionListener(al);
	}
	
	private void initComponents(Object[] sources)
	{
		FlowLayout layout = new FlowLayout();
		layout.setHgap(10);
		layout.setAlignment(FlowLayout.LEFT);
		
		this.setBackground(new Color(0, 0, 0, 0));
		this.setFloatable(false);
		this.setLayout(layout);
		
		this.sourceComboBox = new JComboBox(sources);
		this.nextButton = new JButton("Next");
		this.showButton = new JButton("Show Answer");
		this.addButton = new JButton("Add To List");
		
		this.add(sourceComboBox);
		this.add(nextButton);
		this.add(showButton);
		this.add(addButton);
	}
	
	public String getSelectedSource() { return sourceComboBox.getSelectedItem().toString(); }
}
