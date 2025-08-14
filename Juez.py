import re

archivo = open("estrofas.txt", "r")
contador = 1
# poner el ^ dentro de [] niega todo lo que esta dentro del set

simbolos_invalidos = "[^a-zA-ZñÑ0-9¿?¡!,\\.\-;'-():\"\s]"#corregir, no reconoce bien los ¿
simbolos_validos = "[a-zA-ZñÑ0-9?¿¡!,.-\;'-():\"]"
digitos = "[0-9]"
vocal = "(AEIOU|aeiou)"
vocal_acentuada = "(ÁÉÍÓÚ|áéíóú)"
letras = "[A-Za-zÑñ]"
string = "[]"
espacio = "^\s$"

def estrofas_validas(archivo):
    lista_versos = []
    lista_estrofas = []
    ignorar_bonus = True
    Estrofa = True
    for linea in archivo:
        if ignorar_bonus:
            ignorar_bonus = False
            continue
        elif not re.match(espacio, linea):
            lista_versos.append(linea)
        else:
            if len(lista_versos) == 4:
                for verso in lista_versos:
                    coso = re.compile(r"[a-zA-ZáéíóúÁÉÍÓÚñÑ¿?!\",.;:'-]+")
                    if coso.findall(verso):
                        continue
                    else:
                        Estrofa = False
                        lista_versos = []
                        break

                    """if re.search(simbolos_invalidos, verso):
                        Estrofa = False
                        lista_versos = []
                        break"""
                lista_estrofas.append(Estrofa)
            else:
                lista_estrofas.append(False)
            lista_versos = []
#elementos de la ultima estrofa 
    if len(lista_versos) > 0:
        if len(lista_versos) == 4:
            for verso in lista_versos:
                if re.search(simbolos_invalidos, verso):
                    Estrofa = False
                    lista_versos = []
                    break 
            lista_estrofas.append(Estrofa)
        else:
            lista_estrofas.append(False)       
            
    lista_estrofas.pop(0)
    return lista_estrofas

print(estrofas_validas(archivo))