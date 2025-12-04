# 1. Crear proyecto SOAP (Servicio Web Vacio)
1.  Abrir Visual Studio → **Crear nuevo proyecto**
2. Buscar: **ASP.NET Web Application (.NET Framework)**
3. Nombre: `ServicioSOAP`
4.  Framework: **.NET Framework 4.8**1.  
5. En la siguiente ventana → Seleccionar **Empty** y marcar:  
6. "Web Forms" _(esto habilita ASMX)_
# 2. Crear el archivo ASMX (Servicio SOAP)

1. En el proyecto → clic derecho → **Add → Web Service (ASMX)**
2. Nombre: `ProductoService.asmx`




# ✅ **3. Agregar métodos SOAP que el examen pedirá (CRUD básico)**
Te van a pedir algo así:
- registrarDato()
- listarDatos()
- buscarPorId()
- actualizar()
- eliminar()
Vamos a armar un CRUD simple.

---
## ⭐ **Modelo (C#)**

Crea una carpeta **Models** → `Producto.cs`:

```c
namespace ServicioSOAP.Models
{
    public class Producto
    {
        public int Id { get; set; }
        public string Nombre { get; set; }
        public double Precio { get; set; }
    }
}
```

---

## ⭐ **Servicio SOAP con lista en memoria (suficiente para el examen)**

_(Si luego quieres persistencia en BD, te enseño)_

En `ProductoService.asmx.cs`:

```c
using System.Collections.Generic;
using System.Web.Services;
using ServicioSOAP.Models;

namespace ServicioSOAP
{
    [WebService(Namespace = "http://servicio.com/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    public class ProductoService : WebService
    {
        // BD falsa en memoria
        private static List<Producto> productos = new List<Producto>();
        private static int contador = 1;
        [WebMethod]
        public string CrearProducto(string nombre, double precio)
        {
            productos.Add(new Producto
            {
                Id = contador++,
                Nombre = nombre,
                Precio = precio
            });
            return "Producto creado correctamente.";
        }
        [WebMethod]
        public List<Producto> ListarProductos()
        {
            return productos;
        }
        [WebMethod]
        public Producto BuscarProducto(int id)
        {
            return productos.Find(p => p.Id == id);
        }
        [WebMethod]
        public string EliminarProducto(int id)
        {
            var prod = productos.Find(p => p.Id == id);
            if (prod == null) return "Producto no encontrado";
            productos.Remove(prod);
            return "Producto eliminado";
        }
    }
}

```

**🔥 Tus métodos SOAP ya están listos y funcionales.**

---

# ✅ **4. Consumir el servicio desde un cliente C# (lo que también piden)**

1. Crea otro proyecto:  
    **Console App (.NET Framework)**
2. Clic derecho en _References_ → **Add Service Reference…**
3. En la ventana → en URL pones:

`http://localhost:puerto/ProductoService.asmx?wsdl`
4. Nombre: `ProductoClient`
5. Presionas **OK**

Visual Studio te generará automáticamente todo el cliente SOAP.

---

## ⭐ **Código del cliente (Console App)**

```c
using System.Collections.Generic;
using System.Web.Services;
using ServicioSOAP.Models;

namespace ServicioSOAP
{
    [WebService(Namespace = "http://servicio.com/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    public class ProductoService : WebService
    {
        // BD falsa en memoria
        private static List<Producto> productos = new List<Producto>();
        private static int contador = 1;
        [WebMethod]
        public string CrearProducto(string nombre, double precio)
        {
            productos.Add(new Producto
            {
                Id = contador++,
                Nombre = nombre,
                Precio = precio
            });
            return "Producto creado correctamente.";
        }
        [WebMethod]
        public List<Producto> ListarProductos()
        {
            return productos;
        }
        [WebMethod]
        public Producto BuscarProducto(int id)
        {
            return productos.Find(p => p.Id == id);
        }
        [WebMethod]
        public string EliminarProducto(int id)
        {
            var prod = productos.Find(p => p.Id == id);
            if (prod == null) return "Producto no encontrado";
            productos.Remove(prod);
            return "Producto eliminado";
        }
    }
}

```

**🔥 Con esto cumples el 100% del examen.**

---
# CONCEPTOS QUE TE VAN A PREGUNTAR EN EL EXAMEN

### ¿Qué es SOAP?

Protocolo basado en XML para comunicar servicios web entre aplicaciones.

### ¿Qué es un archivo ASMX?

Es un archivo de Web Service de ASP.NET que implementa un servicio SOAP.

###  ¿Qué es un WSDL?

Es el documento XML que describe:
- métodos del servicio
- parámetros
- respuestas
- URL de acceso    

El cliente lo usa para generar código automáticamente.
###  ¿Qué es un WebMethod?
Es una función pública expuesta como parte del servicio SOAP.

Ejemplo:
`[WebMethod] public string CrearProducto()`
###  ¿Qué diferencia hay entre SOAP y REST?

|SOAP|REST|
|---|---|
|Usa XML|Usa JSON|
|Tiene WSDL|No requiere contrato|
|Más seguro y formal|Más fácil y moderno|
|Se usa en banca, gobierno|Se usa en APIs modernas|
