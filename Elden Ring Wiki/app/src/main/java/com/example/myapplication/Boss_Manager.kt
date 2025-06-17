package com.example.myapplication


import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Request.Method
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication.Weapon_Manager.Companion.weapons
import org.json.JSONObject

class Boss_Manager {
    companion object {
        val bosses = ArrayList<Boss>()

        val url = "https://final-project-208a3-default-rtdb.europe-west1.firebasedatabase.app/"
        fun getBosses(context: Context, boss_gotten: () -> Unit) {
            bosses.clear()

            //step 1
            val requestQueue = Volley.newRequestQueue(context)
            //step 2 - create the request
            val jsonObjectRequest = JsonObjectRequest(
                Request.Method.GET,
                "${url}Bosses.json",
                null,
                Response.Listener { response ->
                    val keys = response.keys()
                    keys.forEach { key ->
                        val jsonObject = response.getJSONObject(key)

                        val items = jsonObject.getString("Items_dropped")
                        val location = jsonObject.getString("Location")
                        val name = jsonObject.getString("Name")
                        val strengths = jsonObject.getString("Strong_to")
                        val weakness = jsonObject.getString("Weak_to")
                        val image = jsonObject.getString("image")






                        val boss = Boss(key, items, location, name, strengths, weakness, image)
                        bosses.add(boss)
                        Log.v("bosses",boss.toString())

                    }
                    boss_gotten()
                },
                Response.ErrorListener { error ->
                    error.printStackTrace()
                }
            )

            //step 3 add the request
            requestQueue.add(jsonObjectRequest)

        }

        fun addBoss(context: Context, items:String, location:String,name:String, strengths: String, weakness:String, image:String, boss_added:()->Unit)
        {
            val request= Volley.newRequestQueue(context)
            val weapon_data= JSONObject().apply()
            {
                put("Items_dropped",items)
                put("Location",location)
                put("Name",name)
                put("Strong_to",strengths)
                put("Weak_to",weakness)
                put("image",image)

            }

            val jsoneObjectRequest= JsonObjectRequest(
                Method.POST,
                "${url}Boss Weapons.json"
                ,weapon_data,
                {
                        response->
                    bosses.add(Boss(response.getString("name"),items,location,name,strengths,weakness,image))

                    boss_added()
                },
                {
                        error->error.printStackTrace()
                }
            )
            request.add(jsoneObjectRequest)
        }


    }
}


