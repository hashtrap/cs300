package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.squareup.picasso.Picasso

class Log_in : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_log_in)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var username=findViewById<EditText>(R.id.username)
        var password=findViewById<EditText>(R.id.password)
        var btn_log_in=findViewById<Button>(R.id.btn_log_in)


        btn_log_in.setOnClickListener {
            if(User_Manager.users.isEmpty())
            {
                User_Manager.getUsers(this) {
                    User_Manager.users.forEach label@ {
                            user->
                        if(username.text.toString()==user.username &&password.text.toString()==user.password)
                        {
                            Toast.makeText(this,"Correct password and username",Toast.LENGTH_SHORT).show()
                            var intent=Intent(this,Home::class.java)
                            startActivity(intent)
                            return@label
                        }

                        else if(username.text.toString()==user.username)
                        {
                            Toast.makeText(this,"Incorrect password",Toast.LENGTH_SHORT).show()
                        }

                        else if(password.text.toString()==user.password)
                        {
                            Toast.makeText(this,"Incorrect username",Toast.LENGTH_SHORT).show()
                        }

                        else
                        {
                            Toast.makeText(this,"Incorrect credentials",Toast.LENGTH_SHORT).show()
                        }

                    }


                }
            }

            else
            {
                User_Manager.users.forEach {
                        user->
                    if(username.text.toString()==user.username &&password.text.toString()==user.password)
                    {

                        var intent=Intent(this,Home::class.java)
                        startActivity(intent)

                    }

                    else if(username.text.toString()==user.username)
                    {
                        Toast.makeText(this,"Incorrect password",Toast.LENGTH_SHORT).show()
                    }

                    else if(password.text.toString()==user.password)
                    {
                        Toast.makeText(this,"Incorrect username",Toast.LENGTH_SHORT).show()
                    }

                    else
                    {
                        Toast.makeText(this,"Incorrect credentials",Toast.LENGTH_SHORT).show()
                    }

                }


            }
        }





    }
}