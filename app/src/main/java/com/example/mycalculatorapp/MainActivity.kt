package com.example.mycalculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mycalculatorapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.btnAdd.setOnClickListener {
            validate("+")
        }

        binding.btnSubtract.setOnClickListener {
            validate("-")
        }

        binding.btnMultiply.setOnClickListener {
            validate("*")
        }

        binding.btnDivide.setOnClickListener {
            validate("/")
        }
    }

    fun validate(sign: String){
        val num1 = binding.etnum1.text.toString()
        val num2 = binding.etnum2.text.toString()
        var inputError = false

        if (num1.isBlank()){
            inputError = true
            binding.etnum1.error = "First number is required"
        }
        if (num2.isBlank()){
            inputError = true
            binding.etnum2.error = "Second number is required"
        }
        if (!inputError){
            calculate(num1.toDouble(), num2.toDouble(), sign )
        }

    }

    fun calculate(num1:Double, num2:Double, sign:String){
        var result = 0.0
        when(sign){
            "+" -> result = (num1 + num2)
            "-" -> result = (num1 - num2)
            "*" -> result = (num1 * num2)
            "/" -> {
                if (num2 == 0.0) {
                    binding.tvResult.text = "Cannot divide by zero"
                    return
                }
                result = num1 / num2
            }

        }
        if (sign == "/") {
            binding.tvResult.text = result.toString()
        }
        else {
            binding.tvResult.text = if (num1 % 1 == 0.0 && num2 % 1 == 0.0) result.toInt().toString()
            else result.toString()
        }
    }
}