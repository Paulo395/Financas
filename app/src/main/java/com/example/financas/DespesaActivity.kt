package com.example.financas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class DespesaActivity : AppCompatActivity() {
    private lateinit var btnAdicionarGasto: Button
    private lateinit var btnRetornar: Button
    private lateinit var valorGastoEditText: EditText
    private lateinit var txtTotalGastos: TextView
    private var totalGastos: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_despesa)

        btnAdicionarGasto = findViewById(R.id.btnAdicionarGasto)
        btnRetornar = findViewById(R.id.btnRetornar)
        valorGastoEditText = findViewById(R.id.editValorGasto)
        txtTotalGastos = findViewById(R.id.txtTotalGastos)

        btnAdicionarGasto.setOnClickListener {
            adicionarGasto()
        }

        btnRetornar.setOnClickListener {
            retornarValor()
        }
    }

    private fun adicionarGasto() {
        val valorGastoStr = valorGastoEditText.text.toString()

        if (valorGastoStr.isNotEmpty()) {
            val valorGasto = valorGastoStr.toDouble()
            totalGastos += valorGasto

            // Limpa o campo de entrada
            valorGastoEditText.text.clear()
            txtTotalGastos.text = totalGastos.toString()
        }
    }

    private fun retornarValor() {
        val intent = Intent(this, FinancaActivity::class.java)
        intent.putExtra("totalGastos", totalGastos)
        startActivity(intent)
    }
}