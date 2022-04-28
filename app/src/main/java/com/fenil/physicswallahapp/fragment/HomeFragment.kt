package com.fenil.physicswallahapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fenil.physicswallahapp.MainActivity
import com.fenil.physicswallahapp.ViewModel.UserViewModel
import com.fenil.physicswallahapp.adapters.UserAdapter
import com.fenil.physicswallahapp.databinding.FragmentHomeBinding
import com.fenil.physicswallahapp.utils.Resource

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var userViewModel: UserViewModel
    lateinit var userAdapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false);
        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecycleView()
        userViewModel = (requireActivity() as MainActivity).userViewModel
        userViewModel.allUser.observe(viewLifecycleOwner) { response ->
            if (response is Resource.Success) {
                userAdapter.differ.submitList(response.data!!)
            } else if (response is Resource.Error) {
                Toast.makeText(requireContext(), "Error : ${response.message}", Toast.LENGTH_SHORT).show()
            } else if (response is Resource.Loading) {

            }
        }
    }

    private fun setUpRecycleView() {
        userAdapter = UserAdapter(
            requireContext()
        )
        binding.rvUser.setLayoutManager(
            LinearLayoutManager(
                context,
                RecyclerView.VERTICAL,
                false
            )
        )
        binding.rvUser.setHasFixedSize(true)
        binding.rvUser.adapter = userAdapter
    }

}