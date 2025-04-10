package model;

import java.io.*;
import java.util.*;


//Classe PessoaJuridicaRepo
class PessoaJuridicaRepo {
 private List<PessoaJuridica> pessoas = new ArrayList<>();

 public void inserir(PessoaJuridica p) {
     pessoas.add(p);
 }

 public void alterar(int id, PessoaJuridica p) {
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

 public PessoaJuridica obter(int id) {
     return pessoas.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
 }

 public List<PessoaJuridica> obterTodos() {
     return new ArrayList<>(pessoas);
 }

 public void persistir(String nomeArquivo) throws IOException {
     try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
         oos.writeObject(pessoas);
     }
 }

 public void recuperar(String nomeArquivo) throws IOException, ClassNotFoundException {
     try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
         pessoas = (List<PessoaJuridica>) ois.readObject();
     }
 }
}
