// Agent gato in project projeto_jogos

+inicio (X) : true
	<-	.wait (400);
		!cacarRato.

+!cacarRato : true
	<-	
		.wait(2500);
		proximaCasaGato.
		
+!pegaRato [source (donaCasa)] : true
				<- .wait(100);
				   .print ("Cacando o rato");
				    proximaCasaGato.
				    
