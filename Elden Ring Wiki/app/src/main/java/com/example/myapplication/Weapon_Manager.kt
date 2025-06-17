package com.example.myapplication

import android.content.Context
import android.util.Log
import com.android.volley.Request.Method
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class Weapon_Manager {

    companion object
    {
        private const val endpoint="https://final-project-208a3-default-rtdb.europe-west1.firebasedatabase.app/"
        var weapons= ArrayList<Weapon>()

        fun getWeapons(context: Context,retrival:()->Unit)
        {
            weapons.clear()
            val request=Volley.newRequestQueue(context)
            val json_objects= JsonObjectRequest(
                Method.GET,
                "${endpoint}Boss Weapons.json"
                ,null,
                {
                        response->

                    weapons.clear()
                    val keys=response.keys()
                    keys.forEach { key->
                        var weapon_obj=response.getJSONObject(key)
                       var name=weapon_obj.getString("Name")
                        var scalability=weapon_obj.getString("Scalability")
                        var owner=weapon_obj.getString("Owner")
                        var skill=weapon_obj.getString("Skill")
                        var image=weapon_obj.getString("image")

                        val boss=Weapon(key,name, owner ,scalability,skill,image)
                        weapons.add(boss)
                        Log.v("weapons",weapons.toString())
                    }

                    retrival()


                },
                {
                        error->error.printStackTrace()
                }

            )

            request.add(json_objects)


        }

        fun addWeapon(context: Context, name:String, owner:String, scales: String,skill:String,image:String, weapon_added:()->Unit)
        {
            val request= Volley.newRequestQueue(context)
            val weapon_data= JSONObject().apply()
            {
                put("Name",name)
                put("Owner",owner)
                put("Scalability",scales)
                put("Skill",skill)
                put("image",image)
            }

            val jsoneObjectRequest= JsonObjectRequest(
                Method.POST,
                "${endpoint}Boss Weapons.json"
                ,weapon_data,
                {
                        response->
                    weapons.add(Weapon(response.getString("name"),name,owner,scales,skill,image))
                    Log.d("response",response.toString())
                    weapon_added()
                },
                {
                        error->error.printStackTrace()
                }
            )
            request.add(jsoneObjectRequest)
        }






    }
}