package com.mustafa.crudrealtimeclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.mustafa.crudrealtimeclient.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchButton.setOnClickListener {
            val searchVehicleNumber: String = binding.searchVehicleNumber.text.toString()
            if(searchVehicleNumber.isNotEmpty()){
                readData(searchVehicleNumber)
            }else{
                Toast.makeText(this,"please enter the vehicle number",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun readData(vehicleNumber: String){
        databaseReference = FirebaseDatabase.getInstance().getReference("Vehicle Information")
        databaseReference.child(vehicleNumber).get().addOnSuccessListener {
            if(it.exists()){
                val ownerName = it.child("ownerName").value
                val vehicleBrand = it.child("vehicleBrand").value
                val vehicleRTO = it.child("vehicleRTO").value
                Toast.makeText(this,"Result Found",Toast.LENGTH_SHORT).show()

                binding.searchVehicleNumber.text.clear()

                binding.readOwnerName.text = ownerName.toString()
                binding.readVehicleBrand.text = vehicleBrand.toString()
                binding.readVehicleRTO.text = vehicleRTO.toString()
            }else{
                Toast.makeText(this,"vehicle number does not exists",Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this,"Something went wrong",Toast.LENGTH_SHORT).show()
        }
    }
}