package com.mustafa.crudrealtimeadmin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.mustafa.crudrealtimeadmin.databinding.ActivityUploadBinding

class UploadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUploadBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener {

            val ownerName = binding.uploadOwnerName.text.toString()
            val vehicleBrand = binding.uploadVehicleBrand.text.toString()
            val vehicleRTO = binding.uploadVehicleRTO.text.toString()
            val vehicleNumber = binding.uploadVehicleNumber.text.toString()

            if (vehicleNumber.isNotEmpty()) {
                databaseReference =
                    FirebaseDatabase.getInstance().getReference("Vehicle Information")
                val vehicleData = VehicleData(ownerName, vehicleBrand, vehicleRTO, vehicleNumber)
                databaseReference.child(vehicleNumber).setValue(vehicleData).addOnSuccessListener {
                    binding.uploadOwnerName.text.clear()
                    binding.uploadVehicleBrand.text.clear()
                    binding.uploadVehicleRTO.text.clear()
                    binding.uploadVehicleNumber.text.clear()

                    Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@UploadActivity, MainActivity::class.java))
                    finish()

                }.addOnFailureListener {
                    Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
                }


            }else{
                Toast.makeText(this, "vehicle number can not empty", Toast.LENGTH_SHORT).show()
            }

        }
    }
}