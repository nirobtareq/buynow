package com.mehedi.letsbuy.views.dashboard.seller

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.auth.FirebaseAuth
import com.mehedi.letsbuy.R
import com.mehedi.letsbuy.databinding.ActivitySellerDashboardBinding
import com.mehedi.letsbuy.views.starter.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SellerDashboard : AppCompatActivity() {

    lateinit var binding: ActivitySellerDashboardBinding
    lateinit var navcontroller: NavController

    @Inject
    lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySellerDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navcontroller = findNavController(R.id.fragmentContainerView)
        val appBarConfig = AppBarConfiguration(
            setOf(
                R.id.myProductFragment,
                R.id.uploadProductFragment,
                R.id.sellerProfileFragment

            )
        )

        binding.bottomNavigationView.setupWithNavController(navcontroller)
        setupActionBarWithNavController(navcontroller, appBarConfig)


    }

    override fun onSupportNavigateUp(): Boolean {
        return navcontroller.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.seller_top_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_logout -> {
                mAuth.signOut()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }

            R.id.menu_settings -> {
                Toast.makeText(this, "setting clicked", Toast.LENGTH_SHORT).show()

            }

            R.id.menu_report -> {
                Toast.makeText(this, " report clicked", Toast.LENGTH_SHORT).show()

            }


        }




        return super.onOptionsItemSelected(item)
    }


}