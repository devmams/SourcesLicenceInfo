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
K = 10^3;
X =c(rep(0,K));
Y =c(rep(0,K));
P =c(rep(0,K));
for( i in 1:K){
  aRemise = sample(N,n,replace=T);
  X[i]= min(aRemise);
  Y[i]= max(aRemise);
  if(X[i] != Y[i]){
    P[i] = 2/K;
  }
  else{
    P[i] = 1/K;
  }
};
P;
tableau = data.frame(P);

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


