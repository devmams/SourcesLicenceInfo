# Auteur : TASSI KEVIN et DIALLO MAMADOU

#= Première modélisation vue en cours
   Afin de pouvoir réutiliser le modèle (on pourrait avoir d'autres médicaments et d'autres toxines),
   on le déclare indépendamment des données dans une fonction.
   Il s'agit ici d'une modélisation implicite =#

# On utilisera les packages suivants

using JuMP, GLPKMathProgInterface

#= fonction retournant un modèle dépendant de données en entrée (on parle de modélisation implicite car les données sont séparées)
    c représente le vecteur des coefficients de la fonction objectif
    A représente la matrice des contraintes
    b représente les membres de droite des contraintes
    solverSelected est un paramètre permettant de choisir le solveur utilisé pour résoudre le problème =#

function modelImplicite(solverSelected, d::Vector{Int},cn::Int ,csup::Int ,cstock::Int,prodmax::Int,sinit::Int,nbmois::Int)
    # Déclaration d'un modèle (initialement vide)
    m = Model(solver = solverSelected)

    # Déclaration des variables
    @variable(m,xn[1:nbmois] >= 0,Int)
    @variable(m,xs[1:nbmois] >= 0,Int)
    @variable(m,s[1:nbmois] >= 0,Int)

    # Déclaration de la fonction objectif (avec le sens d'optimisation)
    @objective(m, Min,sum(cn*xn[i] + csup*xs[i] + cstock*s[i] for i in 1:nbmois) + (sinit * cstock))

    # Déclaration des contraintes
    # (leur donner un nom est ici obligatoire pour grouper des contraintes en une seule déclaration)
    @constraint(m, ctr1[i=1:nbmois], xn[i] <= prodmax)

    @constraint(m, mois1, sinit + xn[1] + xs[1] == d[1] + s[1])

    @constraint(m, mois[i=2:nbmois],s[i-1] + xn[i] + xs[i] == d[i] + s[i])

    # Valeur retournée
    return m
end

# Déclaration des données

d = [30000,15000,15000,25000,33000,40000,45000,45000,26000,14000,25000,30000]
cn = 20
csup = 30
cstock = 3
prodmax = 30000
sinit = 2000
nbmois = 12


# Création d'un modèle complété à partir des données

m = modelImplicite(GLPKSolverMIP(),d,cn,csup,cstock,prodmax,sinit,nbmois)

# Résolution

status = solve(m)

# Affichage des résultats (ici assez complet pour gérer certains cas d'"erreur")

if status == :Optimal
    println("Problème résolu à l'optimalité")

    println("z = ",getobjectivevalue(m)) # affichage de la valeur optimale
    println("xn = ",getvalue(m[:xn])) # affichage des valeurs du vecteur de variables
    println("xs = ",getvalue(m[:xs])) # affichage des valeurs du vecteur de variables
    println("s = ",getvalue(m[:s])) # affichage des valeurs du vecteur de variables

elseif status == :Unbounded
    println("Problème non-borné")

elseif status == :Infeasible
    println("Problème impossible")
end
