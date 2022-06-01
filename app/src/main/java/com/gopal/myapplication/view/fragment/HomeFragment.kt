package com.gopal.myapplication.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.*
import com.gopal.myapplication.FacilitiesApplication
import com.gopal.myapplication.data.model.FacilitiesAPI
import com.gopal.myapplication.databinding.FragmentHomeBinding
import com.gopal.myapplication.view.adapter.FacilitiesAdapter
import com.gopal.myapplication.view.adapter.OptionsAdapter
import com.gopal.myapplication.viewmodel.HomeViewModel
import com.gopal.myapplication.workmanager.APIWorker
import java.util.concurrent.TimeUnit

class HomeFragment : Fragment() {
    private var mBinding: FragmentHomeBinding? = null

    //private lateinit var homeViewModel: HomeViewModel
    private lateinit var facilitiesAdapter: FacilitiesAdapter
    private lateinit var optionsAdapter: OptionsAdapter
    private var facility: List<FacilitiesAPI.Facility> = listOf();

    private var exclusion: List<List<FacilitiesAPI.Exclusion>> = listOf();

    private val homeViewModel: HomeViewModel by viewModels {
        HomeViewModel.HomeViewModelFactory((requireActivity().application as FacilitiesApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return mBinding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //  homeViewModel.getFacilityList();
        //  randomViewModelObserver()


        homeViewModel.allDishesList.observe(viewLifecycleOwner) { randomDishResponse ->
            randomDishResponse.let {
                if (it.isNotEmpty()) {
                    Log.d("RoomData", it.toString())
                    randomDishResponse.forEachIndexed { index, facilities ->
                        facility = facilities.facilities
                        exclusion = facilities.exclusions
                    }
                    facilitiesAdapter.facilityList_(facility)
                    facilitiesAdapter.facilityList(exclusion)
                } else {
                    homeViewModel.getFacilityList();
                    randomViewModelObserver()
                }

            }
        }

        mBinding!!.rvFacilitiesList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        facilitiesAdapter = FacilitiesAdapter(this@HomeFragment)
        mBinding!!.rvFacilitiesList.adapter = facilitiesAdapter

        mBinding!!.rvOptionsList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        optionsAdapter = OptionsAdapter(this@HomeFragment)
        mBinding!!.rvOptionsList.adapter = optionsAdapter
    }

    private fun randomViewModelObserver() {
        homeViewModel.loadList.observe(
            viewLifecycleOwner,
            Observer { randomDishResponse ->
                randomDishResponse?.let {
                    homeViewModel.insert(randomDishResponse)
                    Log.e("Insertion", "Success")
                    randomDishResponse.facilities.let { it1 ->
                        facilitiesAdapter.facilityList_(
                            it1
                        )
                    }

                    randomDishResponse.exclusions.let { it1 ->
                        facilitiesAdapter.facilityList(
                            it1
                        )
                    }
                }
            })

        homeViewModel.loadingError.observe(
            viewLifecycleOwner,
            Observer { dataError ->
            })

        homeViewModel.load.observe(viewLifecycleOwner, Observer { loadRandomDish ->
            loadRandomDish?.let {
                Log.i("Loading", "$loadRandomDish")
            }
        })
    }

    fun optionList(
        option: List<FacilitiesAPI.Option>,
        exclusions: List<List<FacilitiesAPI.Exclusion>>
    ) {
        Log.e("OptionList", option.toString())
        optionsAdapter.optionsList(option, exclusions)
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }

    private fun createConstraints() = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.NOT_REQUIRED)  // if connected to WIFI
        .setRequiresCharging(false)
        .setRequiresBatteryNotLow(true)                 // if the battery is not low
        .build()

    private fun createWorkRequest() = PeriodicWorkRequestBuilder<APIWorker>(15, TimeUnit.MINUTES)
        .setConstraints(createConstraints())
        .build()

    private fun startWork() {

        /* enqueue a work, ExistingPeriodicWorkPolicy.KEEP means that if this work already exists, it will be kept
        if the value is ExistingPeriodicWorkPolicy.REPLACE, then the work will be replaced */
        WorkManager.getInstance(requireActivity())
            .enqueueUniquePeriodicWork(
                "API Work", ExistingPeriodicWorkPolicy.KEEP,
                createWorkRequest()
            )
    }
}