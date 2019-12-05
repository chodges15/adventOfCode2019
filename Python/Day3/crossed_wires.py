
from enum import Enum
from typing import List
from Python.Day3.bcolors import Bcolors


class Direction(Enum):
    LEFT = 'L'
    RIGHT = 'R'
    UP = 'U'
    DOWN = 'D'
    def isVertical(self):
        return self.value == 'U' or self.value == 'D'


class Vector(object):
    def __init__(self, direction: int = Direction.UP, magnitude: int = 0):
        self.direction = direction
        self.magnitude = magnitude

    def __repr__(self):
        return "{}{}".format(self.direction.value, str(self.magnitude))

    def __eq__(self, other):
        return self.direction == other.direction and self.magnitude == other.magnitude

    def calculate_vector_range(self, current_point: (int, int)) -> range:
        sign_value = {
            Direction.UP: 1,
            Direction.DOWN: -1,
            Direction.LEFT: -1,
            Direction.RIGHT: 1
        }
        current_point_on_axis = current_point[1 if self.direction.isVertical() else 0]
        return range(current_point_on_axis + sign_value[self.direction]*1,  # start
                     current_point_on_axis + sign_value[self.direction]*(self.magnitude + 1),  # stop
                     sign_value[self.direction])  # step

    direction: Direction
    magnitude: int


class Grid(object):

    def __init__(self, half_axis_size=1000, oversize_multiplier=4):
        self.half_axis_size = half_axis_size
        self.oversize_multiplier = oversize_multiplier
        self.coordinate_shift = self.half_axis_size * self.oversize_multiplier
        self.origin = (0, 0)
        self.wire_point_list = [[]]
        self.wires = 0

    def __repr__(self):
        def get_char_for_val(coord: (int, int)) -> str:

            val = self.get_coord_val(coord)
            if coord == self.origin:
                return Bcolors.FAIL + 'O' + Bcolors.ENDC
            elif val == 0:
                return Bcolors.OKBLUE + '.' + Bcolors.ENDC
            elif val == 1:
                return Bcolors.OKGREEN + '*' + Bcolors.ENDC
            else:
                return Bcolors.WARNING + '#' + Bcolors.ENDC

        return_str = "\n\n\t(GRID):\n"
        for y in range(self.half_axis_size*self.oversize_multiplier, -self.half_axis_size*self.oversize_multiplier, -1):
            return_str += "\t"
            for x in range(-self.half_axis_size*self.oversize_multiplier, self.half_axis_size*self.oversize_multiplier):
                return_str += get_char_for_val((x, y))
            return_str += "\n"

        return return_str

    def get_array_coord_for_coord(self, coord: (int, int)) -> (int, int):
        return coord[0] + self.coordinate_shift, coord[1] + self.coordinate_shift

    def get_coord_for_array_coord(self, array_cord: (int, int)) -> (int, int):
        return array_cord[0] - self.coordinate_shift, array_cord[1] - self.coordinate_shift

    def get_coord_val(self, coord: (int, int)) -> int:
        array_coord = self.get_array_coord_for_coord(coord)
        count = 0
        for wire in self.wire_point_list:
            if array_coord in wire:
                count += 1
        return count

    def mark_vector(self, vector: Vector, current_point: (int, int), wire: int) -> (int, int):
        array_point = self.get_array_coord_for_coord(current_point)
        line_range: range = vector.calculate_vector_range(array_point)
        if wire not in self.wire_point_list:
            self.wire_point_list.append([])
        if vector.direction.isVertical():
            array_point = (array_point[0], array_point[1] + (line_range.step * vector.magnitude))
            for y in line_range:
                self.wire_point_list[wire].append((array_point[0], y))
        else:
            array_point = (array_point[0] + line_range.step * vector.magnitude, array_point[1])
            for x in line_range:
                try:
                    self.wire_point_list[wire].append((x, array_point[1]))
                except IndexError:
                    print("Index error")
                    exit(1)
        return self.get_coord_for_array_coord(array_point)

    def update_grid_with_vector_list(self, vector_list: List[Vector]):
        current_point = self.origin
        for this_vector in vector_list:
            current_point = self.mark_vector(this_vector, current_point, self.wires)
        self.wires += 1

    def get_intersection_coordinates(self):
        return [self.get_coord_for_array_coord(coord) for coord in list(set(self.wire_point_list[0]) & set(self.wire_point_list[1]))]




class CrossedWires(object):
    def __init__(self, wire_list: List[str], grid_size: int):
        self.grid = Grid(grid_size)
        self.vectors = [self.parse_wire(wire) for wire in wire_list]
        [self.grid.update_grid_with_vector_list(vector) for vector in self.vectors]

    @staticmethod
    def manhattan_distance(point1: (int, int), point2: (int, int)) -> int:
        return abs(point2[0] - point1[0]) + abs(point2[1] - point1[1])

    def parse_wire(self, wire: str) -> List[Vector]:
        vector_list = []
        for raw_vector in wire.split(','):
            vector = Vector()
            vector.direction = self.decode_direction[raw_vector[0]]
            vector.magnitude = int(raw_vector[1:])
            vector_list.append(vector)
        return vector_list

    def get_intersections(self):
        return self.grid.get_intersection_coordinates()

    def get_minimum_manhattan_distance(self) -> int:
        return min([self.manhattan_distance(self.grid.origin, intersection)
                    for intersection in self.get_intersections()])


    vectors: List[List[Vector]]
    decode_direction = {
        'L': Direction.LEFT,
        'R': Direction.RIGHT,
        'U': Direction.UP,
        'D': Direction.DOWN
    }


