#lang scheme

"""
Parametro 1: alguna funcion definida por el usuario que modifique elementos de una lista
Parametro 2: lista de numeros
Descripcion: aplica la funcion del parametro 1 a cada elemento de la lista del parametro 2. Lo hace por medio de recursion simple
"""

(define (calibra-simple f lecturas)
  (if(null? lecturas)
     '() ;si la lista esta vacia se retorna la lista vacia
     (cons (f (car lecturas))
              (calibra-simple f (cdr lecturas)))))


(define (calibra-cola f lecturas)
  (define (loop f lecturas acumulador)
    (if(null? lecturas)
       (reverse acumulador)
       (loop f (cdr lecturas)(cons (f (car lecturas)) acumulador))))
  (loop f lecturas '()))

;Casos de prueba

(define corr-sat (lambda (x)
(let ((y (+ x 5)))
(max 0 (min 100 y)))))

"PRUEBA SIMPLE"
(calibra-simple corr-sat '())
(calibra-simple corr-sat '(95 -3 50))
(calibra-simple (lambda (x) (if (< x 10) 0 x))'(3 12 8 20))
(calibra-simple (lambda (x) (if (negative? x) 0 (sqrt x)))'(0 1 4 9 -5 16))

"PRUEBA COLA"
(calibra-cola corr-sat '())
(calibra-cola corr-sat '(95 -3 50))
(calibra-cola (lambda (x) (if (< x 10) 0 x))'(3 12 8 20))
(calibra-cola (lambda (x) (if (negative? x) 0 (sqrt x)))'(0 1 4 9 -5 16))


