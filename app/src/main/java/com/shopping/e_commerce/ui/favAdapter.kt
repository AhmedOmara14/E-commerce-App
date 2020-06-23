package com.shopping.e_commerce.ui

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.shopping.e_commerce.pojo.items
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_favitem.view.*
import kotlinx.android.synthetic.main.layout_recommendeditem.view.*

class favAdapter(val conxt: Context): RecyclerView.Adapter<favAdapter.viewholder>() {

     var list = mutableListOf<items>()
     val reference:DatabaseReference =FirebaseDatabase.getInstance().getReference().child("E-commerce")
    init {
        list=ArrayList()
    }
    fun setlist(data:MutableList<items>){
        list=data
    }
    class viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var imageButton: ImageButton
        fun find(item: items){
            itemView.name_item_fav.text=item.name
            Picasso.get().load(item.image).into(itemView.img_item_fav)
            itemView.price_item_fav.text=item.price
            imageButton=itemView.deleteitemfromsave
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        return viewholder(LayoutInflater.from(conxt).inflate(com.shopping.e_commerce.R.layout.layout_favitem,parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        val items=list[position]
        holder.find(items)
        holder.imageButton.setOnClickListener {
            reference.child("favorite").child(items.name).removeValue().addOnCompleteListener{
                task -> if (task.isSuccessful){
                Toast.makeText(conxt,"Delete item,successfully",Toast.LENGTH_SHORT).show()
            }
            }
        }

        holder.itemView.setOnClickListener {

            val intent = Intent(conxt,showitem::class.java)
            intent.putExtra("image",items.image)
            intent.putExtra("name",items.name)
            intent.putExtra("price",items.price)
            conxt.startActivity(intent)
        }
    }
}