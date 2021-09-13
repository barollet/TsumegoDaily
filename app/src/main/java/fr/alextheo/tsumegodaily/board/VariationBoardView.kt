package fr.alextheo.tsumegodaily.board

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import fr.alextheo.tsumegodaily.SGFParser
import java.util.*

class VariationBoardView(context: Context, sgf: SGFParser, attrs: AttributeSet? = null) :
    BoardView(context, sgf, attrs) {

    // A vector of moves (x, y) intersections
    // Black plays first
    private var _variation: Vector<Pair<Int, Int>> = Vector()

    // TODO une structure pour stocker les pierres sur le plateau et les libertés
    //private var un truc

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Add the variation stones to the board
        drawVariationStones(canvas)

        // TODO Alex enlever ce commentaire quand tu as fini
        // Exemple de placement de pierre
        //placeStone(canvas, Pair(2, 3), Color.BLACK, 5)
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

    private fun canPlaceStone(position: Pair<Int, Int>): Boolean {
        return false;
    }

    private fun drawVariationStones(canvas: Canvas) {
        // TODO Alex Ici tu as juste à lire les pierres de _variations et les placer
        // En fait non mdr il faut regarder si tu as le droit de placer et tout.
        // Tu peux surement afficher avec ta structure du plateau et pas l'historique des coups
    }

    fun addVariationMove(move: Pair<Int, Int>) {
        // TODO modifier le plateau
        _variation.add(move)
        invalidate()
    }
    fun removeLastVariationMove() {
        // TODO modifier le plateau
        _variation.removeLast()
        invalidate()
    }
    fun clearVariation() {
        // TODO modifier le plateau
        _variation.clear()
        invalidate()
    }

}