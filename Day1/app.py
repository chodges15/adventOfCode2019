
def get_required_fuel_for_module(module_mass: int) -> int:
    fuel_for_current_mass = get_required_fuel(module_mass)
    if fuel_for_current_mass < 0:
        return 0
    else:
        return get_required_fuel_for_module(fuel_for_current_mass) + fuel_for_current_mass


def get_required_fuel(mass: int) -> int:
    return mass // 3 - 2


def get_required_fuel_on_list(list_of_masses: list) -> int:
    return sum(map(get_required_fuel, list_of_masses))


def get_final_required_fuel_on_list(list_of_masses: list) -> int:
    return sum(map(get_required_fuel_for_module, list_of_masses))
