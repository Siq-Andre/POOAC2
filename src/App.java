public class App {
    public static void main(String[] args) throws Exception {
        Cliente cliente = new Cliente("nome", "documento", null);

        // Fazendo o casting de Cliente para PessoaFisica (downcasting)
        if (cliente instanceof PessoaFisica) {
            PessoaFisica pessoaFisica = (PessoaFisica) cliente;
            // Agora você pode acessar membros específicos de PessoaFisica, como o CPF
            System.out.println("CPF: ");
        } else {
            System.out.println("O objeto Cliente não é uma instância de PessoaFisica.");
        }
    }
}
