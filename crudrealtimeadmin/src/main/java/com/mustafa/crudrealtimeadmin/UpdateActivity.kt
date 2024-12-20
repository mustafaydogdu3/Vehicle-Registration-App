package com.mustafa.crudrealtimeadmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.mustafa.crudrealtimeadmin.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.updateButton.setOnClickListener {
            val ownerName = binding.updateOwnerName.text.toString()
            val vehicleBrand = binding.updateVehicleBrand.text.toString()
            val vehicleRTO = binding.updateVehicleRTO.text.toString()
            val vehicleNumber = binding.updateVehicleNumber.text.toString()

            if(ownerName.isNotEmpty() && vehicleNumber.isNotEmpty() && vehicleBrand.isNotEmpty() && vehicleRTO.isNotEmpty()){
                updateData(vehicleNumber, ownerName, vehicleRTO, vehicleBrand)
            }else{
                Toast.makeText(this,"line can not empty",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateData(vehicleNumber: String, ownerName: String, vehicleRTO:String, vehicleBrand:String){
        databaseReference = FirebaseDatabase.getInstance().getReference("Vehicle Information")
        val vehicleData = mapOf<String,String>("ownerName" to ownerName,"vehicleBrand" to vehicleBrand,"vehicleRTO" to vehicleRTO)
        databaseReference.child(vehicleNumber).updateChildren(vehicleData).addOnSuccessListener {
            binding.updateOwnerName.text.clear()
            binding.updateVehicleBrand.text.clear()
            binding.updateVehicleRTO.text.clear()
            binding.updateVehicleNumber.text.clear()

            Toast.makeText(this,"Update Successfull",Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this,"Update Unsuccessfull",Toast.LENGTH_SHORT).show()
        }

    }
}