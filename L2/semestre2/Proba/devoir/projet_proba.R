#Devoir Maison

#NOM1 : Mamadou DIALLO
#nom2 : Fernand NIYOGUSHIMA 

#question 1

N = c(1:10);
n = 5;
K = 10;

X =c(rep(0,K));
Y =c(rep(0,K));

for( i in 1:K){
  aRemise = sample(N,n,replace=TRUE);
  X[i]= min(aRemise);
  Y[i]= max(aRemise);
};

X;
Y;

#Question 2

N = c(1:3);
n = 2;
K = 10^3;
X =c(rep(0,K));
Y =c(rep(0,K));
donnees <- matrix(rep(0,9), nrow = 3, ncol = 3, byrow = TRUE)
for( i in 1:K){
  aRemise = sample(N,n,replace=TRUE);
  X[i]= min(aRemise);
  Y[i]= max(aRemise);
  for (j in 1:9){
    for (k in 1:3){
      donnees[j] = (X[j]=k) + (Y[j]=k)
    }
  }
}
table(X,Y)/K

#Question 3

N = c(1:10);
n = 6;
K = 10^3;
X =c(rep(0,K));
Y =c(rep(0,K));
for( i in 1:K){
  aRemise = sample(N,n,replace=T);
  X[i]= min(aRemise);
  Y[i]= max(aRemise);
};
par(mfrow=c(1,2));
titre1="distribution de X"
barplot(table(X)/K,main=titre1);
titre2="distribution de Y"
barplot(sort(table(Y))/K,main=titre2);

#Question 4

N = c(1:10);
n = 10;
K = 10^3;

X =c(rep(0,K));
Y =c(rep(0,K));

E = 0 #erreur

# PP est la proportion de l'experience quand X=2 et Y=8.
Proportion = 0

# Sa probabilit?? est egale a: 
P = (1/N^n)*(((8-2+1)^n) - 2*((8-2)^n) + ((8-2-1)^n))

for(i in 1:K){
  aRemise = sample(N,n,replace=TRUE)
  X[i] = min(aRemise)
  Y[i] = max(aRemise)
  if ((X[i] = 2) && (Y[i]==8)) {
    Proportion = Proportion +1
  }
}

Proportion = Proportion/K
E = (P-Proportion)/P


