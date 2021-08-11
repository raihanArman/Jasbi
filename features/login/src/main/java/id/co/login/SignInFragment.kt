package id.co.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import id.co.core.data.response.ResponseState
import id.co.login.databinding.FragmentSignInBinding
import id.co.login.module.LoginModule
import id.co.login.module.LoginModule.loginModule
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules


class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding
    private val viewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadKoinModules(loginModule)

        binding.btnSignIn.setOnClickListener {
            loginProses()
        }

        binding.tvRegistrasi.setOnClickListener{
            setFragment(SignUpFragment())
        }

    }

    private fun loginProses() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        viewModel.loginUser(email, password)
            .observe(viewLifecycleOwner, Observer {response ->
                when(response){
                    is ResponseState.Success ->{
                        goToMainActivity()
                    }
                    is ResponseState.Error ->{
                        Toast.makeText(requireContext(), response.errorMessage, Toast.LENGTH_SHORT).show()
                    }
                    is ResponseState.Loading ->{

                    }
                }
            })
    }

    private fun goToMainActivity() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("jasbi://main"))
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    private fun setFragment(fragment: Fragment){
        val transaction =activity?.supportFragmentManager?.beginTransaction()
        transaction!!.replace((activity as LoginActivity?) !!.dataBinding.frameLogin.id, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}