package com.johans.convertmonedasapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.math.RoundingMode

class MainActivity : AppCompatActivity() {

    companion object {
        private const val EMPTY = ""
        private const val SPACE = "  "
        private const val MON_O_S = "Selecione Moneda"
        private const val MON_S_E = "Select currency"
        private const val PESO = "Pesos"
        private const val DOLAR = "Dólares"
        private const val EURO = "Euros"
        private const val LIBRA = "Libras Esterlinas"
        private const val peso_dolar: Double = 3826.02
        private const val peso_euro: Double = 4460.11
        private const val peso_libra: Double = 4865.30
        private const val euro_dolar: Double = 1.17
        private const val euro_libra: Double = 0.92
        private const val libra_dolar: Double = 1.27


    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        convertir_btn.setOnClickListener{

            val mon = valor_cambio_edit_text.text.toString()
            val mon_original = moneda_original_spinner.selectedItem
            val mon_final = moneda_cambiol_spinner.selectedItem
            var moneda_entrada: Double = 0.0;
            var moneda_salida: Double=0.0;
            var salida = EMPTY

            when {

                mon.isEmpty() -> {
                    Toast.makeText(this, getString(R.string.valor_convert), Toast.LENGTH_LONG).show()
                    valor_cambio_edit_text.requestFocus()
                }
                mon_original == MON_O_S || mon_original == MON_S_E-> {
                    Toast.makeText(this,getString(R.string.moneda_entrada),Toast.LENGTH_LONG).show();
                    moneda_original_spinner.requestFocus();
                }
                mon_final== MON_O_S || mon_final == MON_S_E-> {
                    Toast.makeText(this,getString(R.string.moneda_salida),Toast.LENGTH_LONG).show();
                    moneda_cambiol_spinner.requestFocus();
                }
                mon_final== mon_original -> {
                    Toast.makeText(this,getString(R.string.moneda_salida_entrada),Toast.LENGTH_LONG).show();
                    moneda_cambiol_spinner.requestFocus();
                }

                else -> {
                        moneda_entrada = mon.toDouble()
                    when{
                        mon_original == PESO && mon_final == DOLAR -> {
                        moneda_salida = moneda_entrada / peso_dolar
                        }
                        mon_original == PESO && mon_final == EURO -> {
                            moneda_salida = moneda_entrada / peso_euro
                        }
                        mon_original == PESO && mon_final == LIBRA -> {
                            moneda_salida = moneda_entrada / peso_libra
                        }
                        mon_original == DOLAR && mon_final == PESO -> {
                            moneda_salida = moneda_entrada * peso_dolar
                        }
                        mon_original == DOLAR && mon_final == EURO -> {
                            moneda_salida = moneda_entrada / euro_dolar
                        }
                        mon_original == DOLAR && mon_final == LIBRA -> {
                            moneda_salida = moneda_entrada / libra_dolar
                        }
                        mon_original == EURO && mon_final == PESO -> {
                            moneda_salida = moneda_entrada * peso_euro
                        }
                        mon_original == EURO && mon_final == DOLAR -> {
                            moneda_salida = moneda_entrada * euro_dolar
                        }
                        mon_original == EURO && mon_final == LIBRA -> {
                            moneda_salida = moneda_entrada * euro_libra
                        }
                        mon_original == LIBRA && mon_final == PESO -> {
                            moneda_salida = moneda_entrada * peso_libra
                        }
                        mon_original == LIBRA && mon_final == DOLAR -> {
                            moneda_salida = moneda_entrada * libra_dolar
                        }
                        mon_original == LIBRA && mon_final == EURO -> {
                            moneda_salida = moneda_entrada / euro_libra
                        }
                    }
                    val rounded:Double = moneda_salida.toBigDecimal().setScale(3, RoundingMode.UP).toDouble()
                    salida += moneda_entrada.toString() + SPACE + mon_original + SPACE + getString(R.string.equival) + SPACE +rounded.toString() + SPACE + mon_final
                    respuesta_text_view.text = salida
                }
                }

        }
    }
}
