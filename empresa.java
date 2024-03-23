import java.util.ArrayList;
import java.util.List;

// Classe principal sistema
public class Sistema {
    private List<Empresa> empresas;

    public Sistema() {
        this.empresas = new ArrayList<>();
    }

    // Adiciona uma empresa ao sistema
    public void adicionarEmpresa(Empresa empresa) {
        empresas.add(empresa);
    }

    // Realiza uma transação entre o cliente e a empresa
    public void realizarTransacao(Cliente cliente, Empresa empresa, double valor) {
        double taxa = empresa.calcularTaxa(valor);
        if (empresa.processarTransacao(valor, taxa)) {
            enviarCallback(empresa, "Transação realizada para empresa: " + empresa.getNome());
            notificarCliente(cliente, "Transação realizada com sucesso.");
        } else {
            System.out.println("Saldo insuficiente para realizar a transação.");
        }
    }

    // Método para simular envio de callback
    private void enviarCallback(Empresa empresa, String mensagem) {
        String callbackUrl = "https://webhook.site/seu-callback-url";
        System.out.println("Enviando callback para: " + callbackUrl);
        System.out.println("Mensagem: " + mensagem);
    }

    // Método para notificar o cliente
    private void notificarCliente(Cliente cliente, String mensagem) {
        System.out.println("Notificação para o cliente " + cliente.getNome() + ": " + mensagem);
    }

    public static void main(String[] args) {
        // Exemplo de uso do sistema
        Sistema sistema = new Sistema();

        Empresa empresa1 = new Empresa("Empresa A", "12345678901234", 0.05);
        Empresa empresa2 = new Empresa("Empresa B", "98765432109876", 0.03);

        sistema.adicionarEmpresa(empresa1);
        sistema.adicionarEmpresa(empresa2);

        Cliente cliente1 = new Cliente("Cliente X", "12345678901");

        sistema.realizarTransacao(cliente1, empresa1, 1000);
    }
}

// Classe que representa uma pessoa
abstract class Pessoa {
    private String nome;
    private String cpfCnpj;

    public Pessoa(String nome, String cpfCnpj) {
        this.nome = nome;
        this.cpfCnpj = cpfCnpj;
    }

    // Método para validar CPF ou CNPJ
    public abstract boolean validarCpfCnpj();

    public String getNome() {
        return nome;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }
}

// Classe que representa uma empresa
class Empresa extends Pessoa {
    private double saldo;
    private double taxa;

    public Empresa(String nome, String cnpj, double taxa) {
        super(nome, cnpj);
        this.taxa = taxa;
    }

    public double calcularTaxa(double valor) {
        return taxa * valor;
    }

    public boolean processarTransacao(double valor, double taxa) {
        if (saldo >= valor + taxa) {
            saldo -= valor + taxa;
            return true;
        } else {
            return false;
        }
    }
}

// Classe que representa um cliente
class Cliente extends Pessoa {
    public Cliente(String nome, String cpf) {
        super(nome, cpf);
    }

    public boolean validarCpfCnpj() {
        String cpfCnpj = getCpfCnpj();
        if (cpfCnpj.length() == 11) { // CPF
            // Lógica de validação de CPF
            return true;
        } else if (cpfCnpj.length() == 14) { // CNPJ
            // Lógica de validação de CNPJ
            return true;
        } else {
            return false;
        }
    }
}
