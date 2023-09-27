package com.example.myapplication

public class ApiResponse {
    var message: String? = null
    var username: String? = null
    var device: Device? = null
    var gateway: GatewayWrapper? = null
}

class Device {
    var sn: String? = null
    var devEui: String? = null
    var lastUpdate: String? = null
    var rssi: Double = 0.0
    var snr: Double = 0.0
    var counter: Int = 0
    var dev_lat: String? = null
    var dev_lon: String? = null
    var dev_location: String? = null
}

class Gateway {
    var gw_id: String? = null
    var duplicate: Boolean = false
    var gw_name: String? = null
    var gw_lat: String? = null
    var gw_lon: String? = null
    var distance: Double = 0.0
}

class GatewayWrapper {
    var list: List<Gateway>? = null
}
