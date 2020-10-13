package forms;

import java.awt.Color;
import java.awt.Cursor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import comunication.ControlPort;
import comunication.Led;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;

public class Control extends JFrame {

	private static final long serialVersionUID = 8621322676347060260L;
	private JButton btnLow = null;
	private JButton btnHigh = null;
	private JButton btnBlue = null;
	private JButton btnRed = null;
	private ControlPort controlPort = null;
	
	public static void main(String[] args) {
		Control c = null;
		try {
			c = new Control();
			c.init(new ControlPort("COM3", 9600));
		} catch (Exception e) {
			c.close();
			e.printStackTrace();
		}
	}
	
	public void init(ControlPort controlPort) {
		this.controlPort = controlPort;
		setBounds(0, 0, 310, 150);
		setResizable(false);
		setVisible(true);
		setLayeredPane(new JLayeredPane());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(new ImageIcon(getClass().getResource("/images/favicon.jpg")).getImage());
		setTitle("Serial Communication");
		initComponents();
	}
	
	private void initComponents() {
		JLayeredPane pane = getLayeredPane();
		pane.setBackground(new Color(255, 255, 255));
		pane.setOpaque(true);

		btnLow = new JButton("LOW");
		btnLow.setBorder(new EmptyBorder(10, 10, 10, 10));
		btnLow.setFocusable(false);
		btnLow.setBounds(20, 5, 120, 40);
		btnLow.setBackground(new Color(255, 255, 255));
		btnLow.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnLow.setBorder(new LineBorder(Color.BLACK));
		
		btnHigh = new JButton("HIGH");
		btnHigh.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnHigh.setFocusable(false);
		btnHigh.setBounds(150, 5, 120, 40);
		btnHigh.setBackground(new Color(255, 255, 255));
		btnHigh.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		btnHigh.setBorder(new LineBorder(Color.BLACK));
		btnBlue = new JButton("BLUE");
		btnBlue.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnBlue.setFocusable(false);
		btnBlue.setBounds(20, 55, 120, 40);
		btnBlue.setBackground(Color.BLUE);
		btnBlue.setForeground(new Color(255, 255, 255));
		btnBlue.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		btnRed = new JButton("RED");
		btnRed.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnRed.setFocusable(false);
		btnRed.setBounds(150, 55, 120, 40);
		btnRed.setBackground(Color.RED);
		btnRed.setForeground(new Color(255, 255, 255));
		btnRed.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		pane.add(btnLow);
		pane.add(btnHigh);
		pane.add(btnBlue);
		pane.add(btnRed);
		
		initEvent();
	}
	
	private void initEvent() {
		btnLow.addMouseListener(new MouseAdapter() {
		   public void mouseClicked(MouseEvent evt) {
			   controlPort.sendData(Led.LOW);
           }
        });
		btnHigh.addMouseListener(new MouseAdapter() {
		   public void mouseClicked(MouseEvent evt) {
			   controlPort.sendData(Led.HIGH);
           }
        });
		btnBlue.addMouseListener(new MouseAdapter() {
		   public void mouseClicked(MouseEvent evt) {
			   controlPort.sendData(Led.BLUE);
           }
        });
		btnRed.addMouseListener(new java.awt.event.MouseAdapter() {
		   public void mouseClicked(MouseEvent evt) {
			   controlPort.sendData(Led.RED);
           }
        });
	}
	
	private void close() {
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
}
