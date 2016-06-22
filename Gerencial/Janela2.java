package Operacional;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.BorderLayout;
import java.awt.Color;   
import java.awt.Container;
import java.awt.Dimension;   
import java.text.DecimalFormat;   
import java.text.SimpleDateFormat;   
import java.util.List;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;   
import org.jfree.chart.*;   
import org.jfree.chart.axis.DateAxis;   
import org.jfree.chart.axis.DateTickMarkPosition;   
import org.jfree.chart.labels.StandardXYToolTipGenerator;   
import org.jfree.chart.plot.PlotOrientation;   
import org.jfree.chart.plot.XYPlot;   
import org.jfree.chart.renderer.xy.XYItemRenderer;   
import org.jfree.chart.title.TextTitle;   
import org.jfree.data.time.*;   
import org.jfree.data.xy.IntervalXYDataset;   
import org.jfree.ui.ApplicationFrame;   
import org.jfree.ui.RefineryUtilities;   
   
public class Janela2 extends JPanel   
{   
   
    
    /**  
     *   
     */   
    private ChartPanel chartpanel;
    private static final long serialVersionUID = 1L;   
    static Class class$org$jfree$data$time$Year; /* synthetic field */   
    private JFreeChart jfreechart;
    
    public Janela2()   
    {   
        super();   
        IntervalXYDataset intervalxydataset = createDataset();   
        jfreechart = createChart(intervalxydataset);   
        chartpanel = new ChartPanel(jfreechart);   
        chartpanel.setPreferredSize(new Dimension(500, 300));   
        setLayout(new BorderLayout());
        add(chartpanel, BorderLayout.NORTH);
    }   
   
    private static JFreeChart createChart(IntervalXYDataset intervalxydataset)   
    {   
        JFreeChart jfreechart = ChartFactory.createXYBarChart("Dados Parquimetro", "Mes", true, "Dados", intervalxydataset, PlotOrientation.VERTICAL, true, false, false);           jfreechart.setBackgroundPaint(Color.white);   
        XYPlot xyplot = jfreechart.getXYPlot();   
        XYItemRenderer xyitemrenderer = xyplot.getRenderer();   
        StandardXYToolTipGenerator standardxytooltipgenerator = new StandardXYToolTipGenerator("{1} = {2}", new SimpleDateFormat("yyyy"), new DecimalFormat("0"));   
        //xyitemrenderer.setToolTipGenerator(standardxytooltipgenerator);   
        xyplot.setBackgroundPaint(Color.lightGray);   
        xyplot.setRangeGridlinePaint(Color.white);   
        DateAxis dateaxis = (DateAxis)xyplot.getDomainAxis();   
        dateaxis.setTickMarkPosition(DateTickMarkPosition.MIDDLE);   
        dateaxis.setLowerMargin(0.01D);   
        dateaxis.setUpperMargin(0.01D);   
        return jfreechart;   
    }   
    
    private String[] splitMes(String s) {
        String aux="";
        String[] aux2=new String[3];
        int cont=0;
        for(int i=0; i<s.length(); i++) {
            if(cont==0) {
                if(s.charAt(i)=='-') {
                    aux2[cont]=aux;
                    cont++;
                    aux="";
                } else {
                    aux=aux+s.charAt(i);
                }
            } else if(cont==1) {
                if(s.charAt(i)==':') {
                    aux2[cont]=aux;
                    cont++;
                    aux="";
                } else {
                    aux=aux+s.charAt(i);
                }
            } else {
                if(s.charAt(i)!=' ') aux=aux+s.charAt(i);
            }
        }
        aux2[2]=aux;
        return aux2;
    }
    
    private String[] splitAno(String s) {
        String aux="";
        String[] aux2=new String[2];
        int cont=0;
        for(int i=0; i<s.length(); i++) {
            if(cont==0) {
                if(s.charAt(i)==':') {
                    aux2[cont]=aux;
                    cont++;
                    aux="";
                } else {
                    aux=aux+s.charAt(i);
                }
            } else {
                if(s.charAt(i)!=' ') aux=aux+s.charAt(i);
            }
        }
        aux2[1]=aux;
        return aux2;
    }
    
    private void update(IntervalXYDataset i) {
        //chartpanel.remove(this);
        IntervalXYDataset intervalxydataset = i;
        this.remove(chartpanel);
        jfreechart = createChart(i); 
        chartpanel = new ChartPanel(jfreechart);
        chartpanel.setPreferredSize(new Dimension(500, 300)); 
        this.add(chartpanel, BorderLayout.NORTH);
        this.revalidate();
        this.repaint();
    }
   
    public void updateDataSet(List<String> l, String d) {
        IntervalXYDataset intervalxydataset = updateDataset(l, d);
        System.out.println("AQUI2");
        update(intervalxydataset);
    }
    
    private IntervalXYDataset updateDataset(List<String> l, String d) {
        IntervalXYDataset i;
        TimeSeries timeseries = new TimeSeries("Recebimento", "Year", "Count");
        String[] aux;
        if(d.equals("ano")) {
            try {
                System.out.println("AQUI");
                for(String s:l) {
                    aux=splitAno(s);
                    System.out.println(aux[0]+" "+aux[1]);
                    timeseries.add(new Year(Integer.parseInt(aux[0])), Float.parseFloat(aux[1]));
                }
            } catch(Exception exception) {    
                System.err.println(exception.getMessage());   
            } 
        } else {
             
            try {
                System.out.println("AQUI");
                for(String s:l) {
                    aux=splitMes(s);
                    System.out.println(aux[0]+" "+aux[1]+" "+aux[2]);
                    timeseries.add(new Month(Integer.parseInt(aux[0]),Integer.parseInt(aux[1])), Float.parseFloat(aux[2]));
                }
            } catch(Exception exception) {    
                System.err.println(exception.getMessage());   
            }  
        }
        TimeSeriesCollection timeseriescollection = new TimeSeriesCollection(timeseries);
        return timeseriescollection;
    } 
    
    private static IntervalXYDataset createDataset()   
    {   
        TimeSeries timeseries = new TimeSeries("Recebimento", "Year", "Count");   
        try   
        {   
            timeseries.add(new Month(12, 1975), new Integer(1));   
            timeseries.add(new Month(11, 1975), new Integer(1));
            timeseries.add(new Month(10, 1975), new Integer(1));
            timeseries.add(new Month(9, 1975), new Integer(1));

        }   
        catch(Exception exception)   
        {   
            System.err.println(exception.getMessage());   
        }   
        TimeSeriesCollection timeseriescollection = new TimeSeriesCollection(timeseries);   
        //timeseriescollection.setDomainIsPointsInTime(false);   
        return timeseriescollection;   
    }   
   
    public static JPanel createDemoPanel()   
    {   
        return new ChartPanel(createChart(createDataset()));   
    }     
   
    static Class class$(String s)   
    {   
        try {   
            return Class.forName(s);   
        } catch (ClassNotFoundException e) {   
            // TODO Auto-generated catch block   
            e.printStackTrace();   
        }   
        ClassNotFoundException classnotfoundexception = null;   
        throw new NoClassDefFoundError(classnotfoundexception.getMessage());   
    }   



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
