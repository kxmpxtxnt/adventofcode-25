package fyi.pauli.aoc.days

import fyi.pauli.aoc.day

val day7 = day(7) {
    first = {
        val startCol = inputLines.first().indexOf('S')
        val activeCols = mutableSetOf(startCol)

        inputLines
            .drop(2)
            .chunked(2, List<String>::first)
            .sumOf { row ->
                activeCols.toList()
                    .sumOf { col ->
                        if (row[col] == '^') {
                            activeCols.remove(col)
                            activeCols.addAll(listOf(col - 1, col + 1).filter { it in row.indices })
                            1
                        } else 0
                    }
            }
    }

    second = {
        (inputLines.size - 2 downTo 2 step 2)
            .fold(LongArray(inputLines[0].length) { 1L }) { cols, row ->
                cols.apply {
                    inputLines[row].withIndex()
                        .filter { it.value == '^' }
                        .forEach { (col, _) -> this[col] = this[col - 1] + this[col + 1] }
                }
            }[inputLines[0].length / 2]

    }
}