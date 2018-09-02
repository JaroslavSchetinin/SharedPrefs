package com.example.admin.myapplication

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class Frag : Fragment() {
    private lateinit var textView: TextView
    private lateinit var edit: EditText
    private lateinit var button: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textView = view.findViewById(R.id.text_view)
        edit = view.findViewById(R.id.edit_text)
        button = view.findViewById(R.id.button)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val sharedPref = activity?.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val savedValue = sharedPref?.getString(ADVERTISING_TEXT_KEY, null)
        savedValue?.let{
            savedText -> textView.text = savedText
        }

        button.setOnClickListener{
            val ourText = edit.text.toString()
            textView.text = ourText
            sharedPref?.let{
                saveToSharedPref(it, ourText)
            }
        }
    }

    private fun saveToSharedPref(sharedPrefs: SharedPreferences, text: String){
        sharedPrefs.edit().putString(ADVERTISING_TEXT_KEY, text).apply()
    }



    companion object {
        const val SHARED_PREF_NAME = "name"
        const val ADVERTISING_TEXT_KEY = "key"
    }


}