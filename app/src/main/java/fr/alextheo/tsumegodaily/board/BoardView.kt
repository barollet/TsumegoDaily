package fr.alextheo.tsumegodaily.board

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.example.tsumegodaily.R

private const val DEFAUT_INTERSECTIONS = 8

class BoardView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
    ) : View(context, attrs, defStyleAttr) {

    private var _min_intersection_width: Int = DEFAUT_INTERSECTIONS
    private var _min_intersection_height: Int = DEFAUT_INTERSECTIONS

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        // Paint styles used for rendering are initialized here. This
        // is a performance optimization, since onDraw() is called
        // for every screen refresh.
        style = Paint.Style.FILL
        color = Color.BLACK
        textAlign = Paint.Align.CENTER
        textSize = 55.0f
    }

    init {
        // Load attributes
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.BoardView)
        _min_intersection_width = typedArray.getInt(R.styleable.BoardView_min_intersection_width, DEFAUT_INTERSECTIONS)
        _min_intersection_height = typedArray.getInt(R.styleable.BoardView_min_intersection_height, DEFAUT_INTERSECTIONS)
        typedArray.recycle()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        setBackgroundColor(ContextCompat.getColor(context, R.color.board_color))

        val size = minOf(width / _min_intersection_width, height / _min_intersection_height)
        val intersection_width = width / size
        val intersection_height = height / size

        for (i in 0..maxOf(intersection_width, intersection_height)) {
            val index_coord = (i*size + size/2).toFloat()
            // Horizontal lines
            canvas.run {
                // Horizontal lines
                drawLine((size/2).toFloat(), index_coord, width.toFloat(), index_coord, paint)
                // Vertical lines
                drawLine(index_coord, (size/2).toFloat(), index_coord, height.toFloat(), paint)
            }
        }
    }
}