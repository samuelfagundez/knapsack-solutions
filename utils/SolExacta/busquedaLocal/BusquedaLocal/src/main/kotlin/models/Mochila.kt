package models

data class Mochila(
    val peso_total: Double,
    val valor_total: Double,
    val objetos_seleccionados: Set<Objeto>,
    val objetos_remanentes: MutableSet<Array<Objeto>>
)
