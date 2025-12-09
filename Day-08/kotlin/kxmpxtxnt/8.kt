package fyi.pauli.aoc.days

import fyi.pauli.aoc.day

val day8 = day(8) {
    first = {
        inputLines
            .map { it.split(",").map(String::toLong) }
            .let { points ->
                val parent = IntArray(points.size) { it }
                fun find(x: Int): Int =
                    if (parent[x] == x) x
                    else find(parent[x]).also { parent[x] = it }

                fun union(triple: Triple<Int, Int, Long>) {
                    val rx = find(triple.first)
                    val ry = find(triple.second)
                    if (rx != ry) parent[rx] = ry
                }

                points.indices
                    .flatMap { i ->
                        (i + 1 until points.size).map { j ->
                            Triple(
                                i,
                                j,
                                (0..2).sumOf { k ->
                                    val d = points[i][k] - points[j][k]
                                    d * d
                                }
                            )
                        }
                    }
                    .sortedBy(Triple<Int, Int, Long>::third)
                    .take(1000)
                    .forEach(::union)

                parent.indices
                    .groupBy(::find)
                    .values
                    .map(List<Int>::size)
                    .sortedDescending()
                    .take(3)
                    .reduce(Int::times)
            }
    }

    second = {
        inputLines
            .map { it.split(",").map(String::toLong) }
            .let { points ->
                val parent = IntArray(points.size) { it }
                fun find(x: Int): Int =
                    if (parent[x] == x) x
                    else find(parent[x]).also { parent[x] = it }

                var lastConnection: Pair<Int, Int>? = null
                var components = points.size

                points.indices
                    .flatMap { i ->
                        (i + 1 until points.size).map { j ->
                            Triple(
                                i,
                                j,
                                (0..2).sumOf { k ->
                                    val d = points[i][k] - points[j][k]
                                    d * d
                                }
                            )
                        }
                    }
                    .sortedBy(Triple<Int, Int, Long>::third)
                    .forEach { (i, j, _) ->
                        val rx = find(i)
                        val ry = find(j)
                        if (rx != ry) {
                            parent[rx] = ry
                            components--
                            lastConnection = i to j
                        }
                    }

                lastConnection!!.let { (i, j) ->
                    points[i][0] * points[j][0]
                }
            }
    }
}