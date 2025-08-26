Nombre: Gabriel Ordenes
Rol: 202473521-7

La funcion estrofas_validas lee el archivo considerando que siempre va a haber una linea bonus independientemente de su contenido, por lo cual si no se pone terminaria por eliminar una estrofa en la lista que retorna, ademas se entiende que siempre habra un salto de linea que separe una estrofa de otra, y que no existira nunca un linea vacia seguida de otra. (considere que cada archivo estrofas.txt tendria ese formato)
En cuanto a las rimas si bien el codigo identificara correctamente los simbolos permitidos, se asume que la ultima linea de cualquier verso sera una palabra y no un numero escrito como "1" (palabras reales, no del estilo "klanjdsldalkd" o "holaaaaaaaaaaaaaa", o "hol1", etc). (Esto para la correcta identificacion de rimas, ya que los numeros que se encuentren se reemplazaran con un "" directamente)
El codigo aplicara la deteccion de todos los tipos de rimas al instante
El codigo para funcionar necesita tener en la misma carpeta el archivo de estrofas.txt, eso deberia bastar