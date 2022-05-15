from doctest import ELLIPSIS_MARKER
import random

# importación de clases
from carta import Carta
from torres import Torre
from columna import Columna
from arrastre import Arrastre

FIGURAS = ('D', 'C', 'T', 'P',)
CARTAS = ('A', '2', '3', '4', '5', '6', '7', '8', '9', '10', 'J', 'Q', 'K',)
PUNTAJE = (1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,)
COLUMNA = [Columna(i) for i in range(1,8)] # lista de la Clase columna
TORRE = {figura: Torre(figura) for figura in FIGURAS}
# * list comprehension, crea la baraja completa con las 52 cartas
BARAJA = [Carta(figura, carta, puntaje, color='rojo' if figura in ['D', 'C'] else 'negro') for figura in FIGURAS for carta, puntaje in zip(CARTAS, PUNTAJE)]

# proceso para barajar
# * random.sample porque shuffle esta deprecated despues de Python 3.9
for i in range(7):
    sample = random.sample(BARAJA, k=i+1)
    for carta in sample:
        COLUMNA[i].default_cartas(carta) # Agregar a columna y remover de la baraja
        BARAJA.remove(carta)
    COLUMNA[i].voltear_ultima() 

arrastre = Arrastre(random.sample(BARAJA, k=len(BARAJA)))
del BARAJA 

def print_columnas():
    arrastre.print_arrastre()
    for i in range(-1,max([columna.__len__() for columna in COLUMNA])): # imprimer en pantalla las columnas
        if i == -1:
            for k in range(7):
                print(f' col{k+1} ', end='\t')
            print()
        else:
            for j in range(7):
                print(COLUMNA[j].beautiful_print(i), end='\t')
            print()
            
def examinar_juego():
    if all(map(lambda x: x.__len__() == 13, TORRE.values())): # validar todas las torres llenas
        print('Felicidades!!, has ganado el juego')
    # else:
    #     print('Aún quedan cartas por ubicar ._.')
        

def llevar_de_cola_a_torre():
    figura = input('¿A cúal Torre (P, C, T ó D)? ')
    if figura not in FIGURAS:
        print('Selección de torre inválida')
    else:
        carta = arrastre.mover_descubierta()
        if isinstance(carta, Carta):
            insercion = TORRE[figura].insertar(carta)
            if not insercion.startswith('No es'):
                arrastre.eliminar_descubierta() 
                print(insercion)
        else:
            print(carta)    
            
def llevar_de_cola_a_columna():
    columna = input('¿A cúal Columna (1, 2, ..., 7)? ')
    if columna.isdigit() and int(columna) not in range(1,8):
        print('Selección de Columna inválida')
    else:
        carta = arrastre.mover_descubierta()
        if isinstance(carta, Carta):
            insercion = COLUMNA[int(columna)-1].insertar(carta)
            if not insercion.startswith('No es'):
                arrastre.eliminar_descubierta() 
            print(insercion)
        else:
            print(carta)
            
def llevar_de_columna_X_a_Torre_Y():
    columna1 = input('¿Desde cúal Columna (1, 2, ..., 7)? ')
    torre = input('A cúal Torre (P, C, T ó D)? ')
    if not columna1.isdigit() or torre not in TORRE.keys():
        print('Selección de Columna inválida')
    columna1 = int(columna1)
    if columna1 not in range(1,8):
        print('Selección de Columna inválida')
    else:
        carta = COLUMNA[columna1-1].pasar_n_cartas(n=1)
        insercion = TORRE[torre].insertar(*carta)
        if not insercion.startswith('No es'):
            COLUMNA[columna1-1].borrar_n_cartas(n=1)
        print(insercion)
            
def llevar_Z_carta_de_columna_X_a_Y():
    columna1 = input('¿Desde cúal Columna (1, 2, ..., 7)? ')
    Z = input('¿Cúantas cartas? ')
    columna2 = input('A cúal Columna (1, 2, ..., 7)? ')
    if not columna1.isdigit() or not columna2.isdigit():
        print('Selección de Columna inválida')
    columna1 = int(columna1)
    columna2 = int(columna2)
    if columna1 not in range(1,8) or columna2 not in range(1,8) or columna1 == columna2:
        print('Selección de Columna inválida')
    elif not Z.isdigit():
        print('Número invalido')
    elif int(Z) > COLUMNA[columna1-1].volteadas():
        print(f'No hay esa cantidad de cartas volteadas en la Columna {columna1}')
    else:
        cartas = COLUMNA[columna1-1].pasar_n_cartas(n=int(Z))
        carta1 = cartas.pop(0)
        pre_insert = COLUMNA[columna2-1].insertar(carta1)
        if not pre_insert.startswith('No es'):
            for carta in cartas[::-1]: # No se validan las cartas en conjunto porque se suponen ya estan en orden
                insercion = COLUMNA[columna2-1].insertar(carta)
            COLUMNA[columna1-1].borrar_n_cartas(n=int(Z)) 
            print(f'Inserción exitosa de las {Z} cartas')
        else:
            print(pre_insert)
            
def menu():
    print('\t   **** MENÚ **** ')
    print('1. Destapar Cola de arrastre')
    print('2. Reiniciar Cola de arrastre')
    print('3. Llevar de Cola de arrastre a Torre de figura X')
    print('4. Llevar de Cola de arrastre a Columna Y')
    print('5. LLevar de Columna Y a Torre X')
    print('6. Llevar Z cartas de Columna X a Columna Y')
    print('7. Imprimir Torres')
    print('8. Imprimir tablero')
    print('9. salir')
    select = input()
    if not select.isdigit():
        print('Selcción inválida')
    elif int(select) in range(1,10):
        if select == '1':
            arrastre.voltear()
        elif select == '2':
            arrastre.reiniciar_arrastre()
        elif select == '3':
            llevar_de_cola_a_torre()
        elif select == '4':
            llevar_de_cola_a_columna()
        elif select == '5':
            llevar_de_columna_X_a_Torre_Y()
        elif select == '6':
            llevar_Z_carta_de_columna_X_a_Y()
        elif select == '7':
            [torre.print_torre() for torre in TORRE.values()]
        elif select == '8':
            print_columnas()
        elif select == '9':
            print('Salida exitosa del juego')
        examinar_juego()
        return select 
    print('Seleccion inválida')
        
select = 0
print_columnas()
print()
while select != '9':
    select = menu()
        
            

            
            
                
    