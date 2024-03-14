package src.p03.c01;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Clase Parque que implementa la interfaz IParque.
 */
public class Parque implements IParque {

	/** maximo numero de personas permitidas en el parque */
	private final int maxPersonas;

	/** contador de personas totales en el parque. */
	private int contadorPersonasTotales;

	/** mapa de contadores de personas en cada puerta. */
	private Hashtable<String, Integer> contadoresPersonasPuerta;

	/**
	 * Constructor de una nueva instancia parque.
	 *
	 * @param personas maximas permitidas en el parque
	 */
	public Parque(int personas) {
		maxPersonas = personas;
		contadorPersonasTotales = 0;
		contadoresPersonasPuerta = new Hashtable<String, Integer>();
	}

	/**
	 * Método sincronizado para gestionar la entrada de personas al parque por una
	 * puerta específica.
	 *
	 * @param puerta por la que entra
	 */
	@Override
	public synchronized void entrarAlParque(String puerta) {
		// Si no hay entradas por esa puerta, inicializamos
		if (contadoresPersonasPuerta.get(puerta) == null) {
			contadoresPersonasPuerta.put(puerta, 0);
		}

		// Comprobar precondiciones
		comprobarAntesDeEntrar();

		// Aumentamos el contador total y el individual
		contadorPersonasTotales++;
		contadoresPersonasPuerta.put(puerta, contadoresPersonasPuerta.get(puerta) + 1);

		// Imprimimos el estado del parque
		imprimirInfo(puerta, "Entrada");

		// Comprobamos que se cumple condición de invariante
		checkInvariante();

		// Se notifica el cambio de estado
		notifyAll();

	}

	/**
	 * Método sincronizado para gestionar la salida de personas al parque por una
	 * puerta específica.
	 *
	 * @param puerta por la que sale
	 */
	@Override
	public synchronized void salirDelParque(String puerta) {
		// Si no hay entradas por esa puerta, inicializamos
		if (contadoresPersonasPuerta.get(puerta) == null) {
			contadoresPersonasPuerta.put(puerta, 0);
		}

		// Comprobar precondiciones
		comprobarAntesDeSalir();

		// Decrementamos el contador total y el individual
		contadorPersonasTotales--;
		contadoresPersonasPuerta.put(puerta, contadoresPersonasPuerta.get(puerta) - 1);

		// Imprimimos el estado del parque
		imprimirInfo(puerta, "Salida");

		// Comprobamos que se cumple condición de invariante
		checkInvariante();

		// Se notifica el cambio de estado
		notifyAll();
	}

	/**
	 * Muestra por pantalla la información de movimientos (entradas y salidas).y el
	 * numero total de personas en el parque
	 *
	 * @param puerta     en la que sucede el movimiento
	 * @param movimiento entrada o salida
	 */
	protected void imprimirInfo(String puerta, String movimiento) {
		System.out.println(movimiento + " por puerta " + puerta);
		System.out.println("-> Personas en el parque " + contadorPersonasTotales); // + " tiempo medio de estancia: " +
																					// tmedio);

		// Iteramos por todas las puertas e imprimimos sus entradas
		for (String p : contadoresPersonasPuerta.keySet()) {
			System.out.println("--> Por puerta " + p + " " + contadoresPersonasPuerta.get(p));
		}
		System.out.println(" ");
	}

	/**
	 * Comprobar si el parque ha alcanzado el numero maximo de personas antes de
	 * entrar.
	 */
	protected void comprobarAntesDeEntrar() {
		while (contadorPersonasTotales >= maxPersonas) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Comprobar si existen personas en el parque antes de salir.
	 */
	protected void comprobarAntesDeSalir() {
		while (contadorPersonasTotales <= 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Suma los contadores de las puertas.
	 *
	 * @return sukma de los contadores de las puertas
	 */
	private int sumarContadoresPuerta() {
		int sumaContadoresPuerta = 0;
		Enumeration<Integer> iterPuertas = contadoresPersonasPuerta.elements();
		while (iterPuertas.hasMoreElements()) {
			sumaContadoresPuerta += iterPuertas.nextElement();
		}
		return sumaContadoresPuerta;
	}

	/**
	 * Verifica la coherencia de los contadores del parque.
	 */
	protected void checkInvariante() {
		assert sumarContadoresPuerta() == contadorPersonasTotales
				: "INV: La suma de contadores de las puertas debe ser igual al valor del contador del parte";
		assert contadorPersonasTotales <= maxPersonas : "Límite de Aforo en el parque sobrepasado";
		assert contadorPersonasTotales >= 0 : "No quedan personas en el parque";
	}

}
