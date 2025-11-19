%----------------asignatura(Codigo, Nombre, Creditos, SemestrePlan, Orden).-------------------
%semestre 1
asignatura('IWI-131', 'Programacion', 5, 1, 1).
asignatura('MAT-021', 'Matematicas 1', 8, 1, 2).
asignatura('FIS-100', 'Introduccion a la Fisica', 6, 1, 3).
asignatura('HRW-132', 'Humanistico 1', 3, 1, 4).
asignatura('DEW-100', 'Educacion Fisica 1', 2, 1, 5).

%semestre 2
asignatura('QUI-010', 'Quimica y Sociedad', 5, 2, 6).
asignatura('MAT-022', 'Matematicas 2', 7, 2, 7).
asignatura('FIS-110', 'Fisica General 1', 8, 2, 8).
asignatura('IWG-101', 'Introduccion a la Ingenieria', 3, 2, 9).
asignatura('HRW-133', 'Humanistico 2', 3, 2, 10).
asignatura('DEW-101', 'Educacion Fisica 2', 2, 2, 11).

%semestre 3
asignatura('INF-134', 'Estructuras de Datos', 5, 3, 12).
asignatura('MAT-023', 'Matematicas 3', 7, 3, 13).
asignatura('FIS-130', 'Fisica General 3', 8, 3, 14).
asignatura('INF-152', 'Estructuras Discretas', 5, 3, 15).
asignatura('INF-260', 'Teoria de Sistemas', 5, 3, 16).
asignatura('INF-1', 'Libre 1', 2, 3 , 17).

%semestre 4
asignatura('INF-253', 'Lenguajes de Programacion', 5, 4, 18).
asignatura('MAT-024', 'Matematicas 4', 6, 4, 19).
asignatura('FIS-120', 'Fisica General 2', 8, 4, 20).
asignatura('INF-155', 'Informatica Teorica', 5, 4, 21).
asignatura('IWN-170', 'Economia 1A', 5, 4, 22).
asignatura('INF-2', 'Libre 2', 2, 4, 23).

%semestre 5
asignatura('INF-239', 'Bases de Datos', 5, 5, 24).
asignatura('INF-245', 'Arquitectura y Organizacion de Computadores', 5, 5, 25).
asignatura('FIS-140', 'Fisica General 4', 8, 5, 26).
asignatura('INF-280', 'Estadistica Computacion', 5, 5, 27).
asignatura('INF-270', 'Organizacion y Sistemas de Informacion', 5, 5, 28).
asignatura('INF-3', 'Libre 3', 2, 5, 29).

%semestre 6
asignatura('INF-236', 'Analisis y Diseño de Software', 5, 6, 30).
asignatura('INF-246', 'Sistemas Operativos', 5, 6, 31).
asignatura('INF-276', 'Ingenieria Informatica y Sociedad', 5, 6, 32).
asignatura('INF-221', 'Algoritmos y Complejidad', 5, 6, 33).
asignatura('INF-292', 'Optimizacion', 5, 6, 34).
asignatura('INF-4', 'Libre 4', 2, 6, 35).

%semestre 7
asignatura('INF-225', 'Ingenieria de Software', 5, 7, 36).
asignatura('INF-256', 'Redes de Software', 5, 7, 37).
asignatura('ICN-270', 'Informacion y Matematicas Financieras', 5, 7, 38).
asignatura('INF-285', 'Computacion Cientifica', 5, 7, 39).
asignatura('INF-293', 'Investigacion y Operaciones', 6, 7, 40).
asignatura('INF-5', 'Libre 5', 2, 7, 41).

%semestre 8
asignatura('INF-322', 'Diseño Interfaces Usuarias', 5, 8, 42).
asignatura('INF-343', 'Sistemas Distribuidos', 5, 8, 43).
asignatura('INF-305', 'Electivo Informatica 1', 5, 8, 44).
asignatura('INF-295', 'Inteligencia Artificial', 5, 8, 45).
asignatura('INF-266', 'Sistemas de Gestion', 5, 8, 46).
asignatura('INF-6', 'Libre 6', 2, 8, 47).

%semestre 9
asignatura('INF-306', 'Electivo Informatica 2', 5, 9, 48).
asignatura('INF-307', 'Electivo Informatica 3', 5, 9, 49).
asignatura('INF-311', 'Electivo 1', 5, 9, 50).
asignatura('INF-312', 'Electivo 2', 5, 9, 51).
asignatura('INF-360', 'Gestion de Proyectos de Informatica', 5, 9, 52).
asignatura('INF-7', 'Libre 7', 2, 9, 53).

%semestre 10
asignatura('INF-308', 'Electivo Informatica 4', 5, 10, 54).
asignatura('INF-313', 'Electivo 3', 5, 10, 55).
asignatura('INF-314', 'Electivo 4', 5, 10, 56).
asignatura('INF-228', 'Taller Desarrollo de Proyecto de Informatica', 10, 10, 57).
asignatura('INF-309', 'Trabajo de Titulo 1', 2, 10, 58).

%semestre 11
asignatura('INF-310', 'Trabajo de Titulo 2', 20, 11, 59).


%----------------prerrequisitos(Asignatura, Requisitos).--------------------------------------
%semestre 1
prerrequisitos('IWI-131', []).
prerrequisitos('MAT-021', []).
prerrequisitos('FIS-100', []).
prerrequisitos('HRW-132', []).
prerrequisitos('DEW-100', []).

%semestre 2
prerrequisitos('QUI-010', []).
prerrequisitos('MAT-022', ['MAT-021']).
prerrequisitos('FIS-110', ['MAT-021', 'FIS-100']).
prerrequisitos('IWG-101', []).
prerrequisitos('HRW-133', []).
prerrequisitos('DEW-101', ['DEW-100']).

%semestre 3
prerrequisitos('INF-134', ['IWI-131']).
prerrequisitos('MAT-023', ['MAT-022']).
prerrequisitos('FIS-130', ['MAT-022', 'FIS-110']).
prerrequisitos('INF-152', ['IWI-131', 'MAT-021']).
prerrequisitos('INF-260', ['IWG-101']).
prerrequisitos('INF-1', []).

%semestre 4
prerrequisitos('INF-253', ['INF-134']).
prerrequisitos('MAT-024', ['MAT-023']).
prerrequisitos('FIS-120', ['MAT-022', 'FIS-110']).
prerrequisitos('INF-155', ['INF-134', 'INF-152']).
prerrequisitos('IWN-170', ['MAT-023']).
prerrequisitos('INF-2', []).

%semestre 5
prerrequisitos('INF-239', ['INF-134']).
prerrequisitos('INF-245', ['INF-134']).
prerrequisitos('FIS-140', ['FIS-130', 'FIS-120']).
prerrequisitos('INF-280', ['IWI-131', 'MAT-023']).
prerrequisitos('INF-270', ['INF-260']).
prerrequisitos('INF-3', []).

%semestre 6
prerrequisitos('INF-236', ['INF-239', 'INF-253']).
prerrequisitos('INF-246', ['INF-245']).
prerrequisitos('INF-276', ['INF-270']).
prerrequisitos('INF-221', ['INF-152', 'INF-253']).
prerrequisitos('INF-292', ['MAT-023']).
prerrequisitos('INF-4', []).

%semestre 7
prerrequisitos('INF-225', ['INF-236']).
prerrequisitos('INF-256', ['INF-246']).
prerrequisitos('ICN-270', ['IWN-170']).
prerrequisitos('INF-285', ['MAT-024', 'INF-221']).
prerrequisitos('INF-293', ['INF-280', 'INF-292']).
prerrequisitos('INF-5', []).

%semestre 8
prerrequisitos('INF-322', ['INF-225']).
prerrequisitos('INF-343', ['INF-256']).
prerrequisitos('INF-305', []).
prerrequisitos('INF-295', ['INF-292', 'INF-221']).
prerrequisitos('INF-266', ['INF-276']).
prerrequisitos('INF-6', []).

%semestre 9
prerrequisitos('INF-306', []).
prerrequisitos('INF-307', []).
prerrequisitos('INF-311', []).
prerrequisitos('INF-312', []).
prerrequisitos('INF-360', ['INF-322', 'INF-266']).
prerrequisitos('INF-7', []).

%semestre 10
prerrequisitos('INF-308', []).
prerrequisitos('INF-313', []).
prerrequisitos('INF-314', []).
prerrequisitos('INF-228', ['INF-360']).
prerrequisitos('INF-309', ['INF-360']).

%semestre 11

prerrequisitos('INF-310', ['INF-228', 'INF-309']).



%-----------habilitada/2---------------------
%nombre: habilitada
%parametro 1: asignatura a comprobar
%parametro 2: lista de ramos aprobados
%descripcion: dado los parametro determina si la asignatura se puede cursar si los prerrequisitos estan aprobados, para esto utiliza un predicado auxiliar llamado comprobar_prerrequisitos que comprueba si los prerrequisitos se encuentran en la lista de aprobados

habilitada(Asignatura, Aprobados):-
    prerrequisitos(Asignatura, X), %member pero con 2 listas
    comprobar_prerrequisitos(X, Aprobados),!.

%nombre: comprobar_prerrequisitos
%parametro 1: lista de prerrequisitos
%parametro 2: lista de ramos aprobados
%descripcion: dadas las 2 listas verifica si la lista de prerrequisitos se encuentra dentro de la de aprobados, la funcion es practicamente un member que recibe 2 listas y comprueba si la lista 1 esta dentro de la lista 2.

comprobar_prerrequisitos([], _). %corta la recursion
comprobar_prerrequisitos([Prerrequisito|Resto], Aprobados):-
    member(Prerrequisito, Aprobados), %verifia si el primer elemento de la lista de prerrequisitos esta aprobado
    comprobar_prerrequisitos(Resto, Aprobados). %llama de nuevo a la funcion pero con la cola de la lista



%-----------es_prerrequisito/2----------------
%nombre: es_prerrequisito
%parametro 1: prerrequsisito
%parametro 2: asignatura a comprobar
%descripcion: el predicado compureba si el prerrequisito (parametro 1 ) es prerrequisito de la asignatura (parametro 2), para esto utiliza el caso base que es el caso donde es prerrequisito directo, y si es indirecto utiliza recursion para probar con toda la lista de prerrequisitos si es o no es 
es_prerrequisito(Pre, Asignatura):- 
    prerrequisitos(Asignatura, Prerrequisitos),
    member(Pre, Prerrequisitos),!. % si Pre es prerrequisito directo retorna true

es_prerrequisito(Pre, Asignatura):-
    prerrequisitos(Asignatura, Prerrequisitos),
    member(Miau, Prerrequisitos), % se saca una asignatura cualquiera de la lista de Prerrequisitos (un Miau) y se verifica si es prerrequisito o no con recursion
    es_prerrequisito(Pre, Miau),!. %corte para evitar backtracking

%------------permite_dar/3----------------------
%nombre: permite_dar
%parametro 1: Lista de ramos aprobados
%parametro 2: asignatura que se va a aprobar 
%parametro 3: lista de retorno 
%descripcion: dada la lista de asignaturas aprobadas y la asignatura a aprobar, la funcion busca las asignaturas que se van a desbloquear para poder ser tomadas gracias a la aprobacion de la asignatura nueva (parametro 2)
permite_dar(AprobadosPrev, AsignaturaRecienAprobada, NuevosHabilitados):-
    ListaAprobados = [AsignaturaRecienAprobada | AprobadosPrev], %lista actualizada con los ramos aprobados
    findall( Asignatura, (asignatura(Asignatura, _, _, _, _),\+ habilitada(Asignatura, AprobadosPrev), habilitada(Asignatura, ListaAprobados) ) , NuevosHabilitados). % el findall mete en la lista de NuevosHabilitados todas las asignaturas que cumplen que; no estaban habilitadas con la lista antigua de aprobados, y que pasaron a estar habilitadas con la nueva lista de aprobadoss