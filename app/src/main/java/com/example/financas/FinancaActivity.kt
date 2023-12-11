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

    // Variável para armazenar o valor total de gastos
    private var totalGastos: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_financa)

        btnDespesa = findViewById(R.id.btnDespesa)
        btnCalcular = findViewById(R.id.btnCalcular)
        salario = findViewById(R.id.editSalario)
        txtResultado = findViewById(R.id.txtResultado)

        // Verifica se há dados extras na intent (valor total de gastos da DespesaActivity)
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

            // Lógica para calcular gastos
            val gastosComDespesas = salario + totalGastos // Adiciona os gastos totais ao salário

            // Lógica para recomendação com base nos gastos
            val recomendacao = when {
                gastosComDespesas < 0.3 * salario -> "Você está economizando bem!"
                gastosComDespesas < 0.5 * salario -> "Considere economizar um pouco mais."
                else -> "Seus gastos estão altos. Considere fazer um orçamento."
            }

            val mensagem = "Você gastou $gastosComDespesas. $recomendacao"
            txtResultado.text = mensagem
        } else {
            txtResultado.text = "Digite o salário antes de calcular."
        }
    }
}
