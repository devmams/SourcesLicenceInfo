# Auteur : TASSI KEVIN et DIALLO MAMADOU

using JuMP, GLPKMathProgInterface

# Fonction de modélisation implicite du problème

function modelMuseeImplicite(solverSelected, Ind::Vector{Int}, ValInd::Vector{Vector{Int}}, b::Vector{Int}, c::Vector{Int})
    # Déclaration d'un modèle (initialement vide)
    m = Model(solver = solverSelected)

    # Déduction du nombre de variables à partir des données
    nbVariable = length(b)

    # Déclaration des variables
    @variable(m, x[Ind], Bin)

    # Déclaration de la fonction objectif (avec le sens d'optimisation)
    @objective(m, Max, sum(c[j]x[j] for j in Ind))

    # Déclaration des contraintes
    @constraint(m, Variable[i=1:nbVariable], sum(x[j] for j in ValInd[i]) <= b[i])

    # Valeur retournée
    return m
end

# Déclaration des données

Ind = [1:9 ...] # Définition du tableau [1,2,3,...,9]

ValInd = Vector{Vector{Int}}(11)
ValInd = [[1,5],
          [2,5],
          [3,5],
          [3,4],
          [2,7],
          [5,7],
          [5,4],
          [6,7],
          [6,8],
          [8,4],
          [5,9]]

b = [1 for i in 1:11]

c = [1,3,7,3,12,4,9,4,3]

# Création d'un modèle complété à partir des données

m = modelMuseeImplicite(GLPKSolverMIP(),Ind,ValInd,b,c)

#print(m)

# Résolution

status = solve(m)

# Affichage des résultats (ici assez complet pour gérer certains cas d'"erreur")

if status == :Optimal
    println("Problème résolu à l'optimalité")

    println("z = ",getobjectivevalue(m)) # affichage de la valeur optimale

    println("x = ",getvalue(m[:x])) # affichage des valeurs du vecteur de variables

elseif status == :Unbounded
    println("Problème non-borné")

elseif status == :Infeasible
    println("Problème impossible")
end
