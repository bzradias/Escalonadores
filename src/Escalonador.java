import java.util.List;
import static java.lang.String.format;

public class Escalonador 
{
	private double retornoMedio;
	private double respostaMedia;
	private double esperaMedia;
	
	public int tempoChegadaMinimo(List<Processo> processos) 
	{
    	int min = Integer.MAX_VALUE;
    	for (Processo p : processos) 
    	{
			if(p.getTempoChegada() < min)
				min = p.getTempoChegada();
		}
    	return min;
    }
	
	public void setRetMedia(double ret)
	{
		retornoMedio = ret;
	}
	
	public void setRespMedia(double resp)
	{
		respostaMedia = resp;
	}
	
	public void setEspMedia(double esp)
	{
		esperaMedia = esp;
	}
	
	public void printMedias(String escalonador)
	{
		System.out.println(format("%s %.1f %.1f %.1f", escalonador, retornoMedio, respostaMedia, esperaMedia));
	}
}
