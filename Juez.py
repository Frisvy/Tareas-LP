import re
#archivo = open("estrofas.txt", "r", encoding="utf-8")
# poner el ^ dentro de [] niega todo lo que esta dentro del set, se supone...
#LA PALABRA BONUS DEBE HACER RIMA
#-------------------------------------EBNF-----------------------------------------------------------------
espacio = r"\s"   # se utiliza para buscar los saltos de linea despues de cada estrofa
caracteres_permitidos = r"[¿?¡!,.;'():\"-]" 
digitos = r"[0-9]"
vocal = r"[AEIOUaeiou]"
vocal_acentuada = r"[ÁÉÍÓÚáéíóú]"
letras = r"[A-Za-zÑñ]"
string = rf"(?:{vocal}|{vocal_acentuada}|{letras})"
palabra = rf"(?:{string}|{caracteres_permitidos}|{digitos}|{espacio})+"

#-------------------------------------FUNCIONES-----------------------------------------------------------
"""
***
Nombre: bonus
***
Parametro 1: Archivo
***
Tipo de retorno: Lista de strings
***
Descripcion: Lee la primera linea de estrofas.txt y retonar una lista con las palabras Bonus
"""

def bonus(archivo):
    lista_bonus = []
    for linea in archivo:
        lista_bonus.append(linea.strip()) 
        break
    return lista_bonus

"""
***
Nombre: estrofas_validas
***
Parametro 1: Archivo
***
Tipo de retorno: Lista de booleanos
***
Descripcion: Recibe estrofas.txt y retorna una Lista de Booleanos con True o False 
             dependiendo de si la estrofa es valida o no
"""
def estrofas_validas(archivo):
    lista_versos = []
    lista_estrofas = []
    ignorar_bonus = True
    Estrofa = True
    for linea in archivo:
        if ignorar_bonus:
            ignorar_bonus = False
            continue
        elif not re.match(espacio, linea): #si la linea tiene texto, se añade a la lista de versos
            lista_versos.append(linea)
        else:
            if len(lista_versos) == 4:
                for verso in lista_versos:
                    if re.fullmatch(palabra, verso):
                        continue
                    else:
                        Estrofa = False
                        lista_versos = []
                        break
        
                lista_estrofas.append(Estrofa)
                Estrofa = True
            else:
                lista_estrofas.append(False)
            lista_versos = []            
# como no hay salto de linea despues de la ultima estrofa, no se revisa en el ciclo de arriba.

    if len(lista_versos) > 0:
        if len(lista_versos) == 4:
            for verso in lista_versos:
                if re.fullmatch(palabra, verso):
                    continue
                else:
                    Estrofa = False
                    lista_versos = []
                    break
                
            lista_estrofas.append(Estrofa)
        else:
            lista_estrofas.append(False)
           
            
    lista_estrofas.pop(0) # elimina la linea de bonus de la lista para que hayan solo estrofas
    return lista_estrofas

#print(estrofas_validas(archivo))

"""
***
Nombre: Estrofas
***
Parametro 1: Archivo
***
Tipo de retorno: Lista
***
Descripcion: Lee estrofas.txt y retorna una lista de listas en donde la lista de mas afuera representa las estrofas,
             y la de mas adentro los versos, sigue el siguiente formato 
             [[verso1_estrofa1,verso2_estrofa1...],[[verso1_estrofa2,verso2_estrofa2]....,]...[...]]
"""
def Estrofas(archivo):
    lista_estrofas_validas = estrofas_validas(open("estrofas.txt", "r", encoding="utf-8")) 
    estrofa_actual = 0
    lista_estrofa_verso = [] #lista con el contenido de todos los versos
    bonus = True
    salta_bonus = 0
    for cosa in lista_estrofas_validas:
        lista_estrofa_verso.append([]) # lista de listas donde cada elemento sera una estrofa con todos sus versos

    for linea in archivo:
        if bonus:
            salta_bonus +=1
            if salta_bonus == 2:
                bonus = False

        else:
            if lista_estrofas_validas[estrofa_actual]:
                if len(lista_estrofa_verso[estrofa_actual]) < 4:
                    lista_estrofa_verso[estrofa_actual].append(linea.strip())
                else:
                    estrofa_actual += 1
            else:
                lista_estrofa_verso[estrofa_actual].append(linea.strip())
                if lista_estrofa_verso[estrofa_actual][-1] == "":
                        estrofa_actual += 1

    return lista_estrofa_verso

#print(Estrofas(open("estrofas.txt", "r", encoding="utf-8")))

#lista_validas = [[verso1,verso2,verso3,verso4], [verso5,verso6,verso7,verso8],...]

"""
***
Nombre: final_versos
***
Parametro 1: lista de strings 
***
Tipo de retorno: lista de strings
***
Descripcion: recibe una lista de estrofas con versos y retorna una lista con unicamente las ultimas palabras de los versos
"""
def final_versos(estrofa): #pasarle un indice de lista_estrofa_verso, para rescatar las ultimas palabras de los versos y poder ver las rimas despues
    ultima_palabra_versos = []
    if len(estrofa) == 4:
        for verso in estrofa:
            palabras_verso = verso.split()
            ultima_palabra_versos.append(palabras_verso[-1].lower())
    
    return ultima_palabra_versos

"""
***
Nombre: quitar_tildes_signos
***
Parametro 1: Lista de strings
***
Tipo de retorno: Lista de strings
***
Descripcion: Recibe una lista que contenga la ultima palabra de los versos de una estrofa
             y retorna las mismas palabras pero sin signos como ".,-" y sin tildes, ademas
             pasa todas las palabras a minusculas
"""
def quitar_tildes_y_signos(ultima_palabra_versos):#quita tildes y signos de cualquier tipo
    palabras_sin_tildes = []
    vocales_a_reemplazar = {"á":"a", "é":"e", "í":"i", "ó": "o", "ú": "u"}
    for palabra in ultima_palabra_versos:
        palabra = re.sub(caracteres_permitidos, "", palabra) #para eliminar caracteres que puedan interferir con la rima
        palabra = re.sub(digitos,"",palabra) #elimina los numeros
        for tilde, letra in vocales_a_reemplazar.items():
            palabra = re.sub(tilde,letra,palabra)
        palabras_sin_tildes.append(palabra)

    return palabras_sin_tildes

"""
***
Nombre: Lista_definitiva
***
Parametro 1: Lista de strings con la ultima palabra de cada verso
Parametro 2: Lista boleanos con las estrofas validas
***
Tipo de retorno: Lista de strings
***
Descripcion: Recibe una lista que contenga la ultima palabra de los versos de una estrofa
             y una lista de booleanos con la cantidad de estrofas validas o invalidas (sacada de la funcion lista_estrofas_validas)
             la funcion retorna una lista con las 6 tuplas posibles de un verso valido,
             en caso de que la estrofa sea invalida, esta lista tendra una tupla de (False, False)
"""
def Lista_definitiva(lista_estrofas_casi_lista, estrofas_validas):
    lista_retorno = []
    for estrofa, valida in zip(lista_estrofas_casi_lista, estrofas_validas): 
        lista_parejas = []
        if valida:
            for i in range(len(estrofa)):
                for j in range( i + 1 ,len(estrofa)):
                    parejas = (estrofa[i],estrofa[j]) 
                    lista_parejas.append(parejas)
                    #lista_retorno.append(lista_parejas)
            lista_retorno.append(lista_parejas)
        else:
            lista_retorno.append([(False,False)])

    return lista_retorno

"""
***
Nombre: largo_rima_consonante
***
Parametro 1: string de palabra1 a comparar
Parametro 2: string de palabra2 a comparar
***
Tipo de retorno: int del largo de la rima consonante encontrada
***
Descripcion: Dadas 2 palabras se calcula el largo del sufijo que tienen en comun (rima), y se retorna.
             para esto se crea un patron con las letras de la palabra mas grande, despues de eso se va
             comparando caracter a caracter desde el final hacia el inicio de la palabra. El ciclo
             se corta una vez se encuentra una palbra distinta, acto seguido se retorna el largo en comun de la rima
"""

def largo_rima_consonante(palabra1,palabra2): #permite saber cuanto puntaje hay que otorgar con la rima consonante
    largo_comun = 0
    max_largo = min(len(palabra1), len(palabra2)) #largo max teorico que podria tener el sufijo en comun
    while largo_comun < max_largo:
        largo_prueba = largo_comun + 1
        sufijo1 = palabra1[-largo_prueba:]
        sufijo2 = palabra2[-largo_prueba:]
        patron_sufijo = rf"{sufijo1}$" # creamos un patron con el prefijo que queremos comparar, para ver si son iguales entre las 2 palabras
        if re.search(patron_sufijo, palabra2):
            largo_comun = largo_prueba 
        else:
            break
    return largo_comun

#print(largo_rima_consonante("inicial","superficial"))

"""
***
Nombre: vocales_rima
***
Parametro 1: string de palabra a modificar
***
Tipo de retorno: string de vocales
***
Descripcion: Dado el string de una palabra, se usa el findall para encontrar todas las coincidencias de vocales
             en una palabra, para acto seguido unirlas todas en un unico strig de vocales. Es decir
             se retorna la palabra unicamente con sus vocales
"""
def vocales_rima(palabra): #retorna unicamente las vocales de una palabra
    return "".join(re.findall(vocal,palabra))

"""
***
Nombre: largo_rima_asonante
***
Parametro 1: string de palabra1 a comparar
Parametro 2: string de palabra2 a comparar
***
Tipo de retorno: int correspondiente del largo de la rima
***
Descripcion: Dadas 2 palabras, se extraen todas sus consonantes con la funcion vocales_rima
             para dejar unicamente las vocales, acto seguido se van comparando las vocales desde el
             final hacia el principio para determinar el largo de la rima asonante. La logica de esta
             funcion es practicamente la misma que la de la funcion largo_rima_consonante
"""

def largo_rima_asonante(palabra1,palabra2): #la estructura es practicamente la misma de la funcion de la rima asonante
    vocalesp1 = vocales_rima(palabra1)
    vocalesp2 = vocales_rima(palabra2)
    largo_comun = 0
    max_largo = min(len(vocalesp1), len(vocalesp2))
    while largo_comun < max_largo:
        largo_prueba = largo_comun + 1
        vocales_finales1 = vocalesp1[-largo_prueba:]
        vocales_finales2 = vocalesp2[-largo_prueba:]
        patron_vocales1 = rf"{vocales_finales1}$"
        if re.search(patron_vocales1,   vocalesp2):
            largo_comun = largo_prueba
        else:
            break
    return largo_comun

"""
***
Nombre: rimas
***
Parametro 1: Lista de estrofas con sus tuplas correspondientes
Parametro 2: Lista de palabras bonus
***
Tipo de retorno 1: Lista con los tipos de rima encontrados por estrofa
Tipo de retorno 2: Lista con los puntajes brutos de cada tupla
***
Descripcion: Dadas las listas, se calculan las rimas encontradas en cada estrofa.
             Ademas de esto se llena una lista con el puntaje bruto de cada tupla
***
"""
#print(largo_rima_asonante("malambo","mangos"))
# Lista_final_estrofas = [[()]]
def rimas(lista_final_estrofas,lista_bonus): #pasar bonus limpio
    lista_rimas = []
    Sin_rima = True
    Bonus = True
    puntaje = []
    numero_de_estrofa = 0
    for estrofa in lista_final_estrofas:
        lista_rimas.append([])
        puntaje.append([])
        Bonus = True
        Sin_rima = True
        for lista_tupla in estrofa:
            if lista_tupla[0] != False:
                asonante = largo_rima_asonante(lista_tupla[0],lista_tupla[1])
                consonante = largo_rima_consonante(lista_tupla[0],lista_tupla[1])
#---------------------GEMELA------------------------------------                
                if lista_tupla[0] == lista_tupla[1]: #comprobar si son gemelas
                    lista_rimas[numero_de_estrofa].append("gemela")
                    puntaje[numero_de_estrofa].append(1)
                    if Bonus:
                        if (lista_tupla[0] in lista_bonus) or (lista_tupla[1] in lista_bonus):
                            puntaje[numero_de_estrofa].append(2)
                            lista_rimas[numero_de_estrofa].append("Bonus")
                            Bonus = False
#--------------------CONSONANTE-------------------------------
                elif consonante >= 5:
                    puntaje[numero_de_estrofa].append(8)
                    lista_rimas[numero_de_estrofa].append("consonante")
                    if Bonus:
                        if (lista_tupla[0] in lista_bonus) or (lista_tupla[1] in lista_bonus):
                            puntaje[numero_de_estrofa].append(2)
                            lista_rimas[numero_de_estrofa].append("Bonus")
                            Bonus = False
                elif (consonante == 3 or consonante == 4) and (asonante < 3): #el and esta para que se priorize la rima que de mas puntaje, en caso de revisar una estrofa con solo rimas consonantes basta borrar el and para que se les de prioridad a estas y se calcule solo ese puntaje :3
                    puntaje[numero_de_estrofa].append(5)
                    lista_rimas[numero_de_estrofa].append("consonante")
                    if Bonus:
                        if (lista_tupla[0] in lista_bonus) or (lista_tupla[1] in lista_bonus):
                            puntaje[numero_de_estrofa].append(2)
                            lista_rimas[numero_de_estrofa].append("Bonus")
                            Bonus = False
#---------------------ASONANTE--------------------------------
                elif asonante >= 3:
                    puntaje[numero_de_estrofa].append(8)
                    lista_rimas[numero_de_estrofa].append("asonante")
                    if Bonus:
                        if (lista_tupla[0] in lista_bonus) or (lista_tupla[1] in lista_bonus):
                            puntaje[numero_de_estrofa].append(2)
                            lista_rimas[numero_de_estrofa].append("Bonus")
                            Bonus = False
                elif asonante == 2:
                    puntaje[numero_de_estrofa].append(4)
                    lista_rimas[numero_de_estrofa].append("asonante")
                    if Bonus:
                        if (lista_tupla[0] in lista_bonus) or (lista_tupla[1] in lista_bonus):
                            puntaje[numero_de_estrofa].append(2)
                            lista_rimas[numero_de_estrofa].append("Bonus")
                            Bonus = False
                elif asonante == 1:
                    puntaje[numero_de_estrofa].append(3)
                    lista_rimas[numero_de_estrofa].append("asonante")
                    if Bonus:
                        if (lista_tupla[0] in lista_bonus) or (lista_tupla[1] in lista_bonus):
                            puntaje[numero_de_estrofa].append(2)
                            lista_rimas[numero_de_estrofa].append("Bonus")
                            Bonus = False
#----------------------------MISMA_TERMINACION--------------------
                elif asonante == 0 and consonante == 2: #super raro que esto llegue a pasar...
                    puntaje[numero_de_estrofa].append(2)
                    lista_rimas[numero_de_estrofa].append("misma terminacion")
                    if Bonus:
                        if (lista_tupla[0] in lista_bonus) or (lista_tupla[1] in lista_bonus):
                            puntaje[numero_de_estrofa].append(2)
                            lista_rimas[numero_de_estrofa].append("Bonus")
                            Bonus = False
#-----------------------------SIN RIMA----------------------------                
                elif asonante == 0 and consonante == 0:
                    puntaje[numero_de_estrofa].append(0)
                    lista_rimas[numero_de_estrofa].append("sin rima")
                    if Sin_rima:
                        puntaje[numero_de_estrofa].append(-2)
                        Sin_rima = False
        numero_de_estrofa += 1
    return lista_rimas, puntaje #puntaje bruto

"""
***
Nombre: lista_rimas_sin_repeticion
***
Parametro 1: Lista de rimas encontradas por estrofa
***
Tipo de retorno 1: Lista con los tipos de rima encontrados por estrofa
***
Descripcion: Recibe la lista de rimas encontradas por tupla. Con esta lista
             se borran todos los elementos repetidos para que a la hora de escribir
             el archivo sea mas facil, y ademas no salgan elementos repetidos, por ejemplo
             Rimas: Asonante Asonante Asonante. En vez de eso solo saldra Asonante una vez
***
"""

def lista_rimas_sin_repeticion(lista_rimas):
    rimas_estrofa_sin_repetidos = []
    for estrofa in lista_rimas:
        miau = [] #se me acabaron los nombres 
        for rima in estrofa:
            if rima not in miau:
                miau.append(rima)
        rimas_estrofa_sin_repetidos.append(miau)

    return rimas_estrofa_sin_repetidos

"""
***
Nombre: decision
***
Parametro 1: Lista de rimas por estrofa
Parametro 2: Lista de puntajes brutos
***
Tipo de retorno 1: no retorna
***
Descripcion: Recibe la lista de Rimas por estrofa y la lista de puntajes brutos por estrofa para escribir
             el archivo "decision.txt".para eso la funcion primero verifica si hay algun tipo de rima en la 
             lista de rimas, si no encuentra ninguna rima implica que la estrofa era invalida, por lo cual escribe 
             Estrofa i: Invalida. Si se encuentra la rima, se escribe el numero de estrofa, con su puntaje correspondiente
             y las rimas encontradas 
***
"""
def decision( lista_rimas, lista_puntajes_brutos):
    with open("decision.txt", "w", encoding="utf-8") as decision:
        i = 1
        for (estrofa_r, estrofa_p) in zip(lista_rimas, lista_puntajes_brutos): 
            if not estrofa_r:
                decision.write(f"Estrofa {i}: Invalida\n")
            else:
                puntaje = round((sum(estrofa_p)/5),1) # se calcula el puntaje en escala 0/10 redondeado a 1 decimal
                if puntaje < 0 : # en caso de que el puntaje sea negativo se deja en 0
                    puntaje = 0
                decision.write(f"Estrofa {i}: {puntaje}\n")
                decision.write(f"Rimas : {', '.join(estrofa_r)}\n")
            i += 1

#---------------------------------------MAIN------------------------------------------------------
""" Se llaman a las funciones para armar el archivo, el for termina de crear y armar las listas 
    necesarias para detectar las rimas y esas cosas, hay comentarios para debugear el codigo
    de forma mas facil en caso de ser necesario"""

linea_bonus = bonus(open("estrofas.txt", "r", encoding="utf-8"))[0]
palabras_bonus = linea_bonus.split()
palabras_bonus = quitar_tildes_y_signos(palabras_bonus)
#print(palabras_bonus)

Estrofas_validas = estrofas_validas(open("estrofas.txt", "r", encoding="utf-8")) #Se crea una lista de estrofas validas con booleanos #print(Estrofas_validas)
#print(Estrofas_validas)
Estrofas_versos = Estrofas(open("estrofas.txt", "r", encoding="utf-8")) #se crea una lista de lista con el formato [[verso1estrofa1,verso2estrofa1...],[verso1estrofa2,verso2estrofa2,...]] 
#print(Estrofas_versos)
Lista_estrofas_casi_lista = [] 
for Estrofa in Estrofas_versos: #ciclo para limpiar las palabras de la lista Estrofas_versos
    Estrofa_con_palabras_finales = quitar_tildes_y_signos(final_versos(Estrofa)) #los versos pasan a tener solo las palabras finales y a tener las palabras en minusculas sin simbolos permitidos
    Lista_estrofas_casi_lista.append(Estrofa_con_palabras_finales)  #queda con las palabras finales sin simbolos raros ni nada

lista_final_estrofas = Lista_definitiva(Lista_estrofas_casi_lista, Estrofas_validas) #lista con todas las tuplas por estrofa, si la estrofa es invalida en vez de tuplas con palabras  hay un (False, False) ademas de eso las parejas tienen todos los simbolos limpios, no considera numeros la limpieza

lista_rimas, lista_puntajes_brutos = rimas(lista_final_estrofas, palabras_bonus)
lista_rimas = lista_rimas_sin_repeticion(lista_rimas)
decision(lista_rimas,lista_puntajes_brutos)

#En caso de querer ver el calculo de rimas por estrofa y pareja utilizar este print: print(rimas(lista_final_estrofas, palabras_bonus))

#print(Lista_estrofas_casi_lista)
#print(lista_final_estrofas,palabras_bonus)
#print(rimas(lista_final_estrofas, palabras_bonus))


