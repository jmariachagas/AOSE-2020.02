// Agent dona_agent in project projeto_jogos

+inicio (X) : true
	<-	.wait (800);
		!perambular.
		
+!perambular : true
	<-	
		proximaCasaDonaCasa.

+ratoPercebido(X, Y) : true
	<- 	.print("O rato está na posição ", X, " e ", Y);
		.send (gato1, achieve, pegaRato).

