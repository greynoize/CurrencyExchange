package com.greynoize.base.ui.first

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.greynoize.base.Const.Companion.TAG
import com.greynoize.base.repository.network.MovieRepository
import com.greynoize.base.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class FirstViewModel(private val movieRepository: MovieRepository): BaseViewModel() {
    fun getMovies() {
        Log.d(TAG, "Fetch movies")

        viewModelScope.launch {
            Log.d(TAG, "Fetch movies stared")

            movieRepository.getPopularMovies()

            Log.d(TAG, "Fetch movies completed")

        }

        Log.d(TAG, "Movies fetched")
    }
}