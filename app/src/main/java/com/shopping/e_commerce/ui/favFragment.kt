package com.shopping.e_commerce.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shopping.e_commerce.R
import com.shopping.e_commerce.data.Repositry
import com.shopping.e_commerce.pojo.items
import kotlinx.android.synthetic.main.fragment_fav.*


class favFragment : Fragment() {
   lateinit var recyclerView: RecyclerView
    lateinit var adapter:favAdapter
    lateinit var repositry: Repositry
    companion object {
        fun newInstance() = favFragment()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView=view.findViewById(R.id.recycler_favorite)
        recyclerView.layoutManager=LinearLayoutManager(context)
        adapter= context?.let { favAdapter(it) }!!
        repositry=ViewModelProviders.of(this).get(Repositry::class.java)
        repositry.getinfo_fav().observe(viewLifecycleOwner, Observer<ArrayList<items>> {item->
            adapter.setlist(item)
            adapter.notifyDataSetChanged()
            recyclerView.adapter=adapter

        })

    }


}