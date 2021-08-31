package fr.alextheo.tsumegodaily

import java.lang.StringBuilder
import java.util.*

class SGFParser(sgf: String?) {
    var min_intersection_width: Int = 0
        private set
    var min_intersection_height: Int = 0
        private set
    var _initial_stones: Pair<Vector<Pair<Int, Int>>, Vector<Pair<Int, Int>>> = Pair(Vector(), Vector())
        private set

    init {
        sgf?.let {
            // Parsing SGF
            for (node in parseSgfNode(sgf)) {
                if (node.startsWith("B")) {
                    _initial_stones.first.add(nodeToCoordinate(node))
                } else if (node.startsWith("W")) {
                    _initial_stones.second.add(nodeToCoordinate(node))
                }
            }

            // Getting min needed size
            // Max width between black and white stones + overhead of 2
            min_intersection_width = 1 + maxOf(
                _initial_stones.first.map { e -> e.first }.maxOrNull()!!,
                _initial_stones.second.map { e -> e.first }.maxOrNull()!!) + 2
            min_intersection_height = 1 + maxOf(
                _initial_stones.first.map { e -> e.second }.maxOrNull()!!,
                _initial_stones.second.map { e -> e.second }.maxOrNull()!!) + 2

            // Flip coordinates if not in portrait mode
            if (min_intersection_width > min_intersection_height) {
                flipPosition(_initial_stones.first)
                flipPosition(_initial_stones.second)

                // swap minimum size
                val tmp = min_intersection_height
                min_intersection_height = min_intersection_width
                min_intersection_width = tmp
            }
        }
    }

    private fun flipPosition(positions: Vector<Pair<Int, Int>>) {
        for (i in 0 until positions.size) {
            val old = positions[i]
            positions[i] = Pair(old.second, old.first)
        }
    }

    private fun parseSgfNode(sgf: String) = sequence {
        val buffer = StringBuilder()
        for (c in sgf) {
            when (c) {
                '[' -> buffer.clear()
                ']' -> yield(buffer.toString())
                else -> buffer.append(c)
            }
        }
    }

    private fun nodeToCoordinate(node: String): Pair<Int, Int> {
        return Pair(node[1] - 'a', node[2] - 'a')
    }
}