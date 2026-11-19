
# Juego de adivinar el número - TCP

## Descripción

Aplicación cliente-servidor que utiliza TCP para jugar a adivinar un número.

El servidor genera un número aleatorio entre 1 y 100 y el cliente intenta adivinarlo introduciendo números por teclado.

## Funcionamiento

El servidor recibe una línea de texto y responde según su contenido:

| Mensaje del cliente | Respuesta del servidor |
|---|---|
| Número menor que el secreto | MAYOR |
| Número mayor que el secreto | MENOR |
| Número correcto | ACIERTO en N intentos |
| ADIOS | OK Hasta luego |
| Otro comando | ERR Comando no válido |

Después de acertar, el servidor inicia una nueva partida con otro número aleatorio.

## Comunicación

Se utiliza TCP mediante las clases:

- ServerSocket: espera conexiones.
- Socket: establece la conexión entre cliente y servidor.
- BufferedReader: recibe mensajes.
- PrintWriter: envía respuestas.

Cada mensaje se envía como una línea de texto terminada en salto de línea.

## Gestión de desconexiones

Si el cliente se desconecta, el servidor detecta que readLine() devuelve null y cierra la conexión sin mostrar una traza de excepción.

## Partida de prueba utilizando netcat (nc)

Primero arrancamos el servidor:

```bash
java Servidor
```

Después abrimos una terminal y ejecutamos:

```bash
nc localhost 5000
```

Ejemplo de partida (suponiendo que el número secreto es 42):

```text
20
MAYOR
60
MENOR
50
MENOR
42
ACIERTO en 4 intentos
hola
ERR Comando no válido
ADIOS
OK Hasta luego
```

## Ejecución

1. Ejecutar Servidor.java.
2. Ejecutar Cliente.java o conectarse mediante nc.
3. Introducir números entre 1 y 100.
4. Escribir ADIOS para finalizar la conexión.

## Puerto utilizado

5000

## Protocolo

TCP
