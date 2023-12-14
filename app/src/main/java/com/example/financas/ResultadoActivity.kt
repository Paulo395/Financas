package com.example.financas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultadoActivity : AppCompatActivity() {
    private var totalGastos: Double = 0.0
    private var salario: Double = 0.0
    private lateinit var txtResultado: TextView
    private lateinit var btnRetornar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)

        txtResultado = findViewById(R.id.txtResultado)
        btnRetornar = findViewById(R.id.btnRetornar)

        val extras = intent.extras
        if (extras != null && extras.containsKey("totalGastos")) {
            totalGastos = extras.getDouble("totalGastos")
            salario = extras.getDouble("salario")
            calcularGastos()
        }

        btnRetornar.setOnClickListener {
            val intent = Intent(this, FinancaActivity::class.java)
            startActivity(intent)
        }
    }

    private fun calcularGastos() {
        val porcentagemGastos = (totalGastos / salario) * 100

        val mensagem = when {
            porcentagemGastos <= 20 -> "Você está economizando muito bem! Continue assim."
            porcentagemGastos <= 40 -> "Você está em uma boa situação financeira."
            porcentagemGastos <= 60 -> "Situação financeira normal."
            porcentagemGastos <= 80 -> "Considere economizar um pouco mais para melhorar sua situação."
            porcentagemGastos <= 100 -> "Seus gastos estão elevados. É hora de repensar seus hábitos financeiros."
            else -> "Atenção! Seus gastos ultrapassaram sua receita. É essencial reavaliar seus gastos e economizar."
        }

        val mensagemFinal = "$mensagem\n\nVocê gastou ${"%.2f".format(porcentagemGastos)}% do seu salário."
        txtResultado.text = mensagemFinal
    }
}