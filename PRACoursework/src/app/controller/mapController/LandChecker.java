package app.controller.mapController;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class LandChecker extends JPanel{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage image;
    final int threshold = 50;

    public LandChecker(URL address) {
		try {
		    image = ImageIO.read(address);
		} catch (IOException e) {
		}    	
    }
    
    public boolean isBlack(int x, int y){
    	if(image == null){
    		return false;
    	}
    	int colour = image.getRGB(x, y);

    	int  red = (colour & 0x00ff0000) >> 16;
    	int  green = (colour & 0x0000ff00) >> 8;
    	int  blue = colour & 0x000000ff;
    	

    	if(red < threshold && green < threshold && blue < threshold ){
    		return true;
    	}
    	return false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null); // see javadoc for more info on the parameters            
    }
    
    public static boolean isWater(double lat, double lon){
    	LandChecker img = null;
		try {
			img = new LandChecker(new URL(prepareCoords(lat, lon)));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(img != null){
			int count = 0;
			for(int i=1; i<=7;i++){
				for(int j=1; j<=7;j++){
					if(img.isBlack(i, j)){
						count++;
					}
				}
			}
			if(count > 24){
				return true;
			}else{
				return false;
			}
			
		}else{
			System.out.println("failed to load image, propably offline");
			return false;
		}
	}
    
    
    private static String prepareCoords(double lat, double lon){
		String s =	"http://maps.googleapis.com/maps/api/staticmap"
				+ "?center=" + lat + "," + lon
				+ "&size=7x7"
				+ "&zoom=32"
				+ "&style=element:labels|visibility:off"
				+ "&style=element:geometry.stroke|visibility:off"
				+ "&style=feature:landscape|element:geometry|saturation:-100"
				+ "&scale=2"
				+ "&style=feature:water|saturation:-100|invert_lightness:true";
			return s;
	}
    
    

}
