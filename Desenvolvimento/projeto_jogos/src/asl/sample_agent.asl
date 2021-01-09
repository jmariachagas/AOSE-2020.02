// Agent sample_agent in project projeto_jogos

/* Initial beliefs and rules */

/* Initial goals */

/* Initial goals */

+pos(L, X, Y) : true
	<- 	!mover (X, Y).

+!mover (X, Y) : X < 19
	<- 	.wait (300);
		proximaCasa.

+!mover (X, Y) : X == 19 & Y < 29
	<- 	.wait (300);
		proximaCasa.

+!mover (X, Y) : true.