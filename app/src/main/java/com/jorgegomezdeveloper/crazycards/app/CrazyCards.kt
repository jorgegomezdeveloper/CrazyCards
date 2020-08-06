package com.jorgegomezdeveloper.crazycards.app

import android.app.Application
import com.jorgegomezdeveloper.crazycards.di.ccFragmentsModule
import com.jorgegomezdeveloper.crazycards.di.ccManagerModule
import com.jorgegomezdeveloper.crazycards.di.ccUtilsModule
import com.jorgegomezdeveloper.crazycards.di.ccViewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * @author Jorge Gomez Alvarez (jorgegomezdeveloper@gmail.com)
 */
class CrazyCards: Application() {

    override fun onCreate() {
        super.onCreate()
        initializeKoin()

    }

    /**
     * I use the Koin service locator to instantiate the different classes.
     * For large or very large apps it would be necessary to use a dependency injector like Dagger,
     * but for small apps Koin is sufficient and easy to implement.
     */
    private fun initializeKoin() {

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@CrazyCards)
            androidFileProperties()

            //Declare modules.
            modules(listOf(
                //FRAGMENTS
                ccFragmentsModule,
                //VIEW MODELS
                ccViewModelsModule,
                //UTILS
                ccUtilsModule,
                //MANAGERS
                ccManagerModule
            ))
        }
    }
}