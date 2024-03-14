package src.p03.c01;

/**
 * Interfaz IParque.
 */
public interface IParque {

	/**
	 * Entrar al parque.
	 *
	 * @param puerta por la que entra la persona
	 */
	public abstract void entrarAlParque(String puerta);

	/**
	 * Salir del parque.
	 *
	 * @param puerta por la que entra la persona
	 */
	public abstract void salirDelParque(String puerta);
}
