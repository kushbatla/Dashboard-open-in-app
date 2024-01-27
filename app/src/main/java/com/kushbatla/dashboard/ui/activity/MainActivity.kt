package com.kushbatla.dashboard.ui.activity

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.kushbatla.dashboard.databinding.ActivityMainBinding
import com.kushbatla.dashboard.repository.api.RetrofitInstance
import com.kushbatla.dashboard.ui.adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import retrofit2.HttpException
import java.io.IOException
import java.util.Calendar
import java.util.Date


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var viewPagerAdapter  : ViewPagerAdapter

    val animationDuration = 1000L


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        hideactionbar()
        binding.bottomNavView.background = null
        binding.bottomNavView.menu.getItem(2).isEnabled = false


        val date = Date()
        val cal: Calendar = Calendar.getInstance()
        cal.setTime(date)
        val hour: Int = cal.get(Calendar.HOUR_OF_DAY)

        var greeting: String = "Greetings"
        if(hour>= 12 && hour < 17){
            greeting = "Good Afternoon";
        } else if(hour >= 17 && hour < 21){
            greeting = "Good Evening";
        } else if(hour >= 21 && hour < 24){
            greeting = "Good Night";
        } else {
            greeting = "Good Morning";
        }
        binding.greeting.text = greeting
        /*binding.linechart.animate(mySet)*/

        binding.whtsBtn.setOnClickListener {

        }

        viewPagerAdapter =  ViewPagerAdapter(this)
        binding.viewpager.adapter = viewPagerAdapter

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.viewpager.currentItem = tab!!.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        binding.viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tabLayout.getTabAt(position)!!.select()
            }
        })

        lifecycleScope.launchWhenCreated {
            val response = try {
                RetrofitInstance.api.getDashboard()
            }catch (e : IOException){
                Log.d("Error", "IO EXCEPTION"+e)
                return@launchWhenCreated
            }catch (e: HttpException){
                Log.d("Error", "Http Exception"+ e)
                return@launchWhenCreated
            }


            if(response.isSuccessful && response.body()!=null){
                Log.d("Success", response.body().toString())
                binding.itemHeading.text = response.body()!!.today_clicks.toString()
                binding.itemHeading1.text = response.body()!!.top_location.toString()
                binding.itemHeading2.text = response.body()!!.top_source.toString()
                binding.itemHeading3.text = response.body()!!.startTime.toString()
                val chatList = response.body()!!.data.overall_url_chart
                Log.d("chatList", chatList.toString())
                binding.linechart.animation.duration = animationDuration
                binding.linechart.animate(LinkedHashMap(chatList.mapValues { it.value.toFloat() }))
                binding.linechart.gradientFillColors =
                    intArrayOf(
                        Color.parseColor("#0E6FFF"),
                        Color.TRANSPARENT
                    )

                binding.whtsBtn.setOnClickListener{
                    sendMessage("Hello", response.body()!!.support_whatsapp_number.toString())
                }

            }else {
                Log.d("Error", "Response not successful")
            }
        }
    }

    private fun hideactionbar() {
        supportActionBar?.hide()
    }

    private fun sendMessage(message: String, number:String) {

        // Creating new intent
        val intent = Intent(
            Intent.ACTION_SEND
        )
        intent.type = "text/plain"
        intent.setPackage("com.whatsapp")
        intent.data = Uri.parse("http://api.whatsapp.com/send?phone="+"+91"+ number + "&text="+message);

        // Give your message here


        // Checking whether Whatsapp
        // is installed or not
        if (intent.resolveActivity(packageManager ) == null ) {
            Toast.makeText(this,"Please install whatsapp first.",Toast.LENGTH_SHORT).show()
            return
        }

        // Starting Whatsapp
        startActivity(intent)
    }
}