package com.example.wedding_book_keeper.presentation.view

import android.os.Bundle
import android.util.Log
import com.example.wedding_book_keeper.R
import com.example.wedding_book_keeper.data.remote.WeddingBookKeeperClient
import com.example.wedding_book_keeper.data.remote.response.WeddingInfoResponse
import com.example.wedding_book_keeper.databinding.ActivityMainBinding
import com.example.wedding_book_keeper.presentation.config.BaseActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val TAG = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}
