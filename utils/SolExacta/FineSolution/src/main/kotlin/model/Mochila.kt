package model

data class Mochila(
    var peso_total: Double,
    var valor_total: Double,
    val objetos_seleccionados: Set<Objeto>,
    val objetos_remanentes: Set<Objeto>
) {
    companion object {
        fun copy(mochila: Mochila) = Mochila(
            mochila.peso_total,
            mochila.valor_total,
            mochila.objetos_seleccionados.toSet(),
            mochila.objetos_remanentes.toSet()
        )
    }
}