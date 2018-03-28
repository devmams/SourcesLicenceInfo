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

# function constLiens(m::JuMP.Model)
#     # C = parseTSP("plat/exemple.dat")
#     # m = TSP(GLPKSolverMIP(),C) # m = Model(solver=GLPKSolverMIP())
#     status = solve(m)
#     if status == :Optimal
#         T  = Vector{Vector{Int}}(0)
#         for i in 1:size(C,1), j in 1:size(C,1)
#             if isapprox(1.0,getvalue(m[:x][i,j]))
#                 push!(T,[i,j])
#             end
#         end
#     end
#     return T
# end


function constCycleRec(TLiens::Vector{Vector{Int}}, cycle::Vector{Int}, PointUtilises::Vector{Int} ,pointdeb::Int ,pointCour::Int)
    if( TLiens[pointCour][2] == pointdeb)
        push!(cycle,pointCour)
        push!(PointUtilises,pointCour)
        return cycle
    else
        return constCycleRec(TLiens,push!(cycle,pointCour),push!(PointUtilises,pointCour),pointdeb,TLiens[pointCour][2])
    end
end

function appartient(PointUtilises::Vector{Int}, ind::Int)
    for i in 1:size(PointUtilises,1)
        if(PointUtilises[i] == ind)
            return true
        end
    end
    return false
end

function constCycles(T::Vector{Vector{Int}})
    TLiens = T
    # println(TLiens)
    # println(size(TLiens))
    PointUtilises = Vector{Int}(0)
    Tcycles = Vector{Vector{Int}}(0)
    ind = 1

    while ind <= size(TLiens,1)
        if(appartient(PointUtilises,ind))
            ind = ind + 1
        else
            push!(PointUtilises,ind)
            push!(Tcycles,constCycleRec(TLiens,[ind],PointUtilises,ind,TLiens[ind][2]))
            ind = ind + 1
        end
    end

    # println("resultats :")
    # println(Tcycles)
    # println(PointUtilises)

    return Tcycles
end

function casseCyclesRec(T::Vector{Vector{Int}},m::JuMP.Model)
    t = Tc[indmin([size(i) for i in Tc])]
    mo = @constraint(m, ctr3, sum(x[2,6] + x[6,2]) <= 1)


    return mo

end

# Fonction de résolution exacte du problème de voyageur de commerce, dont le distancier est passé en paramètre

function TSP(solverSelected, C::Array{Int,2})

    m = Model(solver = solverSelected)
    nbLieu= size(C,1)
    @variable(m,x[1:nbLieu, 1:nbLieu] >= 0, Bin)
    @objective(m, Min, sum(C[i,j]x[i,j] for i in 1:nbLieu, j in 1:nbLieu) )
    @constraint(m, ctr1[i=1:nbLieu], sum(x[i,j] for j in 1:nbLieu if (i != j)) == 1)
    @constraint(m, ctr2[j=1:nbLieu], sum(x[i,j] for i in 1:nbLieu if (i != j)) == 1)

    status = solve(m)
    x = m[:x]
    T  = Vector{Vector{Int}}(0)
    for i in 1:size(C,1), j in 1:size(C,1)
        if isapprox(1.0,getvalue(x[i,j]))
            push!(T,[i,j])
    end
    end
    println(T)
    Tc = constCycles(T)
    println(Tc)

    while size(Tc,1) >= 1
        m = casseCyclesRec(Tc)
        status = solve(m)
        x = m[:x]
        T  = Vector{Vector{Int}}(0)
        for i in 1:size(C,1), j in 1:size(C,1)
            if isapprox(1.0,getvalue(x[i,j]))
                push!(T,[i,j])
            end
        end
        Tc = constCycles(T)
    end



    # @constraint(m, ctr3, sum(x[2,6] + x[6,2]) <= 1)

    return m

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

# constCycles()
C = parseTSP("plat/exemple.dat")
TSP(GLPKSolverMIP(),C)
