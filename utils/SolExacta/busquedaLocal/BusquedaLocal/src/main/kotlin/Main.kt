import BusquedaLocal.Companion.elementosDisponibles
import models.Mochila

fun main(args: Array<String>) {
    val mochila = Mochila(
        0,
        0,
        mutableSetOf(),
        mutableSetOf(elementosDisponibles.toTypedArray())
    )

    println("${BusquedaLocal.busquedaLocal(mochila), BusquedaLocal.funcionVecindad(mochila)}")
}