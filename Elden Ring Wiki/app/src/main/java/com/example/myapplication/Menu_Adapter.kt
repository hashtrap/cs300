package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Menu_Adapter(private val context:Context, private var menus: ArrayList<Menu_item>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.menu_layout, parent, false)
        return object: RecyclerView.ViewHolder(view){}
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val menu = menus[position]
        val imageid = when (menu.image) {
            "bosses" -> R.drawable.bosses
            "weapons" -> R.drawable.weapons
            "user" -> R.drawable.log_sign_logo
            else -> R.drawable.ic_launcher_background // Default image resource
        }
        holder.itemView.findViewById<ImageView>(R.id.item_img).setImageResource(imageid)
        holder.itemView.findViewById<TextView>(R.id.item_text).text = menu.desc
        holder.itemView.setOnClickListener {
            if(menu.desc=="Bosses")
            {
                var intent= Intent(context, Boss_Activity::class.java)
                context.startActivity(intent)
            }

            else if(menu.desc=="Boss Weapons")
            {
                var intent= Intent(context, Weapon_activity::class.java)
                context.startActivity(intent)
            }

            else{}
        }
    }
    override fun getItemCount(): Int {
        return this.menus.size
    }
}