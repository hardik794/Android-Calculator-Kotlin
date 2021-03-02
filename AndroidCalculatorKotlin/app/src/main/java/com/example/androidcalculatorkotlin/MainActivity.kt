package com.example.androidcalculatorkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Number Click....

        num_zero.setOnClickListener { ExpressionEvaluate("0", true) }
        num_one.setOnClickListener { ExpressionEvaluate("1", true) }
        num_two.setOnClickListener { ExpressionEvaluate("2", true) }
        num_three.setOnClickListener { ExpressionEvaluate("3", true) }
        num_four.setOnClickListener { ExpressionEvaluate("4", true) }
        num_five.setOnClickListener { ExpressionEvaluate("5", true) }
        num_six.setOnClickListener { ExpressionEvaluate("6", true) }
        num_seven.setOnClickListener { ExpressionEvaluate("7", true) }
        num_eight.setOnClickListener { ExpressionEvaluate("8", true) }
        num_nine.setOnClickListener { ExpressionEvaluate("9", true) }
        num_dot.setOnClickListener { ExpressionEvaluate(".", true) }

        //operation Click....

        op_div.setOnClickListener { ExpressionEvaluate("/", false) }
        op_mul.setOnClickListener { ExpressionEvaluate("*", false) }
        op_add.setOnClickListener { ExpressionEvaluate("+", false) }
        op_sub.setOnClickListener { ExpressionEvaluate("-", false) }

        op_back.setOnClickListener {
            val string = Input.text.toString()
            if (string.isNotEmpty()) {
                Input.text = string.substring(0, string.length - 1)
            }
            Output.text = ""
        }

        op_clear.setOnClickListener {
            Input.text = ""
            Output.text = ""
        }

        op_equal.setOnClickListener {
            try {
                val expressionBuilder = ExpressionBuilder(Input.text.toString()).build()
                val result = expressionBuilder.evaluate()
                val longResult = result.toLong()
                if (result == longResult.toDouble())
                    Output.text = longResult.toString()
                else
                    Output.text = result.toString()
            } catch (e: Exception) {
                Log.d("Exception", "message:" + e.message)
            }
        }
    }

    fun ExpressionEvaluate(string: String, clear: Boolean) {
        if (Output.text.isNotEmpty()) {
            Output.text = ""
        }
        if (clear) {
            Output.text = ""
            Input.append(string)
        } else {
            Input.append(Output.text)
            Input.append(string)
            Output.text = ""
        }
    }
}