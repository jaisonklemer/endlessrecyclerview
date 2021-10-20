package com.klemer.EndlessRecyclerViewSample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.recyclerview.widget.LinearLayoutManager
import com.klemer.endlessrecyclerview.EndlessRecyclerView
import com.klemer.EndlessRecyclerViewSample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sampleAdapter: SampleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sampleAdapter = SampleAdapter()
        loadMoreItems()
        setupSampleRecyclerView()
    }

    private fun setupSampleRecyclerView() {
        binding.rvMain.layoutManager = LinearLayoutManager(this)
        binding.rvMain.adapter = sampleAdapter

        val infinityScrolling = EndlessRecyclerView(binding.rvMain) {
            println("You reached final of list")

            Handler().postDelayed({
                loadMoreItems()
            }, 2000)
        }

        infinityScrolling
            .setCustomLoadingView(R.layout.custom_loading)
            .showLoading(true)

        binding.rvMain.addOnScrollListener(infinityScrolling.create())
    }

    private fun loadMoreItems() {
        val list = mutableListOf<String>()
        for (i in 0..30) {
            list.add("Sample item $i")
        }
        //update SampleAdapter with new items
        sampleAdapter.update(list)
    }
}