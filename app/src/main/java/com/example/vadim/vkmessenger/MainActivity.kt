package com.example.vadim.vkmessenger

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.vk.sdk.VKScope
import com.vk.sdk.VKSdk
import com.vk.sdk.util.VKUtil
import java.util.*


class MainActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        VKSdk.login(this,
            VKScope.MESSAGES,
            VKScope.FRIENDS,
            VKScope.NOTIFICATIONS,
            VKScope.WALL,
            VKScope.OFFLINE)


    }
}
