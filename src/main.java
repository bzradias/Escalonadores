import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class main 
{

	/**
	 * @param args
	 */
	public static void main(String[] args) throws FileNotFoundException
	{
		FileReader arq = new FileReader("src/dados.txt");
		Scanner dados = new Scanner(arq);
		ArrayList<Processo> listaProcessos = new ArrayList<>();
		int id = 0;
		
		while(dados.hasNext())
		{
			int tempoChegada = Integer.valueOf(dados.next());
			int duracao = Integer.valueOf(dados.next());
			
			listaProcessos.add(new Processo(++id, tempoChegada, duracao));
		}
		
		Collections.sort(listaProcessos, new Comparator<Processo>()
		{
			public int compare(Processo p1, Processo p2)
			{
				return Integer.valueOf(p1.getTempoChegada()).compareTo(p2.getTempoChegada());
			}
		});
		
		FCFS fcfs = new FCFS(listaProcessos);
		SJF sjf = new SJF(listaProcessos);
		RR rr = new RR(listaProcessos);
		fcfs.printMedias();
		sjf.printMedias();
		rr.printMedias();		
		
	}
}
