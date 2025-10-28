package cliente;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class Cliente {
    public static void main(String[] args) {
        String codigo = JOptionPane.showInputDialog("Código da disciplina:");
        try (Socket s = new Socket("localhost", 12345);
             BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
             PrintWriter out = new PrintWriter(s.getOutputStream(), true)) {

            out.println(codigo);
            String resposta = in.readLine();
            JOptionPane.showMessageDialog(null, resposta);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }
    }
}
