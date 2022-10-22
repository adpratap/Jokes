package com.noreplypratap.randomuser.fragments

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.noreplypratap.randomuser.R
import com.noreplypratap.randomuser.databinding.FragmentRandomInfoBinding
import com.noreplypratap.randomuser.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RandomInfoFragment : Fragment() {

    lateinit var binding : FragmentRandomInfoBinding

    private val mainViewModel : MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRandomInfoBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
        getJoke()

        binding.getPicBtn.setOnClickListener {
            getJoke()
        }

    }

    private fun getJoke() {
        if (isOnline() != false){
            loadjoke()
        } else{
            nonet()
        }
    }

    private fun nonet() {
        binding.setUserTextView.text = "No Internet"
        binding.punchline.text = "No connection...."
    }

    private fun loadjoke() {
        mainViewModel.getJokes()
    }

    private fun setObservers() {
        mainViewModel.responseLiveData.observe(viewLifecycleOwner){ data ->

            Log.d("MVVMRANDOM",data.setup)
            binding.setUserTextView.text = data.setup
            binding.punchline.text = data.punchline

        }
        mainViewModel.responseErrorData.observe(viewLifecycleOwner){
            Log.d("MVVMERROR",it.toString())
        }
    }

    fun isOnline(): Boolean {
        val connectivityManager =
            context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                return true
            }
        }
        return false
    }


}