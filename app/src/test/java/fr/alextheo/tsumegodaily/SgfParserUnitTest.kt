package fr.alextheo.tsumegodaily

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class SgfParserUnitTest {
    @Test
    fun sgf_parser_content() {
        val sgf = SGFParser("[GO][Ccoucou][Wab][Wac][Bbc][Wde]")

        assertArrayEquals(arrayOf(Pair(1, 2)), sgf._initial_stones.first.toArray())
        assertArrayEquals(arrayOf(Pair(0, 1), Pair(0, 2), Pair(3, 4)), sgf._initial_stones.second.toArray())
    }

    @Test
    fun sgf_parser_size() {
        val sgf = SGFParser("[GO][Ccoucou][Wab][Wac][Bbc][Wde]")
        assertEquals(4+2, sgf.min_intersection_width)
        assertEquals(5+2, sgf.min_intersection_height)
    }

    @Test
    fun sgf_parser_flip() {
        val sgf = SGFParser("[GO][Ccoucou][Wba][Wca][Bcb][Wed]")

        assertArrayEquals(arrayOf(Pair(1, 2)), sgf._initial_stones.first.toArray())
        assertArrayEquals(arrayOf(Pair(0, 1), Pair(0, 2), Pair(3, 4)), sgf._initial_stones.second.toArray())

        assertEquals(4+2, sgf.min_intersection_width)
        assertEquals(5+2, sgf.min_intersection_height)
    }
}