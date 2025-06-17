package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class Weapons_Adapter (private val context:Context, private var weapons: ArrayList<Weapon>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.weapon_single_layout, parent, false)
        return object: RecyclerView.ViewHolder(view){}
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val weapon = weapons[position]
        Picasso.get().load(weapon.image).into(holder.itemView.findViewById<ImageView>(R.id.weapon_img1))
        holder.itemView.findViewById<TextView>(R.id.weapon_text1).text = weapon.name
        holder.itemView.setOnClickListener {
            var intent=Intent(context, Single_weapon::class.java)
            intent.putExtra("name",weapon.name)
            intent.putExtra("owner",weapon.owner)
            intent.putExtra("scalability",weapon.Scalability)
            intent.putExtra("skill",weapon.skill)
            intent.putExtra("image",weapon.image)
            context.startActivity(intent
            )
        }
    }

    override fun getItemCount(): Int {
        return weapons.size
    }
}