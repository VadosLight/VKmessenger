package com.example.vadim.vkmessenger;

import android.content.Intent;
import android.support.annotation.Nullable;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKAccessTokenTracker;
import com.vk.sdk.VKSdk;

public class Application extends android.app.Application {

    //Проверяем токен на корректность
    VKAccessTokenTracker vkAccessTokenTracker = new VKAccessTokenTracker() {
        @Override
        public void onVKAccessTokenChanged(@Nullable VKAccessToken oldToken, @Nullable VKAccessToken newToken) {
            if(newToken == null){
                Intent intent = new Intent(Application.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        }
    };

    @Override
    public void onCreate(){
        super.onCreate();

        //Не особо важная функция, но она позволяет правильно работать при неправильном токене
        vkAccessTokenTracker.startTracking();

        VKSdk.initialize(this);
    }
}
