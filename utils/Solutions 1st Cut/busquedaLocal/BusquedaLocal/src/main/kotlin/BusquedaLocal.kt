import models.Mochila
import models.Objeto

typealias Espacio = List<Mochila>
typealias FuncionVecindad = (Mochila) -> Mochila?
typealias FuncionEvaluacion = (Mochila) -> Double
class BusquedaLocal {
    companion object {
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
            Objeto("borra", 3.5, 1.0),
        )
    }


    fun copiarMochila(mochila: Mochila): Mochila {
        val nuevosObjetosRemanentes = mutableSetOf<Objeto>()
        val nuevosObjetosSeleccionados = mutableSetOf<Objeto>()
        for (objeto in mochila.objetos_remanentes) {
            nuevosObjetosRemanentes.add(objeto)
        }
        for (objeto in mochila.objetos_seleccionados) {
            nuevosObjetosSeleccionados.add(objeto)
        }
        return mochila.copy(
            objetos_remanentes = nuevosObjetosRemanentes,
            objetos_seleccionados = nuevosObjetosSeleccionados,
        )
    }

    val funcionEvaluacion: FuncionEvaluacion = { m -> m.valor_total }

    // El espacio combinatorio E es son las distintas combinaciones de seleccion de objetos dentro de la mochila
// que respetan que la suma de sus pesos no puede superar el peso maximo.

// La funcion de evaluacion toma una de las selecciones (una mochila) y retorna el valor_total.

// La vecindad es 1-intercambio, tomar uno de los elementos seleccionados y cambiarlo
// El tema de este cambio es que puede abrir espacio para meter otro elemento entonces se debe chequear.

    fun funcionVecindad(m: Mochila): Mochila? {
        for (objeto in m.objetos_remanentes) {
            if (objeto.peso + m.peso_total <= PESO_LIMITE_MOCHILA) {
                m.objetos_seleccionados.add(objeto)
                m.objetos_remanentes.remove(objeto)
                m.peso_total += objeto.peso
                m.valor_total += objeto.valor
                return m
            }
        }
        for (objetoSel in m.objetos_seleccionados) {
            for (objetoRem in m.objetos_remanentes) {
                if (objetoSel.valor < objetoRem.valor && m.peso_total - objetoSel.peso + objetoRem.peso <= PESO_LIMITE_MOCHILA) {
                    m.objetos_seleccionados.add(objetoRem)
                    m.objetos_remanentes.add(objetoSel)
                    m.objetos_remanentes.remove(objetoRem)
                    m.objetos_seleccionados.remove(objetoSel)
                    m.peso_total -= objetoSel.peso
                    m.peso_total += objetoRem.peso
                    m.valor_total -= objetoSel.valor
                    m.valor_total += objetoRem.valor
                    return m
                }
            }
        }
        return null
    }

    fun generarSolucionInicial(e: Espacio): Mochila? {
        val auxMochila = e[0]
        if (auxMochila != null) {
            for (objeto in auxMochila.objetos_remanentes) {
                if (auxMochila.peso_total + objeto.peso <= PESO_LIMITE_MOCHILA) {
                    auxMochila.objetos_seleccionados.add(objeto)
                    auxMochila.objetos_remanentes.remove(objeto)
                    auxMochila.peso_total += objeto.peso
                    auxMochila.valor_total += objeto.valor
                } else {
                    break
                }
            }
            return auxMochila
        } else {
            return null
        }
    }

    fun busquedaLocal(E: Espacio, V: FuncionVecindad): Mochila? {
        var X = generarSolucionInicial(E)
        if (X != null) {
            var VX = V(X)
            while (VX != null) {
                X = VX
                VX = V(VX)
            }
            return X
        } else {
            return null
        }
    }
}
