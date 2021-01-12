// Agent cao in project projeto_jogos

+inicio (X) : true
	<-	.wait(200);
		!acordar.
		
+!acordar : true
	<-	proximaCasaCao.

+!buscarGatos : true
	<-	proximaCasaCao.
		
+!devorarGato : true
	<-	.kill_agent (gato);
		.wait (100).

+!devorarRato : true
	<-	.kill_agent (rato);
		.wait (100).