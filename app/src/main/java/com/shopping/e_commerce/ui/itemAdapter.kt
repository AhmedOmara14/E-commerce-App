package com.shopping.e_commerce.ui

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.shopping.e_commerce.data.Repositry
import com.shopping.e_commerce.pojo.items
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_recommendeditem.view.*


class itemAdapter(val conxt: Context) :RecyclerView.Adapter<itemAdapter.viewholder>() {
    var list= mutableListOf<items>()
    lateinit var repositry:Repositry
     val reference: DatabaseReference=FirebaseDatabase.getInstance().getReference().
                       child("E-commerce").child("favorite")
    init {
        this.list=ArrayList()
        repositry=ViewModelProviders.of((conxt as AppCompatActivity?)!!).get(Repositry::class.java)
    }

    fun setlist(data:MutableList<items>){
        list=data
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val itemview1=LayoutInflater.from(conxt).inflate(com.shopping.e_commerce.R.layout.layout_recommendeditem,parent,false)
        return viewholder(itemview1)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        val items=list[position]
        holder.find(items)
        holder.imageButton.setOnClickListener {

            val hashMap: HashMap<String, Any> = HashMap<String,Any>()
            hashMap.put("image",items.image)
            hashMap.put("name",items.name)
            hashMap.put("price",items.price)
           reference.child(items.name).updateChildren(hashMap).addOnCompleteListener(
               OnCompleteListener {
                   task ->
                   if (task.isSuccessful){
                       Toast.makeText(conxt,"Saved",Toast.LENGTH_SHORT).show()
                   }
               })
        }
        holder.itemView.setOnClickListener {

            val intent = Intent(conxt,showitem::class.java)
            intent.putExtra("image",items.image)
            intent.putExtra("name",items.name)
            intent.putExtra("price",items.price)
           conxt.startActivity(intent)
        }

    }

    class viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var imageButton:ImageButton
        fun find(item:items){
            itemView.name_item.text=item.name
            Picasso.get().load(item.image).into(itemView.img_item)
            itemView.price_item.text=item.price
            imageButton=itemView.btnforrecommended

        }


    }

    companion object {
        private const val TAG = "itemAdapter"
    }

}