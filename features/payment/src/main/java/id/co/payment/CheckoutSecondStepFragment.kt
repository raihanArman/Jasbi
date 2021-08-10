package id.co.payment

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import id.co.payment.databinding.FragmentCheckoutSecondStepBinding


class CheckoutSecondStepFragment : Fragment() {

    private lateinit var binding: FragmentCheckoutSecondStepBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_checkout_second_step, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLanjut.setOnClickListener {
            val deepLink = Uri.parse("jasbi://checkoutthird")
            findNavController().navigate(deepLink)
        }

    }
}