package rw_ui;
import resources.Boat;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import mvc.ListModel;
import mvc.TextView;

/**
 * main visualization window
 */
public class RWFrame extends JFrame {
	private List<Boat> boats = new ArrayList<Boat>();

	public void start() {
		for (Boat boat : boats)
			boat.start();
	}

	public RWFrame(ListModel<String> ecluseList, ListModel<String> idleBoatA, ListModel<String> idleBoatB, ListModel<String> ecluseEtat) {
		super("Ecluse");

		JButton quitButton = new JButton("Quitter");
		quitButton.addActionListener(new Quit());

		JButton startButton = new JButton("Demarrer");
		startButton.addActionListener(new Start());

		add("North", startButton);
		add("South", quitButton);
		
		JPanel panel = new JPanel(new BorderLayout());
		
		panel.add("West", createPanel(idleBoatB, "Bateau en B"));
		panel.add("Center", createPanel(ecluseList, "dans l'Ecluse"));
		panel.add("East", createPanel(idleBoatA, "Bateau en A"));
		panel.add("South", createPanel(ecluseEtat, "Etat de l'ecluse"));
		
		add("Center", panel);

		setLocation(250, 120);
		setVisible(true);
		pack();
	}

	public static JPanel createPanel(ListModel<?> l, String msg) {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setPreferredSize(new Dimension(200, 200));
		panel.add("Center", new TextView(l));
		panel.add("North", new JLabel(msg));
		return panel;
	}

	/**
	 * @param reader register the boat
	 */
	public void register(Boat boat) {
		this.boats.add(boat);
	}
	class  Quit implements ActionListener {
		public void actionPerformed(ActionEvent e){
			System.exit(0);
		}
	}
	class Start implements ActionListener {
		public void actionPerformed(ActionEvent e){
			System.out.println("Demarrage");
			start();
		}
	}
}
