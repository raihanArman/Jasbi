package id.co.payment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import id.co.payment.databinding.FragmentCheckoutThirdStepBinding


class CheckoutThirdStepFragment : Fragment() {

    private lateinit var binding: FragmentCheckoutThirdStepBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_checkout_third_step, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(binding.rbBank.isChecked){
            binding.rbDana.isChecked = false
        }

        if(binding.rbDana.isChecked){
            binding.rbBank.isChecked = false
        }

        binding.btnLanjut.setOnClickListener {
            val intent = Intent(requireContext(), PaymentActivity::class.java)
            startActivity(intent)
        }

    }
}