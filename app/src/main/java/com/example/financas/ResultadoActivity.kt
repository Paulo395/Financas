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
        var mensagem = ""

        // Lógica para recomendação com base nos gastos
        if (totalGastos == 0.0) {
            mensagem = "Você não cadastrou nenhuma despesa!."
        } else {
            mensagem = when {
                porcentagemGastos <= 30 -> "Você está economizando bem!"
                porcentagemGastos <= 50 -> "Situação normal."
                porcentagemGastos <= 100 -> "Considere economizar."
                else -> "Seus gastos passaram do seu salario, considere economizar."
            }
        }

        txtResultado.text = mensagem
    }
}