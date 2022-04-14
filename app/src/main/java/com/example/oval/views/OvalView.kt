package com.example.oval.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.oval.R

class OvalView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val ovalPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val linesPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var ovalWidth: Float = 0f
    private var ovalHeight: Float = 0f

    private var ovalColor: Int = 0
        set(value) {
            ovalPaint.color = value
            field = value
        }

    private var linesColor: Int = 0
        set(value) {
            linesPaint.color = value
            field = value
        }

    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.OvalView, 0, 0)
            .apply {
                ovalWidth = getDimension(R.styleable.OvalView_ovalWidth, 25f)
                ovalHeight = getDimension(R.styleable.OvalView_ovalHeight, 20f)
                ovalColor =
                    getColor(R.styleable.OvalView_ovalColor, context.getColor(R.color.teal_200))
                linesColor =
                    getColor(R.styleable.OvalView_linesColor, context.getColor(R.color.black))
                linesPaint.strokeWidth = 10f
            }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)

        when {
            widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY -> {
                if (widthMeasureSpec > heightMeasureSpec) {
                    setMeasuredDimension(widthMeasureSpec, widthMeasureSpec)
                } else {
                    setMeasuredDimension(heightMeasureSpec, heightMeasureSpec)
                }
            }
            widthMode == MeasureSpec.EXACTLY && heightMode == MeasureSpec.AT_MOST -> {
                setMeasuredDimension(widthMeasureSpec, widthMeasureSpec)
            }
            heightMode == MeasureSpec.EXACTLY && widthMode == MeasureSpec.AT_MOST -> {
                setMeasuredDimension(heightMeasureSpec, heightMeasureSpec)
            }
            else -> setMeasuredDimension(widthMeasureSpec, widthMeasureSpec)
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawOval(width/2f - ovalWidth/2f, height/2f - ovalHeight/2f, width/2f + ovalWidth/2f, height/2f + ovalHeight/2f, ovalPaint)
        canvas.drawLine(width/2f - ovalWidth/2f, height/2f, width/2f + ovalWidth/2f, height/2f, linesPaint)
        canvas.drawLine(width/2f, height/2f - ovalHeight/2f, width/2f, height/2f + ovalHeight/2f, linesPaint)
    }

}