# Practica3_PCTR_DependenciasEstados


Esta es la plantilla ha sido modificada para la realización de la tercera práctica de la asignatura de Programación Concurrente y Tiempo Real (PCTR) del Grado en Ingeniería Informática de la Universidad de Burgos.

### AUTOR

- **Nombre del Autor:** José María Santos
- **Email:** jsr1002@alu.ubu.es
- **Fecha:** 14/03/2024

### MODIFICACIÓN

La plantilla original ha sido modificada para implementar una simulación del flujo de personas en un parque, gestionando entradas y salidas a través de varias puertas. La simulación utiliza un modelo de concurrencia, creando un conjunto de tareas (hilos) que representan las actividades de entrada y salida del parque.

### EJECUCIÓN

- **Parámetro de argumento:** El parámetro de argumento para `SistemaLanzador.java` es el número de puertas para la simulación. Para esta ejecución específica, se ha configurado con `5 puertas`, lo que resulta en `5 pares de hilos` para simular las entradas y salidas, sumando un total de `10 tareas`.
- **Instancia Parque:** La capacidad máxima del parque ha sido establecida en 50 personas, dentro del código de `SistemaLanzador.java`.
- **Argumentos de Ejecución (VM):** Se han de utilizar los argumentos `-ea` para una correcta ejecución.

### CONTENIDO EXTRA AÑÁDIDO

Además del código fuente, se han incluido los siguientes elementos:

- `P03_TablaDependenciasEstado.pdf`: Contiene la tabla de dependencia de estados y una breve explicacion de la salida por pantalla tras la ejecución.
- `application-snapshot-sistemalanzador.apps`: Contiene una captura de la aplicación con VisualVM. Para ver la ejecución de los hilos en tiempo real y el manejo de la concurrencia.
- `salida_ejecucion.txt`: Contiene una ejecución de ejemplo, con maximo 50 personas y 5 puertas.