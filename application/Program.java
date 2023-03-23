package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Funcionario;



public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		List<Funcionario> list = new ArrayList<>();

		System.out.println("Quantos funcionarios serao cadastrados? ");
		int N = sc.nextInt();

		for (int i = 0; i < N; i++) {
			System.out.println();// quebra de linha no boofer
			System.out.println("Funcionario # " + (i + 1) + ":");
			System.out.print("Id: ");
			Integer id = sc.nextInt();
			
			while(hasId(list, id)) {
				System.out.println("Id ja usado! Tente novamente: ");
				id = sc.nextInt();
			}

			System.out.print("Nome: ");
			sc.nextLine();// quebra de linha pendente
			String nome = sc.nextLine();
			System.out.print("Salario: ");
			Double salario = sc.nextDouble();

			Funcionario emp = new Funcionario(id, nome, salario);

			list.add(emp);

		}

		System.out.print("Informe o Id do funcionario que tera aumento salarial : ");
		int idsalario = sc.nextInt();
		Integer pos = position(list, idsalario);
		if (pos == null) {
			System.out.println("Este id nao existe!");
		} else {
			System.out.print("Digite a porcentagem: ");
			double percent = sc.nextDouble();
			list.get(pos).aumentarSalario(percent);

		}
		System.out.println();
		System.out.println("Lista de Funcionarios: ");
		for (Funcionario emp : list) {
			System.out.println(emp);
		}

		sc.close();

	}

	public static Integer position(List<Funcionario> list, int id) { // Funcao retorna a posicao do funcionario na list
		for (int i = 0; i < list.size(); i++) { // varre a lista inteira
			if (list.get(i).getId() == id) {
				return i;
			}

		}
		return null;
	}

	public static boolean hasId(List<Funcionario> list, int id) {  // FUNCAO LAMBIDA
		Funcionario emp = list.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
		return emp != null;
	}
}
