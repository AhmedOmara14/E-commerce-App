package com.shopping.e_commerce.data

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.shopping.e_commerce.pojo.items

class Repositry : ViewModel {
    var name = ""
    var image = ""
    var price = ""
    var mutableLiveDataofitems = MutableLiveData<ArrayList<items>>()
    var mutableLiveDataofitems_backpack = MutableLiveData<ArrayList<items>>()
    var mutableLiveData_Shoes = MutableLiveData<ArrayList<items>>()
    var mutableLiveDataofitems_favorite = MutableLiveData<ArrayList<items>>()
    var mutableLiveDataofitems_list = MutableLiveData<ArrayList<items>>()

    var arrayList = ArrayList<items>()
    var arrayList_backpack = ArrayList<items>()
    var arrayList_Shoes = ArrayList<items>()
    var arrayList_favorite = ArrayList<items>()
    var arrayList_list = ArrayList<items>()

    var reference: DatabaseReference =
        FirebaseDatabase.getInstance().getReference().child("E-commerce")

    constructor() : super()
    constructor(name: String, image: String, price: String) : super() {
        this.name = name
        this.image = image
        this.price = price
    }

    fun getdata(): MutableLiveData<ArrayList<items>> {
        reference.child("recommended").addValueEventListener(
            object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                }

                override fun onDataChange(p0: DataSnapshot) {
                    if (p0.exists()) {
                        arrayList.clear()
                        for (e in p0.children) {
                            val item = e.getValue(items::class.java)
                            Log.d(Companion.TAG, "onDataChange: " + e.child("name").value)
                            arrayList.add(item!!)
                        }
                        mutableLiveDataofitems.value = arrayList
                    }
                }

            })

        return mutableLiveDataofitems
    }

    fun getinfo_packpack(): MutableLiveData<ArrayList<items>> {
        reference.child("backpack").addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()) {
                    arrayList_backpack.clear()
                    for (e in p0.children) {
                        val items = e.getValue(items::class.java)
                        Log.d(TAG, "onDataChange2: " + e.child("name").value)
                        arrayList_backpack.add(items!!)
                    }
                    mutableLiveDataofitems_backpack.value = arrayList_backpack
                }
            }

        })

        return mutableLiveDataofitems_backpack
    }

    fun getinfo_Shoes(): MutableLiveData<ArrayList<items>> {
        reference.child("Shoes").addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()) {
                    arrayList_Shoes.clear()
                    for (e in p0.children) {
                        val items = e.getValue(items::class.java)
                        Log.d(TAG, "onDataChange2: " + e.child("name").value)
                        arrayList_Shoes.add(items!!)
                    }
                    mutableLiveData_Shoes.value = arrayList_Shoes
                }
            }

        })

        return mutableLiveData_Shoes
    }

    fun getinfo_fav(): MutableLiveData<ArrayList<items>> {
        reference.child("favorite").addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()) {
                    arrayList_favorite.clear()
                    for (e in p0.children) {
                        val items = e.getValue(items::class.java)
                        Log.d(TAG, "onDataChange2: " + e.child("name").value)
                        arrayList_favorite.add(items!!)
                    }
                    mutableLiveDataofitems_favorite.value = arrayList_favorite
                }
            }

        })

        return mutableLiveDataofitems_favorite
    }

    fun getinfo_list(): MutableLiveData<ArrayList<items>> {
        reference.child("listitems").addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()) {
                    arrayList.clear()
                    for (e in p0.children) {
                        val items = e.getValue(items::class.java)
                        Log.d(TAG, "onDataChange2: " + e.child("name").value)
                        arrayList.add(items!!)
                    }
                    mutableLiveDataofitems_list.value = arrayList
                }
            }

        })

        return mutableLiveDataofitems_list
    }

    companion object {
        private const val TAG = "Repositry"
    }


}