import re
archivo = open("estrofas.txt", "r", encoding="utf-8")
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

def final_versos(estrofa): #pasarle un indice de lista_estrofa_verso, para rescatar las ultimas palabras de los versos y poder ver las rimas despues
    ultima_palabra_versos = []
    if len(estrofa) == 4:
        for verso in estrofa:
            palabras_verso = verso.split()
            ultima_palabra_versos.append(palabras_verso[-1].lower())
    
    return ultima_palabra_versos

def quitar_tildes_y_signos(ultima_palabra_versos):#quita tildes y signos de cualquier tipo
    palabras_sin_tildes = []
    vocales_a_reemplazar = {"á":"a", "é":"e", "í":"i", "ó": "o", "ú": "u"}
    for palabra in ultima_palabra_versos:
        palabra = re.sub(caracteres_permitidos, "", palabra) #para eliminar caracteres que puedan interferir con la rima
        for tilde, letra in vocales_a_reemplazar.items():
            palabra = re.sub(tilde,letra,palabra)
        palabras_sin_tildes.append(palabra)

    return palabras_sin_tildes

def crear_parejas(palabrasf_verso): #debe recibir una lista de palabras finales de los versos de una estrofa
    lista_parejas = [(palabrasf_verso[0], palabrasf_verso[1]),
                     (palabrasf_verso[0], palabrasf_verso[2]),
                     (palabrasf_verso[0], palabrasf_verso[3]),
                     (palabrasf_verso[1], palabrasf_verso[2]),
                     (palabrasf_verso[1], palabrasf_verso[3]),
                     (palabrasf_verso[2], palabrasf_verso[3]),]
    
    return lista_parejas #tuplas

def rima(palabra1, palabra2): #recibe 2 palabras de la lista de parejas
    puntaje = 0
    rima_estrofa = []
    
    palabra1_invertida = palabra1[::-1] #invierte la palabra para poder comparar las rimas desde el final hacia el inicio
    palabra2_invertida = palabra2[::-1]
    if palabra1== palabra2: # GEMELA
        rima_estrofa.append("gemela")
        puntaje += 1
    elif 

   

#cosas = Estrofas(open("estrofas.txt", "r", encoding="utf-8"))
#print(crear_parejas(quitar_tildes_y_signos(final_versos(cosas[2])))) 
#print(quitar_tildes_y_signos(final_versos(cosas[2])))





"""def rimas(lista_estrofas): 
    lista_validas = estrofas_validas(open("estrofas.txt", "r", encoding="utf-8"))
    contador_val = 0
    for estrofa in lista_estrofas:
        for verso in estrofa:
            if lista_validas[contador_val]:
            
        
        
        contador_val += 1 #para avanzar a la siguiente estrofa valida"""



"""rima = ["consonante",# mismo sufijo con vocales y consonantes
                          # 1 vocal igual 3pts, 2 iguales 4pts, 3 o mas 8pts 
             "asonante",# solo vocales coinciden,
                        # 1 vocal final igual 3pts, 2 vocales finales iguales 4pts, 3 o mas 8pts
             "misma_terminacion", #comparten 2 o mas letras finales pero no cumple con asonante ni consonante 2pts
             "gemela", #palabra identica 1 pt
             "sin rima"] # o pts
# -2pts al puntaje bruto maximo si un verso no rima con nada, se aplica una sola vez por estrofa
# puntajeObtenido/5 redondeado a un decimal
# max 48 rimas, +2 bonus
# max 6 parejas, [1-2, 1-3, 1-4, 2-3, 2-4, 3-4]"""
#---------------------------------------MAIN------------------------------------------------------
