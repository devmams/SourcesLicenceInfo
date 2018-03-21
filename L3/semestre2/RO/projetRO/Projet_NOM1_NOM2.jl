
#= NOM1 - Prénom 1
   NOM2 - Prénom 2
   N'oubliez pas de modifier ce commentaire, ainsi que le nom du fichier! =#

using JuMP, GLPKMathProgInterface


#= Nombreuses autres fonctions à ajouter
   .
   .
   .
   .
   .
=#

function constCycles()
    C = parseTSP("plat/exemple.dat")

    m = TSP(GLPKSolverMIP(),C)

    status = solve(m)

    # Affichage des résultats (ici assez complet pour gérer certains cas d'"erreur")

    if status == :Optimal
        println("Problème résolu à l'optimalité")

        println("z = ",getobjectivevalue(m)) # affichage de la valeur optimale

        for i in 1:size(C,1), j in 1:size(C,1)
            if isapprox(1.0,getvalue(m[:x][i,j]))
                println("x[",i," ",j,"]")
            end
        end
        println("x = ",getvalue(m[:x])) # affichage des valeurs du vecteur de variables


    elseif status == :Unbounded
        println("Problème non-borné")

    elseif status == :Infeasible
        println("Problème impossible")
    end


end


# Fonction de résolution exacte du problème de voyageur de commerce, dont le distancier est passé en paramètre

function TSP(solverSelected, C::Array{Int,2})

    m = Model(solver = solverSelected)

    nbLieu= size(C,1)

    @variable(m,x[1:nbLieu, 1:nbLieu] >= 0, Bin)

    @objective(m, Min, sum(C[i,j]x[i,j] for i in 1:nbLieu, j in 1:nbLieu))


    @constraint(m, ctr1[i=1:nbLieu], sum(x[i,j] for j in 1:nbLieu) == 1)
    @constraint(m, ctr2[j=1:nbLieu], sum(x[i,j] for i in 1:nbLieu) == 1)

    return m

# À compléter!



end


#= Fonction qui résout l'ensemble des instances du projet avec la méthode de résolution exacte,
   le temps d'exécution de chacune des instances est mesuré =#

function scriptTSP()
    # Première exécution sur l'exemple pour forcer la compilation si elle n'a pas encore été exécutée
    C = parseTSP("plat/exemple.dat")
    TSP(C)

    # Série d'exécution avec mesure du temps pour les instances symétriques
    for i in 10:10:150
        file = "plat/plat" * string(i) * ".dat"
        C = parseTSP(file)
        println("Instance à résoudre : plat",i,".dat")
        @time TSP(C)
    end

    # Série d'exécution avec mesure du temps pour les instances asymétriques
    for i in 10:10:150
        file = "relief/relief" * string(i) * ".dat"
        println("Instance à résoudre : relief",i,".dat")
        C = parseTSP(file)
        @time TSP(C)
    end
end

# fonction qui prend en paramètre un fichier contenant un distancier et qui retourne le tableau bidimensionnel correspondant

function parseTSP(nomFichier::String)
    # Ouverture d'un fichier en lecture
    f = open(nomFichier,"r")

    # Lecture de la première ligne pour connaître la taille n du problème
    s = readline(f) # lecture d'une ligne et stockage dans une chaîne de caractères
    tab = parse.(Int,split(s," ",keep = false)) # Segmentation de la ligne en plusieurs entiers, à stocker dans un tableau (qui ne contient ici qu'un entier)
    n = tab[1]

    # Allocation mémoire pour le distancier
    C = Array{Int,2}(n,n)

    # Lecture du distancier
    for i in 1:n
        s = readline(f)
        tab = parse.(Int,split(s," ",keep = false))
        for j in 1:n
            C[i,j] = tab[j]
        end
    end

    # Fermeture du fichier
    close(f)

    # Retour de la matrice de coûts
    return C
end

constCycles()
