X = 1:10;
sample(X, 7); #donne 7 elements parmi les X sans doublons
sample(X, 7, replace=TRUE); #donne 7 elements parmi les X sans doublons
sample(X); # Rearangement: permutation (aleatoire);
sample(5) # Rearangement: permutation (aleatoire); donne tous les X <= 5
sample(X[X > 8],7 ,replace=TRUE);
sample(X[X > 9]);
sample(X[X > 10]);
example(sample);

res_de = sample(1:6,500,replace = T);
table(res_de);

res_de_pipe = sample(6,500,replace =T,prob=c(0.5,0.1,0.1,0.1,0.1,0.1))
table(res_de_pipe);

titre1='Frequence obtenues pour \n 500 lancer de dé equilibre'
titre2='Frequence obtenues pour \n 500 lancer de dé pipe'

par(mfrow=c(1,2))
barplot(table(res_de_pipe)/500,main=titre1)
barplot(table(res_de_pipe)/500,main=titre2)

#-------------------autre_application

#Question 2
proba=c(rep(0.67,10),rep(0.33,5))
urne = c(rep(1,10),rep(0,5))
par(mfrow=c(1,2))
avecRemise =sample(urne,10,replace=T,prob=proba)
sansRemise =sample(urne,10,replace=F)
titre2.1='Frequence obtenue 10 tirage avec remise'
titre2.2='Frequence obtenue 10 tirage sans remise'
barplot(table(avecRemise)/10,main=titre2.1)
barplot(table(sansRemise)/10,main=titre2.2)

#-----------------------
#question 3
#Avec remise
Xsample =c(rep(0,500));
for( i in 1:500){
  nbB = sample(urne,10,replace=T,prob=proba);
  Xsample[i]= length(nbB[nbB==0]);
};
barplot(table(Xsample)/500,main='Frequence obtenu lors 10 tirages repete 500 fois');


Xbinom = dbinom(urne,10,5/15)
barplot(Xbinom,main='Frequence obtenu lors 10 tirages repete 500 fois')


#sans remise
Ysample =c(rep(0,500));
for( i in 1:500){
  nbN = sample(urne,10,replace=F);
  Ysample[i]= length(nbN[nbN==0]);
};
par(mfrow=c(1,2))
barplot(table(Ysample)/500,main='Frequence obtenu lors 10 tirages repete 500 fois');

Yhyper = dhyper(c(1:5),5,10,10);
barplot(Yhyper,main='Loi Geometrique(5,10,10)')

#Exercice 3.2
#Question 1
p = 0.5;
n = 5;
N =100;

#a)
#C'est la loi binomiale de parametre p =0.5 et n = 5
#pour repondre a la question, on utilise rbinom,
#cette fonction permet de calculer sur un echantillon

#b)
Sn = rbinom(100,n,p);

#c)
Wn = Sn/n;

#Question2
epsilon = 0.1;
Rn = Wn-p;
Rn = Rn[abs(Rn)>epsilon];

#-------------------------------

#Question2
epsilon = 0.1;
Rn = Wn-p;
Rn = Rn[abs(Rn)>epsilon];

#Question 3
R10 =  (rbinom(100,10,p)/10)-p;
R10 = R10[abs(R10)>epsilon];

R25 =  (rbinom(100,25,p)/25)-p;
R25 = R25[abs(R25)>epsilon];

R50 =  (rbinom(100,50,p)/50)-p;
R50 = R50[abs(R50)>epsilon];

R75 =  (rbinom(100,75,p)/75)-p;
R75 = R75[abs(R75)>epsilon];

R100 =  (rbinom(100,100,p)/100)-p;
R100 = R100[abs(R100)>epsilon];

R150 =  (rbinom(100,150,p)/150)-p;
R150 = R150[abs(R150)>epsilon];

R200 =  (rbinom(100,200,p)/200)-p;
R200 = R200[abs(R200)>epsilon];

R500 =  (rbinom(100,500,p)/500)-p;
R500 = R500[abs(R500)>epsilon];

#----------------------------------------

#Question 4
# On fixe les paramètres.
p = 0.5
n = c(10,100,200,500,1000,5000,10000,50000)
R = rep(0,8)
epsilon = 0.01

# On effectue les 100 échantillons pour n = 10, 100, 200, 500, 1000, 5000,10000, 50000
for(i in 1:8){
  
  X3 = seq(1,n[i])
  Sn3 = pbinom(X,n[i],p)
  
  N = 100
  sN3 = c() # On créer un vecteur vide
  for(j in 1:N){
    sN3[j] = sum(sample(Sn3,n[i],replace = TRUE)) # Réalisation des 100
    échantillons
  
  Wn3 = sN3/n[i]
  
  for(j in 1:N){
    if(abs(Wn3[j]-p) > eps) R3[i] = R3[i]+1 # Evaluation de Rn
  }
}

#Question 5
# On remarque que plus espilon et n sont petit, plus Rn sera grand. Plus n est grand et
# plus Wn se rapproche de 0.5, donc moins il y a de chance pour que |Wn-p|> epsilon



