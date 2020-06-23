package com.shopping.e_commerce.ui

import android.opengl.Visibility
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


class home_itemFragment : Fragment() {

    companion object {
        fun newInstance() = homeFragment()
    }
    lateinit var recyclerView:RecyclerView
    lateinit var repositry:Repositry
    lateinit var adapter:itemAdapter
    lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView=view.findViewById(R.id.recycler_recommended)
        progressBar=view.findViewById(R.id.progressBar1)
        recyclerView.layoutManager= LinearLayoutManager(context)
        repositry=ViewModelProviders.of(this).get(Repositry::class.java)
        adapter= context?.let { itemAdapter(it) }!!

        repositry.getdata().observe(viewLifecycleOwner, Observer<ArrayList<items>>{items->
            progressBar.visibility=(View.INVISIBLE)
            adapter.setlist(items)
            adapter.notifyDataSetChanged()
            recyclerView.adapter=adapter

        })
    }

}