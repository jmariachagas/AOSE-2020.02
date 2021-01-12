// Agent rato in project projeto_jogos

+inicio (X) : true
	<-	!buscarQueijo.
		
+!buscarQueijo : true
	<-	
		.wait(300);
		proximaCasaRato.
		
		