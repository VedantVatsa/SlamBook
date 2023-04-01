package com.vit.hellovit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class FriendsListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends_list)
        val rvFriends = findViewById<RecyclerView>(R.id.rvFriendList)


        val friendsList = arrayListOf<Friend>()
        val dbHelper = DBHelper(this, null)
        val cursor = dbHelper.getFriendsFromSlamBook()
        if(cursor!=null)
        {
            cursor.moveToFirst()
            while(cursor.moveToNext())
            {
                val name : String = cursor.getString(cursor.getColumnIndex(DBHelper.FRIEND_NAME))
                val dob : String = cursor.getString(cursor.getColumnIndex(DBHelper.FRIEND_DOB))
                val phone : String = cursor.getString(cursor.getColumnIndex(DBHelper.FRIEND_PHONE))
                val hobby : String = cursor.getString(cursor.getColumnIndex(DBHelper.FRIEND_HOBBY))
                val friend = Friend(name, dob, phone, hobby)
                friendsList.add(friend)
            }
        }
        rvFriends.adapter=FriendsAdapter(friendsList,this)
    }
}