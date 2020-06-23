package com.shopping.e_commerce.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.shopping.e_commerce.R
import com.shopping.e_commerce.data.Repositry
import com.shopping.e_commerce.pojo.items
import java.lang.Exception


class searchFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var searchView: androidx.appcompat.widget.SearchView
    lateinit var adapter: itemAdapter
   lateinit var repositry: Repositry
    lateinit var imageView: ImageView
    lateinit var reference: DatabaseReference
    companion object {
        fun newInstance() = searchFragment()
    }

    override fun onCreateView
        (inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
      ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView=view.findViewById(R.id.recycler_search)
        recyclerView.layoutManager=LinearLayoutManager(context)
        searchView=view.findViewById(R.id.searchview1)
        imageView=view.findViewById(R.id.imageView)
        reference=FirebaseDatabase.getInstance().getReference().child("E-commerce")
        repositry=ViewModelProviders.of(this).get(Repositry::class.java)
        searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                Toast.makeText(context,newText,Toast.LENGTH_SHORT).show()
                search_it(newText)
                return true
            }
        })
       try {
           repositry.getinfo_list().observe(viewLifecycleOwner, Observer<ArrayList<items>> {item->
               adapter= context?.let { itemAdapter(it) }!!
               adapter.setlist(item)
               recyclerView.adapter = adapter
               imageView.visibility=View.INVISIBLE
           })

       }
       catch (e:Exception){
           Toast.makeText(context,e.message,Toast.LENGTH_SHORT).show()
       }

    }


    private fun search_it(v: String) {
        reference.child("listitems").addValueEventListener(object : ValueEventListener {
            var list = ArrayList<items>()
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (dataSnapshot1 in dataSnapshot.children) {
                    val items = dataSnapshot1.getValue(items::class.java)
                    if (items != null) {
                        if (items.name.toLowerCase().contains(v.toLowerCase())) {
                            list.add(items)
                        }
                    }
                }
                adapter= context?.let { itemAdapter(it) }!!
                adapter.setlist(list)
                recyclerView.adapter = adapter
                imageView.visibility=View.INVISIBLE
            }
            override fun onCancelled(databaseError: DatabaseError) {}
        })

    }


}