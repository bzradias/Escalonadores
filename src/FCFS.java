import java.util.ArrayList;
import java.util.List;


public class FCFS extends Escalonador
{
	private static List<Processo> listaProntos;
	private int tempoRetorno;
	private int tempoResposta;
	private int tempoEspera;
	private int numProcessos;
	private int aux;
	
	public FCFS(List<Processo> processos)
	{
		try 
		{
			tempoRetorno = 0;
			tempoResposta = 0;
			tempoEspera = 0;
			aux = tempoChegadaMinimo(processos);
			numProcessos = processos.size();
			listaProntos = new ArrayList<Processo>(processos);
			
			while (!listaProntos.isEmpty()) 
			{
				Processo p = listaProntos.remove(0);
				aux += p.getDuracao();
				tempoRetorno += (aux - p.getTempoChegada());
				tempoEspera += (aux - p.getTempoChegada() - p.getDuracao());
			}
			tempoResposta = tempoEspera;
			
			super.setRespMedia((double) tempoResposta/numProcessos);
			super.setRetMedia((double) tempoRetorno/numProcessos);
			super.setEspMedia((double) tempoEspera/numProcessos);
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}
	}
	
	public void printMedias()
	{
		printMedias("FCFS");
	}
}
