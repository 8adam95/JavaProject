package app.controller.mapController;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

import org.openstreetmap.gui.jmapviewer.events.JMVCommandEvent;
import org.openstreetmap.gui.jmapviewer.interfaces.JMapViewerEventListener;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;

import api.jaws.Location;
import app.view.mapFrame.MapPanel;

public class MapController extends MouseAdapter implements JMapViewerEventListener , ActionListener, ItemListener{
	
	private MapPanel map;
	
	public MapController(MapPanel map){
		this.map = map;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "button"){
			 map.setDisplayToFitMapMarkers();
		}else if(e.getActionCommand() == "showMapMarker"){
			map.setMapMarkerVisible();
		}else if(e.getActionCommand() == "showTileGrid"){
			map.setTileGrid();
		}else if(e.getActionCommand() == "showZoomControls"){
			map.setZoomContolsVisible();
		}else if(e.getActionCommand() == "scrollWrapEnabled"){
			map.setScrollWrapEnabled();
		}
	}

	@Override
	public void processCommand(JMVCommandEvent command) {
	     if (command.getCommand().equals(JMVCommandEvent.COMMAND.ZOOM) ||
	             command.getCommand().equals(JMVCommandEvent.COMMAND.MOVE)) {
	         map.updateZoomParameters();
	     }
	 }
	
	@Override
    public void mouseMoved(MouseEvent e) {
        Point p = e.getPoint();
        MapMarker marker = map.mouseAtMarker(e.getPoint());
        if (marker != null) {
            map.setCursorHand(true, marker.getName());
        } else {
        	map.setCursorHand(false, null);
        }
    }
	
	@Override
    public void mouseClicked(MouseEvent e) {
   	 if (e.getButton() == MouseEvent.BUTTON1) {
   		 
   		 MapMarker mar = map.mouseAtMarker(e.getPoint());
   		 if( mar !=  null){
   			 System.out.println("MATCH!!!!");
	         System.out.println("OBJECT NAME: " + mar.getName());
	         JFrame sharkFrame = new JFrame("TEST " + mar.getName());
	         JLabel sharkName = new JLabel("TEST " + mar.getName());
	         sharkName.setFont(new Font("Verdana", Font.BOLD, 30));
	         sharkName.setForeground(Color.CYAN);
	         sharkFrame.add(sharkName, BorderLayout.CENTER);
	         sharkFrame.pack();
	         sharkFrame.setVisible(true);
   		 }else{
   			 System.out.println("NO MATCH");
   		 }
   		}
    }

	//public void addSharks(ArrayList<>)

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		map.setTileSource();
	}


	public void displayShark(String name, Location loc) {
		double lon = loc.getLongitude();
		double lat = loc.getLatitude();
		map.addShark(lat, lon, name);
	}
	
	
	

}
