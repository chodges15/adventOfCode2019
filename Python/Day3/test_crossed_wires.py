from Python.Day3.crossed_wires import CrossedWires, Vector, Direction


def test_parsing_input():
    cw = CrossedWires('R75,D30', 'L75,U30')
    assert(cw.vectors[0] == [Vector(Direction.RIGHT, 75), Vector(Direction.DOWN, 30)])

