package com.example.beardwulf.reva

import android.Manifest
import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.support.test.InstrumentationRegistry
import android.support.test.InstrumentationRegistry.getContext
import android.support.test.InstrumentationRegistry.getInstrumentation
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.closeSoftKeyboard
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.Intents.intending
import android.support.test.espresso.intent.matcher.IntentMatchers.toPackage
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.RootMatchers.withDecorView
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.GrantPermissionRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import android.widget.ImageView
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.stream.Collectors

import com.example.beardwulf.reva.activities.MainActivity

@RunWith(AndroidJUnit4::class)
class MainActivityTest{
    @get:Rule
    val mainActivity = IntentsTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun launchTest() {
        onView(withId(R.id.btnLogin)).check(matches(isDisplayed()))
    }

    @Test
    fun inputText(){
        onView(withId(R.id.txtInput)).perform(ViewActions.typeText("!")).perform(closeSoftKeyboard())
        onView(withId(R.id.btnLogin)).perform(click())
        onView(withId(R.id.txtInput)).check(matches(withHint("Voer enkel cijfers en letters in aub")))
    }
}