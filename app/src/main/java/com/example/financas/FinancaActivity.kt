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
            if(totalGastos == 0.0) {
                txtResultado.text = "Você não cadastrou nenhuma despesa!."
            } else {
                val salarioStr = salario.text.toString()

                if (salarioStr.isNotEmpty()) {
                    val salarioDouble = salarioStr.toDouble()

                    val intent = Intent(this, ResultadoActivity::class.java)
                    intent.putExtra("salario", salarioDouble)
                    intent.putExtra("totalGastos", totalGastos)
                    startActivity(intent)
                } else {
                    txtResultado.text = "Digite um valor de salário antes de calcular."
                }
            }
        }
    }
}