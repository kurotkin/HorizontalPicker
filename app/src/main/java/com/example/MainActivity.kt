package com.example

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.slider.R
import com.example.slider.ScreenUtils
import com.example.slider.SliderAdapter
import com.example.slider.SliderLayoutManager

class MainActivity : AppCompatActivity() {

    private lateinit var rvHorizontalPicker: RecyclerView
    private lateinit var tvSelectedItem: TextView
    private lateinit var selecterItem: ImageView

    private val data = arrayListOf("Один", "Два", "Три", "Четыре", "Пять", "Шесть", "Семь", "Восемь", "Девять", "Очень длинный текст текст текст")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvSelectedItem = findViewById(R.id.tv_selected_item)
        selecterItem = findViewById(R.id.selector)

        setHorizontalPicker()
    }

    private fun setHorizontalPicker() {
        rvHorizontalPicker = findViewById(R.id.rv_horizontal_picker)

        // Setting the padding such that the items will appear in the middle of the screen
        val padding: Int = ScreenUtils.getScreenWidth(this) / 2 - ScreenUtils.dpToPx(this, 40)
        rvHorizontalPicker.setPadding(padding, 0, padding, 0)

        // Setting layout manager
        rvHorizontalPicker.layoutManager = SliderLayoutManager(this).apply {
            callback = object : SliderLayoutManager.OnItemSelectedListener {
                override fun onItemSelected(layoutPosition: Int) {
                    tvSelectedItem.text = data[layoutPosition]
                    val anim = ObjectAnimator.ofFloat(selecterItem, View.SCALE_X, 0.5f * data[layoutPosition].length.toFloat())
                    anim.duration = 200
                    val animSet = AnimatorSet()
                    animSet.playTogether(anim)
                    animSet.start()
                }
            }
        }

        // Setting Adapter
        rvHorizontalPicker.adapter = SliderAdapter().apply {
            setData(data)
            callback = object : SliderAdapter.Callback {
                override fun onItemClicked(view: View) {
                    rvHorizontalPicker.smoothScrollToPosition(rvHorizontalPicker.getChildLayoutPosition(view))
                }
            }
        }
    }
}
