
#= Première modélisation vue en cours
   Il s'agit ici d'une modélisation explicite =#

# On utilisera les packages suivants

using JuMP, GLPKMathProgInterface

# Déclaration d'un modèle (initialement vide), on précise qu'on utilisera le solveur de GLPK pour les programmes linéaires

m = Model(solver = GLPKSolverLP())

# Déclaration des variables

@variable(m,x1 >= 0)
@variable(m,x2 >= 0)

# Déclaration de la fonction objectif (avec le sens d'optimisation)

@objective(m, Max, 2x1 + 2x2)

# Déclaration des contraintes
# (leur donner un nom est ici facultatif, mais s'imposera ensuite pour grouper des contraintes)

@constraint(m, Toxine1, x1 >= 1)
@constraint(m, Toxine2, -x1 + x2 <= 3)

# Résolution

status = solve(m)

# Affichage des résultats (ici assez complet pour gérer certains cas d'"erreur")

if status == :Optimal
    println("Problème résolu à l'optimalité")

    println("z = ",getobjectivevalue(m)) # affichage de la valeur optimale

    println("x1 = ",getvalue(x1))
    println("x2 = ",getvalue(x2))

elseif status == :Unbounded
    println("Problème non-borné")

elseif status == :Infeasible
    println("Problème impossible")
end
