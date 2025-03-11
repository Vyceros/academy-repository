package com.example.baseandroidproject.domain.singletons

import com.example.baseandroidproject.domain.abstractions.PreferenceKey

object DataStoreKeys {

     object UserEmail : PreferenceKey<String> {
        override val name = "USER_EMAIL"
        override val defaultValue = ""
    }

     object UserToken : PreferenceKey<String>{
        override val name = "USER_TOKEN"
        override val defaultValue = ""
    }

     object RememberMe : PreferenceKey<Boolean>{
        override val name = "REMEMBER_ME"
        override val defaultValue = false
    }
}