%Define predicate brother(X, Y) which succeeds if X and Y are brothers.
%Define predicate cousin(X, Y) which succeeds if  X and Y are cousins.
%Define predicate grandson(X, Y) which succeeds if X is grandson of Y.
%Define predicate descendent(X, Y) which succeeds if X is a descendent of Y.

   parent(jose, maria).               
   parent(jose, pedro).
   parent(maria, luis).
   parent(maria, afonso).
   parent(pedro, ana).
   
   
   brother(X,Y) :- parent(Z,X), parent(Z,Y).
   
   grandson(X,Y) :- parent(Y,Z), parent(Z,X).
   
   descendent(X,Y) :- parent(Y,X).
   descendent(X,Y) :- parent(Y,Z), descendent(Z,W).
   
   cousin(X,Y) :- parent(Z,X), parent(W,Y), brother(Z,W).
   
%Queries:
%?- brother(X, Y).
%?- cousin(X, Y).
%?- grandson(X, Y).
%?- descendent(X, Y).