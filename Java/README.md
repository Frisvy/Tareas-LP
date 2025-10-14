Nombre: Gabriel Ordenes
Rol: 202473521-7

---------------------------CONSIDERACIONES----------------------------------
1.- a la hora de seleccionar "volver a la nave", la nueva profundidad se seteara en la profundidad de anclaje de la nave

2.- la seleccion de "volver a la nave" no consumira oxigeno alguno, por lo cual por mas profundo que este el jugador volver a la nave siempre lo salvara de morir (el coordinador dijo que estaba bien asi)

3.- Me reserve el derecho de eliminar opciones que salian en el ejemplo de ejecucion, mas especificamente la opcion de "5) Ver profundidad actual" y "6) Ver oxigeno restante", ya que me parecio mas oportuno integrarlo directamente en la interfaz de seleccion de opciones

4.- utilizo double por que math.random devuelve double y asi me ahorro casteos y hay mas presicion en el calculo de profundidad normalizada y oxigeno

5.- opte por no comentar todos los setters y getters por que siento que la gran mayoria explican solos y harian el codigo mucho mas denso y dificil de revisar, a excepcion de setZonaActual ya que tiene directa relacion con la validacion de zonas atraves de la interfaz de AccesoProfundidad

6.- para simplificar calculos de oxigeno, el cambio de zona se implemento de tal manera que tengas que volver a la nave para realizar la accion de cambiar de zona, por lo cual si se intenta cambiar de zona a nado se imprimira un mensaje indicando que se debe volver a la nave y se seteara la profundidad actual en la maxima permitida en la zona (El coordinador menciono en el foro que esta opcion es totalmente valida y que no se evaluara el cambio de zona a nado)

7.- en cuanto a la condicion de derrota, en caso de que se quede inconsciente el jugador, perdera el inventario y reaparecera en la profundidad de anclaje de la nave, los objetos clave que haya conseguido el jugador no se perderan para no arruinar la progesion del juego (pieza tanque ,etc)

8.- entiendo que las mejoras de oxigeno pueden ser infinitas tras mejorar el tanque de oxigeno, añadieendo 30 mas de oxigeno por mejora de este

9.- no se especifica explicitamente en el enunciado de la tarea, pero asumi que el acceso a la zona volcanica esta limitado tambien por el modulo profundidad y no solo por el traje termico y la mejora del tanque, ya que al final esta a mas de 500 de profundidad la zona, lo que implica que la nave no soportaria la profundidad de la zona 

10.- El pdf de la tarea no especifica nada acerca de la cantidad de recursos que otorgan las acciones de explorar y recolectar en Nave Estrellada, menciono esto debido que no se da una formula especial para la zona, y dado que sale definido que el nMax debe ser 4 y el nMin 1, como la formula de recursos a obtener implica el utilizar la profundidad normalizada, esta ultima siempre sera 0 ya que nave estrellada esta en la superficie, por lo cual las acciones ya mencionadas siempre daran 1 solo recurso como maximo aun siguiendo al pie de la letra las formulas. Lo menciono para que se tenga en cuenta en la revision, ya que me parece un detalle importante






-------------INSTRUCCIONES DE EJECUCION--------------------------------------



Por implemetnar:
    2) cambio de zona (interfaz) //revisar implementacion en naveexploradora
    4) aplicar mejoras de modulos y eso 
    5) configurar anclaje
    6) hacer funcional la mejora del traje termico y hacer q te mueras y eso
