class Torre(object):
    
    def __init__(self, figura):
        self.figura = figura
        self.elementos = []
        
    def insertar(self, carta):
        if not self.validar_figura(carta): # No pasó validación de figura
            return f'No es posible ingresar una carta en una torre diferente a su propia figura'
        
        if not self.validar_puntaje(carta): # No pasó validación de puntaje
            return f'No es posible ubicar la carta {carta} en la torre {self.figura}'
        
        self.elementos.append(carta) # inserta elemnto si pasa validaciones
        return f'Inserción exitosa de la carta {carta} en la Torre {self.figura}'
        
        
    def validar_puntaje(self, carta):
        if not self.elementos: # validar que esté vacia la lista de cartas en la torre
            return carta.puntaje == 1 # devuelve True or false si el primero a insertar es el Az  
        else:
            return self.elementos[-1].puntaje + 1 == carta.puntaje # compara el último elemento con el nuevo a insertar
        
                
    def validar_figura(self, carta):
        return self.figura == carta.figura # mismo tipo de figura a insertar
    
    def print_torre(self):
        print(f'TORRE {self.figura}')
        [print(f'{carta.figura} - {carta.carta}') for carta in self.elementos]
        
    def __str__(self):
        return self.figura
    
    def __len__(self):
        return len(self.elementos)