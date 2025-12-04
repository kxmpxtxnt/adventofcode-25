package fyi.pauli.aoc.days

import fyi.pauli.aoc.day

val day4 = day(4) {
    val dirs = listOf(
        (0 to 1), (1 to 0), (0 to -1), (-1 to 0), (1 to 1), (-1 to -1), (1 to -1), (-1 to 1)
    )

    fun neighbours(col: Int, row: Int, grid: List<String>): Int {
        return dirs.mapNotNull { (height, side) ->
            grid.getOrNull(height + col)?.getOrNull(side + row)
        }.count { it == '@' }
    }

    first = {
        inputLines.withIndex().sumOf { (col, currentRow) ->
            currentRow.withIndex().count { (row, c) ->
                c == '@' && neighbours(col, row, inputLines) < 4
            }
        }
    }

    second = {
        generateSequence(inputLines.map(String::toCharArray) to 0) { (grid, _) ->
            grid.flatMapIndexed { col, row ->
                row.withIndex().mapNotNull { (row, c) ->
                    (col to row).takeIf {
                        c == '@' && neighbours(col, row, grid.map(::String)) < 4
                    }
                }
            }.takeIf(List<Pair<Int, Int>>::isNotEmpty)?.also { toRemove ->
                toRemove.forEach { (col, row) -> grid[col][row] = '.' }
            }?.let { grid to it.size }
        }.sumOf(Pair<List<CharArray>, Int>::second)
    }
}