#lang scheme



(define(borrar_inexistentes tabla codigos);funcion auxiliar
  (filter (lambda(codigo)
             (assoc codigo tabla))
           codigos))

(define(traducir-codigos tabla codigos)
  (map(lambda (codigo)
        (cdr (assoc codigo tabla)))
           (borrar_inexistentes tabla codigos)))

;casos prueba
(define tabla '((A . titanio) (B . cobre) (C . litio)))
(borrar_inexistentes tabla '(B X A C X))
(traducir-codigos tabla '(B X A C X))
