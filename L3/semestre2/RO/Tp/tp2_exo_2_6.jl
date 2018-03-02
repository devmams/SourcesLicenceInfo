# Auteur : TASSI KEVIN et DIALLO MAMADOU

using JuMP, GLPKMathProgInterface

#= fonction retournant un modèle dépendant de données en entrée (on parle de modélisation implicite car les données sont séparées)
    c représente le vecteur des coefficients de la fonction objectif
    A représente la matrice des contraintes
    b représente les membres de droite des contraintes
    solverSelected est un paramètre permettant de choisir le solveur utilisé pour résoudre le problème =#

function modelImplicite(solverSelected, f::Vector{Int}, c::Array{Int,2}, Ca::Vector{Int}, d::Vector{Int})
    # Déclaration d'un modèle (initialement vide)
    m = Model(solver = solverSelected)

    # Déduction du nombre de variables et du nombre de contraintes à partir des données
    # nbCen = size(c,1) # taille de la matrice c sur la première dimension = nombre de lignes de c
    # nbEnt = size(d,1) # taille de la matrice d sur la deuxième dimension = nombre de colonnes de d
    nbCen= size(c,1)
    nbEnt= size(d,1)

    # Déclaration des variables
    @variable(m,x[1:nbEnt] >= 0, Bin)
    @variable(m,y[1:nbEnt, 1:nbCen] >= 0)

    # Déclaration de la fonction objectif (avec le sens d'optimisation)
    @objective(m, Min, sum(f[i]x[i] for i in 1:nbEnt) + sum(c[i,j]y[i,j] for i in 1:nbEnt, j in 1:nbCen))

    # Déclaration des contraintes
    # (leur donner un nom est ici obligatoire pour grouper des contraintes en une seule déclaration)
    @constraint(m, ctr1[j=1:nbCen], sum(y[i,j] for i in 1:nbEnt) == 1)
    @constraint(m, ctr2[i=1:nbEnt], sum(y[i,j]d[j] for j in 1:nbCen) <= Ca[i]x[i])

    # Valeur retournée
    return m
end

# Déclaration des données

f = [3500,9000,10000,4000,3000,9000,9000,3000,4000,10000,9000,3500]

M = 50000

c = [
        100 80 50 50 60 100 120 90 60 70 65 110;
        120 90 60 70 65 110 140 110 80 80 75 130;
        140 110 80 80 75 130 160 125 100 100 80 150;
        160 125 100 100 80 150 190 150 130 M M M;
        190 150 130 M M M 200 180 150 M M M;
        200 180 150 M M M 100 80 50 50 60 100;
        100 80 50 50 60 100 120 90 60 70 65 110;
        120 90 60 70 65 110 140 110 80 80 75 130;
        140 110 80 80 75 130 160 125 100 100 80 150;
        160 125 100 100 80 150 190 150 130 M M M;
        190 150 130 M M M 200 180 150 M M M;
        200 180 150 M M M 100 80 50 50 60 100
    ]

Ca = [300,250,110,180,275,300,200,220,270,250,230,180]

d = [120,80,75,100,110,100,90,60,30,150,95,120]

# Création d'un modèle complété à partir des données

m = modelImplicite(GLPKSolverMIP(),f,c,Ca,d)

println(m)

# Résolution

status = solve(m)

# Affichage des résultats (ici assez complet pour gérer certains cas d'"erreur")

if status == :Optimal
    println("Problème résolu à l'optimalité")

    println("z = ",getobjectivevalue(m)) # affichage de la valeur optimale

    println("x = ",getvalue(m[:x])) # affichage des valeurs du vecteur de variables
    println("y = ",getvalue(m[:y])) # affichage des valeurs du vecteur de variables


elseif status == :Unbounded
    println("Problème non-borné")

elseif status == :Infeasible
    println("Problème impossible")
end
