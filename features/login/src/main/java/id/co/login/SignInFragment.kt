package id.co.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import id.co.login.databinding.FragmentSignInBinding


class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding

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

        binding.btnSignIn.setOnClickListener {
            val intent = Intent(requireContext(), VerifyOtpActivity::class.java)
            startActivity(intent)
        }

        binding.tvRegistrasi.setOnClickListener{
            setFragment(SignUpFragment())
        }

        binding.tvForget.setOnClickListener {
            val intent = Intent(requireContext(), ForgetPasswordActivity::class.java)
            startActivity(intent)
        }

    }

    private fun setFragment(fragment: Fragment){
        val transaction =activity?.supportFragmentManager?.beginTransaction()
        transaction!!.replace((activity as LoginActivity?) !!.dataBinding.frameLogin.id, fragment)
        transaction.addToBackStack(null)
        transaction.commit()

    }
}