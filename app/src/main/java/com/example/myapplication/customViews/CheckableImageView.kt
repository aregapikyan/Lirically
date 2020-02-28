package com.example.myapplication.customViews

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import com.example.myapplication.R

class CheckableImageView(context: Context, attrs: AttributeSet) : ImageView(context, attrs) {

    var isChecked = false

    init {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.CheckableImageView)
        isChecked = attributes.getBoolean(R.styleable.CheckableImageView_isCkecked, false)
        attributes.recycle()
    }

}