#devoir 

#question 1

N = c(1:10);
n = 5;
K = 10;

X =c(rep(0,K));
Y =c(rep(0,K));

for( i in 1:K){
  aRemise = sample(N,n,replace=T);
  X[i]= min(aRemise);
  Y[i]= max(aRemise);
};

X;
Y;

#Question 2

N = c(1:3);
n = 2;
K = 10*10*10;

for( i in 1:K){
  aRemise = sample(N,n,replace=T);
  X[i]= min(aRemise);
  Y[i]= max(aRemise);
};

titre="approximation de la loi du couple(X,Y)"
barplot(table(X)/K,main=titre);
