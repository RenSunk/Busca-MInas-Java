package mundo;

import java.awt.geom.GeneralPath;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class Tablero {
	
	private Mina mimina[];
	
	private Jugador miJugador;
	
	private String puntajeGeneral;
	
	public Tablero(Jugador miJugador) {
		this.miJugador = miJugador;
	}

	public Mina[] getMimina() {
		return mimina;
	}

	public void setMimina(Mina[] mimina) {
		this.mimina = mimina;
	}

	public Jugador getMiJugador() {
		return miJugador;
	}

	public void setMiJugador(Jugador miJugador) {
		this.miJugador = miJugador;
	}
	
	public String getpuntuacion() {
		return puntajeGeneral;
	}
	
	public void jugar() {
		int cant_minas = 5;
		mimina = new Mina[cant_minas];
		for(int i=0;  i < cant_minas; i++) {
			int x = (int)(Math.random()*5)+0;
			int y = (int)(Math.random()*5)+0;
			mimina[i] = new Mina(x, y);
			for (int j = 0; j < cant_minas; j++) {
				try {
					if(mimina[i].getPosicionX() == mimina[j].getPosicionX() && mimina[i].getPosicionY() == mimina[j].getPosicionY() && i != j) {
						i--;
						break;
					}
				}catch(NullPointerException e) {}
			}
		}
	}
	
	public void leer_datos() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("./data/puntajes.txt"));
			
			String texto;
			do {
				texto = br.readLine();
				if(puntajeGeneral ==  null) {
					puntajeGeneral = texto;
				}else if(texto != null){
					puntajeGeneral = puntajeGeneral+"\n"+texto;
				}
					
				
			}while(texto != null);
			br.close();
		}catch(IOException e) {
			JOptionPane.showMessageDialog(null, "Vuelva a iniciar el juego");
		}
	}
	public void escribir_archivo() {
		try {
			PrintWriter pw = new PrintWriter(new File("./data/puntajes.txt"));
			
			puntajeGeneral = puntajeGeneral+"\n"+miJugador.getNombre()+":"+miJugador.getPuntaje();
			
			pw.print(puntajeGeneral);
			
			pw.close();
		} catch (IOException e) {
			
		}
	}
}
