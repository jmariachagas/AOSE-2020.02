// Agent gato in project projeto_jogos

+inicio (X) : true
	<-	.wait (400);
		!cacarRato.

+aindaNaoPegou (X,Y) : true
				<- !pegaRato.		

+ratoApanhado : true <- .print ("Nham! Nham! Nham!"); .kill_agent (rato1).

+!cacarRato : true
	<-	
		.wait(250);
		proximaCasaGato.
		
+!pegaRato [source (donaCasa)] : true
				<- .wait(100);
				   .print ("Cacando o rato");
				    proximaCasaGato.
				    
