import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import jason.asSyntax.Literal;
import jason.asSyntax.Structure;
import jason.asSyntax.Term;
import jason.environment.Environment;
import jason.environment.grid.GridWorldModel;
import jason.environment.grid.GridWorldView;
import jason.environment.grid.Location;

public class Ambiente extends Environment{
	
	Location donaCasaLoc;
	Location gatoLoc1;
	Location gatoLoc2;
	Location gatoLoc3;
	Location caoLoc;
	Location ratoLoc1;
	Location ratoLoc2;
	Location ratoLoc3;
	Location ratoLoc4;
	Location ratoLoc5;
	Location ratoLoc6;
	Location ratoLoc7;
	Location ratoLoc8;
	Location ratoLoc9;
	
	private ModeloCasaInfestada modeloCasaInfestada;
	private VisaoAmbiente visaoAmbiente;
	private static final int QUEIJO = 8;
	private static final int EntradaSaida = 16;
	private static final int BURACO = 32;
	
	List<Integer> listX = new ArrayList<Integer>();
	List<Integer> listY = new ArrayList<Integer>();
	private List<Integer> resultPosicaoQueijos = new ArrayList<Integer>();
			
	
	public static final Term  pc = Literal.parseLiteral("proximaCasaDonaCasa");	
	public static final Term  pcg = Literal.parseLiteral("proximaCasaGato");
	public static final Term  pcc = Literal.parseLiteral("proximaCasaCao");
	public static final Term  pcr = Literal.parseLiteral("proximaCasaRato");
	
	public int conInicio = 0, conGato = 0;
	
	
	/**
	 * metodo relacionado a classe Environment
	 * Chamado antes da execu��o da MAS como os argumentos informados em .mas2j
	 */
	@Override
    public void init(String[] args) {
		super.init(args);
		modeloCasaInfestada = new ModeloCasaInfestada(20, 20, 14); //modificar o numero de agentes conforme for incrementando
		visaoAmbiente = new VisaoAmbiente(modeloCasaInfestada);
		modeloCasaInfestada.setView(visaoAmbiente);
		
		clearPercepts();		
				
		donaCasaLoc = modeloCasaInfestada.getAgPos(0);
		Literal inicio = Literal.parseLiteral("inicio(0)");
		addPercept(inicio);
		
		modeloCasaInfestada.add(EntradaSaida, 0, 0);
        for(int i = 0; i < 12; i++) {
        	resultPosicaoQueijos = posicionaElementos();        	
        	modeloCasaInfestada.add(QUEIJO, resultPosicaoQueijos.get(0), resultPosicaoQueijos.get(1));        	
        }

        modeloCasaInfestada.add(BURACO, ratoLoc1);
        modeloCasaInfestada.add(BURACO, ratoLoc2);
        modeloCasaInfestada.add(BURACO, ratoLoc3);
        modeloCasaInfestada.add(BURACO, ratoLoc4);                
	}
	
	/**
	 * metodo relacionado a classe Environment
	 * Executa uma a��o no ambiente. 
	 * Este m�todo � provavelmente substitu�do na classe de ambiente do usu�rio.
	 */
	@Override
    public boolean executeAction(String agName, Structure action) {
		if (true) { // you may improve this condition
            informAgsEnvironmentChanged();
       }
       
       if (action.equals(pc)) {
    	   modeloCasaInfestada.proximaCasaDonaCasa();
       }
       if(action.equals(pcg)) {
    	   modeloCasaInfestada.proximaCasaGato();
       }
       if(action.equals(pcc)) {
    	  modeloCasaInfestada.proximaCasaCao();
       }
       if(action.equals(pcr)) {
    	  modeloCasaInfestada.proximaCasaRato();
       }
       return true; // the action was executed with success
		
	}
	
	/**
	 * metodo relacionado a classe Environment
	 * Chamado pouco antes do final da execu��o do MAS, o ambiente do usu�rio pode substitu�-lo.
	 */
	 @Override
	    public void stop() {
		 
		 super.stop();		 
	 }
	 
	 public List<Integer> posicionaElementos() {		 
		 
		 List<Integer> result = new ArrayList<Integer>();
		 int x = 0;
		 int y = 0;
		 
		 Random r = new Random();
		 x = r.nextInt(19);
		 y = r.nextInt(19);
		 
		 for(Integer n : listX) {
			 if(n == x) {
				 while(n == x) {
					 x = r.nextInt(19);
				 }
			 }
		 }
		 
		 for (Integer n : listY) {
			if(n == y) {
				while(n == y) {				
					y = r.nextInt(19);
				}
			}
		}
		 listX.add(x);
		 listY.add(y);
		 result.add(x);
		 result.add(y);
		 
		 return result;		 
	 }
	
	/**
	 * Classe que implementa o GridWorldModel onde define o modelo do ambiente
	 * quantidade de linhas, colunas e agentes que irao atuar no ambiente
	 * @author User
	 *
	 */
	class ModeloCasaInfestada extends GridWorldModel{
		
		public ModeloCasaInfestada(int colunas, int linhas, int agentes) {
			super(colunas, linhas, agentes);			
			
			//inicializar aqui a posicao inicial dos agentes
			List <Integer> posicaoGatos = new ArrayList<Integer>();
			posicaoGatos = posicionaElementos();
			List <Integer> posicaoGatos2 = new ArrayList<Integer>();
			posicaoGatos2 = posicionaElementos();
			List <Integer> posicaoGatos3 = new ArrayList<Integer>();
			posicaoGatos3 = posicionaElementos();
			
			List <Integer> posicaoCao = new ArrayList<Integer>();
			posicaoCao = posicionaElementos();
			
			List<Integer> posicaoRato1 = new ArrayList<Integer>();
			posicaoRato1 = posicionaElementos();
			List<Integer> posicaoRato2 = new ArrayList<Integer>();
			posicaoRato2 = posicionaElementos();
			List<Integer> posicaoRato3 = new ArrayList<Integer>();
			posicaoRato3 = posicionaElementos();
			List<Integer> posicaoRato4 = new ArrayList<Integer>();
			posicaoRato4 = posicionaElementos();	
			
			try {				
				setAgPos(0, 1, 0); // Posiciona o primeiro agente na posi��o 0,0
				setAgPos(1, posicaoGatos.get(0), posicaoGatos.get(1));
				setAgPos(2, posicaoGatos2.get(0), posicaoGatos2.get(1));
				setAgPos(3, posicaoGatos3.get(0), posicaoGatos3.get(1));
				setAgPos(4, posicaoCao.get(0), posicaoCao.get(1));
				setAgPos(5, posicaoRato1.get(0), posicaoRato1.get(1));
				setAgPos(6, posicaoRato2.get(0), posicaoRato2.get(1));
				setAgPos(7, posicaoRato3.get(0), posicaoRato3.get(1));
				setAgPos(8, posicaoRato4.get(0), posicaoRato4.get(1));
				setAgPos(9, posicaoRato4.get(0), posicaoRato4.get(1));
				setAgPos(10, posicaoRato3.get(0), posicaoRato3.get(1));
				setAgPos(11, posicaoRato3.get(0), posicaoRato3.get(1));
				setAgPos(12, posicaoRato1.get(0), posicaoRato1.get(1));
				setAgPos(13, posicaoRato2.get(0), posicaoRato2.get(1));
				
				
				donaCasaLoc = getAgPos(0);
				gatoLoc1 = getAgPos(1);
				gatoLoc2 = getAgPos(2);
				gatoLoc3 = getAgPos(3);
				caoLoc = getAgPos(4);
				ratoLoc1 = getAgPos(5);
				ratoLoc2 = getAgPos(6);
				ratoLoc3 = getAgPos(7);
				ratoLoc4 = getAgPos(8);
				ratoLoc5 = getAgPos(9);
				ratoLoc6 = getAgPos(10);
				ratoLoc7 = getAgPos(11);
				ratoLoc8 = getAgPos(12);
				ratoLoc9 = getAgPos(13);
				
			} catch(Exception e) {
				 e.printStackTrace();
			}		
			
		}		
		
		
		/**
		 * movimento linear da dona de casa
		 */		
		public void proximaCasaDonaCasa() {
			//implementar 	
		 }
		
		
		/**
		 * movimento aleatorio gato
		 */
		public void proximaCasaGato() {
			
			//implementar
		    	
		 }
		
		/**
		 * movimento aleatorio do cao
		 */
		public void proximaCasaCao() {
			
			//implementar
			
			Random alea = new Random();
			setAgPos(4, caoLoc);
			caoLoc = getAgPos(4);
						
//			int linhatual = caoLoc.x;
//			int colunatual = caoLoc.y;
			
			int direcao = alea.nextInt(4);
			
			switch (direcao) {
				case 0: {
					if (caoLoc.x < 19) {
						caoLoc.x ++;
					}
					else if (caoLoc.y < 19) {
						caoLoc.y ++;
					}
					break;
				}
				case 1:	{
					if (caoLoc.x > 0) {
						caoLoc.x --;
					}
					break;
					}
				case 2: {
					if (caoLoc.y < 19) {
						caoLoc.y ++;
					}
					break;
				}
				case 3:	{
					if (caoLoc.y > 0) {
						caoLoc.y --;
					}
					break;
				}
			}
			
		}
		
		/**
		 * movimento aleatorio do rato
		 */
		public void proximaCasaRato() {			

	       //implementar
			
		}
	}
	
	class VisaoAmbiente extends GridWorldView{

		public VisaoAmbiente(ModeloCasaInfestada modelo) {
			super(modelo, "Casa Infestada", 700);
			
			//defaultFont = new Font("Arial", Font.BOLD, 12); // Muda a fonte padr�o
			
			//renderiza o ambiente
			setVisible(true);            
	   		
			//atualiza o ambiente
			repaint();
			
		}
		
		/**
		 * metodo relacionado a classe GridWorldView
		 * desenha alguma outro modelo que nao seja o agente. Exemplo: queijos
		 * variavel g referencia da classe Graphics
		 * variavel x representa a coluna no ambiente
		 * variavel y representa a linha no ambiente
		 * object representa o objeto a ser representado
		 */
		@Override
		public void draw(Graphics g, int x, int y, int object) {
			
			switch(object) {
				case Ambiente.QUEIJO:
					criarQueijo(g, x, y);
					break;
				case Ambiente.EntradaSaida:
					criarEntradaSaida(g, x, y);
					break;
				case Ambiente.BURACO:
					criarBuracosRatos(g, x, y);
					break;
			}			
		}
		
		public void criarQueijo(Graphics g, int x, int y) {
			
			super.drawObstacle(g, x, y);
			g.setColor(Color.yellow);		        
			drawString(g, x, y, defaultFont, "Queijo");
			
		}
		
		public void criarEntradaSaida(Graphics g, int x, int y) {
			super.drawObstacle(g, x, y);
			g.setColor(Color.ORANGE);		        
			drawString(g, x, y, defaultFont, "Entrada/Saida");
		}
		
		public void criarBuracosRatos(Graphics g, int x, int y) {
			super.drawObstacle(g, x, y);
			g.setColor(Color.white);		        
			drawString(g, x, y, defaultFont, "Buraco");
		}
		
		
		/**
		 * metodo relacionado a classe GridWorldView
		 * seta as caracteristicas do agente no ambiente
		 * variavel g referencia da classe Graphics - modifica cor, cor do texto, etc
		 * variavel x posicao da coluna do agente
		 * variavel y posicao da linha do agente
		 * variavel c referencia da classe Color
		 * variavel id identificador do agente
		 */
		@Override
	    public void drawAgent(Graphics g, int x, int y, Color c, int id) {
			
			switch(id) {
				case 0:
					criaDonaDeCasa(g, x, y, c, id);
					break;
				case 1:
					criaGato(g, x, y, c, id);
					break;
				case 2:
					criaGato(g, x, y, c, id);
					break;
				case 3:
					criaGato(g, x, y, c, id);
					break;
				case 4:
					criaCao(g, x, y, c, id);
					break;
				case 5:
					criaRato(g, x, y, c, id);
					break;
				case 6:
					criaRato(g, x, y, c, id);
					break;
				case 7:
					criaRato(g, x, y, c, id);
					break;
				case 8:
					criaRato(g, x, y, c, id);
					break;
				case 9:
					criaRato(g, x, y, c, id);
					break;
				case 10:
					criaRato(g, x, y, c, id);
					break;
				case 11:
					criaRato(g, x, y, c, id);
					break;
				case 12:
					criaRato(g, x, y, c, id);
					break;
				case 13:
					criaRato(g, x, y, c, id);
					break;
			}			
		}
		
		private void criaDonaDeCasa(Graphics g, int x, int y, Color c, int id) {
			c = Color.yellow;
		    
		    super.drawAgent(g, x, y, c, -1);
		    
		    g.setColor(Color.blue);
		                
		    super.drawString(g, x, y, defaultFont, "donaCasa");
		         
		    setVisible(true);

		}
		
		private void criaGato(Graphics g, int x, int y, Color c, int id) {
			
			c = Color.BLUE;
		    
		    super.drawAgent(g, x, y, c, -1);
		    
		    g.setColor(Color.white);
		                
		    super.drawString(g, x, y, defaultFont, "gato");
		         
		    setVisible(true);
		}
		
		private void criaCao(Graphics g, int x, int y, Color c, int id) {
			
			c = Color.RED;
		    
		    super.drawAgent(g, x, y, c, -1);
		    
		    g.setColor(Color.BLACK);
		                
		    super.drawString(g, x, y, defaultFont, "cao");
		         
		    setVisible(true);
		}
		
		private void criaRato(Graphics g, int x, int y, Color c, int id) {
			
			c = Color.GRAY;
		    
		    super.drawAgent(g, x, y, c, -1);
		    
		    g.setColor(Color.BLACK);
		                
		    super.drawString(g, x, y, defaultFont, "rato");
		         
		    setVisible(true);
		}
	}
		

}

