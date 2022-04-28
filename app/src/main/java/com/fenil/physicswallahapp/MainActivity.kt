package com.fenil.physicswallahapp

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.fenil.physicswallahapp.Repository.UserRepository
import com.fenil.physicswallahapp.ViewModel.UserViewModel
import com.fenil.physicswallahapp.ViewModel.UserViewModelProviderFactory

class MainActivity : AppCompatActivity() {
    lateinit var userViewModel: UserViewModel
    lateinit var ivBack: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = UserViewModelProviderFactory(
            application, UserRepository()
        )
        userViewModel = ViewModelProvider(this, factory).get(UserViewModel::class.java)

        setContentView(R.layout.activity_main)
        ivBack=findViewById(R.id.ivBack);
        getSupportActionBar()!!.hide();

        ivBack.setOnClickListener {
            onBackPressed()
        }
    }

}