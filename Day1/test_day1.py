from Day1 import app
import pytest
from typing import List


@pytest.fixture()
def input_data() -> List[int]:
    with open('./input.txt', 'r') as inputFile:
        mass_list = map(int, inputFile.readlines())
    return mass_list


@pytest.mark.parametrize("mass, expected_fuel", [
    (12, 2),
    (14, 2),
    (1969, 654),
    (100756, 33583)])
def test_example_fuel_requirements(mass: int, expected_fuel: int):
    assert app.get_required_fuel(mass) == expected_fuel


@pytest.mark.parametrize("mass, expected_fuel", [
    (14, 2),
    (1969, 966),
    (100756, 50346)])
def test_example_final_fuel_requirements(mass: int, expected_fuel: int):
    assert app.get_required_fuel_for_module(mass) == expected_fuel


def test_part_1(input_data):
    assert app.get_required_fuel_on_list(input_data) == 3456641


def test_part_2(input_data):
    assert app.get_final_required_fuel_on_list(input_data) == 5182078
