package com.example.financas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class FinancaActivity : AppCompatActivity() {
    private lateinit var btnDespesa: Button
    private lateinit var btnCalcular: Button
    private lateinit var salario: EditText
    private lateinit var txtResultado: TextView
    private var totalGastos: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_financa)

        btnDespesa = findViewById(R.id.btnDespesa)
        btnCalcular = findViewById(R.id.btnCalcular)
        salario = findViewById(R.id.editSalario)
        txtResultado = findViewById(R.id.txtResultado)

        // Verifica se há dados extras na intent
        val extras = intent.extras
        if (extras != null && extras.containsKey("totalGastos")) {
            totalGastos = extras.getDouble("totalGastos")
        }

        btnDespesa.setOnClickListener {
            val intent = Intent(this, DespesaActivity::class.java)
            startActivity(intent)
        }

        btnCalcular.setOnClickListener {
            calcularGastos()
        }
    }

    private fun calcularGastos() {
        val salarioStr = salario.text.toString()

        if (salarioStr.isNotEmpty()) {
            val salario = salarioStr.toDouble()
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
        } else {
            txtResultado.text = "Digite o salário antes de calcular."
        }
    }
}