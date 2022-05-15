class Columna(object):
    
    def __init__(self, columna_id):
        self.columna_id = columna_id 
        self.elementos = []
        
    def default_cartas(self, carta):
        self.elementos.append(carta)    
    
    def voltear_ultima(self):
        try:
            self.elementos[-1].boca_abajo = False
        except IndexError:
            pass
        
    def insertar(self, carta):
        if not self.validar_puntaje(carta) or not self.validar_color(carta): # No pasó validación de puntaje
            return f'No es posible ubicar la carta {carta} en la columna {self.columna_id}'
        
        self.elementos.append(carta) # inserta elemento si pasa validaciones
        return f'Inserción exitosa de la carta {carta} en la columna {self.columna_id}'
        
    def validar_puntaje(self, carta):
        if not self.elementos: 
            return True if carta.puntaje == 13 else False
        else:
            return self.elementos[-1].puntaje == carta.puntaje + 1
        
    def validar_color(self, carta):
        if not self.elementos: 
            return True
        else:
            return self.elementos[-1].color != carta.color 
        
        
    def pasar_n_cartas(self, n=1): # mover cartas a otra columna
        if n > len(tuple(filter(lambda x: not x.boca_abajo, self.elementos))):
            return f'No es posible pasar a la otra columna mas cartas de las que se tienen'
        return self.elementos[len(self.elementos)-n:]
    
    def borrar_n_cartas(self, n):
        for i in range(n): 
            self.elementos.pop() # borrar los que se acabaron de mover
        self.voltear_ultima() # visualizar la última
        
    def voltear_carta(self):
        message = ''
        try:
            self.elementos[-2].boca_abajo = False
            message = 'se destapó una nueva carta en la columna {self.columna_id}, su valor es {self.elementos[-1]}'
        except KeyError:
            message = 'No es posible voltear más cartas'
        finally:
            return message
        
    def print_columna_entera(self):
        # [print(f'{carta.figura} - {carta.carta}') for carta in filter(lambda x: not x.boca_abajo, self.elementos)]
        for carta in self.elementos:
            if not carta.boca_abajo:
                print(f'{carta.figura} - {carta.carta}')
            else:
                print('******')
                
    def beautiful_print(self, index):
        try:
            # breakpoint()
            message = f'{self.elementos[index].figura} - {self.elementos[index].carta}' if not self.elementos[index].boca_abajo else '******'
        except IndexError:
            message = ' '*6  # blank space
        finally:
            return message 
        
    def volteadas(self):
        return len(list(filter(lambda x: not x.boca_abajo, self.elementos)))
    
    def __len__(self):
        
        return len(self.elementos)
        