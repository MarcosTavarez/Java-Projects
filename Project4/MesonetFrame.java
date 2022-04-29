
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import javax.swing.border.MatteBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;


public class MesonetFrame extends JFrame
{
    
    /**
     * menuBar, sets up a Menu Bar for the Frame
     */
    JMenuBar menuBar = new JMenuBar();
    /**
     * Menu, gives menu to the menuBar
     */
    JMenu menu = new JMenu();
    /**
     * Gives items to select in the menu
     */
    JMenuItem openFile = new JMenuItem();
    /**
     * Another Item that 
     */
    JMenuItem exit = new JMenuItem();
    /**
     * Statistics panel
     */
    JPanel sPanel = new StatisticsPanel();
    /**
     * Parameter Panel
     */
    JPanel pPanel = new ParameterPanel();
    /**
     * Table Panel
     */
    TabelPanel tPanel = new TabelPanel();
    /**
     * Button Panel
     */
    JPanel bPanel = new JPanel();
    /**
     * Name of the Menu
     */
    JMenu menuName = new JMenu();
    /**
     * Calculate button that all of the calculations
     */
    JButton buttonCalculate = new JButton("Calculate");
    /**
     * Exits out of the GUI
     */
    JButton buttonExit = new JButton("Exit");
    /**
     * New constructor for for MapData
     */
    MapData mapD;
    /**
     * Title Name
     */
    JTextField txtMesonetHomie = new JTextField();
    /**
     * Statistics constructor
     */
    Statistics stats;
    /**
     * Table model that contains column data
     */
    DefaultTableModel tableModel = (DefaultTableModel) tPanel.table.getModel();
    /**
     * MesonetFrame constructor
     */
    public MesonetFrame(String title)
    {
        super(title);
        txtMesonetHomie.setText("MESONET HOMIE");
        txtMesonetHomie.setColumns(10);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(1000,1000);

        menu.setText("File");

        menuBar.add(menu);

        openFile.setText("Open data file");

        exit.setText("Exit");

        menu.add(openFile);

        menu.add(exit);

        setJMenuBar(menuBar);
        
        menuBar.add(txtMesonetHomie);

        getContentPane().add(sPanel,BorderLayout.EAST);
        sPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

     
        
        getContentPane().add(pPanel, BorderLayout.WEST);

        getContentPane().add(bPanel, BorderLayout.SOUTH);

        bPanel.add(buttonCalculate);
        bPanel.add(buttonExit);
       
        getContentPane().add(tPanel, BorderLayout.CENTER);
        
        tPanel.setLayout(new BorderLayout());
        tPanel.add(tPanel.table, BorderLayout.CENTER);
        tPanel.add(tPanel.table.getTableHeader(), BorderLayout.NORTH);
        tPanel.table.setModel(tPanel.tMod);
        
        /**
         * Calculates Maximum, Minimum, Average of TAIR, SRAD, PRES, WSPD 
         * 
         */
        buttonCalculate.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
               
                //////////MAX///////////////////////////////////////////////////////////////////////////////////////////
                  if(((StatisticsPanel) sPanel).isJRadioButton1Selected())
                  {
                      if(((ParameterPanel) pPanel).isSRADSelected())
                      {
                          stats = mapD.getStatistics(StatsType.MAXIMUM, "SRAD");
                          
                          Object[] row = new Object[6];
                          row[0] = stats.getStid();
                          row[1] = "SRAD";
                          row[2] = "MAXIMUM";
                          row[3] = stats.getValue();
                          row[4] = stats.getNumberOfReportingStations();
                          row[5] = stats.getUTCDateTimeString();
                          tPanel.tMod.addRow(row);
                      }
                      if(((ParameterPanel) pPanel).isTairSelected())
                      {
                          stats =  mapD.getStatistics(StatsType.MAXIMUM, "TAIR");
                          
                          Object[] row = new Object[6];
                          row[0] = stats.getStid();
                          row[1] = "TAIR";
                          row[2] = "MAXIMUM";
                          row[3] = stats.getValue();
                          row[4] = stats.getNumberOfReportingStations();
                          row[5] = stats.getUTCDateTimeString();
                          tPanel.tMod.addRow(row);
                      }
                      if(((ParameterPanel) pPanel).isTa9mSelected())
                      {
                          stats =  mapD.getStatistics(StatsType.MAXIMUM, "TA9M");
                          
                          Object[] row = new Object[6];
                          row[0] = stats.getStid();
                          row[1] = "TA9M";
                          row[2] = "MAXIMUM";
                          row[3] = stats.getValue();
                          row[4] = stats.getNumberOfReportingStations();
                          row[5] = stats.getUTCDateTimeString();
                          tPanel.tMod.addRow(row);
                      }
                      if(((ParameterPanel) pPanel).isPRESSelected())
                      {
                          stats =  mapD.getStatistics(StatsType.MAXIMUM, "PRES");
                          
                          Object[] row = new Object[6];
                          row[0] = stats.getStid();
                          row[1] = "PRES";
                          row[2] = "MAXIMUM";
                          row[3] = stats.getValue();
                          row[4] = stats.getNumberOfReportingStations();
                          row[5] = stats.getUTCDateTimeString();
                          tPanel.tMod.addRow(row);
                      }
                      if(((ParameterPanel) pPanel).isWSPDSelected())
                      {
                          stats =  mapD.getStatistics(StatsType.MAXIMUM, "WSPD");
                          
                          Object[] row = new Object[6];
                          row[0] = stats.getStid();
                          row[1] = "WSPD";
                          row[2] = "MAXIMUM";
                          row[3] = stats.getValue();
                          row[4] = stats.getNumberOfReportingStations();
                          row[5] = stats.getUTCDateTimeString();
                          tPanel.tMod.addRow(row);
                      }
                      
                  }
                    
                 //MIN///////////////////////////////////////////////////////////////////////////////////////////////////////////
               
                
                   if(((StatisticsPanel) sPanel).isJRadioButtonSelected())
                   {
                       if(((ParameterPanel) pPanel).isSRADSelected())
                       {
                           stats = mapD.getStatistics(StatsType.MINIMUM, "SRAD");
                           
                           Object[] row = new Object[6];
                           row[0] = stats.getStid();
                           row[1] = "SRAD";
                           row[2] = "MINIMUM";
                           row[3] = stats.getValue();
                           row[4] = stats.getNumberOfReportingStations();
                           row[5] = stats.getUTCDateTimeString();
                           tPanel.tMod.addRow(row);
                       }
                       if(((ParameterPanel) pPanel).isTairSelected())
                       {
                           stats =  mapD.getStatistics(StatsType.MINIMUM, "TAIR");
                           
                           Object[] row = new Object[6];
                           row[0] = stats.getStid();
                           row[1] = "TAIR";
                           row[2] = "MINIMUM";
                           row[3] = stats.getValue();
                           row[4] = stats.getNumberOfReportingStations();
                           row[5] = mapD.getStatistics(StatsType.MINIMUM, "TAIR").getUTCDateTimeString();
                           System.out.println(stats.getUTCDateTimeString());
                           tPanel.tMod.addRow(row);
                       }
                       if(((ParameterPanel) pPanel).isTa9mSelected())
                       {
                           stats =  mapD.getStatistics(StatsType.MINIMUM, "TA9M");
                           
                           Object[] row = new Object[6];
                           row[0] = stats.getStid();
                           row[1] = "TA9M";
                           row[2] = "MINIMUM";
                           row[3] = stats.getValue();
                           row[4] = stats.getNumberOfReportingStations();
                           row[5] = stats.getUTCDateTimeString();
                           tPanel.tMod.addRow(row);
                       }
                       if(((ParameterPanel) pPanel).isPRESSelected())
                       {
                           stats =  mapD.getStatistics(StatsType.MINIMUM, "PRES");
                           
                           Object[] row = new Object[6];
                           row[0] = stats.getStid();
                           row[1] = "PRES";
                           row[2] = "MINIMUM";
                           row[3] = stats.getValue();
                           row[4] = stats.getNumberOfReportingStations();
                           row[5] = stats.getUTCDateTimeString();
                           tPanel.tMod.addRow(row);
                       }
                       if(((ParameterPanel) pPanel).isWSPDSelected())
                       {
                           stats =  mapD.getStatistics(StatsType.MINIMUM, "WSPD");
                           
                           Object[] row = new Object[6];
                           row[0] = stats.getStid();
                           row[1] = "WSPD";
                           row[2] = "MINIMUM";
                           row[3] = stats.getValue();
                           row[4] = stats.getNumberOfReportingStations();
                           row[5] = stats.getUTCDateTimeString();
                           tPanel.tMod.addRow(row);
                       }
                       
                   }
                     
                 
                //Average//////////////////////////////////////////////////////////////////////////////////////////////////////
  
                 if(((StatisticsPanel) sPanel).isJRadioButton2Selected())
                 {
                     if(((ParameterPanel) pPanel).isSRADSelected())
                     {
                         stats = mapD.getStatistics(StatsType.AVERAGE, "SRAD");
                         
                         Object[] row = new Object[6];
                         row[0] = stats.getStid();
                         row[1] = "SRAD";
                         row[2] = "AVERAGE";
                         row[3] = stats.getValue();
                         row[4] = stats.getNumberOfReportingStations();
                         row[5] = stats.getUTCDateTimeString();
                         tPanel.tMod.addRow(row);
                     }
                     if(((ParameterPanel) pPanel).isTairSelected())
                     {
                         stats =  mapD.getStatistics(StatsType.AVERAGE, "TAIR");
                         
                         Object[] row = new Object[6];
                         row[0] = stats.getStid();
                         row[1] = "TAIR";
                         row[2] = "AVERAGE";
                         row[3] = stats.getValue();
                         row[4] = stats.getNumberOfReportingStations();
                         row[5] = stats.getUTCDateTimeString();
                         tPanel.tMod.addRow(row);
                     }
                     if(((ParameterPanel) pPanel).isTa9mSelected())
                     {
                         stats =  mapD.getStatistics(StatsType.AVERAGE, "TA9M");
                         
                         Object[] row = new Object[6];
                         row[0] = stats.getStid();
                         row[1] = "TA9M";
                         row[2] = "AVERAGE";
                         row[3] = stats.getValue();
                         row[4] = stats.getNumberOfReportingStations();
                         row[5] = stats.getUTCDateTimeString();
                         tPanel.tMod.addRow(row);
                     }
                     if(((ParameterPanel) pPanel).isPRESSelected())
                     {
                         stats =  mapD.getStatistics(StatsType.AVERAGE, "PRES");
                         
                         Object[] row = new Object[6];
                         row[0] = stats.getStid();
                         row[1] = "PRES";
                         row[2] = "AVERAGE";
                         row[3] = stats.getValue();
                         row[4] = stats.getNumberOfReportingStations();
                         row[5] = stats.getUTCDateTimeString();
                         tPanel.tMod.addRow(row);
                     }
                     if(((ParameterPanel) pPanel).isWSPDSelected())
                     {
                         stats =  mapD.getStatistics(StatsType.AVERAGE, "WSPD");
                         
                         Object[] row = new Object[6];
                         row[0] = stats.getStid();
                         row[1] = "WSPD";
                         row[2] = "AVERAGE";
                         row[3] = stats.getValue();
                         row[4] = stats.getNumberOfReportingStations();
                         row[5] = stats.getUTCDateTimeString();
                         tPanel.tMod.addRow(row);
                     }
                     
                 }
                 
            }
        });
        
        buttonExit.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);

            }
        });
        openFile.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {

                JFileChooser jFile = new JFileChooser("data");
                jFile.showOpenDialog(null);
                String file;


                file = jFile.getSelectedFile().getName();

                mapD = new MapData(file);
                mapD.parseFile();
           



            }
        });

        exit.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);

            }
        });


        setVisible(true);



    }


}
/**
 * Table class, contains all required aspects for the Table
 */
class TabelPanel extends JPanel
{
    
    DefaultTableModel tMod = new DefaultTableModel();
    JTable table = new JTable(tMod);
    
    
    FlowLayout layout = new FlowLayout();
   
    public TabelPanel()
    {
        super();
        
        
       tMod.addColumn("Station");
       tMod.addColumn("Parameter");
       tMod.addColumn("Statistics");
       tMod.addColumn("Value");
       tMod.addColumn("Reporting Stations");
       tMod.addColumn("Date");
        setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
        table.setModel(new DefaultTableModel());
        add(table);
        table.setVisible(true);
        
       
    }   
   
}
/**
 * Panel for Parameter that contains all required aspects
 */
class ParameterPanel extends JPanel
{
    FlowLayout layout = new FlowLayout();
    JCheckBox checkB = new JCheckBox();
    JCheckBox checkB1 = new JCheckBox();
    JCheckBox checkB2 = new JCheckBox();
    JCheckBox checkB3 = new JCheckBox();
    JCheckBox checkB4 = new JCheckBox();

    public ParameterPanel()
    {
        super();

        checkB.setText("TAIR");
        checkB1.setText("TA9M");
        checkB2.setText("SRAD");
        checkB3.setText("WSPD");
        checkB4.setText("PRES");
        setLayout(new GridLayout(5,1));
        add(checkB);
        add(checkB1);
        add(checkB2);
        add(checkB3);
        add(checkB4);

       
        setVisible(true);
    }
    /**
     * Returns TAIR button
     */
    public boolean isTairSelected()
    {
        return checkB.isSelected();
    }
    /**
     * Returns TA9M button
     */
    public boolean isTa9mSelected()
    {
        return checkB1.isSelected();
    }
    /**
     * Returns SRAD button
     */
    public boolean isSRADSelected()
    {
        return checkB2.isSelected();
    }
    /**
     * Returns WSPD button
     */
    public boolean isWSPDSelected()
    {
        return checkB3.isSelected();
    }
    /**
     * Return PRES button
     */
    public boolean isPRESSelected()
    {
        return checkB4.isSelected();
    }
}

/**
 * Statistics panels that contains aspects needed for panel
 */
class StatisticsPanel extends JPanel
{

    JRadioButton radioButton = new JRadioButton();
    JRadioButton radioButton1 = new JRadioButton();
    JRadioButton radioButton2 = new JRadioButton();
    ButtonGroup bTeam = new ButtonGroup();
    public StatisticsPanel() 
    {
        super();
        

        radioButton.setText("Minimum");

        radioButton1.setText("Maximum");

        radioButton2.setText("Average");
        
        bTeam.add(radioButton);
        bTeam.add(radioButton1);
        bTeam.add(radioButton2);
        
        setLayout(new GridLayout(3,1));
        add(radioButton);
        add(radioButton1);
        add(radioButton2);

       
        
        setVisible(true);

      

    }
    /**
     * Returns MINIMUM
     */
    public boolean isJRadioButtonSelected()
    {
        return radioButton.isSelected();
    }
    /**
     * Returns MAXIMUM
     */
    public boolean isJRadioButton1Selected()
    {
        return radioButton1.isSelected();
    }
    /**
     * Returns AVERAGE
     */
    public boolean isJRadioButton2Selected()
    {
        return radioButton2.isSelected();
    }

}