import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SJF extends Escalonador
{
	private int tempoDecorrido;
	private static List<Processo> listaAuxiliar;
	private ArrayList<Processo> listaProntos;
	private int tempoRetorno;
	private int tempoResposta;
	private int tempoEspera;
	private int numProcessos;
	
	public SJF(List<Processo> processos)
	{
		tempoRetorno = 0;
		tempoResposta = 0;
		tempoEspera = 0;
		tempoDecorrido = tempoChegadaMinimo(processos);
		numProcessos = processos.size();
		listaAuxiliar = new ArrayList<Processo>(processos);
		listaProntos = new ArrayList<>();
		
		while(!(listaAuxiliar.isEmpty() && listaProntos.isEmpty())) //Enquanto as listas não estão vazias, faça
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
			
			
			Collections.sort(listaProntos, new Comparator<Processo>() //Ordena Lista de Prontos em relação ao tempo de duração
			{
				public int compare(Processo p1, Processo p2)
				{
					return Integer.valueOf(p1.getDuracao()).compareTo(p2.getDuracao());
				}
			});
			
			if(!listaProntos.isEmpty()) 
			{
				Processo p = listaProntos.remove(0);
				tempoDecorrido += p.getDuracao();
				tempoRetorno += (tempoDecorrido - p.getTempoChegada());
				tempoEspera += (tempoDecorrido - p.getTempoChegada() - p.getDuracao());
			}
		}	
		
		tempoResposta = tempoEspera;
	
		super.setRespMedia((double) tempoResposta/numProcessos);
		super.setRetMedia((double) tempoRetorno/numProcessos);
		super.setEspMedia((double) tempoEspera/numProcessos);
		
	}
	
	public void printMedias()
	{
		printMedias("SJF");
	}
}
