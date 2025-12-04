mutation {
  crearEstudiante(
    CI: "5551651"
    nombre: "probandooo"
    primer_apellido: "sasa"
    segundo_apellido: "sdfs"
    fecha_nacimiento: "2000-04-21"
  ) {
    CI
    nombre
    primer_apellido
    segundo_apellido
    fecha_nacimiento
  }
}

------
query{
  buscarEstudiante(CI:"54654"){
    nombre
    fecha_nacimiento
  }
}

mutation {
  actualizarEstudiante(
    CI: "7777"
    segundo_apellido: "Rojas"
  ) {
    CI
    primer_apellido
    segundo_apellido
  }
}