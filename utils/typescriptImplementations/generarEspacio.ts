import fs from "fs";

const N = 500;

type Objeto = {
  id: string;
  peso: number;
  valor: number;
};

const generarEspacio = () => {
  let ejemplos = ``;
  for (let i = 0; i < N; i++) {
    const peso = 15 + Math.floor(Math.random() * 51);
    const valor = 1 + Math.floor(Math.random() * 499);
    ejemplos = ejemplos.concat(`Objeto("ejemplo${i}", ${peso}, ${valor})\n`);
  }
  fs.unlink("./ejemplos.txt", (err) => {
    if (err) console.error(err);
    console.log("Archivo Eliminado Satisfactoriamente");
  });
  fs.appendFile("./ejemplos.txt", ejemplos, (err) => {
    if (err) console.error(err);
    console.log("Archivo Creado Satisfactoriamente");
  });
};

generarEspacio();
