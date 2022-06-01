package com.gopal.myapplication.viewmodel

import androidx.lifecycle.*
import com.gopal.myapplication.data.api.ClientApi
import com.gopal.myapplication.data.model.FacilitiesAPI
import com.gopal.myapplication.data.repositories.FacilitiesRepositories
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch

class HomeViewModel(private val facilitiesRepositories: FacilitiesRepositories): ViewModel() {

    private val clientApi = ClientApi()
    private val compositeDisposable = CompositeDisposable()
    val load = MutableLiveData<Boolean>()
    val loadingError = MutableLiveData<Boolean>()
    val loadList = MutableLiveData<FacilitiesAPI.Facilities>()

    fun insert(facilities: FacilitiesAPI.Facilities) = viewModelScope.launch {
        // Call the repository function and pass the details.
        facilitiesRepositories.insertData(facilities)
    }

    val allDishesList: LiveData<List<FacilitiesAPI.Facilities>> = facilitiesRepositories.allFacilitiesList.asLiveData()


    fun getFacilityList(){
        load.value = true
        compositeDisposable.add(
            clientApi.getFacilitiesList()
                // Asynchronously subscribes SingleObserver to this Single on the specified Scheduler.
                .subscribeOn(Schedulers.newThread())
                /**
                 * Signals the success item or the terminal signals of the current Single on the specified Scheduler,
                 * asynchronously.
                 *
                 * A Scheduler which executes actions on the Android main thread.
                 */
                ?.observeOn(AndroidSchedulers.mainThread())
                /**
                 * Subscribes a given SingleObserver (subclass) to this Single and returns the given
                 * SingleObserver as is.
                 */
                ?.subscribeWith(object : DisposableSingleObserver<FacilitiesAPI.Facilities>() {
                    override fun onSuccess(value: FacilitiesAPI.Facilities) {
                        // Update the values with response in the success method.
                        load.value = false
                        loadList.value = value;
                        loadingError.value = false
                    }

                    override fun onError(e: Throwable?) {
                        // Update the values in the response in the error method
                        load.value = false
                        loadingError.value = true
                        e!!.printStackTrace()
                    }
                })!!
        )
    }

    class HomeViewModelFactory(private val repository: FacilitiesRepositories) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return HomeViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}