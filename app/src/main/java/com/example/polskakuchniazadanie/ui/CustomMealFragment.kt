package com.example.polskakuchniazadanie.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.polskakuchniazadanie.R
import com.example.polskakuchniazadanie.databinding.FragmentCustomMealBinding
import com.example.polskakuchniazadanie.model.MealItem
import com.example.polskakuchniazadanie.viewmodel.OrderViewModel
import kotlin.getValue

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CustomMealFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CustomMealFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private var _binding : FragmentCustomMealBinding?= null

    private val binding get() = _binding!!


    private val sharedViewModel: OrderViewModel by activityViewModels()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCustomMealBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        binding.addToOrderButton.setOnClickListener {
            when (binding.soupRadioGroup.checkedRadioButtonId) {
                R.id.zupaPomidorowaRadioButton -> sharedViewModel.addMealToCurrentOrder(MealItem("Zupa pomidorowa", 12.0))
                R.id.rosolRadioButton -> sharedViewModel.addMealToCurrentOrder(MealItem("Rosół", 12.0))

            }

            when (binding.mainDishRadioGroup.checkedRadioButtonId) {
                R.id.schabowyRadioButton -> sharedViewModel.addMealToCurrentOrder(MealItem("Schabowy", 22.0))
                R.id.pierogiRadioButton -> sharedViewModel.addMealToCurrentOrder(MealItem("Pierogi ruskie", 18.0))
                R.id.plackiRadioButton -> sharedViewModel.addMealToCurrentOrder(MealItem("Placki ziemniaczane", 16.0))
            }

            if (binding.frytkiCheckBox.isChecked) sharedViewModel.addMealToCurrentOrder(MealItem("Frytki", 6.0))
            if (binding.ziemniakiCheckBox.isChecked) sharedViewModel.addMealToCurrentOrder(MealItem("Ziemniaki", 6.0))

            if (binding.marchewkaCheckBox.isChecked) sharedViewModel.addMealToCurrentOrder(MealItem("Marchewka", 4.0))
            if (binding.kapustaCheckBox.isChecked) sharedViewModel.addMealToCurrentOrder(MealItem("Kapusta", 4.0))

            when (binding.drinkRadioGroup.checkedRadioButtonId) {
                R.id.wodaRadioButton -> sharedViewModel.addMealToCurrentOrder(MealItem("Woda", 4.0))
                R.id.sokPomaranczowyRadioButton -> sharedViewModel.addMealToCurrentOrder(MealItem("Sok pomarańczowy", 5.0))
            }





            findNavController().navigate(
                CustomMealFragmentDirections.actionCustomMealFragmentToMenuChoiceFragment()
            )
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CustomMealFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CustomMealFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}