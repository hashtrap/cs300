package com.example.myapplication

class Menu_Manager {

    companion object
    {
        var items= ArrayList<Menu_item>()

        fun getMenu()
        {
            var item1= Menu_item("bosses","Bosses")
            var item2= Menu_item("weapons","Boss Weapons")
            var item3= Menu_item("user","Account")

            items.add(item1)
            items.add(item2)
            items.add(item3)
        }
    }
}