package com.example.myapplication

import android.content.Context
import android.util.Log
import com.android.volley.Request.Method
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class Boss_Manager {

    companion object
    {
        var bosses= ArrayList<Boss>()

        private val endpoint="https://final-project-208a3-default-rtdb.europe-west1.firebasedatabase.app/"


        fun getBosses(context: Context,retrival:()->Unit)
        {
            val request=Volley.newRequestQueue(context)
            val json_objects= JsonObjectRequest(
                Method.GET,
                "${endpoint}Bosses.json"

                ,null,
                {
                        response->

                    bosses.clear()
                    val keys=response.keys()
                    keys.forEach { key->
                        val boss_obj=response.getJSONObject(key)
                        val json_items=boss_obj.getJSONArray("Items_dropped")
                        val location=boss_obj.getString("Location")
                        val name=boss_obj.getString("name")
                        val json_strengths=boss_obj.getJSONArray("Strong_to")
                        val json_weakness=boss_obj.getJSONArray("Weak_to")
                        val image=boss_obj.getString("IMAGE")
                        val items= ArrayList<String>()
                        val strengths= ArrayList<String>()
                        val weakness= ArrayList<String>()

                        for(i in 0 until json_items.length())
                        {
                            items.add(json_items[i].toString())
                        }

                        for(i in 0 until json_strengths.length())
                        {
                            strengths.add(json_strengths[i].toString())
                        }

                        for(i in 0 until json_weakness.length())
                        {
                            weakness.add(json_weakness[i].toString())
                        }

                        val boss= Boss(key,items,location,name,strengths,weakness,image)
                        bosses.add(boss)
                    }

                    retrival()


                },
                {
                        error->error.printStackTrace()
                }

            )

            request.add(json_objects)


        }

        fun addUser(context: Context,username:String,password:String,email: String, user_added:()->Unit)
        {
            val request= Volley.newRequestQueue(context)
            val users_data= JSONObject().apply()
            {
                put("username",username)
                put("password",password)
                put("email",email)
            }

            val jsoneObjectRequest= JsonObjectRequest(
                Method.POST,
                "${endpoint}Users.json"
                ,users_data,
                {
                        response->
                    bosses.add(User(response.getString("name"),username,password,email))
                    Log.d("response",response.toString())
                    user_added()
                },
                {
                        error->error.printStackTrace()
                }
            )
            request.add(jsoneObjectRequest)
        }

        fun edit_user(context: Context,id:String, username: String, password: String, email:String, edited_user:()->Unit) {

            val request= Volley.newRequestQueue(context)
            val user_data=JSONObject().apply{
                put("username", username)
                put("password",password)
                put("email",  email)
            }
            val jsonObjectRequest=JsonObjectRequest(
                Method.PUT,
                "${endpoint}Users.json",
                user_data,
                {
                        response ->
                    val new_user=User(id
                        ,response.getString("username")
                        ,response.getString("password")
                        ,response.getString("email"))

                    Log.d("response", response.toString())
                    val old_user= bosses.indexOfFirst { it.id==id }
                    bosses.set(old_user,new_user)
                    edited_user()
                },
                {
                        error -> error.printStackTrace()
                }
            )
            request.add(jsonObjectRequest)
        }

        fun delete_user(context:Context,id:String,user_deleted:(user:User?)->Unit)
        {
            val request = Volley.newRequestQueue(context)

            val jsonObjectRequest= JsonObjectRequest(
                Method.DELETE,
                "${endpoint}Users/"+id+".json",
                null,
                {
                        response ->

                    Log.d("response", response.toString())
                    val user = bosses.find { it.id == id }



                    user_deleted(user)
                },
                { error ->
                    error.printStackTrace()
                })

            request.add(jsonObjectRequest)
        }
    }
}