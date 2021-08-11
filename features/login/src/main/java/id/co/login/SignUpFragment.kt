package id.co.login

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import id.co.core.data.response.ResponseState
import id.co.login.databinding.FragmentSignUpBinding
import id.co.login.module.LoginModule
import id.co.login.module.LoginModule.loginModule
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules


class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private val viewModel: LoginViewModel by viewModel()


    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+"
    private val upperCasePattern = "(.*[A-Z].*)"
    private val numberPasswordPattern = "(.*[0-9].*)"
    private var isAtLeast8 = false
    private var hasUppercase = false
    private var hasNumber = false
    private var isRegistrationClickable = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadKoinModules(loginModule)

        binding.tvRegistrasi.setOnClickListener {
            setFragment(SignInFragment())
        }

        binding.btnSignIn.setOnClickListener {
            checkInput()
        }

        binding.etPassword.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                registrationDataCheck()
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })

    }

    @SuppressLint("ResourceType")
    private fun registrationDataCheck() {
        val password = binding.etPassword.text.toString()
        val email = binding.etEmail.text.toString()
        if (password.length >= 6) {
            isAtLeast8 = true
            binding.pItem1IconParent.setCardBackgroundColor(Color.parseColor(getString(R.color.colorCheckOk)))
        } else {
            isAtLeast8 = false
            binding.pItem1IconParent.setCardBackgroundColor(Color.parseColor(getString(R.color.colorCheckNo)))
        }
        if (password.matches(upperCasePattern.toRegex())) {
            hasUppercase = true
            binding.pItem2IconParent.setCardBackgroundColor(Color.parseColor(getString(R.color.colorCheckOk)))
        } else {
            hasUppercase = false
            binding.pItem2IconParent.setCardBackgroundColor(Color.parseColor(getString(R.color.colorCheckNo)))
        }
        if (password.matches(numberPasswordPattern.toRegex())) {
            hasNumber = true
            binding.pItem3IconParent.setCardBackgroundColor(Color.parseColor(getString(R.color.colorCheckOk)))
        } else {
            hasNumber = false
            binding.pItem3IconParent.setCardBackgroundColor(Color.parseColor(getString(R.color.colorCheckNo)))
        }
    }

    private fun checkInput() {
        if (!TextUtils.isEmpty(binding.etEmail.text)) {
            if (binding.etEmail.text.toString().matches(emailPattern.toRegex())) {
                if (!TextUtils.isEmpty(binding.etName.getText())) {
                    if (!TextUtils.isEmpty(binding.etPassword.text)) {
                        if (!TextUtils.isEmpty(binding.etConfirmPassword.text)) {
                            if (binding.etPassword.length() >= 6) {
                                if (isAtLeast8 && hasUppercase && hasNumber) {
                                    if (binding.etPassword.text.toString()
                                            .equals(binding.etConfirmPassword.text.toString())
                                    ) {
                                        registerProses()
                                    } else {
                                        binding.etConfirmPassword.error = "Password tidak cocok"
                                    }
                                } else {
                                    binding.etPassword.error = "Password tidak sesuai"
                                }
                            } else {
                                binding.etPassword.error = "Password terlalu sedikit"
                            }
                        } else {
                            binding.etConfirmPassword.error =
                                "Konfirmasi password tidak boleh kosong"
                        }
                    } else {
                        binding.etPassword.error = "Password tidak boleh kosong"
                    }
                } else {
                    binding.etName.setError("Nama tidak boleh kosong")
                }
            } else {
                binding.etEmail.error = "Email tidak sesuai format"
            }
        } else {
            binding.etEmail.error = "Email tidak boleh kosong"
        }
    }

    private fun registerProses() {
        val email = binding.etEmail.text.toString()
        val name = binding.etName.text.toString()
        val password = binding.etPassword.text.toString()
        viewModel.registerUser(name, email, "", "", password)
            .observe(viewLifecycleOwner, Observer { response ->
                when(response){
                    is ResponseState.Loading ->{

                    }
                    is ResponseState.Success ->{
                        goToMainActivity()
                    }
                    is ResponseState.Error ->{
                        Toast.makeText(requireContext(), response.errorMessage, Toast.LENGTH_SHORT).show()
                    }
                }
            })
    }

    private fun setFragment(fragment: Fragment){
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction!!.replace((activity as LoginActivity?) !!.dataBinding.frameLogin.id, fragment)
        transaction.commit()
    }

    private fun goToMainActivity() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("quiz://main"))
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
}