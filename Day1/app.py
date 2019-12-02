

def get_required_fuel(mass: int) -> int:
    return mass // 3 - 2


def get_required_fuel_on_list(list_of_masses: list) -> int:
    return sum(map(get_required_fuel, list_of_masses))
