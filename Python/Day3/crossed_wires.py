
from enum import Enum
from typing import List


class Direction(Enum):
    LEFT = 'L'
    RIGHT = 'R'
    UP = 'U'
    DOWN = 'D'


class Vector(object):
    def __init__(self, direction: int = Direction.UP, magnitude: int = 0):
        self.direction = direction
        self.magnitude = magnitude

    def __repr__(self):
        return "{}{}".format(self.direction.value, str(self.magnitude))

    def __eq__(self, other):
        print("Hello")
        return self.direction == other.direction and self.magnitude == other.magnitude

    direction: Direction
    magnitude: int


class Grid(object):
    MAX_MAGNITUDE = 1000
    ORIGIN = (MAX_MAGNITUDE, MAX_MAGNITUDE)
    grid = []

    def __init__(self):
        self.grid = [[0 for x in range(0, self.MAX_MAGNITUDE*2+1)]
                        for y in range(0, self.MAX_MAGNITUDE*2+1)]

    def __repr__(self):
        pass

    def update_grid_with_vector_list(self, vlist: List[Vector], current_point: (int, int)):
        for this_vector in vlist:



class CrossedWires(object):
    def __init__(self, wire1: str, wire2: str):
        self.grid = Grid()
        self.vectors = [self.parse_wire(wire1), self.parse_wire(wire2)]

    def parse_wire(self, wire: str) -> List[Vector]:
        vector_list = []
        for raw_vector in wire.split(','):
            vector = Vector()
            vector.direction = self.decode_direction[raw_vector[0]]
            vector.magnitude = int(raw_vector[1:])
            vector_list.append(vector)
        return vector_list

    vectors: List[List[Vector]]
    decode_direction = {
        'L': Direction.LEFT,
        'R': Direction.RIGHT,
        'U': Direction.UP,
        'D': Direction.DOWN
    }


