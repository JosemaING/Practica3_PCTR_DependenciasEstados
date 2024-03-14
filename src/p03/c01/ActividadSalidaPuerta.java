package src.p03.c01;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que simula la actividad de salidas dell parque a través de una puerta.
 * Implementa Runnable para mermitir la ejecucion en hilo separado.
 */
public class ActividadSalidaPuerta implements Runnable {

	/** numero de salidas que se harán a traves de la puerta. */
	private static final int NUMSALIDAS = 20;

	/** id de la puerta. */
	private String idPuerta;

	/** referencia al parque. */
	private IParque parque;

	/**
	 * Constructor para crear una nueva actividad de salida en el parque a traves de
	 * una esta puerta.
	 *
	 * @param nombre de la puerta que se va usar
	 * @param parque del que salen
	 */
	public ActividadSalidaPuerta(String nombre, IParque parque) {
		this.idPuerta = nombre;
		this.parque = parque;
	}

	/**
	 * Se ejecuta cuando el hilo comienza, realiza un numero especifico de salidas.
	 * Además tiene un tiempo de espera aleatorio entre una salida y la siguiente
	 */
	@Override
	public void run() {
		int iteracion;

		for (iteracion = 0; iteracion < NUMSALIDAS; iteracion++) {

			try {
				parque.salirDelParque(idPuerta);
				// numero aleatoprio de milisegundos entre salidas
				TimeUnit.MILLISECONDS.sleep(new Random().nextInt(5) * 1000);
			} catch (InterruptedException e) {
				Logger.getGlobal().log(Level.INFO, "Finalizada entrada por la puerta " + idPuerta);
				Logger.getGlobal().log(Level.INFO, e.toString());
				return;
			}
		}
	}
}
