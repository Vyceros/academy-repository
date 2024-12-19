package com.example.baseandroidproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.baseandroidproject.databinding.FragmentSignInBinding

class SignInFragment : Fragment() {

    private var _binding : FragmentSignInBinding? = null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignInBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun init(){
        binding.btnSignWithPassword.setOnClickListener {
            transactToNextFragment()
        }
    }

    private fun transactToNextFragment(){
        val managerFragment = parentFragmentManager
        val transaction = managerFragment.beginTransaction()
        transaction.replace(R.id.main,RegisterFragment(),"SignInFragment")
        transaction.addToBackStack("SignInFragment")
        transaction.commit()
    }

}