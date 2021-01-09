import java.awt.Color;
import java.awt.Graphics;

import jason.asSyntax.Literal;
import jason.asSyntax.Structure;
import jason.asSyntax.Term;
import jason.environment.Environment;
import jason.environment.grid.GridWorldModel;
import jason.environment.grid.GridWorldView;
import jason.environment.grid.Location;

public class Ambiente extends Environment{
	
	private ModeloCasaInfestada modeloCasaInfestada;
	private VisaoAmbiente visaoAmbiente;
	Location donaCasa;
	public static final Term  pc = Literal.parseLiteral("proximaCasa");
	
	
	/**
	 * metodo relacionado a classe Environment
	 * Chamado antes da execução da MAS como os argumentos informados em .mas2j
	 */
	@Override
    public void init(String[] args) {
		super.init(args);
		modeloCasaInfestada = new ModeloCasaInfestada(20, 20, 1); //modificar o numero de agentes conforme for incrementando
		visaoAmbiente = new VisaoAmbiente(modeloCasaInfestada);
		modeloCasaInfestada.setView(visaoAmbiente);
		
		Location donaCasaLoc = modeloCasaInfestada.getAgPos(0);
        Literal pos1 = Literal.parseLiteral("pos(donaCasa," + donaCasaLoc.x + "," + donaCasaLoc.y + ")");
        addPercept(pos1);
	}
	
	/**
	 * metodo relacionado a classe Environment
	 * Executa uma ação no ambiente. 
	 * Este método é provavelmente substituído na classe de ambiente do usuário.
	 */
	@Override
    public boolean executeAction(String agName, Structure action) {
		if (true) { // you may improve this condition
            informAgsEnvironmentChanged();
       }
       
       if (action.equals(pc)) {
    	   modeloCasaInfestada.proximaCasaDonaCasa();
       } 
       return true; // the action was executed with success
		
	}
	
	/**
	 * metodo relacionado a classe Environment
	 * Chamado pouco antes do final da execução do MAS, o ambiente do usuário pode substituí-lo.
	 */
	 @Override
	    public void stop() {
		 
		 super.stop();		 
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
			
			try {
				setAgPos(0, 0, 0); // Posiciona o primeiro agente na posição 0,0
			} catch(Exception e) {
				 e.printStackTrace();
			}		
			
		}
		
		/**
		 * movimento linear da dona de casa
		 */
		public void proximaCasaDonaCasa() {

		       	Location donaCasaLoc = getAgPos(0);
		       	            
		       	donaCasaLoc.x++;
		       	            
		       	if (donaCasaLoc.x == getWidth()) {
		       		donaCasaLoc.x = 0;
		       		donaCasaLoc.y++;
		       	    }
		       	            
		       	if (donaCasaLoc.y == getHeight()) {
		       	    return;
		       	    }
		       	
		       	setAgPos(0, donaCasaLoc);
		        Literal pos1 = Literal.parseLiteral("pos(donaCasa," + donaCasaLoc.x + "," + donaCasaLoc.y + ")");
		        addPercept(pos1);		       	
		 }
	}
	
	class VisaoAmbiente extends GridWorldView{

		public VisaoAmbiente(ModeloCasaInfestada modelo) {
			super(modelo, "Casa Infestada", 700);
			
			//defaultFont = new Font("Arial", Font.BOLD, 12); // Muda a fonte padrão
			
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
			
			//criar um swicth aqui
			criaDonaDeCasa(g, x, y, c, id);
			
		}
		
		private void criaDonaDeCasa(Graphics g, int x, int y, Color c, int id) {
			c = Color.yellow;
		    
		    super.drawAgent(g, x, y, c, -1);
		    
		    g.setColor(Color.blue);
		                
		    super.drawString(g, x, y, defaultFont, "donaCasa");
		         
		    setVisible(true);

		    }
		}
		

	}

