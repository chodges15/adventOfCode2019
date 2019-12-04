
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
        return self.direction == other.direction and self.magnitude == other.magnitude

    direction: Direction
    magnitude: int


class CrossedWires(object):
    def __init__(self, wire1: str, wire2: str):
        self.vectors = [self.parseWire(wire1), self.parseWire(wire2)]

    def parseWire(self, wire: str) -> List[Vector]:
        vector_list = []
        for raw_vector in wire.split(','):
            vector = Vector()
            vector.direction = self.decode_direction[raw_vector[0]]
            vector.magnitude = str(raw_vector[1:])
            vector_list.append(vector)
        return vector_list

    vectors: List[List[Vector]]
    decode_direction = {
        'L': Direction.LEFT,
        'R': Direction.RIGHT,
        'U': Direction.UP,
        'D': Direction.DOWN
    }


