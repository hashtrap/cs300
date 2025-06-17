package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class Boss_Adapter(private val context: Context, private val bosses: ArrayList<Boss>) :
    RecyclerView.Adapter< RecyclerView.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.boss_single_layout, parent, false)
        return object: RecyclerView.ViewHolder(view){}
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val boss = bosses[position]
        Picasso.get().load(boss.image).into(holder.itemView.findViewById<ImageView>(R.id.boss_img))
        holder.itemView.findViewById<TextView>(R.id.boss_text).text = boss.name
        holder.itemView.setOnClickListener {
        var intent=Intent(context, Single_Boss::class.java)
            Log.v("items_json",boss.items.toString())
            intent.putExtra("items",boss.items)
            intent.putExtra("location",boss.location)
            intent.putExtra("name",boss.name)
            intent.putExtra("strengths",boss.strengths)
            intent.putExtra("weakness",boss.weakness)
            intent.putExtra("image",boss.image)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return bosses.size
    }
}