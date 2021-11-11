package com.falconnine.ui.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.falconnine.R
import com.falconnine.network.model.Launch
import com.falconnine.utils.DateUtils
import java.lang.StringBuilder

class LaunchesAdapter(private val context: Context, private val launches: ArrayList<Launch>):
    RecyclerView.Adapter<LaunchesAdapter.ViewHolder>() {

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val badgeImageView: ImageView = view.findViewById(R.id.badge)
        val nameTextView: TextView = view.findViewById(R.id.name)
        val launchDateTextView: TextView = view.findViewById(R.id.launch_date)
        val missionSuccessImageView: ImageView = view.findViewById(R.id.mission_success_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.launch_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(context)
            .load(launches[position].badgeImageUrl)
            .placeholder(R.drawable.image_placeholder)
            .into(holder.badgeImageView)

        holder.nameTextView.text = launches[position].name
        holder.launchDateTextView.text = StringBuilder()
            .append(context.getString(R.string.launch_date))
            .append(" ")
            .append(DateUtils.formatDate(launches[position].launchDate!!))

        if (launches[position].missionSuccessful) {
            holder.missionSuccessImageView.setBackgroundResource(R.drawable.tick)
        } else {
            holder.missionSuccessImageView.setBackgroundResource(R.drawable.cross)
        }
    }

    override fun getItemCount() = launches.size
}