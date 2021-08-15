package id.co.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import id.co.core.data.model.User
import id.co.core.data.response.ResponseState
import id.co.profile.databinding.ActivityEditProfileBinding
import id.co.profile.module.ProfileModule
import id.co.profile.module.ProfileModule.profileModule
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class EditProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditProfileBinding
    private val viewModel: ProfileViewModel by viewModel()
    var user : User ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_profile)

        loadKoinModules(profileModule)
        setupObserve()

        binding.btnSimpan.setOnClickListener {
            editProses()
        }

    }

    private fun editProses() {
        user!!.name = binding.etNama.text.toString()
        user!!.email = binding.etEmail.text.toString()
        user!!.phoneNumber = binding.etPhone.text.toString()
        user!!.alamat = binding.etAddress.text.toString()

        viewModel.editUser(user!!).observe(this){response ->
            when(response){
                is ResponseState.Loading ->{

                }
                is ResponseState.Success ->{
                    Toast.makeText(this, "Berhasil edit", Toast.LENGTH_SHORT).show()
                    finish()
                }
                is ResponseState.Error ->{

                }
            }
        }

    }

    private fun setupObserve() {
        viewModel.getUser().observe(this){response ->
            when(response){
                is ResponseState.Loading ->{

                }
                is ResponseState.Success ->{
                    setData(response.data[0])
                }
                is ResponseState.Error ->{

                }
            }
        }
    }

    private fun setData(user: User) {
        this.user = user
        binding.etNama.setText(user.name)
        binding.etEmail.setText(user.email)
        binding.etPhone.setText(user.phoneNumber)
        binding.etAddress.setText(user.alamat)
    }
}