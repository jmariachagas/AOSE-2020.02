// Agent dona_agent in project projeto_jogos

+inicio (X) : true
	<-	.wait (400);
		!perambular.
		
+!perambular : true
	<-	proximaCasaDonaCasa.

+ratoPercebido(X, Y) : true
	<- 	!perambular.
//	.print("O rato esta na posicaoo ", X, " e ", Y);
		
//		.send (gato1, achieve, pegaRato).

