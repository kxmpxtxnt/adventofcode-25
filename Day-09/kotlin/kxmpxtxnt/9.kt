package fyi.pauli.aoc.days

import fyi.pauli.aoc.day
import kotlin.math.abs

val day9 = day(9) {
    first = {
        inputLines
            .map { it.trim().split(",") }
            .map { (x, y) -> x.toLong() to y.toLong() }
            .let { positions ->
                positions.flatMapIndexed { index, pos ->
                    positions.drop(index + 1).map { other ->
                        (abs(pos.first - other.first) + 1) * (abs(pos.second - other.second) + 1)
                    }
                }
            }.max()
    }

}