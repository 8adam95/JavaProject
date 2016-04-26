package app.view.searchFrame;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class OptionDropDown extends JPanel {
private String title;
private JComboBox dropSelect;
private boolean createComboBox = true;
private SearchFrameController controller;

	public OptionDropDown(String title, SearchFrameController controller){
		this.title = title;
		createWidgets();
		this.controller = controller;
		addActionListener();
	}
	
	public OptionDropDown(String title, boolean createComboBox){
		this.title = title;
		this.createComboBox = createComboBox;
		createWidgets();
	}
	
	private void createWidgets(){
		
		 
		 this.setBorder(BorderFactory.createEmptyBorder());
		 
		 
		 this.setLayout(new GridBagLayout());
		 
		 GridBagConstraints con = new GridBagConstraints();
		 
		 JLabel jlTitle = new JLabel(title);
		 
		 con.fill = GridBagConstraints.HORIZONTAL;
		 con.insets = new Insets(4,10,4,10);
		 con.gridx = 0;
		 con.gridy = 0;
		 con.weightx = 1;
		 
		 add(jlTitle, con);
		 
		 if (createComboBox) {
			dropSelect = new JComboBox();
			
			con.fill = GridBagConstraints.HORIZONTAL;
			con.gridx = 0;
			con.gridy = 1;
			con.weightx = 1;
			add(dropSelect, con);
		}

		 
		 
		 
		 JSeparator jpSeperator = new JSeparator(SwingConstants.HORIZONTAL);
		 con.fill = GridBagConstraints.HORIZONTAL;
		 con.gridx = 0;
		 con.gridy = 2;
		 con.weightx = 1;
		 add(jpSeperator, con);
		 /*
		 Dimension minSize = new Dimension(3, 3);
		 Dimension prefSize = new Dimension(7, 7);
		 Dimension maxSize = new Dimension(10, 10);
		 
		 jpSeperator.add(new Box.Filler(minSize, prefSize, maxSize));
		 jpSeperator.add(new JSeparator(SwingConstants.HORIZONTAL));*/
		 
	}
	
	public void addOption(String text){
		dropSelect.addItem(text);		
	}
	
	public void addTemp(){
		for(int i = 0; i < 6; i++){
			dropSelect.addItem("Test" + i);
		}
	}
	
	public void addActionListener(){
		dropSelect.addActionListener(controller);
	}

	public String getOption(){
		return (String) dropSelect.getSelectedItem();
	}
}
