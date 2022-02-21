package br.com.marcielbezerra.controller;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.marcielbezerra.model.ModelTarefa;

@Named("beans")
@SessionScoped
public class ModelTarefaMB implements Serializable{
	private static final long serialVersionUID = 1L;
	

	@Inject
	private ModelTarefa modeltarefa;
	private List<ModelTarefa> novatarefas = new ArrayList<ModelTarefa>();

	private List<ModelTarefa> novatarefa = new ArrayList<ModelTarefa>();
	
	
	

	public List<ModelTarefa> getNovatarefa() {
		return novatarefa;
	}

	public ModelTarefa getModeltarefa() {
		return modeltarefa;
	}

	public void setModeltarefa(ModelTarefa modeltarefa) {
		this.modeltarefa = modeltarefa;
	}

	public List<ModelTarefa> getNovatarefas() {
		return novatarefas;
	}

	public void setNovatarefas(List<ModelTarefa> novatarefas) {
		this.novatarefas = novatarefas;
	}

	// @PostConstruct
	public void buscando() {
		String meusql = "Select * from tabelatf";
		conectaMB conec = new conectaMB();
		ResultSet rs = conec.executaBusca(meusql);

		try {

			ModelTarefa tar = new ModelTarefa();

			while (rs.next()) {
				tar = new ModelTarefa();
				String idvindo = rs.getString("id");
				String titulovindo = rs.getString("titulo");
				String responsavelvindo = rs.getString("responsavel");

				System.out.println("puxou " + idvindo);
				System.out.println(titulovindo);
				System.out.println(responsavelvindo);

				tar.setId(Long.parseLong(idvindo));
				tar.setTitulo(titulovindo);
				tar.setResponsavel(responsavelvindo);

				novatarefa.add(tar);
				System.out.println("puxou " + idvindo);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		limpar();
	}

	public void adicionar() {

		// novatarefas.add(modeltarefa);

		String meusql = "INSERT into tabelatf (id, titulo, descricao, responsavel, prioridade, data, status) values (default,'"
				+ modeltarefa.getTitulo() + "','" + modeltarefa.getDescricao() + "','" + modeltarefa.getResponsavel()
				+ "','" + modeltarefa.getPrioridade() + "','" + modeltarefa.getData() + "','Em Aberto')";
		// System.out.println("Saiu assim os dados " + meusql);

		conectaMB conec = new conectaMB();
		int resultado = conec.executaSQL(meusql);

		if (resultado < 1) {
			System.out.println("Aconteceu um erro");
		} else {
			System.out.println("Cadastrado");
		}
		limpar();
		buscando();
	}
	
	private void limpar() {
		modeltarefa = new ModelTarefa();
	}

}
