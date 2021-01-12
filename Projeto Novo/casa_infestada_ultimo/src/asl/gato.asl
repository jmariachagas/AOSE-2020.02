// Agent gato in project casa_infestada_ultimo

!andar.

+aindaNaoPegou (X,Y) : true
	<- !cacar.
	
//modificar !comerRato.
+ratoApanhado : true
	<- 	.wait(100)
		.kill_agent (rato)
		.stopMAS.

+!andar : true
	<- .wait(350) 
		proximaCasaGato;
	!andar.
+!andar.

+percebeRato (X, Y) : true
	<-	!cacar.
		
+!cacar [source(dona)] : true 
	<-	.wait (300);
		perseguirRato.


+!cacar [source(self)] : true 
	<-	.wait (300);
		perseguirRato.
		
+!comerRato : true
	<- 	.print ("Nham! Nham! Nham!");
	 	removerRato.
	
