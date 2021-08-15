package id.co.profile

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import id.co.core.data.model.User
import id.co.core.data.response.ResponseState
import id.co.datastore.UserDataStore
import id.co.profile.databinding.FragmentProfileBinding
import id.co.profile.module.ProfileModule.profileModule
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.GlobalContext
import org.koin.core.context.loadKoinModules


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val viewModel: ProfileViewModel by viewModel()
    private val userDataStore: UserDataStore by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadKoinModules(profileModule)

        Glide.with(requireContext())
            .load(R.drawable.man)
            .into(binding.ivUser)


        binding.ivProfile.setOnClickListener {
            val intent = Intent(requireContext(), EditProfileActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogout.setOnClickListener {
            val alertDialog = AlertDialog.Builder(requireContext())
            alertDialog.setMessage("Apakah anda yakin ingin logout ?")
            alertDialog.setPositiveButton("Ya", object : DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    logoutProses()
                }
            })
            alertDialog.setNegativeButton("Tidak", object : DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    dialog!!.dismiss()
                }
            })
            alertDialog.show()
        }

    }

    private fun logoutProses() {
        GlobalScope.launch {
            userDataStore.storeStatusLogin(false)
            userDataStore.storeTokenUser("")
            userDataStore.storeUser("")

            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("jasbi://login"))
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }

    private fun setupObserve() {
        viewModel.getUser().observe(viewLifecycleOwner){response ->
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
        binding.tvUser.text = user.name
    }

    override fun onResume() {
        super.onResume()
        setupObserve()
    }
}