// Agent dona_agent in project projeto_jogos

+inicio (X) : true
	<-	.wait (800);
		!perambular.
		
+!perambular : true
	<-	
		.wait(2500);
		proximaCasaDonaCasa.

+ratoPercebido(X, Y) : true
	<- 	.print("O rato esta na posicaoo ", X, " e ", Y);
		.send (gato1, achieve, pegaRato).

