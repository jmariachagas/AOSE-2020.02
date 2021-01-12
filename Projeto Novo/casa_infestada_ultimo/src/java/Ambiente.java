import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import jason.asSemantics.Agent;
import jason.asSyntax.Literal;
import jason.asSyntax.Structure;
import jason.asSyntax.Term;
import jason.environment.Environment;
import jason.environment.grid.GridWorldModel;
import jason.environment.grid.GridWorldView;
import jason.environment.grid.Location;
import jason.stdlib.kill_agent;

public class Ambiente extends Environment {
	
	public static final int QUEIJO = 8;
	public static final int EntradaSaida = 16;
	public static final int BURACO = 32;
		
	public List<Elemento> listElementos = new ArrayList<Elemento>();
	public List<Integer> resultPosicaoQueijos = new ArrayList<Integer>();
	public List<Integer> resultPosicaoBuracos = new ArrayList<Integer>();
	
	//movimentos dos agentes
	public static final Term proximaCasaDona = Literal.parseLiteral("proximaCasaDona");
	public static final Term proximaCasaRato = Literal.parseLiteral("proximaCasaRato");
	public static final Term proximaCasaGato = Literal.parseLiteral("proximaCasaGato");
	public static final Term perseguirRato = Literal.parseLiteral("perseguirRato");
	public static final Term proximaCasaCao = Literal.parseLiteral("proximaCasaCao");
	public static final Term perseguirGato = Literal.parseLiteral("perseguirGato");
	public static final Term removerRato = Literal.parseLiteral("removerRato");
	public static final Term removerGato = Literal.parseLiteral("removerGato");
	
	public ModeloAmbiente modelo;				// variável de modelo
    public VisaoAmbiente  visao;				// variável de visão
    public int contQueijos = 0;
	
	/* Chamado antes da execução da MAS como os argumentos informados em .mas2j */

    @Override
    public void init(String[] args) {
        super.init(args);
        
        modelo = new ModeloAmbiente(20,20,4);
        visao  = new VisaoAmbiente(modelo);
        modelo.setView(visao);
        
       
 
        clearPercepts();
        
        modelo.add(EntradaSaida, 0, 0);
        
        Location rato = modelo.getAgPos(1);

        modelo.add(BURACO, rato);
        for(int i = 0; i < 3; i++) {
        	resultPosicaoBuracos = posicionaElementos();
        	modelo.add(BURACO, resultPosicaoBuracos.get(0), resultPosicaoBuracos.get(1));
        	listElementos.add(new Elemento(resultPosicaoBuracos.get(0), resultPosicaoBuracos.get(1)));
        }
        
        for(int i = 0; i < 12; i++) {
        	resultPosicaoQueijos = posicionaElementos();        	
        	modelo.add(QUEIJO, resultPosicaoQueijos.get(0), resultPosicaoQueijos.get(1));
        	listElementos.add(new Elemento(resultPosicaoQueijos.get(0), resultPosicaoQueijos.get(1)));
        }
        
        Literal dormirCao = Literal.parseLiteral("dormirCao");
		addPercept(dormirCao);        
    }
    
    public List<Integer> posicionaElementos() {		 
		 
		 List<Integer> result = new ArrayList<Integer>();
		 int x = 0;
		 int y = 0;
		 boolean posicaoDuplicada = false;
		 
		 Random r = new Random();
		 x = r.nextInt(19);
		 y = r.nextInt(19);
		 
		 Elemento elemento = new Elemento(x, y);
		 
		 for(int i = 0; listElementos.size() > i; i++) {
			if((listElementos.get(i).getLinha() == elemento.getLinha()) && (listElementos.get(i).getColuna() == elemento.getColuna())) {
				posicaoDuplicada = true;
				break;
			}
		 }
		 if(posicaoDuplicada) {
			 while(posicaoDuplicada) {
				 Elemento copia = elemento;
				 elemento.setLinha(r.nextInt(19));
				 elemento.setColuna(r.nextInt(19));
				 
				 if(!(copia.getLinha() == elemento.getLinha() && copia.getColuna() == elemento.getColuna())) {
					 posicaoDuplicada = false;
				 }				 
			 }
		 }		 
		 
		 listElementos.add(elemento);
		 result.add(x);
		 result.add(y);
		 
		 return result;		 
	 }
    
    @Override
    public boolean executeAction(String agName, Structure action) {
    	

    	if(action.equals(proximaCasaDona)) {
    		modelo.proximaCasaDona();
    	} else if(action.equals(proximaCasaRato)) {
    		modelo.proximaCasaRato();
    	} else if(action.equals(proximaCasaGato)) {
    		modelo.proximaCasaGato();
    	} else if(action.equals(perseguirRato)) {
    		modelo.perseguirRato();
    	} else if(action.equals(proximaCasaCao)) {
    		modelo.proximaCasaCao();
    	} else if(action.equals(perseguirGato)) {
    		modelo.perseguirGato();
    	} else if(action.equals(removerRato)) {
    		//getEnvironmentInfraTier().getRuntimeServices()
    			//.killAgent("rato", "gato", 0);    		
    	} else if(action.equals(removerGato)) {
    		//getEnvironmentInfraTier().getRuntimeServices()
			//.killAgent("gato", "cao", 0);
    	}    	
    	return true;
    }
    
    /* Chamado antes do fim da execução do MAS */ 
    @Override
    public void stop() {
        super.stop();
    }
    
    class ModeloAmbiente extends GridWorldModel {		// Classe de modelo

        public ModeloAmbiente (int colunas, int linhas, int agentes) {	// Recebe a coluna, linha e agente
     	   super(colunas, linhas, agentes);
 	       try {
 	    	  
 	    	  Location dona = new Location(0, 0);
	    	  setAgPos(0, dona);
	    	  
	    	  List<Integer> posicaoRato1 = new ArrayList<Integer>();
	    	   posicaoRato1 = posicionaElementos();    	   
	    	   
	    	   Location rato = new Location(posicaoRato1.get(0), posicaoRato1.get(1));
	    	   setAgPos(1, rato);
	    	   
	    	   List<Integer> posicaoGato1 = new ArrayList<Integer>();
	    	   posicaoGato1 = posicionaElementos();
	    	   
	    	   Location gato = new Location(posicaoGato1.get(0), posicaoGato1.get(1));
	    	   setAgPos(2, gato);
	    	   
	    	   List<Integer> posicaoCao = new ArrayList<Integer>();
	    	   posicaoCao = posicionaElementos();
	    	   
	    	   Location cao = new Location(posicaoCao.get(0), posicaoCao.get(1));
	    	   setAgPos(3, cao);
	    	   
 	       } catch(Exception e) {
 	    	   e.printStackTrace();
 	       }
        }
        
        //metodo que realiza a acao de caminhar sobre o ambiente e 
        //verificar as possiveis percepcoes da dona de casa
        void proximaCasaDona() {
        	
        	clearPercepts();
        	Location dona = getAgPos(0);
        	Location rato = getAgPos(1);
        	int colunaAtual = dona.x;
        	int ratoAchado = 0;
        	
        	for (int coluna = colunaAtual; coluna <= (colunaAtual + 3); coluna++) {
     				if((coluna == rato.x) && (rato.y == dona.y)) {
         				System.out.println("Rato percebido");
         				Literal ratoPercebido = Literal.parseLiteral("ratoPercebido(" + rato.x +"," + rato.y + ")");
         				addPercept(ratoPercebido);
         				ratoAchado = 1;
         				setAgPos(0, dona);
         				break;
         			}        		
     		}
        	if(ratoAchado == 0) {
        		
        		if (dona.x < (getWidth() - 1)) {
            		dona.x++;
            	} else if (dona.y < (getHeight() - 1)) {
            		dona.x = 0;
            		dona.y++;
            	} else {
            		dona.x = 0;
            		dona.y = 0;
            	}     		       	      			    		       	
            	colunaAtual = dona.x;            	
            	
            	//Percepcoes da Dona de casa 		
         		for (int coluna = colunaAtual; coluna <= (colunaAtual + 3); coluna++) {
         			//Percepcao de verificar um elemento  queijo
         			if(hasObject(QUEIJO, coluna, dona.y)) {
         				if (dona.y < 20) {   			
         					dona.y++;
         					setAgPos(0, dona);
         					return;
         				}
         			}
         			//Percepcao de verificar um elemento buraco
         			else if(hasObject(BURACO, coluna, dona.y)) {
         				if (dona.y < 20) {   			
         					dona.y++;
         					setAgPos(0, dona);
         					return;
         	    		}
         			}         			
         		}
         		setAgPos(0, dona);         		        		
        	}       	
        }
        
      //metodo que realiza a acao de caminhar sobre o ambiente e 
      //verificar as possiveis percepcoes do cao
        void proximaCasaCao() {
        	
        	Location cao = getAgPos(3);
        	Location gato = getAgPos(2);
      	   
      	   	Random movimentoAleatorio = new Random();
      	   	int direcao = movimentoAleatorio.nextInt(4);
      	   	int colunaAtual = cao.x;
      	   	
      	   	for(int coluna = colunaAtual; coluna < (colunaAtual + 4); coluna++) {
      	   		if(coluna == gato.x && cao.y == gato.y) {
      	   			Literal percebeGato = Literal.parseLiteral("percebeGato(" + gato.x +"," + gato.y + ")");
      	   			addPercept(percebeGato);    			   
      	   		}    		   
      	   	}
      	   	for(int coluna = colunaAtual; coluna >= (colunaAtual - 4); coluna--) {
     		 	if(coluna == gato.x && cao.y == gato.y) {
     		 		Literal percebeRato = Literal.parseLiteral("percebeGato(" + gato.x +"," + gato.y + ")");
     		 		addPercept(percebeRato);      			 
     		 	}
      	   	}
      	   
      	   	//para frente
      	   	if(direcao == 0) {
      	   		cao.x++;
      	   		if (cao.x == getWidth()) {
      	   			cao.x = 0;
      	   			cao.y++;
      	   		}
      	   		if (cao.y == getHeight()) {
      	   			return;
        		}    		   
      	   	}
      		//para tras
      	   	else if(direcao == 1) { 
      	   		cao.x--;
      	   		if (cao.x == -1 & cao.y == 0) {
      	   			cao.x++;
      	   		} if(cao.x == -1 & cao.y > 0) {
      	   			cao.x = getWidth() - 1;
      	   			cao.y--;
      	   		}
      	   	}
      	   	//para baixo
      	   	else if(direcao == 2) {
      	   		cao.y++;    		   
      	   		if (cao.y == getHeight()) {
      	   			cao.y--;
      	   		}    		  
      	   	}
      	   //para cima
      	   else if(direcao == 3) {    		   
      		   cao.y--;    		   

      		   if (cao.y < 0) {
      			 cao.y++;
      		   }    		     		    	   	
      	   }
      	   
      	   //percepcoes do cão
      	   //desvia dos queijos
      	   if(hasObject(QUEIJO, cao.x, cao.y)) {
      		 if (cao.y < 19) {   			
      			 cao.y++;   				
         		} 
          	   else if (cao.x < 19) {
          		   cao.x++;
         		}
          	   else if(cao.x >= 19 && cao.y >= 19) {
          		   cao.x--;
          	   } 
      		 setAgPos(3, cao);
     		 return;
      	   }
      	   //desvia dos buracos
      	   if(hasObject(BURACO, cao.x, cao.y)) {
      		   if (cao.y < 19) {   			
      			   cao.y++;   				
      		   } 
          	   else if (cao.x < 19) {
          		   cao.x++;
          	   }
          	   else if(cao.x >= 19 && cao.y >= 19) {
          		   cao.x--;
          	   }
      		 setAgPos(3, cao);
      		 return;
      	   }      	   
      	   setAgPos(3, cao);        	
        }
        
        //metodo que realiza a acao de perseguir o gato 
        void perseguirGato() {
      	   
      	   Location gato = getAgPos(2);
      	   Location cao = getAgPos(3);
      	   System.out.println("Perseguindo o Gato!!");
            
      	   if((cao.x == gato.x) && (cao.y == gato.y)) {
      		   Literal gatoApanhado = Literal.parseLiteral("gatoApanhado");
      		   addPercept(gatoApanhado);
      		 System.out.println(hasObject(AGENT, gato));
      	   } else {
      	   
      	   if (gato.x > cao.x) {
      		   cao.x++;
            }
            if (gato.x < cao.x) {
            	cao.x--;
            }
            if (gato.x == cao.x) {
         	   if (gato.y > cao.y) {
         		   cao.y++;
            		}
            		if (gato.y < cao.y) {
            			cao.y--;
            		}           		   
            	}        	
           }
      	   Literal perseguicao = Literal.parseLiteral("aindaNaoPegouGato (" + cao.x + ", " + cao.y + ")");
      	   addPercept(perseguicao);
      	   setAgPos(3, cao);
         }
        
        //metodo que realiza a acao de caminhar sobre o ambiente e 
        //verificar as possiveis percepcoes do rato
        void proximaCasaRato() {
     	   
     	   Location rato = getAgPos(1);
     	   
     	   Random movimentoAleatorio = new Random();
     	   int direcao = movimentoAleatorio.nextInt(4);
     	   
     	   //para frente
     	   if(direcao == 0) {
     		   rato.x++;
     		   if (rato.x == getWidth()) {
     			   rato.x = 0;
     			   rato.y++;
     		   }
     		   if (rato.y == getHeight()) {
       				return;
       			}    		   
     	   }
     		//para tras
     	   else if(direcao == 1) { 
     		   rato.x--;
     		   if (rato.x == -1 & rato.y == 0) {
     			   rato.x++;
     		   } if(rato.x == -1 & rato.y > 0) {
     			   rato.x = getWidth() - 1;
     			   rato.y--;
     		   }
     	   }
     	   //para baixo
     	   else if(direcao == 2) {
     		   rato.y++;    		   
     		   if (rato.y == getHeight()) {
     			   rato.y--;
     		   }    		  
     	   }
     	   //para cima
     	   else if(direcao == 3) {    		   
     		   rato.y--;    		   

     		   if (rato.y < 0) {
     			   rato.y++;
     		   }    		     		    	   	
     	   }     	   
     	   setAgPos(1, rato);
     	   
     	   //percepcoes do rato
     	   if(hasObject(QUEIJO, rato)) {
     		   remove(QUEIJO, rato);
     		  contQueijos++;
     	   }
     	   
     	   if(contQueijos >= 12) {
     		   try {
				getEnvironmentInfraTier().getRuntimeServices().stopMAS();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
     	   }
        }
        
        //metodo que realiza a acao de caminhar sobre o ambiente e 
        //verificar as possiveis percepcoes do gato
        void proximaCasaGato() {      	   
        	
      	   Location gato = getAgPos(2);
      	   Location rato = getAgPos(1);
      	   int colunaAtual = gato.x;
      	   
      	   Random movimentoAleatorio = new Random();
      	   int direcao = movimentoAleatorio.nextInt(4);
      	   
      	 for(int coluna = colunaAtual; coluna < (colunaAtual + 3); coluna++) {
    		   if(coluna == rato.x && gato.y == rato.y) {
    			   Literal percebeRato = Literal.parseLiteral("percebeRato(" + rato.x +"," + rato.y + ")");
    			   addPercept(percebeRato);    			   
    		   }    		   
    	   }
      	 for(int coluna = colunaAtual; coluna >= (colunaAtual - 3); coluna--) {
      		 if(coluna == rato.x && gato.y == rato.y) {
      			 Literal percebeRato = Literal.parseLiteral("percebeRato(" + rato.x +"," + rato.y + ")");
      			 addPercept(percebeRato);      			 
      		 }
      	 }
      	   
      	   //para frente
      	   if(direcao == 0) {
      		   gato.x++;
      		   if (gato.x == getWidth()) {
      			   gato.x = 0;
      			   gato.y++;
      		   }
      		   if (gato.y == getHeight()) {
        				return;
        			}    		   
      	   }
      		//para tras
      	   else if(direcao == 1) { 
      		   gato.x--;
      		   if (gato.x == -1 & gato.y == 0) {
      			   gato.x++;
      		   } if(gato.x == -1 & gato.y > 0) {
      			   gato.x = getWidth() - 1;
      			   gato.y--;
      		   }
      	   }
      	   //para baixo
      	   else if(direcao == 2) {
      		   gato.y++;    		   
      		   if (gato.y == getHeight()) {
      			   gato.y--;
      		   }    		  
      	   }
      	   //para cima
      	   else if(direcao == 3) {    		   
      		   gato.y--;    		   

      		   if (gato.y < 0) {
      			   gato.y++;
      		   }    		     		    	   	
      	   }     	   
      	   
      	   //percepcoes do gato
      	   //desvia dos queijos
      	   if(hasObject(QUEIJO, gato.x, gato.y)) {
      		 if (gato.y < 19) {   			
          		 gato.y++;   				
         		} 
          	   else if (gato.x < 19) {
         			gato.x++;
         		}
          	   else if(gato.x >= 19 && gato.y >= 19) {
          		   gato.x--;
          	   } 
      		 setAgPos(2, gato);
     		 return;
      	   }
      	   //desvia dos buracos
      	   if(hasObject(BURACO, gato.x, gato.y)) {
      		   if (gato.y < 19) {   			
      			   gato.y++;   				
      		   } 
          	   else if (gato.x < 19) {
          		   gato.x++;
          	   }
          	   else if(gato.x >= 19 && gato.y >= 19) {
          		   gato.x--;
          	   }
      		 setAgPos(2, gato);
      		 return;
      	   }      	   
      	   setAgPos(2, gato);
         }
        
        //metodo que realiza a acao de perseguir o rato no ambiente       
        void perseguirRato() {
     	   
     	   Location gato = getAgPos(2);
     	   Location rato = getAgPos(1);
     	   System.out.println("Perseguindo o Rato!!");
           
     	   if((rato.x == gato.x) && (rato.y == gato.y)) {
     		   Literal ratoApanhado = Literal.parseLiteral("ratoApanhado");
     		   addPercept(ratoApanhado);
     		   System.out.println(hasObject(AGENT, rato));
     	   } else {
     	   
     	   if (rato.x > gato.x) {
     		   gato.x++;
           }
           if (rato.x < gato.x) {
        	   gato.x--;
           }
           if (rato.x == gato.x) {
        	   if (rato.y > gato.y) {
        		   gato.y++;
           		}
           		if (rato.y < gato.y) {
           			gato.y--;
           		}           		   
           	}       		
        	
          }
     	  Literal perseguicao = Literal.parseLiteral("aindaNaoPegou (" + gato.x + ", " + gato.y + ")");
     	  addPercept(perseguicao);
     	  setAgPos(2, gato);
        }       
    }
    
    class Elemento {	
		
		 private int linha, coluna;
			
		public Elemento (int x, int y) {
			this.linha = x;
			this.coluna = y;
		}
			
		public int getLinha(){
			return linha;
		}
			
		public int getColuna() {
			return coluna;
		}
			
		public void setLinha(int linha) {
			this.linha = linha;
		}
			
		public void setColuna(int coluna) {
			this.coluna = coluna;
		}		
	}
    
    class VisaoAmbiente extends GridWorldView {
        
    	public VisaoAmbiente(ModeloAmbiente model) {
              
		super(model, "Mundo CasaInfestada", 700);
             
    	defaultFont = new Font("Arial", Font.BOLD, 10); // Muda a fonte padrão
            
   		setVisible(true);            
   		
		repaint();
	}
    
    @Override
    public void drawAgent(Graphics g, int x, int y, Color c, int id) {
		String rotulo = "";

		switch (id) {
		case 0: {
			c = Color.green;
			rotulo = new String ("DonaCasa");
			break;
		}
		case 1: {
			c = Color.yellow;
			rotulo = new String ("Rato");
			break;
		}
		case 2: {
			c = Color.gray;
			rotulo = new String ("Gato");
			break;
		}
		case 3: {
			c = Color.orange;
			rotulo = new String ("Cão");
			break;
		}
		}
		
		if (id >= 0 && id < 4) {
			super.drawAgent(g, x, y, c, -1);

			g.setColor(Color.black);
	            
			super.drawString(g, x, y, defaultFont, rotulo);
	     
			setVisible(true);
		}
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
    }   

}
