package com.example.financas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class DespesaActivity : AppCompatActivity() {
    private lateinit var btnAdicionarGasto: Button
    private lateinit var btnCalcularGastos: Button
    private lateinit var valorGastoEditText: EditText
    private var totalGastos: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_despesa)

        btnAdicionarGasto = findViewById(R.id.btnAdicionarGasto)
        btnCalcularGastos = findViewById(R.id.btnCalcularGastos)
        valorGastoEditText = findViewById(R.id.editValorGasto)

        btnAdicionarGasto.setOnClickListener {
            adicionarGasto()
        }

        btnCalcularGastos.setOnClickListener {
            calcularGastos()
        }
    }

    private fun adicionarGasto() {
        val valorGastoStr = valorGastoEditText.text.toString()

        if (valorGastoStr.isNotEmpty()) {
            val valorGasto = valorGastoStr.toDouble()
            totalGastos += valorGasto

            // Limpa o campo de entrada
            valorGastoEditText.text.clear()
        }
    }

    private fun calcularGastos() {
        // Passa o total de gastos para a FinancaActivity
        val intent = Intent(this, FinancaActivity::class.java)
        intent.putExtra("totalGastos", totalGastos)
        startActivity(intent)
    }
}