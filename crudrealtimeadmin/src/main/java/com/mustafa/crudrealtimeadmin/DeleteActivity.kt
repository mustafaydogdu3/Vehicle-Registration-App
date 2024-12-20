package com.mustafa.crudrealtimeadmin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.mustafa.crudrealtimeadmin.databinding.ActivityDeleteBinding

class DeleteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDeleteBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.deleteButton.setOnClickListener {
            val deleteVehicleNumber = binding.deleteVehicleNumber.text.toString()
            if(deleteVehicleNumber.isNotEmpty()){
                deleteData(deleteVehicleNumber)
            }else{
                Toast.makeText(this,"this line can not empty",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun deleteData(vehicleNumber: String){
        databaseReference = FirebaseDatabase.getInstance().getReference("Vehicle Information")
        databaseReference.child(vehicleNumber).removeValue().addOnSuccessListener {
            binding.deleteVehicleNumber.text.clear()
            Toast.makeText(this,"Deleted",Toast.LENGTH_SHORT).show()

        }.addOnFailureListener {
            Toast.makeText(this,"Unable To Delete",Toast.LENGTH_SHORT).show()
        }
    }

}