package servidor;
import java.io.*;
import java.net.*;
import dao.*;
import model.*;

public class Servidor {
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(12345)) {
            System.out.println("Servidor ativo na porta 12345...");
            while (true) {
                Socket cliente = server.accept();
                new Thread(() -> atenderCliente(cliente)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void atenderCliente(Socket cliente) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
             PrintWriter out = new PrintWriter(cliente.getOutputStream(), true)) {

            int idDisciplina = Integer.parseInt(in.readLine());
            DisciplinaDAO dao = new DisciplinaDAO();
            for (Disciplina d : dao.listar()) {
                if (d.getId() == idDisciplina) {
                    out.println(d.getProfessor().getNome() + " - " + d.getProfessor().getEmail());
                    return;
                }
            }
            out.println("Disciplina não encontrada!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
