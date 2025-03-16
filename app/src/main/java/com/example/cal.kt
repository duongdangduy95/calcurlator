package com.example.calcurlator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var display: TextView
    private var currentInput = ""
    private var operator = ""
    private var firstNumber = 0
    private var isNewInput = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById(R.id.display)

        // Gán sự kiện cho các nút số
        val numberButtons = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9
        )
        for (id in numberButtons) {
            findViewById<Button>(id).setOnClickListener { onNumberClick(it) }
        }

        // Gán sự kiện cho các nút phép toán
        val operatorButtons = listOf(
            R.id.btnPlus, R.id.btnMinus, R.id.btnMultiply, R.id.btnDivide
        )
        for (id in operatorButtons) {
            findViewById<Button>(id).setOnClickListener { onOperatorClick(it) }
        }

        // Gán sự kiện cho nút "="
        findViewById<Button>(R.id.btnEqual).setOnClickListener { onEqualClick(it) }

        // Gán sự kiện cho nút "C"
        findViewById<Button>(R.id.btnC).setOnClickListener { onClearClick(it) }
    }

    fun onNumberClick(view: View) {
        if (isNewInput) {
            display.text = ""
            isNewInput = false
        }
        val button = view as Button
        currentInput += button.text.toString()
        display.text = currentInput
    }

    fun onOperatorClick(view: View) {
        if (currentInput.isNotEmpty()) {
            firstNumber = currentInput.toInt()
            val button = view as Button
            operator = button.text.toString()
            currentInput = ""
            isNewInput = true
        }
    }

    fun onEqualClick(view: View) {
        if (currentInput.isNotEmpty() && operator.isNotEmpty()) {
            val secondNumber = currentInput.toInt()
            var result = 0
            when (operator) {
                "+" -> result = firstNumber + secondNumber
                "-" -> result = firstNumber - secondNumber
                "x" -> result = firstNumber * secondNumber
                "/" -> result = if (secondNumber != 0) firstNumber / secondNumber else {
                    display.text = "Error"
                    return
                }
            }
            display.text = result.toString()
            currentInput = result.toString()
            operator = ""
            isNewInput = true
        }
    }

    fun onClearClick(view: View) {
        display.text = "0"
        currentInput = ""
        operator = ""
        firstNumber = 0
        isNewInput = true
    }
}
