package src.p03.c01;

/**
 * Clase principal que lanza la simulación del flujo de entradas y salidas en el
 * parque. Crea e inicia las tareas de entrada y salida de cada puerta.
 */
public class SistemaLanzador {

	/**
	 * Metodo principal, lanza tantos pares de hilos, según su parametro de
	 * argumento.
	 *
	 * @param args el numero de puertas del parque
	 */
	public static void main(String[] args) {

		// nueva instancia de parque con maximo 50 personas
		IParque parque = new Parque(50);
		// inicializa la letra A para identificar las puertas
		char letra_puerta = 'A';

		System.out.println("¡Parque abierto!");

		// itera tantos numeros de puertas haya
		for (int i = 0; i < Integer.parseInt(args[0]); i++) {

			String puerta = "" + ((letra_puerta++));

			// creación de hilos de entrada
			ActividadEntradaPuerta entradas = new ActividadEntradaPuerta(puerta, parque);
			new Thread(entradas).start();

			// creación de hilos de salida
			ActividadSalidaPuerta salidas = new ActividadSalidaPuerta(puerta, parque);
			new Thread(salidas).start();
		}
	}
}