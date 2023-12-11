package com.example.financas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class CalculoActivity : AppCompatActivity() {
    private lateinit var resultadoTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculo)

        resultadoTextView = findViewById(R.id.txtResultado)

        val salario = intent.getDoubleExtra("salario", 0.0)

        // Implemente a lógica para calcular o tempo necessário para pagar as despesas
        val tempoNecessario = calcularTempoNecessario(salario)

        // Implemente a lógica para fornecer um conselho financeiro
        val conselhoFinanceiro = fornecerConselhoFinanceiro(tempoNecessario)

        // Exibe o resultado na tela
        val resultado = "Tempo necessário para pagar: $tempoNecessario meses\n$conselhoFinanceiro"
        resultadoTextView.text = resultado
    }

    private fun calcularTempoNecessario(salario: Double): Int {
        // Implemente a lógica para calcular o tempo necessário
        return 12 // Exemplo: Paga em 12 meses
    }

    private fun fornecerConselhoFinanceiro(tempoNecessario: Int): String {
        // Implemente a lógica para fornecer conselho financeiro
        return "Conselho Financeiro: Planeje suas despesas com sabedoria."
    }
}