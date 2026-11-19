
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Servidor {

    public static void main(String[] args) {

        try {
            ServerSocket servidor = new ServerSocket(5000);

            System.out.println("Servidor iniciado...");

            while (true) {

                Socket cliente = servidor.accept();

                System.out.println("Cliente conectado.");

                try {
                    BufferedReader entrada = new BufferedReader(
                            new InputStreamReader(cliente.getInputStream())
                    );

                    PrintWriter salida = new PrintWriter(
                            cliente.getOutputStream(), true
                    );

                    Random random = new Random();

                    int numero = random.nextInt(100) + 1;
                    int intentos = 0;

                    String mensaje;

                    while ((mensaje = entrada.readLine()) != null) {

                        mensaje = mensaje.trim();

                        if (mensaje.equalsIgnoreCase("ADIOS")) {

                            salida.println("OK Hasta luego");
                            break;

                        }

                        try {

                            int numeroCliente = Integer.parseInt(mensaje);

                            if (numeroCliente < 1 || numeroCliente > 100) {

                                salida.println("ERR Comando no válido");

                            } else {

                                intentos++;

                                if (numeroCliente < numero) {

                                    salida.println("MAYOR");

                                } else if (numeroCliente > numero) {

                                    salida.println("MENOR");

                                } else {

                                    salida.println("ACIERTO en " + intentos + " intentos");

                                    numero = random.nextInt(100) + 1;
                                    intentos = 0;
                                }
                            }

                        } catch (NumberFormatException e) {

                            salida.println("ERR Comando no válido");

                        }
                    }

                } catch (Exception e) {


                } finally {

                    cliente.close();
                    System.out.println("Cliente desconectado.");
                }
            }

        } catch (Exception e) {

            System.out.println("Error en el servidor: " + e.getMessage());

        }
    }
}
