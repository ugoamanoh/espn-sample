package com.bamtech.multisport.mobile.media

import android.databinding.DataBindingUtil
import android.databinding.ObservableArrayList
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bamtech.multisport.R
import com.bamtech.multisport.databinding.MediaListBinding
import com.bamtech.multisport.core.viewmodels.media.MediaListViewModel

/**
 * Created by david on 6/13/17.
 */
class MediaListAdapter(list: ObservableArrayList<MediaListViewModel>,
                       private var mediaItemListener: MediaItemListener) : RecyclerView.Adapter<MediaListAdapter.ViewHolder>() {
    var mediaList: ObservableArrayList<MediaListViewModel> = list

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MediaListAdapter.ViewHolder {
        val binding = DataBindingUtil.inflate<MediaListBinding>(LayoutInflater.from(parent?.context),
                R.layout.media_list, parent, false)
        binding.mediaItemListener = mediaItemListener
        val viewHolder = MediaListAdapter.ViewHolder(binding.root)

        return viewHolder
    }

    override fun getItemCount(): Int {
        return mediaList.size
    }

    override fun onBindViewHolder(holder: MediaListAdapter.ViewHolder, position: Int) {
        with(holder.binding) {
            viewModel = mediaList[position]
            executePendingBindings()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }


    class ViewHolder(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView) {
        var binding = DataBindingUtil.getBinding<MediaListBinding>(itemLayoutView)
    }
}