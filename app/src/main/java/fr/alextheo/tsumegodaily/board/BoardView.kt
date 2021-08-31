package fr.alextheo.tsumegodaily.board

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import com.example.tsumegodaily.R
import fr.alextheo.tsumegodaily.SGFParser
import java.util.*


class BoardView constructor(
        context: Context,
        sgf: SGFParser,
        attrs: AttributeSet? = null,
    ) : View(context, attrs) {

    // A vector of moves (x, y) intersections
    // Black plays first
    private var _variation: Vector<Pair<Int, Int>> = Vector()
    private val _sgf = sgf

    private var _stone_size = 20 // Placeholder value

    private val _line_paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.BLACK
    }

    private val _black_paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.BLACK
        textAlign = Paint.Align.CENTER
        textSize = 55f
    }
    private val _white_paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.WHITE
        textAlign = Paint.Align.CENTER
        textSize = 55f
    }

    fun addVariationMove(move: Pair<Int, Int>) {
        _variation.add(move)
        invalidate()
    }
    fun removeLastVariationMove() {
        _variation.removeLast()
        invalidate()
    }
    fun clearVariation() {
        _variation.clear()
        invalidate()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        // If click is performed (the touch is released)
        if (event?.action == MotionEvent.ACTION_UP) {
            val x = event.getX()
            val y = event.getY()
            // TODO Alex avec les coordonées x et y de l'event tu dois trouver les coordonées du coup
            // tu connais aussi la taille d'une pierre
        }

        return super.onTouchEvent(event)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // The board has to be drawn first to set the size for the stones
        drawBoard(canvas)

        drawInitialStones(canvas)
        drawVariationStones(canvas)

        // TODO Alex enlever ce commentaire quand tu as fini
        // Exemple de placement de pierre
        //placeStone(canvas, Pair(2, 3), Color.BLACK, 5)
        //placeStone(canvas, Pair(3, 4), Color.WHITE)
    }

    private fun drawBoard(canvas: Canvas) {
        setBackgroundColor(ContextCompat.getColor(context, R.color.board_color))

        computeStoneSize()

        val intersection_width = width / _stone_size
        val intersection_height = height / _stone_size

        for (i in 0..maxOf(intersection_width, intersection_height)) {
            val index_coord = coordinateFromIndex(i)
            // Horizontal lines
            canvas.run {
                // Horizontal lines
                drawLine((_stone_size/2).toFloat(), index_coord, width.toFloat(), index_coord, _line_paint)
                // Vertical lines
                drawLine(index_coord, (_stone_size/2).toFloat(), index_coord, height.toFloat(), _line_paint)
            }
        }
    }

    private fun drawInitialStones(canvas: Canvas) {
        // TODO Alex tu peux aller voir dans SGFParser
        // comment voir où sont les pierres
    }
    private fun drawVariationStones(canvas: Canvas) {
        // TODO Alex Ici tu as juste à lire les pierres de _variations et les placer
    }

    private fun placeStone(canvas: Canvas, position: Pair<Int, Int>, color: Int, number: Int? = null) {
        val circle_paint = if (color == Color.BLACK) _black_paint else _white_paint
        canvas.drawCircle(coordinateFromIndex(position.first), coordinateFromIndex(position.second), (_stone_size/2).toFloat(), circle_paint)

        number?.let {
            val text_paint = if (color == Color.BLACK) _white_paint else _black_paint
            // TODO stone_size/5 as a clean value and not a manual value
            canvas.drawText(number.toString(), coordinateFromIndex(position.first), coordinateFromIndex(position.second) + _stone_size/5, text_paint)
        }
    }

    private fun coordinateFromIndex(index: Int): Float {
        return (index*_stone_size + _stone_size/2).toFloat()
    }

    private fun computeStoneSize() {
        _stone_size = minOf(width / _sgf.min_intersection_width, height / _sgf.min_intersection_height)
        // Change text size when changing the stone size
        _black_paint.textSize = (_stone_size / 2).toFloat()
        _white_paint.textSize = (_stone_size / 2).toFloat()
    }
}