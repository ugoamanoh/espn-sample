package com.bamtech.multisport.mobile.media

import android.databinding.ObservableArrayList
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bamtech.multisport.R
import com.bamtech.multisport.core.viewmodels.media.MediaItemViewModel
import com.bamtech.multisport.core.viewmodels.media.MediaItemViewType
import com.bamtech.multisport.mobile.media.view.*


/**
 * Media Item adapter
 */

class MediaItemAdapter(private val mediaItemList: ObservableArrayList<MediaItemViewModel>,
                       private val mediaItemListener: MediaItemListener) : RecyclerView.Adapter<MediaItemAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val mediaItemView = when (viewType) {
            MediaItemViewType.LARGE_ITEM_WITH_HOCKEY_SCOREBOARD.getId() ->
                createLargeMediaItemView(parent, MediaItemViewType.LARGE_ITEM_WITH_HOCKEY_SCOREBOARD)

            MediaItemViewType.LARGE_ITEM_WITH_GOLF_SCOREBOARD.getId() ->
                createLargeMediaItemView(parent, MediaItemViewType.LARGE_ITEM_WITH_GOLF_SCOREBOARD)

            MediaItemViewType.LARGE_ITEM_WITH_INFO_VIEW.getId() ->
                createLargeMediaItemView(parent, MediaItemViewType.LARGE_ITEM_WITH_INFO_VIEW)

            MediaItemViewType.LARGE_ITEM.getId() ->
                createLargeMediaItemView(parent, MediaItemViewType.LARGE_ITEM)

            MediaItemViewType.SMALL_ITEM.getId() ->
                LayoutInflater.from(parent.context).inflate(R.layout.media_item_small, parent, false) as SmallMediaItemView

            MediaItemViewType.SMALL_ITEM_SEARCH_RESULTS.getId() ->
                LayoutInflater.from(parent.context).inflate(R.layout.media_item_search_results, parent, false) as SmallMediaItemView

            MediaItemViewType.MEDIUM_ITEM.getId() ->
                LayoutInflater.from(parent.context).inflate(R.layout.media_item_medium, parent, false) as MediumMediaItemView

            else ->  //default set to medium
                LayoutInflater.from(parent.context).inflate(R.layout.media_item_medium, parent, false) as MediumMediaItemView
        }
        val viewHolder = ViewHolder(mediaItemView)

        return viewHolder
    }


    /**
     * Creates Large media item view
     * @param mediaItemViewType
     */
    private fun createLargeMediaItemView(parent: ViewGroup, mediaItemViewType: MediaItemViewType): LargeMediaItemView {

        val largeMediaItemView = LayoutInflater.from(parent.context).inflate(R.layout.media_item_large, parent, false) as LargeMediaItemView;
        largeMediaItemView.initializeView(mediaItemViewType, mediaItemListener)
        return largeMediaItemView

    }

    override fun getItemCount(): Int {
        return mediaItemList.size
    }


    override fun getItemViewType(position: Int): Int {
        return mediaItemList.get(position).mediaItemViewType.get().getId()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mediaItem = mediaItemList[position]

        if (holder.itemView is BaseMediaItemView) {
            holder.itemView.bind(mediaItem, MediaItemPresenter(mediaItemListener))
        }

    }

    override fun onViewAttachedToWindow(holder: ViewHolder) {
        super.onViewAttachedToWindow(holder)
        if (holder.itemView is BaseMediaItemView) {
            holder.itemView.onVisibilityChanged(true)
        }

    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        if (holder.itemView is BaseMediaItemView) {
            holder.itemView.onVisibilityChanged(false)
        }
    }

    /*Adds data at the end of the existing data*/
    fun updateData(data: List<MediaItemViewModel>) {
        mediaItemList.addAll(mediaItemList.size, data)
    }


    /* Clears adapter data*/
    fun clearData() {
        mediaItemList.clear()
    }

    class ViewHolder(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView)
}