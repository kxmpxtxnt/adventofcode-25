import itertools
from typing import List


def flatten(list_of_lists: List) -> List:
    return [item for single_list in list_of_lists for item in single_list]


def copy_list(elements: List) -> List:
    return [elem for elem in elements]


def transpose(matrix: List[List]) -> List[List]:
    return list(map(list, itertools.zip_longest(*matrix, fillvalue=None)))
