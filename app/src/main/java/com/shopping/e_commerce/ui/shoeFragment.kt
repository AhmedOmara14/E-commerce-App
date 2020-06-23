package com.shopping.e_commerce.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shopping.e_commerce.R
import com.shopping.e_commerce.data.Repositry
import com.shopping.e_commerce.pojo.items

class shoeFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: itemAdapter
    lateinit var repositry: Repositry
    lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shoe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView=view.findViewById(R.id.recycler_Shoes)
        recyclerView.layoutManager=LinearLayoutManager(context)
        adapter= context?.let { itemAdapter(it) }!!
        progressBar=view.findViewById(R.id.progressBar2)
        repositry=ViewModelProviders.of(this).get(Repositry::class.java)
        repositry.getinfo_Shoes().observe(viewLifecycleOwner, Observer<ArrayList<items>> {item->
            adapter.setlist(item)
            progressBar.visibility=View.INVISIBLE
            adapter.notifyDataSetChanged()
            recyclerView.adapter=adapter
        })
    }

}