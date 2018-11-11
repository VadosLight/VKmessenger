package com.example.vadim.vkmessenger

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.vk.sdk.VKAccessToken
import com.vk.sdk.VKCallback
import com.vk.sdk.VKScope
import com.vk.sdk.VKSdk
import com.vk.sdk.api.VKError


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
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
                    //Пользователь решил нажать "ОТМЕНА"
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

    fun btn_CloseApp_Click(view: View){
        finish()
        System.exit(0)
    }

    fun btn_Dialogs_Click(view: View){
        val intent = Intent(this@MainActivity, Dialog_Screen::class.java)
        startActivity(intent)
    }



}
