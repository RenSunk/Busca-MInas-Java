package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import mundo.Jugador;
import mundo.Tablero;

public class Principal extends JFrame implements ActionListener{

	private Tablero mitablero;

	private JButton botones[][];
	private JButton puntaje;
	
	private Panel1 panel1;

	private Panel2 panel2;

	private JLabel tiempo;
	private JLabel nombre;
	private int s=0,cs=0,m=0;
	private Timer t;
	private ActionListener acciones = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			cs++;
			if(cs==100) {
				cs=0;
				s++;
				
				mitablero.getMiJugador().setPuntaje(s);
				mitablero.getMiJugador().setTiempo(s);
				
				tiempo.setText("tiempo: "+mitablero.getMiJugador().getTiempo());
			}
		}
	}; 
	
	
	public Principal() {
		super("Ventana");
		iniciar();
		alinear();
		t = new Timer(10, acciones);
		t.start();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setSize(500, 500);
		setLocationRelativeTo(null);
		
		
		
		//new Principal2(mitablero);
	}

	public void alinear() {
		setLayout(new BorderLayout());
		JPanel panelbotones = new JPanel();
		JPanel panel_superior = new JPanel();
		panelbotones.setLayout(new GridLayout(5, 5));
		
		JPanel panel_inferior = new JPanel();
		panel_inferior.setLayout(new BorderLayout());
		for (int i = 0; i < botones.length; i++) {
			for (int j = 0; j < botones.length; j++) {
				panelbotones.add(botones[i][j]);
			}
		}
		
		panel_superior.setLayout(new BorderLayout());
		panel_superior.add(tiempo,BorderLayout.EAST);
		panel_inferior.add(puntaje, BorderLayout.EAST);
		panel_superior.add(nombre,BorderLayout.WEST);
		add(panel_superior,BorderLayout.NORTH);
		add(panelbotones, BorderLayout.CENTER);
		add(panel_inferior, BorderLayout.SOUTH);

	}

	public void iniciar() {
		String nombre = JOptionPane.showInputDialog("ingrese un nombre");
		mitablero = new Tablero(new Jugador(nombre));
		mitablero.leer_datos();
		mitablero.jugar();
		tiempo = new JLabel("0");
		this.nombre = new JLabel("Nombre: "+mitablero.getMiJugador().getNombre());
		botones = new JButton[5][5];
		puntaje = new JButton("Puntaje");
		puntaje.addActionListener(this);
		for (int i = 0; i < botones[0].length; i++) {
			for (int j = 0; j < botones.length; j++) {
				botones[i][j] = new JButton("");
				botones[i][j].addActionListener(this);
				botones[i][j].setBackground(Color.WHITE);
			}
		}
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
	
	public void ganar() {
		int fina = botones[0].length*botones.length,cant=0;
		
		for (int i = 0; i < botones[0].length; i++) {
			for (int j = 0; j < botones.length; j++) {
				if(botones[i][j].getText() != "") {
					cant++;
				}
			}
		}
		if(cant == fina-5) {
			t.stop();
			JOptionPane.showMessageDialog(null, "ganas :)");
			mitablero.escribir_archivo();
		}
	}
	
	public static void main(String[] args) {
		
		new Principal();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < botones[0].length; i++) {
			for (int j = 0; j < botones.length; j++) {
				if(e.getSource()== botones[i][j]) {
					
					if(haymina(i, j)) {
						t.stop();
						colocar_minas();
						JOptionPane.showMessageDialog(null, "pierdes :(");
						
					}else {
						colocar_numeros(i, j);
					}
					
					
					ganar();
					}
				}
		}
		if(e.getSource() == puntaje) {
			JOptionPane.showMessageDialog(null, mitablero.getpuntuacion());
		}
	}
}
