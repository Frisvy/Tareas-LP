#lang scheme

"""
Parametro 1: tabla para decifrar el codigo
Parametro 2: codigos a decifrar
Descripcion: retorna la lista de codigos eliminando los que no existen en la tabla utilizando filter y assoc
"""
(define(borrar_inexistentes tabla codigos);funcion auxiliar
  (filter (lambda(codigo)
             (assoc codigo tabla))
           codigos))



"""
Parametro 1: tabla para decifrar los codigos
Parametro 2: codigos a decifrar
Descripcion: dado los codigos con su tabla para decifrarlos, la funcion elimina primero los codigos que no existen en la tabla
con la funcion borrar_inexistentes, y depues va cambiando los codigos por su traduccion mediante map y funcion lambda
"""
(define(traducir-codigos tabla codigos)
  (map(lambda (codigo)
        (cdr (assoc codigo tabla)))
           (borrar_inexistentes tabla codigos)))




;casos prueba
(newline)
(define tabla '((A . titanio) (B . cobre) (C . litio)))
(borrar_inexistentes tabla '(B X A C X))
(traducir-codigos tabla '(B X A C X))


