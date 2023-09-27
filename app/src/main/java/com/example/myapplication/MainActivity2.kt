package com.example.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson

class MainActivity2 : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var gatewayAdapter: GatewayAdapter // Ganti dengan nama adapter yang sesuai
    private lateinit var deviceInfoTV: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        recyclerView = findViewById(R.id.recyclerView)
        deviceInfoTV = findViewById(R.id.idDeviceInfoTV)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        val apiResponseJson = intent.getStringExtra("apiResponseJson")
        val gson = Gson()
        val apiResponse = gson.fromJson(apiResponseJson, ApiResponse::class.java)

        val device = apiResponse.device
        val deviceInfo = "SN: ${device?.sn}\n" +
                "DevEui: ${device?.devEui}\n" +
                "Last Update: ${device?.lastUpdate}\n" +
                "rssi ${device?.rssi}\n" +
                "snr ${device?.snr}\n" +
                "latitude ${device?.dev_lat}\n" +
                "longitude ${device?.dev_lon}\n" +
                "location ${device?.dev_location}\n"
        // Tambahkan atribut lainnya yang ingin Anda tampilkan

        deviceInfoTV.text = deviceInfo

        // Mendapatkan daftar gateway dari respons API
        val gatewayList = apiResponse?.gateway?.list

        // Membuat adapter untuk RecyclerView
        gatewayList?.let {
            gatewayAdapter = GatewayAdapter(this, it)

            // Menyetel adapter ke RecyclerView
            recyclerView.adapter = gatewayAdapter
        }
    }
}
