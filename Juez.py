import re

archivo = open("estrofas.txt", "r")
contador = 1
# poner el ^ dentro de [] niega todo lo que esta dentro del set

simbolos_validos = "[a-zA-ZñÑ0-9?¿¡!,.;'-():\"]"
digitos = "[0-9]"
vocal = "(AEIOU|aeiou)"
vocal_acentuada = "(ÁÉÍÓÚ|áéíóú)"
letras = "[A-Za-zÑñ]"
string = "[]"

"""for linea in archivo:
    #if not re.search(simbolos_validos, linea):
    contador + 1
    if"""
"""
x = input("pone un correo wom: ")
patern = "[A-Za-z0-9]+@[a-z]+\.(com|.cl)"
if re.search(patern, x):
    print ("valida la wea")
"""