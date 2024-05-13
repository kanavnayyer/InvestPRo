package com.awesome.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.awesome.Adapters.cAdapter
import com.awesome.investpro.R
import com.awesome.investpro.databinding.FragmentCryptoPRiceBinding

import com.awesome.rev.viewModel
import okhttp3.*
import okhttp3.Request.Builder
import org.chromium.base.ThreadUtils.runOnUiThread
import org.json.JSONObject
import java.io.IOException
import java.util.Locale

class CryptoPRice : Fragment() {
    private val dataa = ArrayList<viewModel>()
private  val adapter = cAdapter(dataa)
    private lateinit var binding: FragmentCryptoPRiceBinding
    private lateinit var navController: NavController

// add own key
    private val apik="     "
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding= FragmentCryptoPRiceBinding.inflate(inflater)
        // Inflate the layout for this fragment
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchprice(view)
        binding.idEdtCurrency.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    filter(newText)
                }
                return true
            }
        })


    }




    private fun filter(text: String) {
        // creating a new array list to filter our data.
        val filteredlist = ArrayList<viewModel>()

        // running a for loop to compare elements.
        for (item in dataa) {
            // checking if the entered string matched with any item of our recycler view.
            //if (item..toLowerCase().contains(text.lowercase(Locale.getDefault()))) {
            if(item.symbol.toLowerCase().contains(text.toLowerCase(Locale.getDefault()))||
                item.name.toLowerCase().contains(text.toLowerCase(
                    Locale.getDefault()))){
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item)
            }


                adapter.setFilteredList(filteredlist)

        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Toast.makeText(activity, "No Data Found..", Toast.LENGTH_SHORT).show()
        } else {
            // at last we are passing that filtered
            // list to our adapter class.


            adapter.filterList(filteredlist)
        }
    }
    private fun fetchprice(view: View) {

        val client = OkHttpClient()

        val request = Builder()
            .url("https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest")
            .addHeader("X-CMC_PRO_API_KEY", apik)
            .addHeader("Accept", "application/json")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Toast.makeText(
                    activity,
                    "Please check Internet Connection",
                    Toast.LENGTH_SHORT
                ).show()
            }


            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val jsonData = response.body?.string()
                    val jsonObject = JSONObject(jsonData)

                    val data = jsonObject.getJSONArray("data")





                    for (i in 0 until data.length()) {
                        val currency = data.getJSONObject(i)
                        val symbol = currency.getString("symbol")
                        val name = currency.getString("name")
                        val Price = currency.getJSONObject("quote").getJSONObject("USD")
                            .getString("price")

                        dataa.add(viewModel(symbol, name, Price))


                    }

                    runOnUiThread {
                        val recyclerView = binding.recy

                        recyclerView.apply {
                            // st a LinearLayoutManager to handle Android
                            // RecyclerView behavior
                            layoutManager = LinearLayoutManager(activity)
                            // set the custom adapter to the RecyclerView

                        }
                        //val ItemAdapter = cAdapter(dataa)

                        // Assign ItemAdapter instance to our RecylerView

                        // Applying OnClickListener to our Adapter




                        recyclerView.adapter = adapter

                        adapter.onItemClickListener(object : cAdapter.onItemClickListener {
                            override fun onItemClick(position: Int) {
                      navController.navigate(R.id.action_cryptoPRice_to_buySellFragment)
                            }
                        })

                    }

                    //else{Toast.makeText(this@CryptoMain,"Please checking spelling",Toast.LENGTH_SHORT).show()}
                }
            }


        })
    }

}