import random

class Arrastre(object):
    def __init__(self, cartas):
        self.cubiertas = []
        self.descubiertas = []
        for carta in cartas:
            self.cubiertas.append(carta)
        
    def voltear(self):
        if not self.cubiertas: # validar que se tengan cartas para voltear
            print('No hay mas cartas por voltear')
        elif len(self.descubiertas) == 3:
            print('No es posible arrastrar mas cartas')
        else:
            carta = self.cubiertas.pop()
            carta.boca_abajo = False
            self.descubiertas.append(carta)  
              
    def reiniciar_arrastre(self):
        if not self.cubiertas:
            print('No hay mas cartas por mover')
        elif len(self.descubiertas) != 0:
            print('No es posible reiniciar el arrastre')
        else:
            cartas = random.sample(self.cubiertas, k=3)
            for carta in cartas: 
                self.cubiertas.remove(carta)
            for carta in cartas:
                carta.boca_abajo = False
                self.descubiertas.append(carta)
            print('reinicio exitoso')
            
    def mover_descubierta(self):
        if self.descubiertas: # validar que hay cartas
            return self.descubiertas[-1]
        else:
            return 'No hay cartas por mover'
            
    def eliminar_descubierta(self, numero=1):
        for i in range(numero): 
            self.descubiertas.pop()
    
    def print_arrastre(self):
        print('-'*30)
        print('\t   ARRASTRE')
        print('-'*30)
        for i in range(len(self.descubiertas)) if self.descubiertas else range(1):
            if i == 0:
                print(f'descubiertas {len(self.descubiertas)}', f'cubiertas {len(self.cubiertas)}', sep='\t')
                print(self.descubiertas[i] if self.descubiertas else ''*6, '*'*6, sep='\t\t')
            else:
                print(f'{self.descubiertas[i]}')
        print('-'*30)
        print('\n')
            