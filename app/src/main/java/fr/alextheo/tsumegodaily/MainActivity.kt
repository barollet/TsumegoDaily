package fr.alextheo.tsumegodaily

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.os.bundleOf
import com.example.tsumegodaily.R
import fr.alextheo.tsumegodaily.board.TsumegoFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {

            val bundle = bundleOf("tsumego_sgf" to "[GO][Ccoucou][Wab][Wac][Bbc][Wde]")

            supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.fragment_container_view, TsumegoFragment::class.java, bundle)
                .commit()
        }
    }
}