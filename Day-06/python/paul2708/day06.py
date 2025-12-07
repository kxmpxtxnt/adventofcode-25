import math
from typing import List

from shared.paul2708.input_reader import *
from shared.paul2708.output import write
from shared.paul2708.utility import transpose

lines = read_plain_input(day=6)

# Part 1
stripped_grid = [[k for k in line.strip().split(" ") if k != ""] for line in lines]

total = 0
for j in range(len(stripped_grid[0])):
    nums = []

    for i in range(len(stripped_grid)):
        current = stripped_grid[i][j]

        if current == "+":
            total += sum(nums)
        elif current == "*":
            total += math.prod(nums)
        else:
            nums.append(int(current))

write(f"The grant total is <{total}>.")


# Part 2
def split_numbers(line: str, max_lengths: List[int]) -> List[str]:
    numbers = []
    curr = ""

    for i in range(len(line)):
        if len(curr) < max_lengths[len(numbers)]:
            curr += line[i]
        else:
            numbers.append(curr)
            curr = ""

    numbers.append(curr)
    return numbers


def get_longest_word_in_column(column: int) -> int:
    return max(list(map(len, transpose(stripped_grid)[column])))


def transform_numbers(nums: List[str]) -> List[int]:
    max_length = max([len(num) for num in nums])

    transformed = []
    for i in range(max_length):
        digits = ""

        for num in nums:
            digits += num[i]

        transformed.append(int(digits))

    return transformed


intended_grid = []
for i in range(len(lines) - 1):
    max_lengths = [get_longest_word_in_column(j) for j in range(len(transpose(stripped_grid)))]

    intended_grid.append(split_numbers(lines[i], max_lengths))

total = 0
for i, col in enumerate(transpose(intended_grid)):
    if stripped_grid[-1][i] == "+":
        total += sum(transform_numbers(col))
    else:
        total += math.prod(transform_numbers(col))

write(f"The grant total using right-to-left in columns is <{total}>.")
