package fr.alextheo.tsumegodaily.board

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tsumegodaily.R

class TsumegoFragment : Fragment() {

    companion object {
        fun newInstance() = TsumegoFragment()
    }

    private lateinit var viewModel: TsumegoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.board_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TsumegoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}