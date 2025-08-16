import re
archivo = open("estrofas.txt", "r", encoding="utf-8")
# poner el ^ dentro de [] niega todo lo que esta dentro del set, se supone...
#LA PALABRA BONUS DEBE HACER RIMA
# EBNF -------------------------------------------------------------------------------------
espacio = r"\s"   # se utiliza para buscar los saltos de linea despues de cada estrofa
caracteres_permitidos = r"[¿?¡!,.;'():\"-]" 
digitos = r"[0-9]"
vocal = r"[AEIOUaeiou]"
vocal_acentuada = r"[ÁÉÍÓÚáéíóú]"
letras = r"[A-Za-zÑñ]"
string = rf"(?:{vocal}|{vocal_acentuada}|{letras})"
palabra = rf"(?:{string}|{caracteres_permitidos}|{digitos}|{espacio})+"
#---------------------------------------------------------------------------------------------

tipo_rima = ["consonante", "asonante", "gemela", ]

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

print(estrofas_validas(archivo))

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

print(Estrofas(open("estrofas.txt", "r", encoding="utf-8")))

#def consonantes()