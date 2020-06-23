package com.shopping.e_commerce.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.shopping.e_commerce.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_showitem.*

class showitem : AppCompatActivity() {
    lateinit var back: ImageView
    lateinit var item: ImageView
    lateinit var item_nam: TextView
    lateinit var item_price: TextView
    lateinit var btn: Button
    lateinit var str: TextView
    lateinit var monis: TextView
    lateinit var plus: TextView
    lateinit var img: String
    lateinit var name: String
    lateinit var price: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_showitem)
        intaize()
        img = intent.getStringExtra("image")
        name = intent.getStringExtra("name")
        price = intent.getStringExtra("price")



        Picasso.get().load(img).into(item)
        item_price.text = price
        item_nam.text = name

        back.setOnClickListener {
            finish()
        }
        val str1: String = str.text as String
        var a: Int = str1.toInt()
        if (a > 0) {
            plus.setOnClickListener {
                a += 1
                str.text = a.toString()
            }
            moins.setOnClickListener {
                if (a > 1) {
                    a -= 1
                    str.text = a.toString()
                }
            }
        }
        btn.setOnClickListener {
                val intent = Intent(this,buyitem::class.java)
                intent.putExtra("name", item_nam.text as String)
                intent.putExtra("price",item_price.text as String)
                intent.putExtra("quantity", str.text as String)

            startActivity(intent)
        }
    }

    private fun intaize() {
        back = findViewById(R.id.back)
        item = findViewById(R.id.item)
        item_nam = findViewById(R.id.item_name)
        item_price = findViewById(R.id.price)
        btn = findViewById(R.id.btn_add_to_cart)
        str = findViewById(R.id.str)
        monis = findViewById(R.id.moins)
        plus = findViewById(R.id.plus)

    }

}