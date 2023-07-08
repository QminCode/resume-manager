package com.example.resume.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import com.example.resume.R

/**
 * @author: playboi_YzY
 * @date: 2023/5/21 12:12
 * @description: 自定义toolbar
 * @version:
 */
class CustomToolBar : FrameLayout{
    private lateinit var toolBar: Toolbar
    private lateinit var toolBarTitle: AppCompatTextView

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet) {
        val view = LayoutInflater.from(context).inflate(R.layout.toolbar_layout_custom, this)
        toolBar = view.findViewById(R.id.toolBar2)
        toolBar.title = ""
        toolBarTitle = view.findViewById(R.id.toolbarTitle)
    }

    fun setCenterTitle(titleStr: String) {
        toolBarTitle.text = titleStr
    }

    fun setCenterTitle(titleResId: Int) {
        toolBarTitle.text = context.getString(titleResId)
    }

    fun setCenterTitleColor(colorResId: Int) {
        toolBarTitle.setTextColor(colorResId)
    }

    fun setToolbarBackGround(colorResId: Int) {
        toolBar.setBackgroundColor(colorResId)
    }

    fun getBaseToolBar(): Toolbar {
        return toolBar
    }
}