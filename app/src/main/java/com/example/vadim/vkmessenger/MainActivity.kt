package com.example.vadim.vkmessenger

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
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
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

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

                }
                override fun onError(error: VKError) {
                    //Пользователь пошел нахуй или решил нажать "ОТМЕНА"
                    finish()
                    System.exit(0)
                }
            }))
                super.onActivityResult(requestCode, resultCode, data)
    }

    fun btn_Friends_Click(view: View){
        val intent = Intent(this@MainActivity, Friends_Screen::class.java)
        startActivity(intent)
    }
}
