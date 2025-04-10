package model;

import java.io.*;
import java.util.*;

//Classe PessoaFisicaRepo
class PessoaFisicaRepo {
 private List<PessoaFisica> pessoas = new ArrayList<>();

 public void inserir(PessoaFisica p) {
     pessoas.add(p);
 }

 public void alterar(int id, PessoaFisica p) {
     for (int i = 0; i < pessoas.size(); i++) {
         if (pessoas.get(i).getId() == id) {
             pessoas.set(i, p);
             break;
         }
     }
 }

 public void excluir(int id) {
     pessoas.removeIf(p -> p.getId() == id);
 }

 public PessoaFisica obter(int id) {
     return pessoas.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
 }

 public List<PessoaFisica> obterTodos() {
     return new ArrayList<>(pessoas);
 }

 public void persistir(String nomeArquivo) throws IOException {
     try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
         oos.writeObject(pessoas);
     }
 }

 public void recuperar(String nomeArquivo) throws IOException, ClassNotFoundException {
     try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
         pessoas = (List<PessoaFisica>) ois.readObject();
     }
 }
}