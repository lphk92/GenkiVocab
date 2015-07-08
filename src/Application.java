import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class Application
{
    protected VocabFrame frame;
    protected DataHandler handler;
    protected ListManager listManager;
    protected File loadedFile;
    
    public Application() throws Throwable
    {
        this.handler = new DataHandler();
        this.handler.readData();
        
        this.listManager = new ListManager("Vocab List");

        this.frame = new VocabFrame(this.handler.getDataSources());
        this.frame.setQuestionDisplayType(VocabPanel.KANA_AND_KANJI);
        this.frame.setAnswerDisplayType(VocabPanel.ENGLISH);

        this.frame.getVocabToolBar().addSourceComboBoxListener(new SourceChangeListener());
        this.frame.getVocabToolBar().addNextButtonListener(new NextButtonListener());
        this.frame.getVocabToolBar().addShowButtonListener(new ShowButtonListener());
        this.frame.getVocabToolBar().addAddButtonListener(new AddButtonListener());

        this.frame.getVocabMenuBar().addLanguageGroupActionListener(new LanguageRadioButtonListener());
        this.frame.getVocabMenuBar().addKanaGroupActionListener(new KanaRadioButtonListener());
        this.frame.getVocabMenuBar().addFontGroupListener(new FontRadioButtonListener());
        this.frame.getVocabMenuBar().addUseListActionListener(new UseListCheckBoxListener());
        this.frame.getVocabMenuBar().addCreateListActionListener(new CreateListListener());
        this.frame.getVocabMenuBar().addSaveListActionListener(new SaveListListener());
        this.frame.getVocabMenuBar().addLoadListActionListener(new LoadListListener());

        this.updateVocabList();
        FontHandler.setFont(FontHandler.GOTHIC);
    }

    public void run()
    {
        this.frame.setVisible(true);
    }

    protected void updateVocabList()
    {
        this.frame.setVocabList(this.handler.getDataFromSource(this.frame.getVocabToolBar().getSelectedSource()));
    }

    private class SourceChangeListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            Application.this.updateVocabList();
        }
    }

    private class NextButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            Application.this.frame.generateVocabCard();
        }
    }
    
    private class AddButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            if (!Application.this.listManager.isVisible())
            {
                Application.this.listManager.setVisible(true);
            }
            Application.this.listManager.addVocabEntry(Application.this.frame.getJapanesePanel().getEntry());
        }
    }
    
    private class ShowButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            Application.this.frame.showAnswer();
        }
    }
    
    private class LanguageRadioButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            Application.this.frame.swapPanels();
            Application.this.frame.generateVocabCard();
        }
    }
    
    private class KanaRadioButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            Application.this.frame.getJapanesePanel().setDisplayType(Integer.parseInt(ae.getActionCommand()));
            Application.this.frame.generateVocabCard();
        }
    }
    
    private class FontRadioButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            int type = Integer.parseInt(ae.getActionCommand());
            FontHandler.setFont(type);
        }
    }
    
    private class UseListCheckBoxListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            boolean checked = Application.this.frame.getVocabMenuBar().isUseListChecked();
            
            Application.this.listManager.setVisible(!checked);
            Application.this.frame.enableListControls(!checked);
            
            if (checked)
            {
                Application.this.frame.setVocabList(Application.this.listManager.getEntries());
            }
            else
            {
                Application.this.updateVocabList();
            }
        }
    }
    
    private class CreateListListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            boolean createNewList = true;
            if (Application.this.listManager.isVisible())
            {
                createNewList =  JOptionPane.showConfirmDialog(null, "Are you sure you want to make a new list?", "Confirm New List", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
            }
            if (createNewList)
            {
                Application.this.listManager.clear();
                Application.this.listManager.setVisible(true);
            }
        }
    }
    
    private class SaveListListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            try
            {
                JFileChooser chooser;
                if (Application.this.loadedFile != null)
                {
                    chooser = new JFileChooser(Application.this.loadedFile);
                    chooser.setSelectedFile(loadedFile);
                }
                else
                {
                    chooser = new JFileChooser();
                    chooser.setSelectedFile(new File("list.genki"));
                }
                
                chooser.setFileFilter(new GenkiFileFilter());
                
                if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION);
                {
                    PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter(chooser.getSelectedFile())));

                    for (VocabEntry entry : Application.this.listManager.getEntries())
                    {
                        fout.println(entry.toString());
                    }

                    fout.flush();
                    fout.close();
                }
            }
            catch (IOException ex)
            {
                JOptionPane.showMessageDialog(null, "There was an error saving your list. Please contact the developer for assistance.");
            }
        }
    }

    private class LoadListListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            try
            {
                JFileChooser chooser = new JFileChooser();
                chooser.setFileFilter(new GenkiFileFilter());

                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION);
                {
                    Application.this.loadedFile = chooser.getSelectedFile();
                    BufferedReader fin = new BufferedReader(new FileReader(Application.this.loadedFile));
                    Application.this.listManager.clear();

                    while (fin.ready())
                    {
                        Application.this.listManager.addVocabEntry(VocabEntry.parseCSVLine(fin.readLine()));
                    }

                    Application.this.listManager.setVisible(true);
                }
            }
            catch (IOException ex)
            {
                JOptionPane.showMessageDialog(null, "There was an error saving your list. Please contact the developer for assistance.\n\n" + ex.getMessage());
            }
        }
    }
}
