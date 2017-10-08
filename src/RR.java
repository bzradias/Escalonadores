import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RR extends Escalonador 
{
	private int tempoDecorrido;
	private static List<Processo> listaAuxiliar;
	private ArrayList<Processo> listaProntos;
	private ArrayList<Processo> listaNova;
	private int tempoRetorno;
	private int tempoResposta;
	private int tempoEspera;
	private int numProcessos;
	private int quantum;
	private int prox;
	private int duracao;
	
	public RR(List<Processo> processos)
	{
		quantum = 2;
		prox = 0;
		tempoRetorno = 0;
		tempoResposta = 0;
		tempoEspera = 0;
		tempoDecorrido = tempoChegadaMinimo(processos);
		numProcessos = processos.size();
		listaAuxiliar = new ArrayList<Processo>(processos);
		listaProntos = new ArrayList<>();
		listaNova = new ArrayList<>();
		
		while(!(listaAuxiliar.isEmpty() && listaProntos.isEmpty() && listaNova.isEmpty())) //Enquanto as listas não estão vazias, faça
		{		
			while (!listaAuxiliar.isEmpty()) //Carrega os processos até o tempo decorrido na Fila de Prontos 
			{
				if((listaAuxiliar.get(0).getTempoChegada() <= tempoDecorrido) || (listaProntos.isEmpty()))
				{
					Processo p = listaAuxiliar.remove(0);
					listaProntos.add(new Processo(p.getId(), p.getTempoChegada(), p.getDuracao()));
				}
				else
				{
					break;
				}
			}
			
			if(!listaNova.isEmpty())
			{
				Processo p = listaNova.remove(0);
				listaProntos.add(new Processo(p.getId(), p.getTempoChegada(), p.getDuracao(), duracao));
			}
			
			if(!listaProntos.isEmpty()) 
			{
				Processo p = listaProntos.remove(0);
				duracao = (p.getDuracaoRestante()) - quantum;
				
				if(!(p.getKey())) //O tempo de resposta só é calculado uma vez para cada processo
				{	
					tempoResposta += tempoDecorrido - p.getTempoChegada();
					p.setKey(); 
				}
				if(duracao <= 0) //Processo terminou sua execução
				{	
					tempoDecorrido += quantum + duracao;
					tempoRetorno += (tempoDecorrido - p.getTempoChegada());
					tempoEspera += (tempoDecorrido - p.getTempoChegada() - p.getDuracao());	
				}
				else
				{
					p.setDuracaoRestante(duracao);
					
					tempoDecorrido += quantum;
					listaNova.add(new Processo(p.getId(), p.getTempoChegada(), p.getDuracao(), duracao));
				}	
			}
		}	
		
		super.setRespMedia((double) tempoResposta/numProcessos);
		super.setRetMedia((double) tempoRetorno/numProcessos);
		super.setEspMedia((double) tempoEspera/numProcessos);
	}
	
	public void printMedias()
	{
		printMedias("RR");
	}
}
