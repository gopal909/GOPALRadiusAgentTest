package com.gopal.myapplication.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.gopal.myapplication.R
import com.gopal.myapplication.data.model.FacilitiesAPI
import com.gopal.myapplication.databinding.OptionsAdapterItemsBinding
import com.gopal.myapplication.view.fragment.HomeFragment

class OptionsAdapter (private val fragment: HomeFragment) :
    RecyclerView.Adapter<OptionsAdapter.ViewHolder>() {

    private var options: List<FacilitiesAPI.Option> = listOf();
    private var exclusion: List<List<FacilitiesAPI.Exclusion>> = listOf();
    private var selectedPosition = -1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionsAdapter.ViewHolder {
        val binding: OptionsAdapterItemsBinding = OptionsAdapterItemsBinding.inflate(
            LayoutInflater.from(fragment.context),
            parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OptionsAdapter.ViewHolder, position: Int) {
        if (options.get(position).name == "Apartment") {
            holder.iv_image.setImageDrawable(
                ContextCompat.getDrawable(
                    fragment.requireContext(),
                    R.drawable.apartment_2x
                )
            )
        }else if (options.get(position).name == "Condo") {
            holder.iv_image.setImageDrawable(
                ContextCompat.getDrawable(
                    fragment.requireContext(),
                    R.drawable.condo_2x
                )
            )
        }else if (options.get(position).name == "Boat House") {
            holder.iv_image.setImageDrawable(
                ContextCompat.getDrawable(
                    fragment.requireContext(),
                    R.drawable.boat_2x
                )
            )
            holder.tvTitle.setText(options.get(position).name)
        }else if (options.get(position).name == "Land") {
            holder.iv_image.setImageDrawable(
                ContextCompat.getDrawable(
                    fragment.requireContext(),
                    R.drawable.land_2x
                )
            )
        }else if (options.get(position).name == "1 to 3 Rooms") {
            holder.iv_image.setImageDrawable(
                ContextCompat.getDrawable(
                    fragment.requireContext(),
                    R.drawable.rooms_2x
                )
            )
        }else if (options.get(position).name == "No Rooms") {
            holder.iv_image.setImageDrawable(
                ContextCompat.getDrawable(
                    fragment.requireContext(),
                    R.drawable.noroom_2x
                )
            )
        }else if (options.get(position).name == "Swimming Pool") {
            holder.iv_image.setImageDrawable(
                ContextCompat.getDrawable(
                    fragment.requireContext(),
                    R.drawable.swimming_2x
                )
            )
        }else if (options.get(position).name == "Garden Area") {
            holder.iv_image.setImageDrawable(
                ContextCompat.getDrawable(
                    fragment.requireContext(),
                    R.drawable.garden_2x
                )
            )
        }else if (options.get(position).name == "Garage") {
            holder.iv_image.setImageDrawable(
                ContextCompat.getDrawable(
                    fragment.requireContext(),
                    R.drawable.garage_2x
                )
            )
        }else{
            holder.iv_image.setImageDrawable(
                ContextCompat.getDrawable(
                    fragment.requireContext(),
                    R.drawable.apartment_3x
                )
            )
        }
        holder.tvTitle.text = options[position].name

        holder.itemView.setOnClickListener(View.OnClickListener {
            exclusion.forEachIndexed { index, e ->
                e.forEach { exclusion: FacilitiesAPI.Exclusion ->
                    println("${exclusion.options_id} at $options[position].id")
                    if (exclusion.options_id == options[position].id){
                        Toast.makeText(
                            fragment.requireActivity(),
                            "You can’t select these options together",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    return@forEach
                }
            }
        })
    }

    override fun getItemCount(): Int {
        return options.size
    }

    fun optionsList(list: List<FacilitiesAPI.Option>, exclusions: List<List<FacilitiesAPI.Exclusion>>) {
        options = list
        exclusion = exclusions
        notifyDataSetChanged()
    }

    class ViewHolder(view: OptionsAdapterItemsBinding) : RecyclerView.ViewHolder(view.root) {
        // Holds the TextView that will add each item to
        val tvTitle = view.tvTextName
        val iv_image = view.ivImage
        val linearLayout = view.line1
    }
}