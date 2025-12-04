
# SOAP
Es un protocolo estricto que utiliza WSDL (Web Service Description Lenguage) define los metodos,parametros y respuestas y esta basado en XML.
SOAP usa XML para recibir y enviar datos.

SOAP es como un contrato muy estricto a seguir.
## Contrato (WSDL - web service description Lenguage)
Este archivo se genera automaticamente, en C# `[WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]`, 
```CSS
Asegura que el servicio cumpla con los estandares para generar los metodos que el cliente pide ejemplo: sumar(); restar();
```

## Sobre (Envelope)

SOAP funciona enviando mensjaes en `XML`, cuando el cliente `Form1.cs` ejecuta `Sumar(a,b)`, internamente se esta contruyendo el XML.



### Tipado Fuerte
SOAP con su estrcita forma, garantiza que si se envia una fecha en forma `date` , el cliente sea Java,python etc,  hara que lo reciba de manera obligatoria como una fecha y no como un texto.

## Arquitectura de un Servicio SOAP
- Cliente SOAP
- Servidor SOAP
- archivo WSDL
- Comunicación XML
- 