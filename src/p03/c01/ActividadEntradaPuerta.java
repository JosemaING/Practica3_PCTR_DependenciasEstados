package src.p03.c01;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que simula la actividad de entrada al parque a través de una puerta.
 * Implementa Runnable para mermitir la ejecucion en hilo separado.
 */
public class ActividadEntradaPuerta implements Runnable {

	/** numero de entradas que se harán a traves de la puerta. */
	private static final int NUMENTRADAS = 20;

	/** id de la puerta. */
	private String puerta;

	/** referencia al parque. */
	private IParque parque;

	/**
	 * Constructor para crear una nueva actividad de entrada en el parque a traves
	 * de una esta puerta.
	 *
	 * @param puerta que se va a usar
	 * @param parque al que entran
	 */
	public ActividadEntradaPuerta(String puerta, IParque parque) {
		this.puerta = puerta;
		this.parque = parque;
	}

	/**
	 * Se ejecuta cuando el hilo comienza, realiza un numero especifico de entradas.
	 * Además tiene un tiempo de espera aleatorio entre una entrada y la siguiente
	 */
	@Override
	public void run() {
		for (int i = 0; i < NUMENTRADAS; i++) {
			try {
				parque.entrarAlParque(puerta);
				// numero aleatoprio de milisegundos entre entradas
				TimeUnit.MILLISECONDS.sleep(new Random().nextInt(5) * 1000);
			} catch (InterruptedException e) {
				Logger.getGlobal().log(Level.INFO, "Entrada interrumpida");
				Logger.getGlobal().log(Level.INFO, e.toString());
				return;
			}
		}
	}

}
