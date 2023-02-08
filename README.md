# Problema de la Mochila (Knapsack problem)
## Integrantes
 - Pedro Samuel Fagundez
 - Jesús Marcano
 ## Explicación del problema a resolver
El problema de la mochila o the Knapsack problem es un problema de combinatoria, que consiste en encontrar la manera de optimizar el proceso de selección de un conjunto finito de objetos, para dar cabida a la mayor cantidad de elementos posibles en un espacio limitado (la mochila), cuyo limitador suele ser el peso, el cual no puede ser excedido.

Este es un problema NP-Completo establecido por Richard Karp en 1972, forma parte de sus 21 problemas NP-Completos.

Un ejemplo sería: dada una mochila con una capacidad de 15 kg que puedo llenar con cajas de distinto peso y valor, ¿qué cajas elijo de modo de maximizar mis ganancias y no exceder los 15 kg de peso permitidos? Las cajas son: (12kg, $4), (2kg, $2), (1kg,$2), (4kg, $10).

<img src="https://user-images.githubusercontent.com/20999799/217559333-93291ece-f3ea-4a23-a92d-f8e147e4ad4a.jpg" width="1000" height="600" alt="Fuerza Bruta">
<img src="https://user-images.githubusercontent.com/20999799/217559750-74693688-e7b6-4237-983b-8fc3c9e3fb5c.png" width="1000" height="600" alt="Fuerza Bruta">
### Definición formal del problema

Supongamos que tenemos n distintos tipos de ítems, que van del 1 al n. De cada tipo de ítem se tienen  q i  ítems disponibles, donde  q i  es un entero positivo que cumple 1 ≤ q i < ∞  .

Cada tipo de ítem _i_ tiene un **beneficio** asociado dado por _vi_ y un **peso** (o volumen) _wi_. Usualmente se asume que el beneficio y el peso no son negativos. Para simplificar la representación, se suele asumir que los ítems están listados en orden creciente según el peso (o volumen).

Por otro lado se tiene una mochila, donde se pueden introducir los ítems, que soporta un **peso máximo** (o volumen máximo) _W_.

El problema consiste en meter en la mochila ítems de tal forma que se **maximice el valor de los ítems que contiene y siempre que no se supere el peso (o volumen) máximo que puede soportar la misma**. La **solución** al problema vendrá dado por la secuencia de variables _x1_, _x2_, ..., _xn_ donde el valor de _xi_ indica cuantas copias se meterán en la mochila del tipo de ítem i.

El problema se puede expresar matemáticamente por medio del siguiente programa lineal

![Screenshot from 2023-01-30 18-45-10](https://user-images.githubusercontent.com/36381930/215613324-874ae4e5-8299-4a04-a514-2afbea832c93.png)

Si q i = 1  para  i = 1 , 2 , . . . , n  se dice que se trata del **problema de la mochila 0-1**. Si uno o más  q i  es infinito entonces se dice que se trata del **problema de la mochila no acotado** también llamado a veces **problema de la mochila entera**. En otro caso se dice que se trata del **problema de la mochila acotado**

## Benchmark
https://www.sciencedirect.com/science/article/pii/S2772941922000072
