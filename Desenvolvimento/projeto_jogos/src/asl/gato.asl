// Agent gato in project projeto_jogos

+inicio (X) : true
	<-	.wait (400);
		!cacarRato.

+!cacarRato : true
	<-	
		proximaCasaGato.
		
+!pegaRato [source (donaCasa)] : true
				<- .wait(100);
				   .print ("Caçando o rato");
				    proximaCasaGato.