package app.view;

import java.awt.GridLayout;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

public class StatsFrame extends JFrame {

	public StatsFrame(String applicationTitle) {
        super(applicationTitle);
        
       
        DefaultPieDataset dataset1 = new DefaultPieDataset();
        dataset1.setValue("Male", 55);
        dataset1.setValue("Female", 45);
        
        JFreeChart chart1 = createChart(dataset1, "Gender");
        ChartPanel leftChartPanel = new ChartPanel(chart1);
        leftChartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
      
        DefaultPieDataset dataset2 = new DefaultPieDataset();
        dataset2.setValue("Mature", 50);
        dataset2.setValue("Immature", 20);
        dataset2.setValue("Untermined", 30);
        
        JFreeChart chart2 = createChart(dataset2, "Stage of life");
        ChartPanel centerChartPanel = new ChartPanel(chart2);
        centerChartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        
        DefaultPieDataset dataset3 = new DefaultPieDataset();
        dataset3.setValue("South Maui", 20);
        dataset3.setValue("Darwin Arch, Galapagos Islands", 10);
        dataset3.setValue("Wolf Island, Galapagos Islands", 10);
        dataset3.setValue("Chile", 10);
        dataset3.setValue("Boca Grande", 10);
        dataset3.setValue("Playa Millonario Baltra, Galapagos Islands", 10);
        dataset3.setValue("Gansbaai", 10);
        dataset3.setValue("Mosquera Island, Galapagos Islands", 10);
        dataset3.setValue("Algoa Bay", 10);
        
        JFreeChart chart3 = createChart(dataset3, "Tag location");
        ChartPanel rightChartPanel = new ChartPanel(chart3);
        rightChartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        
        this.setLayout(new GridLayout(3,0));
        this.add(leftChartPanel);
        this.add(centerChartPanel);
        this.add(rightChartPanel);
        this.pack();
        this.setVisible(false);
	}

    
  private JFreeChart createChart(PieDataset dataset, String title) {
        
        JFreeChart chart = ChartFactory.createPieChart3D(title, dataset, true, true, false);
        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        return chart;
        
    }
  
	public static void main(String[] args) {
		new StatsFrame("Stats");
	}

}