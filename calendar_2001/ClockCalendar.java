import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * A very simple GUI (graphical user interface) for the clock display.
 * In this implementation, time runs at about 3 minutes per second, so that
 * testing the display is a little quicker.
 * 
 * @author Michael Kölling and David J. Barnes
 * 
 * @version 2016.02.29
 */
public class ClockCalendar
{
    private JFrame frame;
    private JLabel labelHeader, labelClock, labelDayOfWeek;
    
    private ClockDisplay clock;
    private CalendarDisplay calendar;

    private int increment = 1;
    
    private boolean clockRunning = false;
    private TimerThread timerThread;
    
    /**
     * Constructor for objects of class ClockCalendar
     */
    public ClockCalendar()
    {
        clock = new ClockDisplay();
        calendar = new CalendarDisplay(0);
        makeFrame();
    }
    
    /**
     * 
     */
    private void start()
    {
        clockRunning = true;
        timerThread = new TimerThread();
        timerThread.start();
    }
    
    /**
     * 
     */
    private void stop()
    {
        clockRunning = false;
    }
    
    /**
     * 
     */
    private void step()
    {
        String tempString;
        String displayString;
        
        clock.timeTick();
        labelClock.setText(clock.getTime());  
    }
    
    /**
     * 
     */
    private void up()
    {      
        calendar.nextDay();
        labelDayOfWeek.setText(calendar.getDay());
    }
    
    /**
     * 
     */
    private void down()
    {
        calendar.prevDay();
        labelDayOfWeek.setText(calendar.getDay());
    }
    
    
    /**
     * 'About' function: show the 'about' box.
     */
    private void showAbout()
    {
        JOptionPane.showMessageDialog (frame, 
                    "Clock/Calendar Version 1.0\n" +
                    "A simple interface for Assignment 1",
                    "About Clock/Calendar", 
                    JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Quit function: quit the application.
     */
    private void quit()
    {
        System.exit(0);
    }

    
    /**
     * Create the Swing frame and its content.
     */
    private void makeFrame()
    {
        frame = new JFrame("Clock/Calendar");
        JPanel contentPane = (JPanel)frame.getContentPane();
        contentPane.setBorder(new EmptyBorder(1, 60, 1, 60));

        makeMenuBar(frame);
        
        // Specify the layout manager with nice spacing
        contentPane.setLayout(new BorderLayout(12, 12));
        
        // Create the header in the center
        JPanel headerPanel = new JPanel();
        labelHeader = new JLabel("Our Clock/Calendar", SwingConstants.CENTER);
        Font displayFontHeader = labelHeader.getFont().deriveFont(30.0f);
        labelHeader.setFont(displayFontHeader);
        headerPanel.add(labelHeader);
        contentPane.add(headerPanel, BorderLayout.NORTH);
        
        
        
        // Create the clock pane in the center
        
        JPanel timePanel = new JPanel();
        timePanel.setLayout(new GridLayout(2,0));
        
        //Create the Clock display
        JPanel clockPanel = new JPanel();
        labelClock = new JLabel("00:00", SwingConstants.CENTER);
        Font displayFontClock = labelClock.getFont().deriveFont(50.0f);
        labelClock.setFont(displayFontClock);
        
        clockPanel.add(labelClock);
        
        timePanel.add(clockPanel);
        
        // Create the toolbar with the buttons
        JPanel toolbar = new JPanel();
        toolbar.setLayout(new GridLayout(1, 0));
        
        JButton startButton = new JButton("Start");
        startButton.addActionListener(e -> start());
        toolbar.add(startButton);
        
        JButton stopButton = new JButton("Stop");
        stopButton.addActionListener(e -> stop());
        toolbar.add(stopButton);

        JButton stepButton = new JButton("Step");
        stepButton.addActionListener(e -> step());
        toolbar.add(stepButton);

        // Add toolbar into panel with flow layout for spacing
        JPanel flow = new JPanel();
        flow.add(toolbar);
        
        timePanel.add(flow);
        
        contentPane.add(timePanel, BorderLayout.CENTER);
        
        
        // Create the calendar pane in the center
        
        JPanel calendarPanel = new JPanel();
        calendarPanel.setLayout(new GridLayout(2,0));
        
        //Create the Clock display
        JPanel dayPanel = new JPanel();
        String initialDay = calendar.getDay();
        labelDayOfWeek = new JLabel(initialDay, SwingConstants.CENTER);
        Font displayFontTemperature = labelDayOfWeek.getFont().deriveFont(50.0f);
        labelDayOfWeek.setFont(displayFontTemperature);
        //imagePanel.setBorder(new EtchedBorder());
        dayPanel.add(labelDayOfWeek);
        
        calendarPanel.add(dayPanel);
        
        // Create the toolbar with the buttons
        JPanel toolbarCalendar = new JPanel();
        toolbarCalendar.setLayout(new GridLayout(1, 0));
        
        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(e -> up());
        toolbarCalendar.add(nextButton);
        
        JButton prevButton = new JButton("Prev");
        prevButton.addActionListener(e -> down());
        toolbarCalendar.add(prevButton);


        // Add toolbar into panel with flow layout for spacing
        JPanel flowCalendar = new JPanel();
        flowCalendar.add(toolbarCalendar);
        
        calendarPanel.add(flowCalendar);
        contentPane.add(calendarPanel, BorderLayout.SOUTH);

        
        // building is done - arrange the components      
        frame.pack();
        
        // place the frame at the center of the screen and show
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(d.width/2 - frame.getWidth()/2, d.height/2 - frame.getHeight()/2);
        frame.setVisible(true);
    }
    
    /**
     * Create the main frame's menu bar.
     * 
     * @param frame   The frame that the menu bar should be added to.
     */
    private void makeMenuBar(JFrame frame)
    {
        final int SHORTCUT_MASK =
            Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);
        
        JMenu menu;
        JMenuItem item;
        
        // create the File menu
        menu = new JMenu("File");
        menubar.add(menu);
        
        item = new JMenuItem("About Clock/Calendar...");
            item.addActionListener(e -> showAbout());
        menu.add(item);

        menu.addSeparator();
        
        item = new JMenuItem("Quit");
            item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, SHORTCUT_MASK));
            item.addActionListener(e -> quit());
        menu.add(item);
    }
    
    class TimerThread extends Thread
    {
        public void run()
        {
            while (clockRunning) {
                step();
                pause();
            }
        }
        
        private void pause()
        {
            try {
                Thread.sleep(300);   // pause for 300 milliseconds
            }
            catch (InterruptedException exc) {
            }
        }
    }
}