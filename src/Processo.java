
public class Processo
{
	private int id;
	private int tempoChegada;
	private int duracao;
	private int duracaoRestante;
	private boolean key = true;

	public Processo(int id, int tempoChegada, int duracao)
	{
		this.id = id;
		this.tempoChegada = tempoChegada;
		this.duracao = duracao;
		this.duracaoRestante = duracao;
		key = false;
	}
	
	public Processo(int id, int tempoChegada, int duracao, int duracaoRestante)
	{
		this.id = id;
		this.tempoChegada = tempoChegada;
		this.duracao = duracao;
		this.duracaoRestante = duracaoRestante;
	}
	
	public int getId() 
	{
		return id;
	}
	
	public int getTempoChegada()
	{
		return tempoChegada;
	}
	
	public int getDuracao()
	{
		return duracao;
	}  
	
	public void setDuracao(int duracao)
	{
		this.duracao = duracao;
	}
	
	public int getDuracaoRestante()
	{
		return duracaoRestante;
	}
	
	public void setDuracaoRestante(int duracaoRestante)
	{
		this.duracaoRestante = duracaoRestante;
	}
	
	public void setKey()
	{
		key = true;
	}
	
	public boolean getKey()
	{
		return key;
	}
}
