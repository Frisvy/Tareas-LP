#lang scheme
;PARA LA FUNCION SE ASUME VECTORES DE LARGOS IGUALES PORQUE EL PDF DE LA TAREA LO DICE, perdon por gritar
"""
Paramtetro 1: indice de corte del umbral
Parametro 2: vector 1
Parametro 3: vector 2
Parametro 4: umbral a superar con la suma parcial
Descripcion: la funcion realiza las sumas parciales de todos los elementos de los 2 vectores, la funcion asume que el input seran vectores de largos
iguales y utiliza delay y force para solo calcular las sumas parciales de los productos hasta que se pase el umbral, en caso de que nunca se pase retorna 0
y en caso contrario cesa los calculos de sumas y retorna el indice
"""

(define(indice-corte v1 v2 umbral)
  (let((largo(vector-length v1)))
       (let loop ((suma 0) (indice 0))
         (if(= indice largo) ;en caso de que ya se haya recorrido todo el vector y nunca se haya pasado el umbral retorna 0
            0   
            (let ((producto (delay(* (vector-ref v1 indice) (vector-ref v2 indice))))) ;let para guardar el producto
              (if(> (+ suma (force producto)) umbral) ;en caso de que las sumas parciales se pasen del umbral se retorna el indice
                 (+ indice 1)
                 (loop (+ suma (force producto)) (+ indice 1)))))))) ;en caso contrario se aplica de nuevo el mismo procedimiento pero aumentando en 1 el indice




;casos de prueba
(indice-corte '#(10 0 0 0) '#(3 9 9 9) 25);1
(indice-corte '#(1 1 1 1 1 100 1 1) '#(1 1 1 1 1 1 1 1) 50);6
(indice-corte '#(5 5 5) '#(1 1 1) 15);0
(indice-corte '#(1 2 3) '#(1 1 1) 10);0
(indice-corte #(1 2) #(1 2) -1000)
