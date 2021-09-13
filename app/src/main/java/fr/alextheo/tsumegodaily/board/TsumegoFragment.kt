package fr.alextheo.tsumegodaily.board

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.example.tsumegodaily.R
import fr.alextheo.tsumegodaily.SGFParser

class TsumegoFragment : Fragment(R.layout.tsumego_fragment) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.tsumego_fragment, container, false)
        val layout: FrameLayout = view.findViewById(R.id.frame_layout)

        // Fetching the SGF from the bundle and adding the board to the fragment
        val sgf = SGFParser(requireArguments().getString("tsumego_sgf"))
        layout.addView(VariationBoardView(requireContext(), sgf))

        return view
    }
}
