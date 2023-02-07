import model.Mochila
import model.Objeto

class MainSol(){
    var mochilasTerminadas = mutableListOf<Mochila>()

    val elementosDisponibles = listOf(
        Objeto("sacapuntas", 1.0, 2.5),
        Objeto("regla_plastico", 1.5, 0.5),
        Objeto("lapiz3", 0.5, 1.0),
        Objeto("boli", 2.0, 2.0),
        Objeto("carpeta", 1.0, 5.0),
        Objeto("color", 1.5, 1.5),
        Objeto("lapiz", 1.0, 1.0),
        Objeto("regla_metal", 3.0, 3.0),
        Objeto("lapiz2", 1.0, 1.0),
        Objeto("hojas", 0.2, 0.7),
        Objeto("boli2", 0.8, 2.0),
        Objeto("borra", 3.5, 1.0)
    )

    val mochila = Mochila(
        0.0,
        0.0,
        setOf(),
        elementosDisponibles.toSet()
    )

    fun main() {
        var mochilasQueue: List<Mochila>
        mochilasQueue = listOf(copiarMochila(mochila))
        var valorMinimo = 0
        var pesoMinimo = Int.MAX_VALUE
        while (mochilasQueue.isNotEmpty()) {
            val mochilaActual = mochilasQueue[0]
            mochilasQueue = mochilasQueue.drop(1)
            for (objeto in mochilaActual.objetos_remanentes) {
                val copiaMochila = copiarMochila(mochilaActual)
                if (copiaMochila.peso_total + objeto.peso <= PESO_LIMITE_MOCHILA && !copiaMochila.objetos_seleccionados.contains(
                        objeto
                    )
                ) {
                    copiaMochila.objetos_remanentes.remove(objeto)
                    copiaMochila.objetos_seleccionados.add(objeto)
                    copiaMochila.peso_total += objeto.peso
                    copiaMochila.valor_total += objeto.valor
                    mochilasQueue += copiaMochila
                } else {
                    if (copiaMochila.valor_total > valorMinimo || (copiaMochila.valor_total.toInt() == valorMinimo && copiaMochila.peso_total < pesoMinimo)) {
                        pesoMinimo = copiaMochila.peso_total.toInt()
                        valorMinimo = copiaMochila.valor_total.toInt()
                        mochilasTerminadas = mutableListOf(copiaMochila)
                    } else if (copiaMochila.valor_total.toInt() == valorMinimo && copiaMochila.peso_total.toInt() == pesoMinimo) {
                        mochilasTerminadas.add(copiaMochila)
                    }
                }
            }
        }

        val mochilasDeValorMaximoYPesoMinimo = mochilas_terminadas.reduce(
            /** Revisamos todas las mochilas */
            { mochilasGuardadas: List<Mochila>, mochilaEnRevision: Mochila ->
                if (mochilasGuardadas.isNotEmpty()) {
                    /** Si la mochila actual tiene un valor mas grande que la primera de las mochilas que llevamos guardadas
                     * entonces reemplazamos el arreglo para solo contener la nueva mochila con mayor valor
                     */
                    if (mochilasGuardadas[0].valor_total < mochilaEnRevision.valor_total) {
                        listOf(mochilaEnRevision)
                        /** Si la mochila actual tiene el mismo valor que la primera mochila de las mochilas que llevamos
                         * guardadas y el peso es el mismo entonces la agregamos a lista.
                         */
                    } else if (
                        mochilasGuardadas[0].valor_total == mochilaEnRevision.valor_total &&
                        mochilasGuardadas[0].peso_total == mochilaEnRevision.peso_total
                    ) {
                        mochilasGuardadas + mochilaEnRevision
                        /**
                         * Si la mochila actual tiene el mismo valor pero un peso menor que la primera mochila de las mochilas
                         * que llevamos guardadas entonces encontramos una mejor solucion asi que rehacemos la lista.
                         */
                    } else if (
                        mochilasGuardadas[0].valor_total == mochilaEnRevision.valor_total &&
                        mochilasGuardadas[0].peso_total > mochilaEnRevision.peso_total
                    ) {
                        listOf(mochilaEnRevision)
                    } else {
                        mochilasGuardadas
                    }
                } else {
                    /** Si no hay mochilas guardadas iniciamos la lista */
                    listOf(mochilaEnRevision)
                }
            },
            listOf()
        )

        /** Valor solucion para las mochilas */
        val valorMaximo = mochilasDeValorMaximoYPesoMinimo[0].valor_total
        /** Peso solucion para las mochilas */
        pesoMinimo = mochilasDeValorMaximoYPesoMinimo[0].peso_total
        /** Objetos a seleccionar para la solucion (repetidos)
         *  Formateados a string para ser impresos
         */
        val soluciones = mochilasDeValorMaximoYPesoMinimo
            .map { it -> it.objetos_seleccionados }
            .map { solucion ->
                solucion
                    .map { it.id }
                    .sorted
            }

    }
    private fun copiarMochila(mochila: Mochila): Mochila {
        val nuevosObjetosRemanentes = hashSetOf<Objeto>()
        val nuevosObjetosSeleccionados = hashSetOf<Objeto>()
        for (objeto in mochila.objetos_remanentes) {
            nuevosObjetosRemanentes.add(objeto)
        }
        for (objeto in mochila.objetos_seleccionados) {
            nuevosObjetosSeleccionados.add(objeto)
        }
        return mochila.copy(
            objetos_remanentes = nuevosObjetosRemanentes,
            objetos_seleccionados = nuevosObjetosSeleccionados
        )
    }
    init {
        main()
    }

}

