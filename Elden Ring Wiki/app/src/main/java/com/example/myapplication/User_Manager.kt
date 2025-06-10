package com.example.myapplication

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Request.Method
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import kotlin.apply

class User_Manager {

    companion object
    {
        private val endpoint="https://final-project-208a3-default-rtdb.europe-west1.firebasedatabase.app/"
        var users= ArrayList<User>()

        fun getUsers(context: Context,retrival:()->Unit)
        {
            val request=Volley.newRequestQueue(context)
            val json_objects= JsonObjectRequest(
                Method.GET,
                "${endpoint}Users.json"
                ,null,
                {
                    response->

                        users.clear()
                        val keys=response.keys()
                    keys.forEach { key->
                        val user_obj=response.getJSONObject(key)
                        val username=user_obj.getString("username")
                        val password=user_obj.getString("password")
                        val email=user_obj.getString("email")

                        val user=User(key,username,password,email)
                        users.add(user)
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
                    users.add(User(response.getString("name"),username,password,email))
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
                    val old_user= users.indexOfFirst { it.id==id }
                    users.set(old_user,new_user)
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
                    val user = users.find { it.id == id }



                    user_deleted(user)
                },
                { error ->
                    error.printStackTrace()
                })

            request.add(jsonObjectRequest)
        }



    }
}