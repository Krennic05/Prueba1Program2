package cl.felixOrtiz.prueba1

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import cl.felixOrtiz.prueba1.modelo.Pedido

class MainActivity : AppCompatActivity() {

    var etPasteles: EditText? = null
    var tvSubtotal1: TextView? = null

    var etCazuelas: EditText? = null
    var tvSubtotal2: TextView? = null

    var tvTotal: TextView? = null
    var tvPropina: TextView? = null
    var tvTotalP: TextView? = null

    var switch1: Switch? = null
    var agregarPropina = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        etPasteles = findViewById<EditText>(R.id.etPasteles)
        etCazuelas = findViewById<EditText>(R.id.etCazuelas)
        tvSubtotal1 = findViewById<TextView>(R.id.tvSubtotal1)
        tvSubtotal2 = findViewById<TextView>(R.id.tvSubtotal2)
        tvTotal = findViewById<TextView>(R.id.tvTotal)
        tvPropina = findViewById<TextView>(R.id.tvPropina)
        tvTotalP = findViewById<TextView>(R.id.tvTotalP)
        switch1 = findViewById<Switch>(R.id.switch1)

        switch1?.setOnCheckedChangeListener { buttonView, isChecked ->
            if(agregarPropina != isChecked){
                agregarPropina = isChecked
                hacerPedido()
            }
        }
        val textWatcher: TextWatcher = object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                hacerPedido()

            }

        }

        etPasteles?.addTextChangedListener(textWatcher)
        etCazuelas?.addTextChangedListener(textWatcher)

    }

    private fun hacerPedido(){
        var pasteles =  0
        var cazuelas =  0
        if(etPasteles?.text.toString() != ""){
            pasteles = etPasteles?.text.toString().toInt()
        }
    //Estas sentencias if impiden que el valor del edit text inicie como " " (es decir como nada)
        if(etCazuelas?.text.toString() != ""){
            cazuelas = etCazuelas?.text.toString().toInt()
        }
        val pedido = Pedido(pasteles, cazuelas)
        val subTotal1 = pedido.calcularSubtotal1()
        val subTotal2 = pedido.calcularSubtotal2()
        val total = subTotal1 + subTotal2
        val propina = pedido.setPropina(agregarPropina)
        val totalP = pedido.calcularTotal()
        tvSubtotal1?.text = ("$"+subTotal1.toString())
        tvSubtotal2?.text = ("$"+subTotal2.toString())
        tvTotal?.text = ("$"+total.toString())
        tvPropina?.text = ("$"+propina.toString())
        tvTotalP?.text = ("$"+totalP.toString())
    }

}