// Agent dona in project casa_infestada_ultimo

!andar.


+!andar : true
	<- .wait(500) 
		proximaCasaDona;
	!andar.
+!andar.

+ratoPercebido(X, Y) : true
	<- .print("Rato percebido e est� na posi��o ", X, " e ", Y);
		.send(gato, achieve, cacar).
	

