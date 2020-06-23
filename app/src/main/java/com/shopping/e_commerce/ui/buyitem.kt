package com.shopping.e_commerce.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.shopping.e_commerce.R
import java.lang.reflect.Array

class buyitem : AppCompatActivity() {
    lateinit var name: String
    lateinit var price2: String
    lateinit var quantity1: String
    lateinit var item:TextView
    lateinit var quantity:TextView
    lateinit var total:TextView
    lateinit var price1:TextView

    @ExperimentalStdlibApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buyitem)
        name = intent.getStringExtra("name")
        price2 = intent.getStringExtra("price")
        quantity1 = intent.getStringExtra("quantity")
        var item_q:Int=quantity1.toInt()
    //
        val pr: List<String> =price2.split("£"," ")

        Log.d(Companion.TAG, "price: "+pr[1].toString().toFloat())
        var item_price:Float=pr[1].toFloat()
        var result:Float=item_price*item_q
        inalize()
        item.text=name
        price1.text=price2
        total.text=result.toString()
        quantity.text=quantity1
    }

    private fun inalize() {
        item=findViewById(R.id.item_name_result)
        quantity=findViewById(R.id.item_quantity_result)
        total=findViewById(R.id.total_order)
        price1=findViewById(R.id.item_price)

    }

    companion object {
        private const val TAG = "buyitem"
    }
}