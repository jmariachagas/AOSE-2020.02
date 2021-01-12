// Agent cao in project casa_infestada_ultimo

+dormirCao : true
	<- .wait(20000);
		!andar.
		
+gatoApanhado : true
	<- .wait(100)
		.kill_agent (gato)
		.stopMAS.

+!caoDormindo : true
	<- .wait(20000);
		!andar.

+!andar : true
	<- .wait(400)
		proximaCasaCao;
	!andar.
+!andar.

+percebeGato (X, Y) : true
	<- !cacarGato.
	
+!cacarGato : true
	<- perseguirGato.

+!comerGato : true
	<- 	.print ("Nham! Nham! Nham!");
			removerGato
			!caoDormindo.

		
+aindaNaoPegouGato (X,Y) : true
	<- !cacarGato.
