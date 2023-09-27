package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    // Deklarasi elemen-elemen UI
    private lateinit var snEdt: EditText
    private lateinit var usernameEdt: EditText
    private lateinit var locationEdt: EditText
    private lateinit var latitudeEdt: EditText
    private lateinit var longitudeEdt: EditText
    private lateinit var postDataBtn: Button
    private lateinit var responseTV: TextView
    private lateinit var loadingPB: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inisialisasi elemen-elemen UI
        snEdt = findViewById(R.id.idEdtsn)
        usernameEdt = findViewById(R.id.idEdtname)
        locationEdt = findViewById(R.id.idEdtlocation)
        latitudeEdt = findViewById(R.id.idEdtlatitude)
        longitudeEdt = findViewById(R.id.idEdtlongitude)
        postDataBtn = findViewById(R.id.idBtnPost)
        responseTV = findViewById(R.id.idTVResponse)
        loadingPB = findViewById(R.id.idLoadingPB)

        postDataBtn.setOnClickListener {
            // Validasi apakah semua kolom terisi
            if (snEdt.text.isEmpty() ||
                usernameEdt.text.isEmpty() ||
                locationEdt.text.isEmpty() ||
                latitudeEdt.text.isEmpty() ||
                longitudeEdt.text.isEmpty()
            ) {
                Toast.makeText(this, "Harap isi semua kolom", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Panggil metode postData dengan nilai-nilai dari kolom
            val sn = snEdt.text.toString()
            val username = usernameEdt.text.toString()
            val location = locationEdt.text.toString()
            val latitude = latitudeEdt.text.toString()
            val longitude = longitudeEdt.text.toString()
            postData(sn, username, location, latitude, longitude)
        }
    }

    private fun postData(sn: String, username: String, location: String, latitude: String, longitude: String) {
        loadingPB.visibility = View.VISIBLE

        val retrofit = Retrofit.Builder()
            .baseUrl("http://36.92.168.180:7480/drivetest/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitAPI = retrofit.create(RetrofitAPI::class.java)

        val call = retrofitAPI.sendData(sn, username, location, latitude, longitude)

        call.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val apiResponse = response.body()

                    // Konversi ApiResponse menjadi JSON string
                    val gson = Gson()
                    val apiResponseJson = gson.toJson(apiResponse)

                    val intent = Intent(this@MainActivity, MainActivity2::class.java)
                    intent.putExtra("apiResponseJson", apiResponseJson) // Mengirim JSON string
                    startActivity(intent)

                    Toast.makeText(this@MainActivity, "Data berhasil dikirim ke API", Toast.LENGTH_SHORT).show()
                } else {
                    // Handle an unsuccessful response here
                    Toast.makeText(this@MainActivity, "Gagal mengirim data ke API", Toast.LENGTH_SHORT).show()
                }
                loadingPB.visibility = View.GONE
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error: " + t.message, Toast.LENGTH_SHORT).show()
                responseTV.text = "Error: " + t.message // Menampilkan pesan kesalahan di responseTV
                loadingPB.visibility = View.GONE
            }
        })
    }
}
