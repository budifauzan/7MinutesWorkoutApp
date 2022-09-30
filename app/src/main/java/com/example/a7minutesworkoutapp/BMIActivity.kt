package com.example.a7minutesworkoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.a7minutesworkoutapp.databinding.ActivityBmiBinding
import com.google.android.material.snackbar.Snackbar
import java.math.BigDecimal

class BMIActivity : AppCompatActivity() {
    private var mActivity: ActivityBmiBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = ActivityBmiBinding.inflate(layoutInflater)
        setContentView(mActivity?.root)

        setToolbar()
        mActivity?.btnCalculate?.setOnClickListener {
            if (validateMetricUnits()) {
                val height: Float = mActivity?.etHeight?.text.toString().toFloat() / 100
                val weight: Float = mActivity?.etWeight?.text.toString().toFloat()
                val bmi = weight / (height * height)
                displayBMI(bmi)
            } else {
                Snackbar.make(
                    mActivity!!.root, "Please enter your height and weight!", Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun setToolbar() {
        setSupportActionBar(mActivity?.tbToolbar)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "BMI Calculator"
        }
        mActivity?.tbToolbar?.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun validateMetricUnits(): Boolean {
        var isValid = true
        if (mActivity?.etWeight?.text.toString().isEmpty() || mActivity?.etHeight?.text.toString()
                .isEmpty()
        ) {
            isValid = false
        }
        return isValid
    }

    private fun displayBMI(bmi: Float) {

        val bmiLabel: String
        val bmiDescription: String

        if (bmi <= 15f) {
            bmiLabel = "Fucking underweight"
            bmiDescription = "Eat more bitch!"
        } else if (bmi > 15f && bmi <= 16f) {
            bmiLabel = "Severely underweight"
            bmiDescription = "Eat more!"
        } else if (bmi > 16f && bmi <= 18.5f) {
            bmiLabel = "Underweight"
            bmiDescription = "Please eat more!"
        } else if (bmi > 18.5f && bmi <= 25f) {
            bmiLabel = "Normal"
            bmiDescription = "Noice!"
        } else if (bmi > 25f && bmi <= 30f) {
            bmiLabel = "Overweight"
            bmiDescription = "Please eat less!"
        } else if (bmi > 30f && bmi <= 35f) {
            bmiLabel = "Severely overweight"
            bmiDescription = "Eat less!"
        } else if (bmi > 35f && bmi <= 40f) {
            bmiLabel = "Fucking overweight"
            bmiDescription = "Eat less bitch!"
        } else {
            bmiLabel = "Overweight AF FR FR"
            bmiDescription = "Seek a doctor smh"
        }

        mActivity?.textView6?.visibility = View.VISIBLE
        mActivity?.tvBmiValue?.visibility = View.VISIBLE
        mActivity?.tvBmiValue?.text = String.format("%.2f", bmi)
        mActivity?.tvBmiType?.visibility = View.VISIBLE
        mActivity?.tvBmiType?.text = bmiLabel
        mActivity?.tvBmiDescription?.visibility = View.VISIBLE
        mActivity?.tvBmiDescription?.text = bmiDescription
    }
}