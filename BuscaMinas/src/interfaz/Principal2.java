package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import mundo.Mina;
import mundo.Tablero;

public class Principal2 extends JFrame{
	private Tablero mitablero;

	private JButton botones[][];

	private Panel1 panel1;

	private Panel2 panel2;

	public Principal2(Tablero miTablero) {
		super("Ventana");
		this.mitablero = miTablero;
		iniciar();
		alinear();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setSize(500, 500);
		setLocationRelativeTo(null);
		
	}

	public void alinear() {
		setLayout(new BorderLayout());
		JPanel panelbotones = new JPanel();
		panelbotones.setLayout(new GridLayout(5, 5));
		for (int i = 0; i < botones.length; i++) {
			for (int j = 0; j < botones.length; j++) {
				panelbotones.add(botones[i][j]);
			}
		}
		add(panelbotones, BorderLayout.CENTER);

	}

	public void iniciar() {
		botones = new JButton[5][5];
		for (int i = 0; i < botones[0].length; i++) {
			for (int j = 0; j < botones.length; j++) {
				botones[i][j] = new JButton("");
				botones[i][j].setBackground(Color.WHITE);
			}
		}
		colocar_minas();
	}

	public void colocar_minas() {
		
		
		for (int i = 0; i < mitablero.getMimina().length; i++) {
			botones[mitablero.getMimina()[i].getPosicionX()][mitablero.getMimina()[i].getPosicionY()]
					.setIcon(new ImageIcon("./data/buscaminas.png"));
		}
	}

	public void colocar_numeros(int x, int y) {
		int catn = 0;
		for (int i = 0; i < mitablero.getMimina().length; i++) {
			try {
				if (x + 1 == mitablero.getMimina()[i].getPosicionX() && y == mitablero.getMimina()[i].getPosicionY()) {
					catn++;
				}
			} catch (NullPointerException e) {
			}
			try {
				if (x + 1 == mitablero.getMimina()[i].getPosicionX()
						&& y + 1 == mitablero.getMimina()[i].getPosicionY()) {
					catn++;
				}
			} catch (NullPointerException e) {
			}
			try {
				if (x == mitablero.getMimina()[i].getPosicionX() && y + 1 == mitablero.getMimina()[i].getPosicionY()) {
					catn++;
				}
			} catch (NullPointerException e) {
			}
			try {
				if (x + 1 == mitablero.getMimina()[i].getPosicionX()
						&& y - 1 == mitablero.getMimina()[i].getPosicionY()) {
					catn++;
				}
			} catch (NullPointerException e) {
			}
			try {
				if (x == mitablero.getMimina()[i].getPosicionX() && y - 1 == mitablero.getMimina()[i].getPosicionY()) {
					catn++;
				}
			} catch (NullPointerException e) {
			}
			try {
				if (x - 1 == mitablero.getMimina()[i].getPosicionX()
						&& y - 1 == mitablero.getMimina()[i].getPosicionY()) {
					catn++;
				}
			} catch (NullPointerException e) { 
			}
			try {
				if (x - 1 == mitablero.getMimina()[i].getPosicionX() && y == mitablero.getMimina()[i].getPosicionY()) {
					catn++;
				}
			} catch (NullPointerException e) {
			}
			try {
				if (x - 1 == mitablero.getMimina()[i].getPosicionX()
						&& y + 1 == mitablero.getMimina()[i].getPosicionY()) {
					catn++;
				}
			} catch (NullPointerException e) {
			}
		}
		botones[x][y].setText("" + catn);
	}
	
	public boolean haymina(int x,int y) {
		for (int i = 0; i < mitablero.getMimina().length; i++) {
			if(mitablero.getMimina()[i].getPosicionX() == x && mitablero.getMimina()[i].getPosicionY() == y) {
				mitablero.getMimina()[i].setEstado(true);
				botones[x][y].setBackground(Color.red);
				return true;
			}
		}
		return false;
	}

	
}
