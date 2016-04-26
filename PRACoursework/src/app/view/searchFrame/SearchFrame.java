package app.view.searchFrame;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import api.jaws.Jaws;
import api.jaws.Shark;
import app.controller.mapController.MapController;
import app.model.Administration;
import app.model.Cache;
import app.view.loginFrame.LoginController;
import app.view.mapFrame.MapPanel;




public class SearchFrame extends JFrame {
	
	private static final Dimension Screen = Toolkit.getDefaultToolkit().getScreenSize();
	private static final String HashMap = null;
	private SearchFrameController controller;
	private JMenuBar jmbMenu;
	private JMenu jmOptions;
	private JMenu jmUsers;
	private JPanel jpSharkData;
	private OptionDropDown opddTrackingRange;
	private OptionDropDown opddGender;
	private OptionDropDown opddStageLife;
	private OptionDropDown opddTagLocation;
	private JButton sharkOfTheDayButton;
	private JButton surpriseButton;
	private JButton statisticsButton;
	private JButton favouritesButton;
	private boolean built = false;
	
	
	public SearchFrame(){
		super();

	}
	
	public void setController(SearchFrameController controller){
		this.controller = controller;
		//System.out.println(controller);
		createWidgets();
		setVisible(false);
	}
	
	private void createWidgets(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
// ----------------------------Layout ----------------------------------------------------------
		int width = 1000;
		int height = 1000;
		
		setBounds((int)(Screen.width - width)/2, (int)(Screen.height -height)/2, width, height);
		
		Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		
		GridBagConstraints con = new GridBagConstraints();
		
		getContentPane().setLayout(new GridBagLayout());
		
		JPanel jpLeftSideBar = new JPanel();
		con.gridx = 0;
		con.gridy = 0;
		con.weightx = 0.2;
		con.weighty = 1;
		con.fill = GridBagConstraints.BOTH;
		
//		jpLeftSideBar.setBackground(Color.BLUE);
		jpLeftSideBar.setLayout(new BorderLayout());
		jpLeftSideBar.setBorder(BorderFactory.createMatteBorder(3, 2, 3, 10, new Color(233,233,233)));
		
		getContentPane().add(jpLeftSideBar, con);
		
//-----------------Adding elements-----------------------------------------
		
		jpSharkData = new JPanel();
//		jpSharkData.setBackground(Color.RED);
		//jpSharkData.setBorder(BorderFactory.createMatteBorder(3, 10, 3, 2, new Color(233,233,233)));
		//jpSharkData.setLayout(new BoxLayout(jpSharkData, BoxLayout.Y_AXIS));
		//JScrollPane jsScrollableSharkData = new JScrollPane(jpSharkData);
		
		con.gridx = 1;
		con.gridy = 0;
		con.weightx = 0.8;
		con.weighty = 1;
		con.fill = GridBagConstraints.BOTH;
		
		getContentPane().add(jpSharkData, con);
		
		
		JLabel acknowledgement = new JLabel("Thanks to Ocearch");
		jpLeftSideBar.add(acknowledgement, BorderLayout.SOUTH);
		
		
		JPanel jpOptionsPanel = new JPanel();
		
		GridLayout optionsGrid = new GridLayout(6,1);
//		optionsGrid.setVgap(20);//make this variable depending on size of window
		jpOptionsPanel.setLayout(optionsGrid);
		jpLeftSideBar.add(jpOptionsPanel, BorderLayout.NORTH);
		
//------------ Adding drop down menus ----------------------------------------------		
		 
		opddTrackingRange = new OptionDropDown("Tracking Range", controller);
		opddTrackingRange.addOption("All Activity");
		opddTrackingRange.addOption("Last 24 Hours");
		opddTrackingRange.addOption("Last Week");
		opddTrackingRange.addOption("Last Month");
		jpOptionsPanel.add(opddTrackingRange);
		
		opddGender = new OptionDropDown("Gender", controller);
		opddGender.addOption("All Genders");
		opddGender.addOption("Male");
		opddGender.addOption("Female");
		jpOptionsPanel.add(opddGender);
		opddGender.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		opddStageLife = new OptionDropDown("Stage of Life", controller);
		opddStageLife.addOption("All Stages of Life");
		opddStageLife.addOption("Mature");
		opddStageLife.addOption("Immature");
		opddStageLife.addOption("Unspecified");
		jpOptionsPanel.add(opddStageLife);
		
		opddTagLocation = new OptionDropDown("Tag Location", controller);
		opddTagLocation.addOption("All Tag Locations");
		controller.addTagLocations();
		jpOptionsPanel.add(opddTagLocation);
		
		sharkOfTheDayButton = new JButton("Shark Of The Day!");
        surpriseButton = new JButton("Surprise");
        statisticsButton = new JButton("Statistics");
        favouritesButton = new JButton("Favourites");
		
        
		//--------------------Adding buttons --------------------------------------------
        
        GridLayout buttonsGridLayout = new GridLayout(2, 2, 10, 10);
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(buttonsGridLayout);
        
		jpOptionsPanel.add(buttonsPanel);
        
		buttonsPanel.add(sharkOfTheDayButton);
		buttonsPanel.add(surpriseButton);
		buttonsPanel.add(statisticsButton);
		buttonsPanel.add(favouritesButton);
		
        sharkOfTheDayButton.addActionListener(controller);
        surpriseButton.addActionListener(controller);
        statisticsButton.addActionListener(controller);
        favouritesButton.addActionListener(controller);

		
		
		//------------------------------------Adding logo-----------------------------------
		
		try {
			BufferedImage logo;
			logo = ImageIO.read(new File("logo.png"));
			JLabel picLabel = new JLabel(new ImageIcon(logo));
			jpLeftSideBar.add(picLabel);
			
		} catch (IOException e) {
			System.out.println("File logo not found");
		}
	}
	
	public void setupMenu(){
		jmbMenu = new JMenuBar();
		jmOptions = new JMenu("Options");
		jmUsers = new JMenu("Users");
		
		JMenuItem user;
		user = jmUsers.add(new JMenuItem("Arthur"));
		
		jmbMenu.add(jmOptions);
		jmbMenu.add(jmUsers);
		
		ArrayList<String> users = controller.getUsers();
		
		System.out.println(users);
		
		HashMap<String, JMenuItem> userItems = new HashMap<>();
		
		for(int i = 0; i <users.size(); i++){
			userItems.put(users.get(i), new JMenuItem(users.get(i)));
			jmUsers.add(userItems.get(users.get(i)));
		}
		
		this.setJMenuBar(jmbMenu);
		
		refresh();
	}
	
	public void addMap(MapPanel map){
		System.out.println(map);
		jpSharkData.add(map);
		built = true;
		refresh();
	}

	public String[] getSelectedSearch(){
		String[] options = new String[4];
		options[0] = opddTrackingRange.getOption();
		options[1] = opddGender.getOption();
		options[2] = opddStageLife.getOption();
		options[3] = opddTagLocation.getOption();
		return options;
	}
	
	public boolean isBuilt(){
		return built;
	}
	
	public void addTagLocation(String tag){
		opddTagLocation.addOption(tag);
	}
	

	public void refresh(){
		getContentPane().revalidate();
		getContentPane().repaint();
	}
	
	public static void main(String[] args)
	{
	    
	    Jaws jaws = new Jaws("LCALruJFV1ApE4T5", "X2J8m3lOSDwik1bj");
        System.out.println(jaws.getLastUpdated());
        
        Cache cache = new Cache();
        cache.setJaws(jaws);
        cache.cacheData();
        Thread caching = new Thread(cache);
        caching.start();
        Shark shark = jaws.getShark("Jill");
        System.out.println(shark.getGender());
        
        Administration u = new Administration();
	    
	    SearchFrame search = new SearchFrame();
        SearchModel model = new SearchModel(jaws);      
        SearchFrameController controller = new SearchFrameController(search, u, model);
        search.setController(controller);
        search.setupMenu();
        
        MapPanel map = new MapPanel();
        MapController mapController = new MapController(map);
        map.addController(mapController);
        search.addMap(map);
        map.addShark(52, 21, "Warszawa");
        controller.setMapController(mapController);
	    
	}
}

