package com.example.vadim.vkmessenger

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.vk.sdk.VKAccessToken
import com.vk.sdk.VKCallback
import com.vk.sdk.VKScope
import com.vk.sdk.VKSdk
import com.vk.sdk.api.VKError
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, object : VKCallback<VKAccessToken> {
                override fun onResult(res: VKAccessToken) {
                    //Пользователь успешно авторизовался
                    val intent = Intent(this@MainActivity, Main_Menu::class.java)
                    startActivity(intent)
                }
                override fun onError(error: VKError) {
                    //Пользователь пошел нахуй или решил нажать "ОТМЕНА"
                }
            }))
                super.onActivityResult(requestCode, resultCode, data)
    }
}
