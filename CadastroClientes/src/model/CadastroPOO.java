package model;

import java.util.Scanner;


//Classe Principal para Cadastro em Modo Texto
public class CadastroPOO {
 public static void main(String[] args) {
     Scanner scanner = new Scanner(System.in);
     PessoaFisicaRepo repoFisica = new PessoaFisicaRepo();
     PessoaJuridicaRepo repoJuridica = new PessoaJuridicaRepo();

     System.out.println("=============================");
     System.out.println("=== SISTEMA DE CADASTROS ===");
     System.out.println("=============================");

     int opcao;
     do {
         System.out.println("\nMENU PRINCIPAL");
         System.out.println("1 - Incluir");
         System.out.println("2 - Alterar");
         System.out.println("3 - Excluir");
         System.out.println("4 - Exibir pelo ID");
         System.out.println("5 - Exibir Todos");
         System.out.println("6 - Salvar");
         System.out.println("7 - Recuperar");
         System.out.println("0 - Sair");
         System.out.print("Escolha uma opção: ");
         opcao = scanner.nextInt();
         scanner.nextLine();

         switch (opcao) {
             case 1: // Incluir
                 System.out.println("1 - Física | 2 - Jurídica");
                 int tipoInc = scanner.nextInt();
                 scanner.nextLine();
                 System.out.print("ID: ");
                 int id = scanner.nextInt();
                 scanner.nextLine();
                 System.out.print("Nome: ");
                 String nome = scanner.nextLine();

                 if (tipoInc == 1) {
                     System.out.print("CPF: ");
                     String cpf = scanner.nextLine();
                     System.out.print("Idade: ");
                     int idade = scanner.nextInt();
                     scanner.nextLine();
                     repoFisica.inserir(new PessoaFisica(id, nome, cpf, idade));
                 } else {
                     System.out.print("CNPJ: ");
                     String cnpj = scanner.nextLine();
                     repoJuridica.inserir(new PessoaJuridica(id, nome, cnpj));
                 }
                 System.out.println("Cadastro incluído com sucesso!");
                 break;

             case 2: // Alterar
                 System.out.println("1 - Física | 2 - Jurídica");
                 int tipoAlt = scanner.nextInt();
                 scanner.nextLine();
                 System.out.print("ID a alterar: ");
                 int idAlt = scanner.nextInt();
                 scanner.nextLine();
                 System.out.print("Novo Nome: ");
                 String novoNome = scanner.nextLine();

                 if (tipoAlt == 1) {
                     System.out.print("Novo CPF: ");
                     String novoCpf = scanner.nextLine();
                     System.out.print("Nova Idade: ");
                     int novaIdade = scanner.nextInt();
                     scanner.nextLine();
                     repoFisica.alterar(idAlt, new PessoaFisica(idAlt, novoNome, novoCpf, novaIdade));
                 } else {
                     System.out.print("Novo CNPJ: ");
                     String novoCnpj = scanner.nextLine();
                     repoJuridica.alterar(idAlt, new PessoaJuridica(idAlt, novoNome, novoCnpj));
                 }
                 System.out.println("Cadastro alterado com sucesso!");
                 break;

             case 3: // Excluir
                 System.out.println("1 - Física | 2 - Jurídica");
                 int tipoExc = scanner.nextInt();
                 scanner.nextLine();
                 System.out.print("ID a excluir: ");
                 int idExc = scanner.nextInt();
                 scanner.nextLine();
                 if (tipoExc == 1) repoFisica.excluir(idExc);
                 else repoJuridica.excluir(idExc);
                 System.out.println("Cadastro excluído com sucesso!");
                 break;

             case 4: // Exibir pelo ID
                 System.out.println("1 - Física | 2 - Jurídica");
                 int tipoEx = scanner.nextInt();
                 scanner.nextLine();
                 System.out.print("ID a exibir: ");
                 int idEx = scanner.nextInt();
                 scanner.nextLine();
                 if (tipoEx == 1) {
                     PessoaFisica pf = repoFisica.obter(idEx);
                     if (pf != null) pf.exibir();
                     else System.out.println("Pessoa Física não encontrada.");
                 } else {
                     PessoaJuridica pj = repoJuridica.obter(idEx);
                     if (pj != null) pj.exibir();
                     else System.out.println("Pessoa Jurídica não encontrada.");
                 }
                 break;

             case 5: // Exibir Todos
                 System.out.println("Dados Pessoa Física Armazenados.");
                 System.out.println("Dados Pessoa Física Recuperados.");
                 for (PessoaFisica pf : repoFisica.obterTodos()) pf.exibir();
                 System.out.println("Dados Pessoa Juridica Armazenados.");
                 System.out.println("Dados Pessoa Juridica Recuperados.");
                 for (PessoaJuridica pj : repoJuridica.obterTodos()) pj.exibir();
                 break;

             case 6: // Salvar
                 try {
                     repoFisica.persistir("pessoas_fisicas.dat");
                     repoJuridica.persistir("pessoas_juridicas.dat");
                     System.out.println("Dados salvos com sucesso!");
                 } catch (Exception e) {
                     System.out.println("Erro ao salvar: " + e.getMessage());
                 }
                 break;

             case 7: // Recuperar
                 try {
                     repoFisica.recuperar("pessoas_fisicas.dat");
                     repoJuridica.recuperar("pessoas_juridicas.dat");
                     System.out.println("Dados recuperados com sucesso!");
                     System.out.println("--- DADOS RECUPERADOS ---");
                     for (PessoaFisica pf : repoFisica.obterTodos()) pf.exibir();
                     for (PessoaJuridica pj : repoJuridica.obterTodos()) pj.exibir();
                 } catch (Exception e) {
                     System.out.println("Erro ao recuperar: " + e.getMessage());
                 }
                 break;

             case 0:
                 System.out.println("Encerrando aplicação...");
                 break;

             default:
                 System.out.println("Opção inválida!");
         }
     } while (opcao != 0);

     scanner.close();
 }
}
