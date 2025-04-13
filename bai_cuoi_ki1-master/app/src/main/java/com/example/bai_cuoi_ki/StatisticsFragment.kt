package com.example.bai_cuoi_ki

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.util.Stack

class StatisticsFragment : Fragment(R.layout.maytinh) {

    private lateinit var display: TextView
    private var currentInput = "" // Lưu trữ đầu vào hiện tại

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        display = view.findViewById(R.id.display)

        // Nút số và phép toán
        view.findViewById<Button>(R.id.btnDecimal).setOnClickListener { appendNumber("0") }
        view.findViewById<Button>(R.id.btn1).setOnClickListener { appendNumber("1") }
        view.findViewById<Button>(R.id.btn2).setOnClickListener { appendNumber("2") }
        view.findViewById<Button>(R.id.btn3).setOnClickListener { appendNumber("3") }
        view.findViewById<Button>(R.id.btn4).setOnClickListener { appendNumber("4") }
        view.findViewById<Button>(R.id.btn5).setOnClickListener { appendNumber("5") }
        view.findViewById<Button>(R.id.btn6).setOnClickListener { appendNumber("6") }
        view.findViewById<Button>(R.id.btn7).setOnClickListener { appendNumber("7") }
        view.findViewById<Button>(R.id.btn8).setOnClickListener { appendNumber("8") }
        view.findViewById<Button>(R.id.btn9).setOnClickListener { appendNumber("9") }

        // Các phép toán
        view.findViewById<Button>(R.id.btnAdd).setOnClickListener { appendOperator("+") }
        view.findViewById<Button>(R.id.btnSubtract).setOnClickListener { appendOperator("-") }
        view.findViewById<Button>(R.id.btnMultiply).setOnClickListener { appendOperator("*") }
        view.findViewById<Button>(R.id.btnDivide).setOnClickListener { appendOperator("/") }

        // Nút tính toán
        view.findViewById<Button>(R.id.btnEqual).setOnClickListener { calculateResult() }

        // Nút làm mới
        view.findViewById<Button>(R.id.btnClear).setOnClickListener { clearDisplay() }

        // Nút chuyển đổi dấu âm/dương
        view.findViewById<Button>(R.id.btnToggleSign).setOnClickListener { toggleSign() }

        // Nút thập phân
        view.findViewById<Button>(R.id.btnDecimal).setOnClickListener { appendDecimal() }
    }

    private fun appendNumber(number: String) {
        currentInput += number
        display.text = currentInput // Hiển thị số vừa nhập
    }

    private fun appendOperator(op: String) {
        if (currentInput.isNotEmpty() && currentInput.last() !in "+-*/") {
            currentInput += " $op "
            display.text = currentInput // Hiển thị biểu thức với phép toán
        }
    }

    private fun calculateResult() {
        try {
            val result = evaluateExpression(currentInput)
            display.text = "$currentInput = $result" // Hiển thị biểu thức và kết quả
            currentInput = result.toString() // Cập nhật biểu thức với kết quả
        } catch (e: Exception) {
            display.text = "Error" // Xử lý lỗi nếu biểu thức không hợp lệ
            currentInput = ""
        }
    }

    private fun clearDisplay() {
        currentInput = ""
        display.text = "0"
    }

    private fun toggleSign() {
        if (currentInput.isNotEmpty()) {
            val parts = currentInput.split(" ").toMutableList()
            val lastPart = parts.removeLast()
            val toggledPart = if (lastPart.startsWith("-")) {
                lastPart.substring(1)
            } else {
                "-$lastPart"
            }
            parts.add(toggledPart)
            currentInput = parts.joinToString(" ")
            display.text = currentInput
        }
    }

    private fun appendDecimal() {
        if (!currentInput.contains(".")) {
            currentInput += "."
            display.text = currentInput
        }
    }

    private fun evaluateExpression(expression: String): Double {
        val tokens = expression.split(" ").filter { it.isNotBlank() }
        val values = mutableListOf<Double>()
        val operators = mutableListOf<String>()

        // Duyệt qua các token trong biểu thức
        var i = 0
        while (i < tokens.size) {
            val token = tokens[i]
            when {
                token.toDoubleOrNull() != null -> {
                    values.add(token.toDouble()) // Thêm số vào danh sách giá trị
                }
                token in "+-*/" -> {
                    // Xử lý ưu tiên của nhân và chia
                    if (token == "*" || token == "/") {
                        val leftOperand = values.removeLast()
                        val rightOperand = tokens[++i].toDoubleOrNull()
                            ?: throw IllegalArgumentException("Invalid expression")
                        val result = if (token == "*") {
                            leftOperand * rightOperand
                        } else {
                            leftOperand / rightOperand
                        }
                        values.add(result)
                    } else {
                        operators.add(token) // Thêm phép toán cộng/trừ vào danh sách
                    }
                }
            }
            i++
        }

        // Xử lý các phép toán cộng/trừ
        var result = values[0]
        var valueIndex = 1
        for (op in operators) {
            val nextValue = values[valueIndex++]
            result = if (op == "+") {
                result + nextValue
            } else {
                result - nextValue
            }
        }
        return result
    }
    private fun hasPrecedence(op1: String, op2: String): Boolean {
        if (op2 in "()" || op2 in "*/") return false
        return !(op1 in "*/" && op2 in "+-")
    }

    private fun applyOperation(op: String, b: Double, a: Double): Double {
        return when (op) {
            "+" -> a + b
            "-" -> a - b
            "*" -> a * b
            "/" -> a / b
            else -> throw UnsupportedOperationException("Operator not supported")
        }
    }
}
