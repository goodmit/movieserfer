package com.goodmit.movieserfer.presentation.ui.incoming

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.goodmit.movieserfer.databinding.FragmentIncomingBinding

class IncomingFragment : Fragment() {

    private var _binding: FragmentIncomingBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val incomingViewModel =
            ViewModelProvider(this).get(IncomingViewModel::class.java)

        _binding = FragmentIncomingBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textIncoming
        incomingViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}