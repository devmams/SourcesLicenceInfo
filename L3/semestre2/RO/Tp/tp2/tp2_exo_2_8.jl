# Auteur : TASSI KEVIN et DIALLO MAMADOU

using JuMP, GLPKMathProgInterface

# Fonction de modélisation implicite du problème
function modelMuseeImplicite(solverSelected, val::Dict{Char,Vector{Char}}, p::Dict{Char,Int},ind::Vector{Char} , nb::Int)
    # Déclaration d'un modèle (initialement vide)
    m = Model(solver = solverSelected)

    # Déduction du nombre de salles à partir des données

    # Déclaration des variables
    @variable(m, x[ind], Bin)
    @variable(m, y[ind], Bin)

    # Déclaration de la fonction objectif (avec le sens d'optimisation)
    @objective(m, Max, sum(p[j]x[j] for j in ind))

    # Déclaration des contraintes
    @constraint(m, ctr1[i='A':'M'], sum(y[j] for j in val[i]) >= x[i])
    @constraint(m, ctr2, sum(y[j] for j in ind) == nb)

    # Valeur retournée
    return m
end

# Déclaration des données

ind = ['A':'M'...] # Définition du tableau ['A','B','C',...,'L','M']

p = Dict( 'A' =>53000,
          'B' =>46000,
          'C' =>16000,
          'D' =>28000,
          'E' =>96000,
          'F' =>84000,
          'G' =>32000,
          'H' =>21000,
          'I' =>15000,
          'J' =>22000,
          'K' =>41000,
          'L' =>53000,
          'M' =>66000
        )

val = Dict(
    'A' => ['A','B','C','D'],
    'B' => ['A','B','C','D','E','F','G'],
    'C' => ['A','B','C','D'],
    'D' => ['A','B','C','D','E','F','G','J','K'],
    'E' => ['B','D','E','F','G','I','J','K'],
    'F' => ['B','D','E','F','G','I','J','K'],
    'G' => ['B','D','E','F','G','H','I','J','K'],
    'H' => ['G','H','I','J','L','M'],
    'I' => ['E','F','G','H','I','J','K','L'],
    'J' => ['D','E','F','G','H','I','J','K','L'],
    'K' => ['D','E','F','G','H','I','J','K','L'],
    'L' => ['H','I','J','K','L','M'],
    'M' => ['H','L','M']
)

nb = 2

# Création d'un modèle complété à partir des données

m = modelMuseeImplicite(GLPKSolverMIP(),val,p,ind,nb)

# Résolution

status = solve(m)

# Affichage des résultats (ici assez complet pour gérer certains cas d'"erreur")

if status == :Optimal
    println("Problème résolu à l'optimalité")

    println("z = ",getobjectivevalue(m)) # affichage de la valeur optimale
    println("x = ",getvalue(m[:x])) # affichage des valeurs du vecteur de variables issues du modèle
    println("y = ",getvalue(m[:y])) # affichage des valeurs du vecteur de variables issues du modèle

    # On peut trouver le premier affichage lourd, et préférer se limiter aux variables fixées à 1 dans l'affichage
    # print("Liste des portes sélectionnées pour le positionnement d'une caméra : ")
    # for i in indCam
    #     if isapprox(getvalue(m[:x][i]),1) print(i," ")
    #     end
    # end
    # println()

elseif status == :Unbounded
    println("Problème non-borné")

elseif status == :Infeasible
    println("Problème impossible")
end
