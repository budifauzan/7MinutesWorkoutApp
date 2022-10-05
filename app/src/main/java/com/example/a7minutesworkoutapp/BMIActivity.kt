package com.example.a7minutesworkoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.a7minutesworkoutapp.databinding.ActivityBmiBinding
import com.google.android.material.snackbar.Snackbar

class BMIActivity : AppCompatActivity() {

    companion object {
        private const val METRIC_UNITS_VIEW = "METRIC_UNITS_VIEW"
        private const val IMP_UNITS_VIEW = "IMP_UNITS_VIEW"
    }

    private var mActivity: ActivityBmiBinding? = null
    private var currentView: String = METRIC_UNITS_VIEW

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = ActivityBmiBinding.inflate(layoutInflater)
        setContentView(mActivity?.root)

        setToolbar()
        setMetricUnitsView()
        mActivity?.btnCalculate?.setOnClickListener {
            calculateBMI()
        }
        mActivity?.rgUnits?.setOnCheckedChangeListener { _, checkedID: Int ->
            if (checkedID == R.id.rb_metric) {
                setMetricUnitsView()
            } else {
                setImperialUnitsView()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mActivity = null
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

    private fun validateImpUnits(): Boolean {
        var isValid = true
        if (mActivity?.etWeightLbs?.text.toString()
                .isEmpty() || mActivity?.etHeightFeet?.text.toString()
                .isEmpty() || mActivity?.etHeightInch?.text.toString().isEmpty()
        ) {
            isValid = false
        }
        return isValid
    }

    private fun calculateBMI() {
        if (currentView == METRIC_UNITS_VIEW) {
            if (validateMetricUnits()) {
                val weight: Float = mActivity?.etWeight?.text.toString().toFloat()
                val height: Float = mActivity?.etHeight?.text.toString().toFloat() / 100
                val bmi = weight / (height * height)
                displayBMI(bmi)
            } else {
                Toast.makeText(this, "Please enter your weight and height", Toast.LENGTH_SHORT)
                    .show()
            }
        } else {
            if (validateImpUnits()) {
                val weight: Float = mActivity?.etWeightLbs?.text.toString().toFloat()
                val heightFeet: Float = mActivity?.etHeightFeet?.text.toString().toFloat()
                val heightInch: Float = mActivity?.etHeightInch?.text.toString().toFloat()
                val heightValue = heightFeet * 12 + heightInch
                val bmi = 703 * (weight / (heightValue * heightValue))
                displayBMI(bmi)
            } else {
                Toast.makeText(this, "Please enter your weight and height", Toast.LENGTH_SHORT)
                    .show()
            }
        }
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

    private fun setMetricUnitsView() {
        currentView = METRIC_UNITS_VIEW
        mActivity?.tilWeight?.visibility = View.VISIBLE
        mActivity?.etWeight?.text!!.clear()
        mActivity?.tilHeight?.visibility = View.VISIBLE
        mActivity?.etHeight?.text!!.clear()

        mActivity?.tilWeightLbs?.visibility = View.INVISIBLE
        mActivity?.tilHeightFeet?.visibility = View.INVISIBLE
        mActivity?.tilHeightInch?.visibility = View.INVISIBLE

        mActivity?.textView6?.visibility = View.INVISIBLE
        mActivity?.tvBmiValue?.visibility = View.INVISIBLE
        mActivity?.tvBmiType?.visibility = View.INVISIBLE
        mActivity?.tvBmiDescription?.visibility = View.INVISIBLE
    }

    private fun setImperialUnitsView() {
        currentView = IMP_UNITS_VIEW
        mActivity?.tilWeightLbs?.visibility = View.VISIBLE
        mActivity?.etWeightLbs?.text!!.clear()
        mActivity?.tilHeightFeet?.visibility = View.VISIBLE
        mActivity?.etHeightFeet?.text!!.clear()
        mActivity?.tilHeightInch?.visibility = View.VISIBLE
        mActivity?.etHeightInch?.text!!.clear()

        mActivity?.tilWeight?.visibility = View.INVISIBLE
        mActivity?.tilHeight?.visibility = View.INVISIBLE

        mActivity?.textView6?.visibility = View.INVISIBLE
        mActivity?.tvBmiValue?.visibility = View.INVISIBLE
        mActivity?.tvBmiType?.visibility = View.INVISIBLE
        mActivity?.tvBmiDescription?.visibility = View.INVISIBLE
    }
}