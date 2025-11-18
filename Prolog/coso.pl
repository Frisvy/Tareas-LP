habilitada(Asignatura, Aprobados):-
    prerrequisitos(Asignatura, Requisitos),
    \+ ( member(X, Requisitos),
        \+ member(X, Aprobados)).