// Define el valor maximo que puede albergar la mochila
const PESO_LIMITE_MOCHILA: number = 10.5;

/** Tipo de los objetos que puedes ingresar a la mochila */
type Objeto = {
  id: string;
  peso: number;
  valor: number;
};

const elementosDisponibles: Objeto[] = [
  { id: "sacapuntas", peso: 1, valor: 2.5 },
  { id: "regla_plastico", peso: 1.5, valor: 0.5 },
  { id: "lapiz3", peso: 0.5, valor: 1 },
  { id: "boli", peso: 2, valor: 2 },
  { id: "carpeta", peso: 1, valor: 5 },
  { id: "color", peso: 1.5, valor: 1.5 },
  { id: "lapiz", peso: 1, valor: 1 },
  { id: "regla_metal", peso: 3, valor: 3 },
  { id: "lapiz2", peso: 1, valor: 1 },
  { id: "hojas", peso: 0.2, valor: 0.7 },
  { id: "boli2", peso: 0.8, valor: 2 },
  { id: "borra", peso: 3.5, valor: 1 },
];

const elementosDisponibles2: Objeto[] = [
  { id: "agua", peso: 2, valor: 1 },
  { id: "sacapuntas", peso: 1, valor: 2.5 },
  { id: "desayuno", peso: 5, valor: 5 },
  { id: "regla_plastico", peso: 1.5, valor: 0.5 },
  { id: "borra2", peso: 1.8, valor: 1 },
  { id: "lapiz3", peso: 0.5, valor: 1 },
  { id: "boli", peso: 2, valor: 2 },
  { id: "borra3", peso: 1, valor: 1 },
  { id: "almuerzo", peso: 8, valor: 6 },
  { id: "carpeta", peso: 1, valor: 5 },
  { id: "color", peso: 1.5, valor: 1.5 },
  { id: "lapiz", peso: 1, valor: 1 },
  { id: "regla_metal", peso: 3, valor: 3 },
  { id: "cartuchera2", peso: 1, valor: 2.5 },
  { id: "merienda", peso: 2.3, valor: 5 },
  { id: "hojas", peso: 0.2, valor: 0.7 },
  { id: "boli2", peso: 0.8, valor: 2 },
  { id: "borra", peso: 3.5, valor: 1 },
  { id: "lapiz2", peso: 1, valor: 1 },
  { id: "borra4", peso: 0.5, valor: 0.5 },
  { id: "cena", peso: 5, valor: 3 },
  { id: "cartuchera", peso: 5, valor: 5 },
  { id: "calculadora", peso: 1.3, valor: 10 },
  { id: "jugo", peso: 2, valor: 2 },
  { id: "dinero", peso: 0.1, valor: 4 },
];

type Mochila = {
  peso_total: number;
  valor_total: number;
  objetos_seleccionados: Set<Objeto>;
  objetos_remanentes: Set<Objeto>;
};

const copiarMochila = (mochila: Mochila) => {
  const nuevosObjetosRemanentes: Set<Objeto> = new Set();
  const nuevosObjetosSeleccionados: Set<Objeto> = new Set();
  for (const objeto of mochila.objetos_remanentes.values()) {
    nuevosObjetosRemanentes.add(objeto);
  }
  for (const objeto of mochila.objetos_seleccionados.values()) {
    nuevosObjetosSeleccionados.add(objeto);
  }
  return {
    ...mochila,
    objetos_remanentes: nuevosObjetosRemanentes,
    objetos_seleccionados: nuevosObjetosSeleccionados,
  } as Mochila;
};

type Espacio = Mochila[];

type FuncionVecindad = (m: Mochila) => Mochila | null;

type FuncionEvaluacion = (m: Mochila) => number;

const funcionEvaluacion: FuncionEvaluacion = (m) => m.valor_total;

// El espacio combinatorio E es son las distintas combinaciones de seleccion de objetos dentro de la mochila
// que respetan que la suma de sus pesos no puede superar el peso maximo.

// La funcion de evaluacion toma una de las selecciones (una mochila) y retorna el valor_total.

// La vecindad es 1-intercambio, tomar uno de los elementos seleccionados y cambiarlo
// El tema de este cambio es que puede abrir espacio para meter otro elemento entonces se debe chequear.

const funcionVecindad = (m: Mochila): Mochila | null => {
  for (const objeto of m.objetos_remanentes) {
    if (objeto.peso + m.peso_total <= PESO_LIMITE_MOCHILA) {
      m.objetos_seleccionados.add(objeto);
      m.objetos_remanentes.delete(objeto);
      m.peso_total += objeto.peso;
      m.valor_total += objeto.valor;
      return m;
    }
  }
  for (const objetoSel of m.objetos_seleccionados) {
    for (const objetoRem of m.objetos_remanentes) {
      if (
        objetoSel.valor < objetoRem.valor &&
        m.peso_total - objetoSel.peso + objetoRem.peso <= PESO_LIMITE_MOCHILA
      ) {
        m.objetos_seleccionados.add(objetoRem);
        m.objetos_remanentes.add(objetoSel);
        m.objetos_remanentes.delete(objetoRem);
        m.objetos_seleccionados.delete(objetoSel);
        m.peso_total -= objetoSel.peso;
        m.peso_total += objetoRem.peso;
        m.valor_total -= objetoSel.valor;
        m.valor_total += objetoRem.valor;
        return m;
      }
    }
  }
  return null;
};

const generarSolucionInicial = (e: Espacio): Mochila | null => {
  const auxMochila = e[0];
  if (auxMochila) {
    for (const objeto of auxMochila.objetos_remanentes) {
      if (auxMochila.peso_total + objeto.peso <= PESO_LIMITE_MOCHILA) {
        auxMochila.objetos_seleccionados.add(objeto);
        auxMochila.objetos_remanentes.delete(objeto);
        auxMochila.peso_total += objeto.peso;
        auxMochila.valor_total += objeto.valor;
      } else {
        break;
      }
    }
    return auxMochila;
  } else {
    return null;
  }
};

const mochila: Mochila = {
  peso_total: 0,
  valor_total: 0,
  objetos_seleccionados: new Set(),
  objetos_remanentes: new Set([...elementosDisponibles2]),
};

// generarSolucionInicial([copiarMochila(mochila)]);

const busquedaLocal = (E: Espacio, V: FuncionVecindad): Mochila | null => {
  /** generar una solucion inicial */
  let X = generarSolucionInicial(E);
  if (X) {
    /** mientras haya un elemento mejor que X en V(X) entonces */
    let VX = V(X);
    if (VX) {
      while (VX) {
        X = VX;
        VX = V(VX);
      }
    }
    return X;
  } else {
    return null;
  }
};

console.log(busquedaLocal([copiarMochila(mochila)], funcionVecindad));
