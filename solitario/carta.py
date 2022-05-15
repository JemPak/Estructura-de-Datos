class Carta(object):
    def __init__(self, figura, carta, puntaje, color):
        self.figura = figura
        self.carta = carta
        self.puntaje = puntaje
        self.color = color
        self.boca_abajo = True
        
    def __str__(self):
        figura = {'P': 'Pica', 'C': 'Corazon', 'T': 'Trébol', 'D': 'Diamante'}
        return f'{figura[self.figura]} - {self.carta}'
