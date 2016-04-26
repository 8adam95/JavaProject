package app.view.mapFrame;


import org.openstreetmap.gui.jmapviewer.*;
import org.openstreetmap.gui.jmapviewer.events.JMVCommandEvent;
import org.openstreetmap.gui.jmapviewer.interfaces.JMapViewerEventListener;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;
import org.openstreetmap.gui.jmapviewer.interfaces.TileSource;
import org.openstreetmap.gui.jmapviewer.tilesources.MapQuestOpenAerialTileSource;
import org.openstreetmap.gui.jmapviewer.tilesources.MapQuestOsmTileSource;
import org.openstreetmap.gui.jmapviewer.tilesources.OsmTileSource;

import app.controller.mapController.LandChecker;
import app.controller.mapController.MapController;
import net.infonode.gui.laf.InfoNodeLookAndFeel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

public class MapPanel extends JPanel implements JMapViewerEventListener{
	
	private final JMapViewerTree treeMap;

	private final JLabel zoomLabel;
	private final JLabel zoomValue;

	private final JLabel mperpLabelName;
	private final JLabel mperpLabelValue;
	 
	final Layer sharks;

	private JPanel panel;

	private JPanel panelTop;

	private JPanel panelBottom;

	private JPanel helpPanel;

	private JLabel helpLabel;

	private JButton button;

	private JComboBox<TileSource> tileSourceSelector;

	private JCheckBox showMapMarker;

	private JCheckBox showTileGrid;

	private JCheckBox showZoomControls;

	private JCheckBox scrollWrapEnabled;
	 
	 public MapPanel() {
	     super();
	     setSize(400, 400);

	     treeMap = new JMapViewerTree("SharkMap");
	     
	     // Listen to the map viewer for user operations so components will
	     // receive events and update
	     map().addJMVListener(this);
	 
	 
	     setLayout(new BorderLayout());
//	     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	     setExtendedState(JFrame.MAXIMIZED_BOTH);
	     panel = new JPanel(new BorderLayout());
	     panelTop = new JPanel();
	     panelBottom = new JPanel();
	     helpPanel = new JPanel();

	     mperpLabelName = new JLabel("Meters/Pixels: ");
	     mperpLabelValue = new JLabel(String.format("%s", map().getMeterPerPixel()));

	     zoomLabel = new JLabel("Zoom: ");
	     zoomValue = new JLabel(String.format("%s", map().getZoom()));
	     
	     add(panel, BorderLayout.NORTH);
	     add(helpPanel, BorderLayout.SOUTH);
	     panel.add(panelTop, BorderLayout.NORTH);
	     panel.add(panelBottom, BorderLayout.SOUTH);
	     helpLabel = new JLabel("Use right mouse button to move,\n "
	             + "left double click or mouse wheel to zoom.");
	     helpPanel.add(helpLabel);
	    
	     button = new JButton("setDisplayToFitMapMarkers");
	     button.setActionCommand("button");

	     
	     tileSourceSelector = new JComboBox<>(new TileSource[] {
	             new OsmTileSource.Mapnik(),
	             new MapQuestOsmTileSource(),
	             new MapQuestOpenAerialTileSource() });
	     tileSourceSelector.setActionCommand("titleSourceSelector");   
	     

	     map().setTileLoader(new OsmTileLoader(map()));
	     panelTop.add(tileSourceSelector);
	     
	     showMapMarker = new JCheckBox("Map markers visible");
	     showMapMarker.setActionCommand("showMapMarker");
	     showMapMarker.setSelected(map().getMapMarkersVisible());

	     panelBottom.add(showMapMarker);
	     
	     
	     showTileGrid = new JCheckBox("Tile grid visible");
	     showTileGrid.setActionCommand("showTileGrid");
	     showTileGrid.setSelected(map().isTileGridVisible());

	     panelBottom.add(showTileGrid);
	     
	     
	     showZoomControls = new JCheckBox("Show zoom controls");
	     showZoomControls.setActionCommand("showZoomControls");
	     showZoomControls.setSelected(map().getZoomControlsVisible());

	     
	     panelBottom.add(showZoomControls);
	     
	     scrollWrapEnabled = new JCheckBox("Scrollwrap enabled");
	     scrollWrapEnabled.setActionCommand("scrollWrapEnabled");
	     
	     panelBottom.add(scrollWrapEnabled);
	     panelBottom.add(button);

	     panelTop.add(zoomLabel);
	     panelTop.add(zoomValue);
	     panelTop.add(mperpLabelName);
	     panelTop.add(mperpLabelValue);

	     add(treeMap, BorderLayout.CENTER);
	 
	     
	     /**
	      * MARKERS ==========================================
	      */
	     
	     sharks = treeMap.addLayer("SHARKS");
	     
	     MapMarkerDot a = new MapMarkerDot( sharks , "Shark1" , 50 , -13 );
	     map().addMapMarker(a);
	     
	 
	 }
	 
	public void addController(MapController c){
		map().addJMVListener(c);
		
		map().addMouseListener(c);
		
		map().addMouseMotionListener(c);
		
		button.addActionListener(c);

		tileSourceSelector.addItemListener(c);

		showMapMarker.addActionListener(c);

		showTileGrid.addActionListener(c);
		
		scrollWrapEnabled.addActionListener(c);

		showZoomControls.addActionListener(c);
		
		
	}

	public void removeMapMarkers(){
		map().removeAllMapMarkers();
	}
	
	public void addShark(double lat, double lon , String name){
		MapMarkerDot dot = new MapMarkerDot( sharks , name , lat , lon );

		if(!LandChecker.isWater(lat, lon)){
	    	System.out.println("SHARKNADO!");
	    	dot.setBackColor(Color.RED);
	    	dot.setColor(Color.CYAN);
	    	dot.setName(name + "- SHARKNADO!");
	    }
		
		map().addMapMarker(dot);
	}
	
	
	

	 public MapMarker mouseAtMarker(Point p){
		 	int X = p.x;
			    int Y = p.y;
			 	List<MapMarker> ar = map().getMapMarkerList();
			    Iterator<MapMarker> i = ar.iterator();
			    while (i.hasNext()) {
			    	MapMarker m = i.next();
			    	
				        if(map().getMapPosition(m.getLat(), m.getLon()) != null && (m instanceof MapMarkerDot)){
			        	
			        	Point pos = map().getMapPosition(m.getLat(), m.getLon());
			            int centerX =  pos.x;
			            int centerY = pos.y;
			            // calculate the radius from the touch to the center of the dot
			            double radCircle  = Math.sqrt( (((centerX-X)*(centerX-X)) + (centerY-Y)*(centerY-Y)));
			            // if the radius is smaller then 23 (radius of a ball is 5), then it must be on the dot
			            if (radCircle < map().getRadius(m, pos)){
			            	return m;
			            }
			        }
			    }
				return null; 
		  }
		 
		 
		 
		 private JMapViewer map() {
		     return treeMap.getViewer();
		 }

		 private static Coordinate c(double lat, double lon) {
		     return new Coordinate(lat, lon);
		 }

		 /**
		  * @param args Main program arguments
		  */
		 public static void main(String[] args) {
			 
			 try {
//					UIManager.setLookAndFeel(new WebLookAndFeel());
					UIManager.setLookAndFeel(new InfoNodeLookAndFeel());
//					UIManager.setLookAndFeel(new WindowsLookAndFeel());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 
		    MapPanel map = new MapPanel();
		    map.setVisible(true);
		    map.addShark(52, 21, "Warszawa");
		 }

		 public void updateZoomParameters() {
		     if (mperpLabelValue != null)
		         mperpLabelValue.setText(String.format("%s", map().getMeterPerPixel()));
		     if (zoomValue != null)
		         zoomValue.setText(String.format("%s", map().getZoom()));
		 }
		 
		 public void setCursorHand(boolean cursorHand, String tooltip){
			 if (cursorHand) {
		            map().setCursor(new Cursor(Cursor.HAND_CURSOR));
		            map().setToolTipText(tooltip);
		        } else {
		        	map().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		        	map().setToolTipText(null);
		        }
			 
		 }
		 
		 public void setDisplayToFitMapMarkers(){
			 map().setDisplayToFitMapMarkers();
		 }
		 
		 public void setTileSource(){
			 map().setTileSource((TileSource) tileSourceSelector.getSelectedItem());
		 }
		 
		 
		 public void setMapMarkerVisible(){
			 map().setMapMarkerVisible(showMapMarker.isSelected());
		 }
		 
		 public void setTileGrid(){
			 map().setTileGridVisible(showTileGrid.isSelected());
		 }
		 
		 public void setZoomContolsVisible(){
			 map().setZoomContolsVisible(showZoomControls.isSelected());
		 }
		 
		 public void setScrollWrapEnabled(){
			 map().setScrollWrapEnabled(scrollWrapEnabled.isSelected());
		 }
		 
		 @Override
		 public void processCommand(JMVCommandEvent command) {
		     if (command.getCommand().equals(JMVCommandEvent.COMMAND.ZOOM) ||
		             command.getCommand().equals(JMVCommandEvent.COMMAND.MOVE)) {
		         updateZoomParameters();
		     }
		 }

}
