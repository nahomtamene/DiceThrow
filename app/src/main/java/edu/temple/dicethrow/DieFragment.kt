package edu.temple.dicethrow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlin.random.Random

class DieFragment : Fragment() {

    companion object {
        private const val DIESIDE = "sidenumber"
        private const val DEFAULT_SIDES = 6


        fun newInstance(sides: Int = DEFAULT_SIDES): DieFragment {
            val fragment = DieFragment()
            fragment.arguments = Bundle().apply {
                putInt(DIESIDE, sides)
            }
            return fragment
        }
    }

    val DIESIDE = "sidenumber"

    lateinit var dieTextView: TextView

    private var dieSides: Int = DEFAULT_SIDES

    val PREVIOUS_ROLL = "previousroll"

    var currenRoll = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            it.getInt(DIESIDE).run {
                dieSides = this
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_die, container, false).apply {
            dieTextView = findViewById(R.id.dieTextView)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null)
            throwDie()
        else{
            currenRoll = savedInstanceState.getInt(PREVIOUS_ROLL)
            dieTextView.text = currenRoll.toString()

        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(PREVIOUS_ROLL , currenRoll)

    }

    fun throwDie() {
        currenRoll = (Random.nextInt(dieSides)+1)
        dieTextView.text = currenRoll.toString()

    }


//    companion object{
//        fun newInstance (sides : Int) = DieFragment().apply {
//            arguments = Bundle().apply {
//                putInt(DIESIDE,sides)
//            }
//        }
//    }
}