class Nodo(object):
    def __init__(self, name=None, value=None):
        self.name = name
        self.value = value
        self.lnode = None
        self.rnode = None

class ArbolHuffman(object):
    def __init__(self, frecuencia):
        self.dicc = {}
        self.fork = list() 
        self.algor_huff = list(range(10))
        for nombre,valor in frecuencia.items():
          self.fork += [Nodo(nombre,valor)]
        
        while len(self.fork) != 1:
            self.fork.sort(key=lambda node:node.value, reverse=True)
            n = Nodo(value=(self.fork[-1].value + self.fork[-2].value))
            n.lnode = self.fork.pop(-1)
            n.rnode = self.fork.pop(-1)
            self.fork.append(n)
        
        self.root = self.fork[0]

    def binary_tree(self, tree, length):
        node = tree
        if not node:
            return
        elif node.name:
          secuence = ""
          for i in range(length):
            secuence += str(self.algor_huff[i])
          self.dicc[node.name] = secuence 
          return
        self.algor_huff[length] = 0
        self.binary_tree(node.lnode, length + 1)
        self.algor_huff[length] = 1
        self.binary_tree(node.rnode, length + 1)

    def active_binary(self):
        self.binary_tree(self.root, 0)
        return self.dicc

def frecuencia(texto):
    result = {}
    for i in texto.lower():
        result[i] = result.get(i, 0) + 1
    return result

text = input('ingrese el texto a codificar (unicamente caracteres alfabeticos o espacios): ')
frec = frecuencia(text)
tree = ArbolHuffman(frec)
print("El mensaje codificado con el algoritmo de HUFFMAN es:")
byte_map = tree.active_binary()
print(byte_map)
for i in text.lower():
  print(byte_map[i], end='')
print()
