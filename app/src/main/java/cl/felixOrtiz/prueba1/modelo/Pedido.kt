package cl.felixOrtiz.prueba1.modelo

import android.util.Log

class Pedido (val pasteles:Int = 0, val cazuelas:Int = 0) {

    var subtotal1 = 0
    var subtotal2 = 0
    var darPropina = false
    var propina = 0

    fun calcularSubtotal1():Int {
        subtotal1 = pasteles * 12_000
        return subtotal1
    }

    fun calcularSubtotal2():Int {
        subtotal2 = cazuelas * 10_000
        return subtotal2
    }

    fun setPropina(tip:Boolean):Int{
        darPropina = tip
        propina = 0
        if(darPropina){
            propina = ((subtotal1 + subtotal2) * 10)/100
        }
        return propina
    }

    fun calcularTotal():Int{
        return (subtotal1 + subtotal2 + propina)
    }

}

