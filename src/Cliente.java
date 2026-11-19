
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            Socket socket = new Socket("localhost", 5000);

            BufferedReader entrada = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );

            PrintWriter salida = new PrintWriter(
                    socket.getOutputStream(), true
            );

            String mensaje;

            System.out.println("Adivina el número del 1 al 100.");
            System.out.println("Escribe ADIOS para salir.");

            while (true) {

                System.out.print("Introduce un número: ");

                mensaje = sc.nextLine();

                salida.println(mensaje);

                String respuesta = entrada.readLine();

                if (respuesta == null) {
                    break;
                }

                System.out.println("Servidor: " + respuesta);

                if (mensaje.equalsIgnoreCase("ADIOS")) {
                    break;
                }
            }

            socket.close();

        } catch (Exception e) {

            System.out.println("No se pudo conectar con el servidor.");

        }

        sc.close();
    }
}
