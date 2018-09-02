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
import com.example.admin.myapplication.R

class Frag2: Fragment(){

    private lateinit var textView: TextView
    private lateinit var edit: EditText
    private lateinit var button: Button


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment, container,false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textView = view.findViewById(R.id.text_view)
        edit = view.findViewById(R.id.edit_text)
        button = view.findViewById(R.id.button)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val sharePref = activity?.getSharedPreferences(NAME, Context.MODE_PRIVATE)
        val savedValue = sharePref?.getString(KEY, null)
        savedValue?.let{
            s ->  textView.text = s
        }

        button.setOnClickListener {
            val ourText = edit.text.toString()
            textView.text = ourText
            sharePref?.let{
                saveToSharedprefs(it, ourText)
            }

        }



    }

    private fun saveToSharedprefs(sharedPref: SharedPreferences, ourText: String){

            sharedPref.edit().putString(KEY, ourText).apply()


    }

    companion object {
        val KEY = "key"
        val NAME ="name"
    }
}