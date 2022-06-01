package com.gopal.myapplication.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gopal.myapplication.data.model.FacilitiesAPI
import com.gopal.myapplication.databinding.FacilitiesAdapterItemBinding
import com.gopal.myapplication.view.fragment.HomeFragment

class FacilitiesAdapter(private val fragment: HomeFragment) :
    RecyclerView.Adapter<FacilitiesAdapter.ViewHolder>() {

    private var facility: List<FacilitiesAPI.Facility> = listOf();

    private var exclusion: List<List<FacilitiesAPI.Exclusion>> = listOf();



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FacilitiesAdapter.ViewHolder {
        val binding: FacilitiesAdapterItemBinding = FacilitiesAdapterItemBinding.inflate(
            LayoutInflater.from(fragment.context),
            parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FacilitiesAdapter.ViewHolder, position: Int) {
        holder.tvTitle.setText(facility.get(position).name)

        if (position == 0){
            fragment.optionList(facility.get(position).options, exclusion)
        }

        holder.itemView.setOnClickListener(View.OnClickListener {
            Log.e("Gopal", facility.get(position).options.toString())

            if (fragment is HomeFragment){
                fragment.optionList(facility.get(position).options, exclusion)
            }
        })
    }

    override fun getItemCount(): Int {
        return facility.size
    }

    fun facilityList(list: List<List<FacilitiesAPI.Exclusion>>) {
        exclusion = list;
    }

    fun facilityList_(list: List<FacilitiesAPI.Facility>) {
        facility = list;
        notifyDataSetChanged()
    }

    class ViewHolder(view: FacilitiesAdapterItemBinding) : RecyclerView.ViewHolder(view.root) {
        // Holds the TextView that will add each item to
        val tvTitle = view.tvText
    }
}