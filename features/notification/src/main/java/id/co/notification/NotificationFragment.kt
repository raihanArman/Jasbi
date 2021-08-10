package id.co.notification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import id.co.core.data.model.Notif
import id.co.notification.databinding.FragmentNotificationBinding
import java.util.*

class NotificationFragment : Fragment() {

    private lateinit var binding: FragmentNotificationBinding
    private val adapter: NotificationAdapter by lazy {
        NotificationAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_notification, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()

    }

    private fun setupAdapter() {
        val listNotif = mutableListOf<Notif>()
        listNotif.add(Notif(
            "1",
            "Paket Snack",
            "Pesanan anda sedang di proses",
            Date(),
            "https://id-test-11.slatic.net/p/6f9a1385aee7dd58a8e104e906b01911.jpg_720x720q80.jpg_.webp",
        ))
        listNotif.add(Notif(
            "1",
            "Paket Snack",
            "Pesanan anda sedang di proses",
            Date(),
            "https://id-test-11.slatic.net/p/6f9a1385aee7dd58a8e104e906b01911.jpg_720x720q80.jpg_.webp",
        ))
        listNotif.add(Notif(
            "1",
            "Paket Snack",
            "Pesanan anda sedang di proses",
            Date(),
            "https://id-test-11.slatic.net/p/6f9a1385aee7dd58a8e104e906b01911.jpg_720x720q80.jpg_.webp",
        ))

        with(binding){
            rvNotif.layoutManager = LinearLayoutManager(requireContext())
            rvNotif.adapter = adapter

            adapter.setListNotif(listNotif)
        }

    }
}