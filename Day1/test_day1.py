from Day1 import app
import pytest
import os


@pytest.mark.parametrize("mass, expected_fuel", [
    (12, 2),
    (14, 2),
    (1969, 654),
    (100756, 33583)])
def test_example_fuel_requirements(mass: int, expected_fuel:int):
    assert app.get_required_fuel(mass) == expected_fuel


def test_input_from_file():
    with open('./input.txt', 'r') as inputFile:
        mass_list = map(int, inputFile.readlines())
        assert app.get_required_fuel_on_list(mass_list) == 3456641

