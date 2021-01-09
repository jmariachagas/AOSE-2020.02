// Agent cao in project projeto_jogos

+inicio (X) : true
	<-	!acordar.
		
+!acordar : true
	<-	
		.wait(2000);
		proximaCasaCao.

+!buscarGatos : true
	<-	proximaCasaCao.
		
+!devorarGato : true
	<-	.kill_agent (gato);
		.wait (100).

+!devorarRato : true
	<-	.kill_agent (rato);
		.wait (100).