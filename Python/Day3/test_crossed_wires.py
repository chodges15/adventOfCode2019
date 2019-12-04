from Python.Day3.crossed_wires import CrossedWires, Vector, Direction, Grid

simple_grid_size = 5
simple_test_vector = Vector(Direction.DOWN, 4)
simple_test_grid = Grid(simple_grid_size)


# def test_parsing_input():
#     cw = CrossedWires('R75,D30', 'L75,U30')
#     assert(cw.vectors[0] == [Vector(Direction.RIGHT, 75), Vector(Direction.DOWN, 30)])
#
#
# def test_calculate_range():
#     vec = Vector(Direction.UP, 5)
#     assert(vec.calculate_vector_range(simple_test_grid.origin) == range(0, 6, 1))
#
#
# def test_get_array_coordinates():
#     assert(simple_test_grid.get_array_coord_for_coord(simple_test_grid.origin) == (5, 5))
#
#
# def test_marking_line():
#     simple_test_grid.mark_vector(Vector(Direction.UP, 3), simple_test_grid.origin)
#     assert(simple_test_grid.get_coord_val((0, 1)) == 1)
#     assert(simple_test_grid.get_coord_val((0, 2)) == 1)
#     assert(simple_test_grid.get_coord_val((0, 3)) == 1)

def test_printing_a_grid():
    grid = Grid(5)
    grid.mark_vector(Vector(Direction.DOWN, 4), grid.origin)
    grid.mark_vector(Vector(Direction.DOWN, 2), grid.origin)
    print(grid)

