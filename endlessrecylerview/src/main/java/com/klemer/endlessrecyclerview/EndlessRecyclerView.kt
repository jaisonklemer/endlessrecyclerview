package com.klemer.endlessrecyclerview

import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView

/**
 * Create Instance of EndlessRecyclerView
 * @param recyclerView RecyclerView to create infinite scroll.
 * @param onScrolledToBottom Callback to be invoked when recyclerView has been scrolled.
 */
open class EndlessRecyclerView(
    private val recyclerView: RecyclerView,
    private val onScrolledToBottom: (endlessRecyclerView: EndlessRecyclerView) -> Unit
) : RecyclerView.OnScrollListener() {

    private var isLoading = false
    private lateinit var endlessRecyclerViewAdapter: EndlessRecyclerViewAdapter
    private var showLoading = true
    private var customLoadingView: Int? = null

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        if (!recyclerView.canScrollVertically(1)
            && newState == RecyclerView.SCROLL_STATE_IDLE
            && !isLoading
        ) {
            isLoading = true
            if (showLoading)
                endlessRecyclerViewAdapter.shouldLoading(true)

            /**
             * This callback is invoked when recyclerView has been scrolled to end
             * */
            onScrolledToBottom.invoke(this)
        }
    }

    /**
     * Register an observer to current adapter. When adapter dataset is changed,
     * this hide the loading view.
     * @param recyclerView current recycler view.
     */
    private fun registerAdapterObserver(recyclerView: RecyclerView) {
        recyclerView.adapter?.registerAdapterDataObserver(object :
            RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                super.onChanged()
                endlessRecyclerViewAdapter.shouldLoading(false)
                isLoading = false
            }
        })
    }

    /**
     * Define if show a loading when scrolled to bottom of list
     *
     * @param show Boolean if show or not loading.
     */
    fun showLoading(show: Boolean = true): EndlessRecyclerView {
        showLoading = show
        return this
    }

    /**
     * Call this function if you need stop loading animation
     */
    fun stopLoading(): EndlessRecyclerView {
        isLoading = false
        endlessRecyclerViewAdapter.shouldLoading(false)
        return this
    }

    /**
     * Define a custom view for loading. If set this, showLoading is set to true
     *
     * @param customView Custom layout.
     */
    fun setCustomLoadingView(@LayoutRes customView: Int): EndlessRecyclerView {
        this.showLoading(true)
        customLoadingView = customView
        return this
    }

    /**
     * Create EndlessRecyclerView
     */
    fun create(): EndlessRecyclerView {
        endlessRecyclerViewAdapter = EndlessRecyclerViewAdapter(customLoadingView)

        registerAdapterObserver(recyclerView)
        val currentAdapter = recyclerView.adapter

        if (showLoading) {
            recyclerView.adapter = ConcatAdapter(currentAdapter, endlessRecyclerViewAdapter)
        }
        return this
    }
}
